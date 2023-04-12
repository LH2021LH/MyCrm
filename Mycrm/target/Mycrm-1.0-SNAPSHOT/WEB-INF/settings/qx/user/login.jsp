<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String basepath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
    + request.getContextPath() + "/";

%>
<html>
<head>
    <base href="<%=basepath%>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $(window).keydown(function (e) {
                if(e.keyCode == 13){
                    $("#submit").click();
                }
            })

           $("#submit").click(function () {
               var loginAct = $.trim($("#loginAct").val());
               var loginPwd = $.trim($("#loginPwd").val());
               var isRemember = $("#isRemember").prop("checked");
               if (loginAct==""){
                $("#msg").text("账户名不能为空");
                return;
               }
               if (loginPwd==""){
                   $("#msg").text("密码不能为空");
                   return;
               }
               $.ajax({
                   url: 'settings/qx/user/login',
                   data: {
                       loginAct: loginAct,
                       loginPwd: loginPwd,
                       isRemember: isRemember
                   },
                   type: 'post',
                   dataType: 'json',
                   success:function(data){
                        if(data.code == "4"){
                            window.location.href="workbench/index";
                        } else {
                            $("#msg").html(data.msg);
                        }
                   }
               })

           })
        });

    </script>

</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 100px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 10px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2023&nbsp;LH</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" id="loginAct" value="${cookie.loginAct.value}" placeholder="用户名">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" id="loginPwd" value="${cookie.loginPwd.value}" placeholder="密码">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						<label>
                            <c:if test="${not empty cookie.loginAct and not empty cookie.loginPwd}">
                                <input type="checkbox" id="isRemember" checked> 十天内免登录
                            </c:if>
                            <c:if test="${empty cookie.loginAct or empty cookie.loginPwd}">
                                <input type="checkbox" id="isRemember"> 十天内免登录
                            </c:if>
						</label>
						&nbsp;&nbsp;
						<span id="msg" style="color: red"></span>
					</div>

					<button type="button" id="submit" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>