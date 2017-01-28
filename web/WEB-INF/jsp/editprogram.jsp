<%--
  Created by IntelliJ IDEA.
  User: dii
  Date: 1/26/17
  Time: 10:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include.jsp" %>
<html>
<head>
    <title>Edit Channel</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
    <link rel="stylesheet" type="text/css"
          href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css"/>
    <script type="text/javascript" src="<c:url value="/resources/js/daterangepicker.js" />"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-6">
            <c:url var="updateProgram" value="/program/update/${program.programId}"/>

            <form:form action="${updateProgram}" commandName="program">
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
                    <form:input value="1" type="number" min="1" cssClass="form-control" path="programLength"/>
                </div>

                <div class="form-group">
                    <form:label class="control-label" path="startTime">
                        <spring:message text="Start Time"/>
                    </form:label>
                    <form:input cssClass="form-control" path="startTime" name="daterange"/>
                </div>

                <div class="form-group">
                    <label for="channels">New Channel</label>
                    <select id="channels" class="form-control" name="channels">
                        <c:forEach items="${listChannels}" var="channel">
                            <option value="${channel.channelId}">${channel.channelName}</option>
                        </c:forEach>
                    </select>
                </div>

                <form:hidden path="programId" value="${program.programId}"/>

                <input type="hidden" name="previousId" value="${channel.channelId}">
                <input class="btn btn-success" type="submit"
                       value="<spring:message text="Save Program"/>"/>
            </form:form>
        </div>
        <div class="col-lg-6">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Program Name</th>
                        <th>Channel</th>
                        <th>Type</th>
                        <th>Duration</th>
                        <th>Start time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${repeatingPrograms}" var="repeatingProgram">
                        <tr>
                            <td>${repeatingProgram.programName}</td>
                            <td>${repeatingProgram.channel.channelName}</td>
                            <td>${repeatingProgram.programType}</td>
                            <td>${repeatingProgram.programLength}</td>
                            <td>${repeatingProgram.startTime}</td>
                            <td><a class="btn btn-danger"
                                   href="<c:url value='/program/remove/${repeatingProgram.programId}' />">Remove
                                Program</a></td>
                            <td><a class="btn btn-info"
                                   href="<c:url value='/program/edit/${repeatingProgram.programId}' />">View/Edit
                                Program</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>