<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Kalah</title>
    <style type="text/css">
    </style>
</head>
<body>
<br>
<div style="text-align:center">
    <spring:url value="/game" var="gameActionUrl" />
    <h2>
        Kalah - implementation by -Pradeep Sadhwani<br> <br>
    </h2>
    <h3>
        <a href="${gameActionUrl}">Click here to start the game </a>
    </h3>
</div>
</body>
</html>