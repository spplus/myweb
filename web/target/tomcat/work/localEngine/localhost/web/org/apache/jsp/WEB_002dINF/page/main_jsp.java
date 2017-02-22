package org.apache.jsp.WEB_002dINF.page;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class main_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("\t<head>\n");
      out.write("\t\t<meta charset=\"UTF-8\">\n");
      out.write("\t\t<title></title>\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"script/plugins/layui/css/layui.css\" media=\"all\" />\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"script/css/main.css\" />\n");
      out.write("\t</head>\n");
      out.write("\n");
      out.write("\t<body>\n");
      out.write("\t\t<div class=\"admin-main\">\n");
      out.write("\t\t\t<blockquote class=\"layui-elem-quote\">\n");
      out.write("\t\t\t\t<p>æ¥ç§æ´»</p>\n");
      out.write("\t\t\t\t<p>c++,Java,.net,android,ios</p>\n");
      out.write("\t\t\t\t<a href=\"http://www.layui.com/doc\" target=\"_blank\">http://www.layui.com/doc</a>\n");
      out.write("\t\t\t\t<p>é¡¹ç®æ¡ä¾ï¼\n");
      out.write("\t\t\t\t\t<a href=\"#\" target=\"_blank\">çµç½è°æ§ä¸ä½åç³»ç»</a>\n");
      out.write("\t\t\t\t</p>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<p>èç³»QQï¼281028799</p>\n");
      out.write("\t\t\t</blockquote>\n");
      out.write("\t\t\t\n");
      out.write("\t\t</div>\n");
      out.write("\t</body>\n");
      out.write("\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
