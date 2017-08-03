<%--
  Created by IntelliJ IDEA.
  User: js
  Date: 9/21/16
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <spring:url value="/resources/style.css" var="style"/>
    <link href="${style}" rel="stylesheet"/>
    <title>Kalah</title>
</head>
<body>
<spring:url value="/game" var="gameActionUrl" />
<div class="background">
    <div id="resultDiv" class="box"><h2>Game Status : ${game.getResult()}</h2></div>
    <div id="main">
        <form:form action="${gameActionUrl}" method="put">
            <table id="gameBoard" class="gameBoard">
                <tbody>
                <tr>
                    <td colspan="8">${game.getPlayer1Name()}</td>
                </tr>
                <tr id="player1-row" ${ !game.isPlayer1Enabled() ? 'class="row-disabled"' : ''}>
                    <c:set var="fieldLength" value="${fn:length(game.getPits1())}"/>
                    <td rowspan="2">${game.getKalah1Stones()}</td>
                    <c:forEach var="pit" items="${game.getPits1()}" varStatus="loop">
                        <c:set var="index" value="${fieldLength - loop.count}"/>
                        <td>
                            <input type="radio" ${ !game.isPlayer1Enabled() || game.getPits1().get(index).getNumberOfStone() eq 0 ? 'disabled="disabled"' : ''}name="pitId" value="${game.getPits1().get(index).getPitId()}"/><br><br>
                            <c:out value="${game.getPits1().get(index).getNumberOfStone()}"></c:out>
                        </td>
                    </c:forEach>

                    <td rowspan="2">${game.getKalah2Stones()}</td>
                </tr>
                <tr id="player2-row" ${ !game.isPlayer2Enabled() ? 'class="row-disabled"' : ''}>
                    <c:forEach var="pit" items="${game.getPits2()}" varStatus="loop">
                        <td>
                            <input type="radio" ${ !game.isPlayer2Enabled() || pit.getNumberOfStone() eq 0 ? 'disabled="disabled"' : ''} name="pitId" value="${pit.getPitId()}"/><br><br>
                            <c:out value="${pit.getNumberOfStone()}"></c:out>
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <td colspan="8">${game.getPlayer2Name()}</td>
                </tr>
                </tbody>
            </table>
            <div style="text-align: center">
                <input type="submit" id="move" class="btn" value="Play" ${ game.getResult() == 'Game in Progress' ?  '' : 'disabled="disabled"'}>
            </div>
        </form:form>

        <form:form action="${gameActionUrl}" method="DELETE">
            <div style="text-align: center">
                <input type="submit" id="reset" class="btn" value="Reset">
            </div>
        </form:form>

    </div>
</div>

<script src="https://code.jquery.com/jquery-3.1.0.min.js"
        integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>

</body>
</html>