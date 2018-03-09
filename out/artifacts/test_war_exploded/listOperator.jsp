<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
      <TD height="100%">
        <table border="1" width="100%" height="100%">
          <tr>
            <td width="20%" align="center" height="16">登录ID</td>
            <td width="20%" align="center" height="16">姓名</td>
            <td width="20%" align="center" height="16">是否管理员</td>
            <td width="20%" align="center" height="16">修改</td>
            <td width="20%" align="center" height="16">删除</td>
          </tr>

          <c:forEach items="${sessionScope.operatorList}" var="temp">
            <tr>
              <td width="20%" align="center" height="17">${temp.id}</td>
              <td width="20%" align="center" height="17">${temp.name}</td>
              <td width="20%" align="center" height="17">${temp.isAdmin()==true?'是':'否'}</td>
              <td width="20%" align="center" height="17"><a href="editOperatorForm?operatorID=${temp.id}">update</a></td>
              <td width="20%" align="center" height="17"><a href="deleteOperator?operatorID=${temp.id}">delete</a></td>
            </tr>
          </c:forEach>

        </table>

      </TD>
    </TR>
    </TBODY>
  </TABLE>
  <BR>
</CENTER>
</body>
</html>
