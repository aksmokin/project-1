<%-- 
    Document   : authError
    Created on : Mar 8, 2020, 9:14:11 AM
    Author     : panam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Kickback: Expense Reimbursement System</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<style>
.container-fluid {
        padding-right: 0;
        padding-left: 0;
        margin-right: auto;
        margin-left: auto;
}
</style>
</head>

<body class="container-fluid" style="background-color: #669999;">
<div style="background-image: url('img/top.jpg'); background-repeat: repeat-x; color:#ffffff; text-align: center; padding-bottom:5px;">
<h2>Kickback</h2>
</div>

<div class="container">
<div class="col-12 d-flex justify-content-center text-center">
<br />
<div class="alert alert-danger">Your username and/or password is incorrect.<br /><a href="login.jsp" class="alert-link" target="_self">Click here</a> to try again.</div>
</div>
</div>
</body>

</html>
