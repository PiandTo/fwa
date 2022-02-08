<%--
  Created by IntelliJ IDEA.
  User: snaomi
  Date: 16.12.2021
  Time: 00:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta http-equiv='content-type' content='text/php; charset=windows-1251'>
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">

    <title>SignIn page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/styles.css" type="text/css"><!-- Google Tag Manager -->
    <!-- End Google Tag Manager -->
    <style>

        form, h1 {
            width:  400px;
            margin:  0 auto;
        }
        h1 {
            margin:  40px auto;
        }
        .form-group {
            margin-top:  20px;
        }
        .btn {
            margin-top:  10px;
        }

    </style>
</head>
<body>
<div id="Login">
    <h1>
        Register page
    </h1>

    <form action="signup_result" method="POST">
        <div class="form-group">
            <label for="exampleInputName">First Name</label>
            <input type="text" class="form-control" name="first_name" id="exampleInputName" aria-describedby="emailHelp" placeholder="Enter your name">
            <small id="nameHelp" class="form-text text-muted">We'll never share your name with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="exampleInputSurname">Surname</label>
            <input type="text" class="form-control" id="exampleInputSurname" name="last_name" aria-describedby="emailHelp" placeholder="Enter your surname">
            <small id="surnameHelp" class="form-text text-muted">We'll never share your surname with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="exampleInputPhone">Phone number</label>
            <input type="phone" class="form-control" id="exampleInputPhone" aria-describedby="emailHelp" name="phone_number" placeholder="Enter your phone number">
            <small id="phoneHelp" class="form-text text-muted">We'll never share your phone number with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email" placeholder="Enter email">
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="Password">
        </div>
        <div class="form-check">
            <input type="checkbox" class="form-check-input" id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1">Check me out</label>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
