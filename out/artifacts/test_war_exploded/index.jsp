<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>HBtranslator</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="Login and Registration Form with HTML5 and CSS3" />
  <meta name="keywords" content="html5, css3, form, switch, animation, :target, pseudo-class" />
  <meta name="author" content="Codrops" />
  <link rel="shortcut icon" href="../favicon.ico">
  <link rel="stylesheet" type="text/css" href="loginfiles/css/demo.css" />
  <link rel="stylesheet" type="text/css" href="loginfiles/css/style1.css" />
  <link rel="stylesheet" type="text/css" href="loginfiles/css/animate-custom.css" />
</head>
<body>
${requestScope.msg}
<div class="container">
  <div class="codrops-top">

    <strong> <br>  </strong>

    <span class="right">

                </span>
    <div class="clr"></div>
  </div><!--/ Codrops top bar -->
  <header>
    <h1>HB技术小组 <span>无与伦比的translator</span></h1>

  </header>
  <section>
    <div id="container_demo" >

      <a class="hiddenanchor" id="toregister"></a>
      <a class="hiddenanchor" id="tologin"></a>
      <div id="wrapper">
        <div id="login" class="animate form">
          <form  action="login" method="post">
            <h1>登录</h1>
            <tr>
              <td height="30">
                <div align="center"><strong>用户名：</strong>
                  <INPUT name="operatorID" type="text"><br>
                </div>
              </td>
            </tr>
            <tr>
              <td height="30">
                <div align="center"><strong>密&nbsp;&nbsp;&nbsp;码：</strong>
                  <INPUT name="operatorPwd" type="password" >
                </div>
              </td>
            </tr>
            <tr>
              <td height="30">
                <div align="center">
                  <font color="red">${sessionScope.loginMsg}</font>
                </div>
              </td>
            </tr>

            <p class="login button">
              <input type="submit" value="Login" />
            </p>
          </form>
        </div>

      </div>
    </div>
  </section>
</div>
</body>
</html>