<%--
  Created by IntelliJ IDEA.
  User: songtie
  Date: 15/4/7
  Time: 下午11:17
  To change this template use File | Settings | File Templates.
--%>
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

        <%@include file="common/englishwordandlanguage_navigation.jsp"%>

        <TR vAlign=top>
            <TD height="100%" bgColor=#f1f3f9>
                <table border="1" width="100%" height="89">
                    <tr>
                        <td width="50%" align="center" height="16">englishWordID</td>
                        <td width="50%" align="center" height="16">englishWordName</td>
                        <td width="50%" align="center" height="16">删除</td>
                    </tr>

                    <c:forEach items="${sessionScope.englishWordList}" var="temp">
                        <tr>
                            <td width="50%" align="center" height="17">${temp.englishWordID}</td>
                            <td width="50%" align="center" height="17">${temp.englishWordName}</td>
                            <td width="25%" align="center" height="17">
                                <a href="deleteEnglishWord?englishWordID=${temp.englishWordID}">delete</a>
                            </td>
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
