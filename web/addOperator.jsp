
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>HBtranslator</title>
  <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<CENTER>
  <%@include file="common/top.jsp" %>
  <TABLE cellSpacing=0 cellPadding=0 width=100% border=0>
    <TBODY>
    <%@include file="common/operator_navigation.jsp"%>
    <TR vAlign=top>
      <TD height="100%" bgColor=#f1f3f9>
        <form action="addOperator" method="POST">
          <p>登录ID：<input type="text" name="id" size="20" ></p>
          <p>姓 名：<input type="text" name="name" size="20" ></p>
          <p>密码：<input type="password" name="password" size="20" ></p>
          <p>密码确认：<input type="password" name="confirmPassword" size="20" ></p>
          <p>是否管理员：
            <input type="radio" name="isAdmin" value="1" checked >是&nbsp;&nbsp;&nbsp;
            <input type="radio" name="isAdmin" value="0">否</p>
          <p><font color="red">${sessionScope.addOperatorMsg}</font></p>
          <p><input type="submit" value="提交"></p>
        </form>
      </TD>
    </TR>
    </TBODY>
  </TABLE>
  <BR>
</CENTER>
</body>
</html>
