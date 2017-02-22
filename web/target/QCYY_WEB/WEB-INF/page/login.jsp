<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>登录</title>
		<link rel="stylesheet" href="script/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="script/css/login.css" />
	</head>

	<body class="beg-login-bg">
		<div class="beg-login-box">
			<header>
				<h1>系统登录</h1>
			</header>
			<div class="beg-login-main">
				<form action="/manage/login" class="layui-form" method="post"><input name="__RequestVerificationToken" type="hidden" value="fkfh8D89BFqTdrE2iiSdG_L781RSRtdWOH411poVUWhxzA5MzI8es07g6KPYQh9Log-xf84pIR2RIAEkOokZL3Ee3UKmX0Jc8bW8jOdhqo81" />
					<div class="layui-form-item">
						<label class="beg-login-icon">
                        <i class="layui-icon">&#xe612;</i>
                    </label>
						<input type="text" id="userName" name="userName" lay-verify="required"  autocomplete="off" placeholder="这里输入登录名" class="layui-input">
					</div>
					<div class="layui-form-item">
						<label class="beg-login-icon">
                        <i class="layui-icon">&#xe642;</i>
                    </label>
						<input type="password" id="password" name="password" lay-verify="required" autocomplete="off" placeholder="这里输入密码" class="layui-input">
					</div>
					<div class="layui-form-item">
						<div class="beg-pull-left beg-login-remember">
							<label>记住帐号？</label>
							<input type="checkbox" name="rememberMe" value="true" lay-skin="switch" checked title="记住帐号">
						</div>
						<div class="beg-pull-right">
							<button class="layui-btn layui-btn-primary" lay-submit lay-filter="login">
                            <i class="layui-icon">&#xe650;</i> 登录
                        </button>
						</div>
						<div class="beg-clear"></div>
					</div>
				</form>
			</div>
			<footer>
				<p></p>
			</footer>
		</div>
		<script type="text/javascript" src="script/plugins/layui/layui.js"></script>
		<script>
			layui.use(['layer', 'form'], function() {
				var layer = layui.layer,
					$ = layui.jquery,
					form = layui.form();
					$("body").addClass("beg-login-bg");
				form.on('submit(login)',function(data){
					
					//location.href='index.shtml?userName='+$("#userName").val()+"&password="+$("#password").val();
					
					var userbean = new Object();
					userbean.username=$("#userName").val();
					userbean.password=$("#password").val();
					
					var searchURL = "dologin.shtml";
					
					$.ajax({
						url : searchURL,
						data : JSON.stringify(userbean),
						type : "post",
						contentType : "application/json; charset=UTF-8",
						dataType : "json",
						success : function(result) {
							if (result.code == 1) {
								// 服务器处理成功,定向到主页
								location.href='index.shtml';
							} else {
								// 服务器处理错误
								layer.msg(result.msg);
							}
						}
					});
					
					return false;
				});
			});
		</script>
	</body>

</html>