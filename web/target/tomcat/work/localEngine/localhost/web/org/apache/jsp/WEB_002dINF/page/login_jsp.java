package org.apache.jsp.WEB_002dINF.page;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("\t<head>\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n");
      out.write("\t\t<title>登录</title>\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"script/plugins/layui/css/layui.css\" media=\"all\" />\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"script/css/login.css\" />\n");
      out.write("\t</head>\n");
      out.write("\n");
      out.write("\t<body class=\"beg-login-bg\">\n");
      out.write("\t\t<div class=\"beg-login-box\">\n");
      out.write("\t\t\t<header>\n");
      out.write("\t\t\t\t<h1>系统登录</h1>\n");
      out.write("\t\t\t</header>\n");
      out.write("\t\t\t<div class=\"beg-login-main\">\n");
      out.write("\t\t\t\t<form action=\"/manage/login\" class=\"layui-form\" method=\"post\"><input name=\"__RequestVerificationToken\" type=\"hidden\" value=\"fkfh8D89BFqTdrE2iiSdG_L781RSRtdWOH411poVUWhxzA5MzI8es07g6KPYQh9Log-xf84pIR2RIAEkOokZL3Ee3UKmX0Jc8bW8jOdhqo81\" />\n");
      out.write("\t\t\t\t\t<div class=\"layui-form-item\">\n");
      out.write("\t\t\t\t\t\t<label class=\"beg-login-icon\">\n");
      out.write("                        <i class=\"layui-icon\">&#xe612;</i>\n");
      out.write("                    </label>\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" id=\"userName\" name=\"userName\" lay-verify=\"required\"  autocomplete=\"off\" placeholder=\"这里输入登录名\" class=\"layui-input\">\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<div class=\"layui-form-item\">\n");
      out.write("\t\t\t\t\t\t<label class=\"beg-login-icon\">\n");
      out.write("                        <i class=\"layui-icon\">&#xe642;</i>\n");
      out.write("                    </label>\n");
      out.write("\t\t\t\t\t\t<input type=\"password\" id=\"password\" name=\"password\" lay-verify=\"required\" autocomplete=\"off\" placeholder=\"这里输入密码\" class=\"layui-input\">\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<div class=\"layui-form-item\">\n");
      out.write("\t\t\t\t\t\t<div class=\"beg-pull-left beg-login-remember\">\n");
      out.write("\t\t\t\t\t\t\t<label>记住帐号？</label>\n");
      out.write("\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"rememberMe\" value=\"true\" lay-skin=\"switch\" checked title=\"记住帐号\">\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"beg-pull-right\">\n");
      out.write("\t\t\t\t\t\t\t<button class=\"layui-btn layui-btn-primary\" lay-submit lay-filter=\"login\">\n");
      out.write("                            <i class=\"layui-icon\">&#xe650;</i> 登录\n");
      out.write("                        </button>\n");
      out.write("\t\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\t<div class=\"beg-clear\"></div>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</form>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<footer>\n");
      out.write("\t\t\t\t<p></p>\n");
      out.write("\t\t\t</footer>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"script/plugins/layui/layui.js\"></script>\n");
      out.write("\t\t<script>\n");
      out.write("\t\t\tlayui.use(['layer', 'form'], function() {\n");
      out.write("\t\t\t\tvar layer = layui.layer,\n");
      out.write("\t\t\t\t\t$ = layui.jquery,\n");
      out.write("\t\t\t\t\tform = layui.form();\n");
      out.write("\t\t\t\t\t$(\"body\").addClass(\"beg-login-bg\");\n");
      out.write("\t\t\t\tform.on('submit(login)',function(data){\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\t//location.href='index.shtml?userName='+$(\"#userName\").val()+\"&password=\"+$(\"#password\").val();\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\tvar userbean = new Object();\n");
      out.write("\t\t\t\t\tuserbean.username=$(\"#userName\").val();\n");
      out.write("\t\t\t\t\tuserbean.password=$(\"#password\").val();\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\tvar searchURL = \"dologin.shtml\";\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\t$.ajax({\n");
      out.write("\t\t\t\t\t\turl : searchURL,\n");
      out.write("\t\t\t\t\t\tdata : JSON.stringify(userbean),\n");
      out.write("\t\t\t\t\t\ttype : \"post\",\n");
      out.write("\t\t\t\t\t\tcontentType : \"application/json; charset=UTF-8\",\n");
      out.write("\t\t\t\t\t\tdataType : \"json\",\n");
      out.write("\t\t\t\t\t\tsuccess : function(result) {\n");
      out.write("\t\t\t\t\t\t\tif (result.code == 1) {\n");
      out.write("\t\t\t\t\t\t\t\t// 服务器处理成功,定向到主页\n");
      out.write("\t\t\t\t\t\t\t\tlocation.href='index.shtml';\n");
      out.write("\t\t\t\t\t\t\t} else {\n");
      out.write("\t\t\t\t\t\t\t\t// 服务器处理错误\n");
      out.write("\t\t\t\t\t\t\t\tlayer.msg(result.msg);\n");
      out.write("\t\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t});\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\treturn false;\n");
      out.write("\t\t\t\t});\n");
      out.write("\t\t\t});\n");
      out.write("\t\t</script>\n");
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
