<%-- 
    Document   : book-details
    Created on : Dec 10, 2021, 3:48:59 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../assets/css/bootstrap-4.6.1-dist/css/bootstrap.min.css">
        <link href="../assets/font/fontawesome-free-5.15.4-web/css/all.css" rel="stylesheet">
        <link rel="stylesheet" href="../assets/css/header.css">
        <link rel="stylesheet" href="../assets/css/side-bar.css">
        <link rel="stylesheet" href="../assets/css/admin/book-details.css">
        <title>Book details</title>
    </head>
    <body>
        <div class="container-fluid pl-0 pr-0">
            <header class="navbar navbar-expand-sm bg-primary navbar-dark pt-2 pb-2 fixed-top">
                <div class="container-fluid p-0">
                    <a class="navbar-brand d-flex flex-row align-items-center pt-0 pb-0" href="#">
                        <i class="fas fa-book" style="font-size: 45px"></i>
                        <p>Book store</p>
                    </a>
                    <div class="user-info d-flex flex-row align-items-center pt-0 pb-0">
                        <i class="fas fa-user-circle" style="font-size: 50px; color: white"></i>
                        <p class="name-user">
                            Admin
                        </p>
                    </div>
                </div>
            </header>
            <div class="row ml-0 d-flex" style="margin-top: 70px;">
                <div class="pl-3 pr-3 side-bar position-fixed">
                    <ul class="nav nav-pills flex-column mb-auto">
                        <li class="nav-item pt-2">
                            <a href="#" class="nav-link active">
                                <i class="fas fa-book-open"></i>
                                Books
                            </a>
                        </li>
                        <li class="nav-item pt-2">
                            <a href="#" class="nav-link">
                                <i class="fas fa-store"></i>
                                Book items
                            </a>
                        </li>
                        <li class="nav-item pt-2">
                            <a href="#" class="nav-link">
                                <i class="fas fa-sign-out-alt"></i>
                                Log out
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="flex-fill" style="margin-left: 300px;">
                    <div class="container-fluid pr-4 pt-2">
                        <div class="shadow p-3 mb-5 bg-white rounded about-book">
                            <h3>Book information</h3>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">ID</h5>
                                <p>1</p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Name</h5>
                                <p>Đắc nhâm tâm</p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Summary</h5>
                                <p>Quyển sách Đắc nhắn tâm là cuốn sách “đầu tiên và hay nhất mọi thời đại về nghệ thuật giao tiếp và ứng xử”, quyển sách đã từng mang đến thành công và hạnh phúc cho hàng triệu người trên khắp thế giới.</p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Number of pages</h5>
                                <p>100</p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Language</h5>
                                <p>Tiếng Việt</p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">ISBN</h5>
                                <p>11111 22222</p>
                            </div>
                        </div>
                        <div class="shadow p-3 mb-5 bg-white rounded about-book">
                            <h3>Author information</h3>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">ID</h5>
                                <p>1</p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Full name</h5>
                                <p>Đắc nhâm tâm</p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Biography</h5>
                                <p>Sinh sống ở Mỹ</p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Address</h5>
                                <p>Mỹ</p>
                            </div>
                        </div>
                        <div class="shadow p-3 mb-5 bg-white rounded about-book">
                            <h3>Publisher information</h3>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">ID</h5>
                                <p>1</p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Name</h5>
                                <p>Đắc nhâm tâm</p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Address</h5>
                                <p>Sinh sống ở Mỹ</p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Email</h5>
                                <p>connectme@gmail.com</p>
                            </div>
                        </div>
                        
                        <a class="btn btn-primary mt-0 mb-2" href="#" role="button">Add book item</a>
                        <a class="btn btn-primary mt-0 mb-2" href="#" role="button">View book item</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
