package org.apache.jsp.WEB_002dINF.page;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("\t<head>\n");
      out.write("\t\t<meta charset=\"utf-8\">\n");
      out.write("\t\t<title>æ²¹ç°çæ§ç³»ç»</title>\n");
      out.write("\t\t<meta name=\"renderer\" content=\"webkit\">\n");
      out.write("\t\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n");
      out.write("\t\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\n");
      out.write("\t\t<meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n");
      out.write("\t\t<meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n");
      out.write("\t\t<meta name=\"format-detection\" content=\"telephone=no\">\n");
      out.write("\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"script/plugins/layui/css/layui.css\" media=\"all\" />\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"script/css/global.css\" media=\"all\">\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"script/plugins/font-awesome/css/font-awesome.min.css\">\n");
      out.write("\n");
      out.write("\t</head>\n");
      out.write("\n");
      out.write("\t<body>\n");
      out.write("\t\t<div class=\"layui-layout layui-layout-admin\">\n");
      out.write("\t\t\t<div class=\"layui-header header header-demo\">\n");
      out.write("\t\t\t\t<div class=\"layui-main\">\n");
      out.write("\t\t\t\t\t<div class=\"admin-login-box\">\n");
      out.write("\t\t\t\t\t\t<a class=\"logo\" style=\"left: 0;\" href=\"http://beginner.zhengjinfan.cn/demo/beginner_admin/\">\n");
      out.write("\t\t\t\t\t\t\t<span style=\"font-size: 22px;\">æ²¹ç°æºè½åºç¨å¹³å°</span>\n");
      out.write("\t\t\t\t\t\t</a>\n");
      out.write("\t\t\t\t\t\t<div class=\"admin-side-toggle\">\n");
      out.write("\t\t\t\t\t\t\t<i class=\"fa fa-bars\" aria-hidden=\"true\"></i>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<ul class=\"layui-nav admin-header-item\">\n");
      out.write("\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t\t<li class=\"layui-nav-item\">\n");
      out.write("\t\t\t\t\t\t\t<a href=\"javascript:;\" class=\"admin-header-user\">\n");
      out.write("\t\t\t\t\t\t\t\t<img src=\"script/images/0.jpg\" />\n");
      out.write("\t\t\t\t\t\t\t\t<span>beginner</span>\n");
      out.write("\t\t\t\t\t\t\t</a>\n");
      out.write("\t\t\t\t\t\t\t<dl class=\"layui-nav-child\">\n");
      out.write("\t\t\t\t\t\t\t\t<dd>\n");
      out.write("\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\"><i class=\"fa fa-user-circle\" aria-hidden=\"true\"></i> ä¸ªäººä¿¡æ¯</a>\n");
      out.write("\t\t\t\t\t\t\t\t</dd>\n");
      out.write("\t\t\t\t\t\t\t\t<dd>\n");
      out.write("\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\"><i class=\"fa fa-gear\" aria-hidden=\"true\"></i> è®¾ç½®</a>\n");
      out.write("\t\t\t\t\t\t\t\t</dd>\n");
      out.write("\t\t\t\t\t\t\t\t<dd id=\"lock\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<a href=\"javascript:;\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<i class=\"fa fa-lock\" aria-hidden=\"true\" style=\"padding-right: 3px;padding-left: 1px;\"></i> éå± (Alt+L)\n");
      out.write("\t\t\t\t\t\t\t\t\t</a>\n");
      out.write("\t\t\t\t\t\t\t\t</dd>\n");
      out.write("\t\t\t\t\t\t\t\t<dd>\n");
      out.write("\t\t\t\t\t\t\t\t\t<a href=\"login.shtml\"><i class=\"fa fa-sign-out\" aria-hidden=\"true\"></i> æ³¨é</a>\n");
      out.write("\t\t\t\t\t\t\t\t</dd>\n");
      out.write("\t\t\t\t\t\t\t</dl>\n");
      out.write("\t\t\t\t\t\t</li>\n");
      out.write("\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t\t<ul class=\"layui-nav admin-header-item-mobile\">\n");
      out.write("\t\t\t\t\t\t<li class=\"layui-nav-item\">\n");
      out.write("\t\t\t\t\t\t\t<a href=\"login.shtml\"><i class=\"fa fa-sign-out\" aria-hidden=\"true\"></i> æ³¨é</a>\n");
      out.write("\t\t\t\t\t\t</li>\n");
      out.write("\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"layui-side layui-bg-black\" id=\"admin-side\">\n");
      out.write("\t\t\t\t<div class=\"layui-side-scroll\" id=\"admin-navbar-side\" lay-filter=\"side\"></div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"layui-body\" style=\"bottom: 0;border-left: solid 2px #1AA094;\" id=\"admin-body\">\n");
      out.write("\t\t\t\t<div class=\"layui-tab admin-nav-card layui-tab-brief\" lay-filter=\"admin-tab\">\n");
      out.write("\t\t\t\t\t<ul class=\"layui-tab-title\">\n");
      out.write("\t\t\t\t\t\t<li class=\"layui-this\">\n");
      out.write("\t\t\t\t\t\t\t<i class=\"fa fa-dashboard\" aria-hidden=\"true\"></i>\n");
      out.write("\t\t\t\t\t\t\t<cite>ä¸»é¡µ</cite>\n");
      out.write("\t\t\t\t\t\t</li>\n");
      out.write("\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t\t<div class=\"layui-tab-content\" style=\"min-height: 150px; padding: 5px 0 0 0;\">\n");
      out.write("\t\t\t\t\t\t<div class=\"layui-tab-item layui-show\">\n");
      out.write("\t\t\t\t\t\t\t<iframe src=\"main.shtml\"></iframe>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"layui-footer footer footer-demo\" id=\"admin-footer\">\n");
      out.write("\t\t\t\t<div class=\"layui-main\">\n");
      out.write("\t\t\t\t\t<p>2017 &copy;\n");
      out.write("\t\t\t\t\t\t<a href=\"http://www.baidu.com/\" target=\"_blank\">æ¥æ´»</a> LGPL license\n");
      out.write("\t\t\t\t\t</p>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"site-tree-mobile layui-hide\">\n");
      out.write("\t\t\t\t<i class=\"layui-icon\">&#xe602;</i>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<div class=\"site-mobile-shade\"></div>\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<!--éå±æ¨¡æ¿ start-->\n");
      out.write("\t\t\t<script type=\"text/template\" id=\"lock-temp\">\n");
      out.write("\t\t\t\t<div class=\"admin-header-lock\" id=\"lock-box\">\n");
      out.write("\t\t\t\t\t<div class=\"admin-header-lock-img\">\n");
      out.write("\t\t\t\t\t\t<img src=\"images/0.jpg\"/>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<div class=\"admin-header-lock-name\" id=\"lockUserName\">beginner</div>\n");
      out.write("\t\t\t\t\t<input type=\"text\" class=\"admin-header-lock-input\" value=\"è¾å¥å¯ç è§£é..\" name=\"lockPwd\" id=\"lockPwd\" />\n");
      out.write("\t\t\t\t\t<button class=\"layui-btn layui-btn-small\" id=\"unlock\">è§£é</button>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</script>\n");
      out.write("\t\t\t<!--éå±æ¨¡æ¿ end -->\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t<script type=\"text/javascript\" src=\"script/plugins/layui/layui.js\"></script>\n");
      out.write("\t\t\t<script type=\"text/javascript\" src=\"script/datas/nav.js\"></script>\n");
      out.write("\t\t\t<script type=\"text/javascript\" src=\"script/js/index.js\"></script>\n");
      out.write("\t\t\t<script>\n");
      out.write("\t\t\t\tlayui.use('layer', function() {\n");
      out.write("\t\t\t\t\tvar $ = layui.jquery,\n");
      out.write("\t\t\t\t\t\tlayer = layui.layer;\n");
      out.write("\n");
      out.write("\t\t\t\t\t$('#video1').on('click', function() {\n");
      out.write("\t\t\t\t\t\tlayer.open({\n");
      out.write("\t\t\t\t\t\t\ttitle: 'YouTube',\n");
      out.write("\t\t\t\t\t\t\tmaxmin: true,\n");
      out.write("\t\t\t\t\t\t\ttype: 2,\n");
      out.write("\t\t\t\t\t\t\tcontent: 'video.html',\n");
      out.write("\t\t\t\t\t\t\tarea: ['800px', '500px']\n");
      out.write("\t\t\t\t\t\t});\n");
      out.write("\t\t\t\t\t});\n");
      out.write("\n");
      out.write("\t\t\t\t});\n");
      out.write("\t\t\t</script>\n");
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
