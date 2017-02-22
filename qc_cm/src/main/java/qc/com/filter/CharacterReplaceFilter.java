package qc.com.filter;

import java.io.IOException;  
import java.io.InputStream;  
import java.util.Properties;  
  
import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  

import org.apache.log4j.Logger;
  
  
public class CharacterReplaceFilter implements Filter {  
  
	private Logger LOG = Logger.getLogger(this.getClass());
    private final static String KEY = "filePath";  
  
    private FilterConfig filterConfig = null;  
  
    private static Properties props = new Properties();  
  
    public void init(FilterConfig filterConfig) throws ServletException {  
        this.filterConfig = filterConfig;  
        String value = this.filterConfig.getInitParameter(KEY); 
        InputStream ins = CharacterReplaceFilter.class.getClassLoader().getResourceAsStream(value);  
        try {  
            props.load(ins);  
            LOG.info("敏感词文件加载成功");  
        } catch (IOException e) {  
            LOG.error("加载敏感词文件时发生错误", e);  
        }  
    }  
  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,ServletException {  
        HttpServletRequest httpReq = (HttpServletRequest) request;  
        final String method = httpReq.getMethod();  
        if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {  
            KeyWordRequestWrapper wrapper = new KeyWordRequestWrapper((HttpServletRequest) request, props);  
            chain.doFilter(wrapper, response);  
        } else {  
            chain.doFilter(request, response);  
        }  
    }  
  
    public void destroy() {  
        filterConfig = null;  
        props = null;  
    }  
  
}  
