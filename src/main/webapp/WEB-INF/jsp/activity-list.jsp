<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
      <%@ taglib prefix = "fmt"  uri = "http://java.sun.com/jsp/jstl/fmt"  %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
          <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
          <title>Activity List</title>
          <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        </head>

        <body>
          <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand " href="/">ReservationApp</a>
            <ul class="navbar-nav">
              <li class="nav-item">
                <a class="nav-link active" href="${pageContext.request.contextPath}/activity/list">Activity List</a>
              </li>
            </ul>
          </nav>
          <form action="deleteSelected" method="GET">
            <div class="container" style="width:80%; padding-top:20px;">
              <div class="container text-left" style="margin-bottom: 10px">
                <button class="btn btn-primary">Delete selected</button>
              </div>
              <table class="table table-striped table-bordered table-hover">
                <thead class="thead-light">
                  <tr>
                    <td></td>
                    <td>Symbol</td>
                    <td>Name</td>
                    <td>Action</td>
                  </tr>
                </thead>
                <c:forEach var="tempActivity" items="${activityList}">
                  <tr>
                    <td><input type=checkbox name="activityid" value="${tempActivity.id}"/></td>
                    <td>${tempActivity.symbol}</td>
                    <td>${tempActivity.name}</td>
                    <td><a href="${pageContext.request.contextPath}/activity/update?activityId=${tempActivity.id}">Update</a></td>
                  </tr>
                </c:forEach>
              </table>
            </div>
          </form>

          <div class="container text-right" style="width:80%;">
            <a href="${pageContext.request.contextPath}/activity/add">Add activity</a>
          </div>
          <!-- Bootstrap js-->
          <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
          <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
          <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
          <!-- and of bootstrap js-->
        </body>
