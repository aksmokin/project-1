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

<div class="row">
<div class="col-12 d-flex justify-content-center text-center">
<div style="clear:both;">

<table>
<form action="main" method="POST">

<tr>
    <td colspan="1"></td>
    <td><fieldset><input type="radio" name="type" value="e" checked><label for="type"><b>Employee</b></label>&nbsp;&nbsp;<input type="radio" name="type" value="m"><label for="type"><b>Manager</b></label></fieldset></td>
</tr>

<tr>
<td><label for="uname"><b>Username</b></label></td>
<td><input type="text" name="uname" required></td>
</tr>

<tr>
<td><label for="psw"><b>Password</b></label></td>
<td><input type="password" name="psw" required></td>
</tr>

<tr>
<td><input type="image" src="img/login.jpg"></td>
<td style="font-weight:bold;">New to Kickback?</td>
<td><a href="register.jsp" target="_self"><button style="color:#ffffff;background-color:#b7492e;font-weight:bold;" 
            type="button">SIGN UP
    </button></a></td>
</tr>
</form>
</table>

</div>
</div>
</div>
</body>

</html>
