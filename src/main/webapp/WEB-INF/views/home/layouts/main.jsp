<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/taglib.jsp" %>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Small Business - Start Bootstrap Template</title>

  <!-- Bootstrap core CSS -->
  <link href="<c:url value='/template/home/vendor/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="<c:url value='/template/home/css/small-business.css'/>" rel="stylesheet">

</head>

<body>

  <!-- Navigation -->
  <%@include file="/WEB-INF/views/home/partials/header.jsp" %>

  <!-- Page Content -->
  <div class="container">

    <dec:body/>

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <%@include file="/WEB-INF/views/home/partials/footer.jsp" %>

  <!-- Bootstrap core JavaScript -->
  <script src="<c:url value='/template/home/vendor/jquery/jquery.min.js'/>"></script>
  <script src="<c:url value='/template/home/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>

</body>

</html>
