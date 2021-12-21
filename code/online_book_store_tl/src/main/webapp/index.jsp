<%-- 
    Document   : index
    Created on : Dec 8, 2021, 3:29:19 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/bootstrap-4.6.1-dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/font/google-font/dancing.css">
        <link rel="stylesheet" href="assets/font/animate/animate.css-4.1.1-animate.min.css">
        <link rel="stylesheet" href="assets/css/login.css">
        <title>Login</title>
    </head>
    <body>
        <div class="container">
            <div class="row m-0 p-0 mb-1">
                <div class="col-sm-5 pl-0">
                    <img src="assets/img/login/book.jpg" alt="book" class="img-fluid rounded-left">
                </div>
                <div class="col-sm-7 text-center align-self-center">
                    <h1 class="animate__animated animate__heartBeat animate__infinite text-primary mb-0">Log in</h1>
                    <form action="Login" method="POST">
                        <div class="form-row py-3 pt-5">
                            <div class="offset-1 col-sm-10">
                                <input type="text" class="inp px-3" placeholder="Username" name="username">
                            </div>
                        </div>
                        <div class="form-row py-3">
                            <div class="offset-1 col-sm-10">
                                <input type="password" class="inp px-3" placeholder="Password"  name="password">
                            </div>
                        </div>
                        <div class="form-row py-3">
                            <div class="offset-1 col-sm-10">
                                <button class="btn-primary btn-login">Log in</button>
                            </div>
                        </div>
                    </form>
                    <a href="Register" class="mb-3">Register</a>
                </div>
            </div>
        </div>
    </body>
</html>
