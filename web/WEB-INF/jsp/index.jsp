<%--
  Created by IntelliJ IDEA.
  User: raul
  Date: 22/01/17
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include.jsp" %>
<html>
<head>
    <title>Home</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
          integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
            integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
            integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <script type="text/javascript" src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/js/daterangepicker.js" />"></script>
</head>
<body>

<div class="jumbotron text-center">
    <h1>TV Guide</h1>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-6">
            <a href="${pageContext.request.contextPath}/channels">
                <button class="btn-lg btn-primary btn-block">View Channels</button>
            </a>
            <div style="margin-top: 2%" class="form-group">
                <label style="margin-top: 2%" for="genreSelect">Program type</label>
                <select id="genreSelect" class="form-control">
                    <c:forEach items="${typeList}" var="genre">
                        <option value="${genre.key}">${genre.key}</option>
                    </c:forEach>
                </select>
                <label style="margin-top: 2%" for="dateInput">Airing time</label>
                <input id="dateInput" class="form-control" type="text" name="daterange">
                <button style="margin-top: 2%" id="searchButton" class="btn btn-info btn-block">Search</button>
            </div>
            <div id="searchResults">

            </div>
        </div>
        <div class="col-sm-6">
            <button class=" btn-lg btn-primary btn-block" data-toggle="collapse" data-target="#channelAdd">Add Channel</button>
            <div id="channelAdd" class="collapse">

                <c:url var="addAction" value="/channel/add"/>

                <form:form action="${addAction}" commandName="channel">
                    <div class="form-group">
                        <form:label class="control-label" path="channelName">
                            <spring:message text="Channel name"/>
                        </form:label>
                        <form:input cssClass="form-control" path="channelName"/>
                    </div>
                    <div class="form-group">
                        <form:select cssClass="form-control" path="genre">
                            <form:options items="${genreList}"/>
                        </form:select>
                    </div>
                    <div class="form-group">
                        <form:label class="control-label" path="channelDescription">
                            <spring:message text="Channel description"/>
                        </form:label>
                        <form:input cssClass="form-control" path="channelDescription"/>
                    </div>

                    <input class="btn btn-success" type="submit"
                           value="<spring:message text="Add Channel"/>"/>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
