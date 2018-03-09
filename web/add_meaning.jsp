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
        <%@include file="common/meaning_navigation.jsp"%>
        <TR vAlign=top>
            <TD height="100%" bgColor=#f1f3f9>
                <form action="addMeaning" method="POST">
                    <p>增加meaning：<input type="text" name="meaningName" size="20" ></p>
                    <p><font color="red">${sessionScope.addMeaningMsg}</font></p>
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
