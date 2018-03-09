<%--
  Created by IntelliJ IDEA.
  User: songtie
  Date: 15/4/7
  Time: 下午11:15
  To change this template use File | Settings | File Templates.
--%>
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
        <%@include file="common/wordmeaning_navigation.jsp"%>
        <TR vAlign=top>
            <TD height="100%" bgColor=#f1f3f9>
                <form action="FindWordMeaningsByEnglishWordName" method="POST">
                    <p>查询英文单词的所有义项：<input type="text" name="englishWordName" size="20" ></p>
                    <p><input type="submit" value="查询"></p>
                </form>
                <form action="FindWordMeaningsByWordName" method="POST">
                    <p>查询某个单词的所有义项：<input type="text" name="wordName" size="20" ></p>
                    <p><input type="submit" value="查询"></p>
                </form>
                <form action="FindWordMeaningsByMeaningName" method="POST">
                    <p>查询某个义项的所有单词：<input type="text" name="meaningName" size="20" ></p>
                    <p><input type="submit" value="查询"></p>
                </form>
                <form action="FindAverageMeaningsNumByEnglishWordName" method="POST">
                    <p>查询英文单词的平均义项数：<input type="text" name="englishWordName" size="20" ></p>
                    <p><font color="red">${sessionScope.findAverageMeaningsNumByEnglishWordNameMsg}</font></p>
                    <p><input type="submit" value="查询"></p>
                </form>
                <form action="FindMaxMeaningsNumByEnglishWordName" method="POST">
                    <p>查询英文单词的最大义项数：<input type="text" name="englishWordName" size="20" ></p>
                    <p><font color="red">${sessionScope.findMaxMeaningsNumByEnglishWordNameMsg}</font></p>
                    <p><input type="submit" value="查询"></p>
                </form>
                <form action="FindMinMeaningsNumByEnglishWordName" method="POST">
                    <p>查询英文单词的最小义项数：<input type="text" name="englishWordName" size="20" ></p>
                    <p><font color="red">${sessionScope.findMinMeaningsNumByEnglishWordNameMsg}</font></p>
                    <p><input type="submit" value="查询"></p>
                </form>
            </TD>
        </TR>
        </TBODY>
    </TABLE>
    <BR>
</CENTER>
</body>
</html>
