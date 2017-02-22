/**
 * Copyright (c) 2012,USTC E-BUSINESS TECHNOLOGY CO.LTD All Rights Reserved.
 */

package qc.com.filter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import qc.com.util.AntUrlPathMatcher;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Factory;
import com.opensymphony.sitemesh.Content;
import com.opensymphony.sitemesh.ContentProcessor;
import com.opensymphony.sitemesh.Decorator;
import com.opensymphony.sitemesh.DecoratorSelector;
import com.opensymphony.sitemesh.compatability.DecoratorMapper2DecoratorSelector;
import com.opensymphony.sitemesh.compatability.PageParser2ContentProcessor;
import com.opensymphony.sitemesh.webapp.ContainerTweaks;
import com.opensymphony.sitemesh.webapp.ContentBufferingResponse;
import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
import com.opensymphony.sitemesh.webapp.SiteMeshWebAppContext;

/**
 * SiteMesh过滤器 
 * @author wangjj
 */
public class EditSiteMeshFilter extends SiteMeshFilter {

	private FilterConfig filterConfig;
	private ContainerTweaks containerTweaks;
	private List<String> list = new ArrayList<String>();
	private AntUrlPathMatcher antUrlPathMatcher = new AntUrlPathMatcher();

	@SuppressWarnings("unchecked")
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
		containerTweaks = new ContainerTweaks();
		String path = getClass().getResource("/").getPath();
		File f = new File(new File(path).getParentFile().getPath()
				+ "/decorators.xml");
		SAXReader reader = new SAXReader();
		Document doc;
		try {
			doc = reader.read(f);
			Element root = doc.getRootElement();
			for (Iterator<Element> i = root.elementIterator("decorator"); i.hasNext();) {
				for (Iterator<Element> j = i.next().elementIterator("pattern"); j.hasNext();) {
					list.add(j.next().getText());
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	public void destroy() {
		filterConfig = null;
		containerTweaks = null;
	}

	/**
	 * Main method of the Filter.
	 * <p>
	 * Checks if the Filter has been applied this request. If not, parses the
	 * page and applies {@link com.opensymphony.module.sitemesh.Decorator} (if
	 * found).
	 */
	public void doFilter(ServletRequest rq, ServletResponse rs,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) rq;
		HttpServletResponse response = (HttpServletResponse) rs;
		ServletContext servletContext = filterConfig.getServletContext();

		SiteMeshWebAppContext webAppContext = new SiteMeshWebAppContext(
				request, response, servletContext);

		ContentProcessor contentProcessor = initContentProcessor(webAppContext);
		DecoratorSelector decoratorSelector = initDecoratorSelector(webAppContext);

		int num = 0;
		for (int i = 0; i < list.size(); i++) {
			String s = request.getServletPath();
			if (antUrlPathMatcher.pathMatchesUrl(list.get(i), s)) {
				num++;
				break;
			}
		}

		if (num == 0) {
			chain.doFilter(request, response);
			return;
		}

		if (filterAlreadyAppliedForRequest(request)) {
			// Prior to Servlet 2.4 spec, it was unspecified whether the filter
			// should be called again upon an include().
			chain.doFilter(request, response);
			return;
		}

		if (!contentProcessor.handles(webAppContext)) {
			// Optimization: If the content doesn't need to be processed, bypass
			// SiteMesh.
			chain.doFilter(request, response);
			return;
		}

		if (containerTweaks.shouldAutoCreateSession()) {
			// Some containers (such as Tomcat 4) will not allow sessions to be
			// created in the decorator.
			// (i.e after the response has been committed).
			request.getSession(true);
		}

		try {
			Content content = obtainContent(contentProcessor, webAppContext,
					request, response, chain);
			if (content == null) {
				return;
			}
			Decorator decorator = decoratorSelector.selectDecorator(content,
					webAppContext);
			decorator.render(content, webAppContext);

		} catch (IllegalStateException e) {
			if (!containerTweaks.shouldIgnoreIllegalStateExceptionOnErrorPage()) {
				throw e;
			}
		} catch (RuntimeException e) {
			if (containerTweaks.shouldLogUnhandledExceptions()) {
				servletContext.log(
						"Unhandled exception occurred whilst decorating page",
						e);
			}
			throw e;
		}

	}

	protected ContentProcessor initContentProcessor(
			SiteMeshWebAppContext webAppContext) {
		// TODO: Remove heavy coupling on horrible SM2 Factory
		Factory factory = Factory.getInstance(new Config(filterConfig));
		factory.refresh();
		return new PageParser2ContentProcessor(factory);
	}

	protected DecoratorSelector initDecoratorSelector(
			SiteMeshWebAppContext webAppContext) {
		// TODO: Remove heavy coupling on horrible SM2 Factory
		Factory factory = Factory.getInstance(new Config(filterConfig));
		factory.refresh();
		return new DecoratorMapper2DecoratorSelector(
				factory.getDecoratorMapper());
	}

	private boolean filterAlreadyAppliedForRequest(HttpServletRequest request) {
		final String alreadyAppliedKey = "com.opensymphony.sitemesh.APPLIED_ONCE";
		if (request.getAttribute(alreadyAppliedKey) == Boolean.TRUE) {
			return true;
		} else {
			request.setAttribute(alreadyAppliedKey, Boolean.TRUE);
			return false;
		}
	}

	private Content obtainContent(ContentProcessor contentProcessor,
			SiteMeshWebAppContext webAppContext, HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		ContentBufferingResponse contentBufferingResponse = new ContentBufferingResponse(
				response, contentProcessor, webAppContext);
		chain.doFilter(request, contentBufferingResponse);

		webAppContext.setUsingStream(contentBufferingResponse.isUsingStream());
		return contentBufferingResponse.getContent();
	}
}