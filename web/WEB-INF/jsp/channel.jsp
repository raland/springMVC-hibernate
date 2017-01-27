<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Channel</title>


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
          integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
            integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
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
    <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css"/>
    <script type="text/javascript" src="<c:url value="/resources/js/daterangepicker.js" />"></script>
</head>
<body>
<div class="jumbotron">
    <div class="row">
        <div class="col-sm-6">

            <h1>${channel.channelName}</h1>

        </div>
        <div class="col-sm-6">

            <h4>${channel.channelDescription}</h4>

        </div>
    </div>
    <%--<p>Greetings, it is now <c:out value="${now}"/></p>--%>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-6">

            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#mon" role="tab">Monday</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#tue" role="tab">Tuesday</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#wed" role="tab">Wednesday</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#thu" role="tab">Thursday</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#fri" role="tab">Friday</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#sat" role="tab">Saturday</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#sun" role="tab">Sunday</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div class="tab-pane active" id="mon" role="tabpanel">...</div>
                <div class="tab-pane" id="tue" role="tabpanel">...</div>
                <div class="tab-pane" id="wed" role="tabpanel">...</div>
                <div class="tab-pane" id="thu" role="tabpanel">...</div>
                <div class="tab-pane" id="fri" role="tabpanel">...</div>
                <div class="tab-pane" id="sat" role="tabpanel">...</div>
                <div class="tab-pane" id="sun" role="tabpanel">...</div>
            </div>

<%--            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Program Name</th>
                        <th>type</th>
                        <th>duration</th>
                        <th>Start time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${programList}" var="program1">
                        <tr>
                            <td>${program1.programName}</td>
                            <td>${program1.programType}</td>
                            <td>${program1.programLength}</td>
                            <td>${program1.startTime}</td>
                            <td><a class="btn btn-danger"
                                   href="<c:url value='/program/remove/${program1.programId}' />">Remove Program</a></td>
                            <td><a class="btn btn-info" href="<c:url value='/program/edit/${program1.programId}' />">View/Edit Program</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>--%>


        </div>
        <div class="col-sm-6">
            <c:url var="addProgram" value="/program/add"/>

            <form:form action="${addProgram}" commandName="program">
                <div class="form-group">
                    <form:label class="control-label" path="programName">
                        <spring:message text="Program name"/>
                    </form:label>
                    <form:input cssClass="form-control" path="programName"/>
                </div>
                <div class="form-group">
                    <form:select cssClass="form-control" path="programType">
                        <form:options items="${typeList}"/>
                    </form:select>
                </div>
                <div class="form-group">
                    <form:label class="control-label" path="programLength">
                        <spring:message text="Program duration"/>
                    </form:label>
                    <form:input cssClass="form-control" path="programLength"/>
                </div>

                <div class="form-group">
                    <form:label class="control-label" path="startTime">
                        <spring:message text="Start Time"/>
                    </form:label>
                    <form:input cssClass="form-control" path="startTime" name="daterange"/>
                </div>


                <form:hidden path="channel" value="${channel.channelId}"/>

                <input class="btn btn-default" type="submit"
                       value="<spring:message text="Add Program"/>"/>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>
