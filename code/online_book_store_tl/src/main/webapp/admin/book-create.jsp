<%-- 
    Document   : book-create
    Created on : Dec 11, 2021, 3:38:45 PM
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
                    <form class="container-fluid pr-4 pt-2" action="#" method="POST">
                        <div class="shadow p-3 mb-5 bg-white rounded about-book">
                            <h3>Book information</h3>
                            <div class="form-group row">
                                <label for="name" class="col-sm-5 h5 m-0 d-flex align-items-center">Name</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="name" name="name">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="summary" class="col-sm-5 h5 m-0 d-flex align-items-center">Summary</label>
                                <div class="col-sm-7">
                                    <textarea class="form-control" id="summary" name="summary"></textarea>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="number-of-pages" class="col-sm-5 h5 m-0 d-flex align-items-center">Number of pages</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="number-of-pages" name="number-of-pages">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="language" class="col-sm-5 h5 m-0 d-flex align-items-center">Language</label>
                                <div class="col-sm-7">
                                    <select class="custom-select" id="language" required name="language">
                                        <option selected disabled value="">---Language---</option>
                                        <option value="Tiếng Việt">Tiếng Việt</option>
                                        <option value="Tiếng Anh">Tiếng Anh</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="isbn" class="col-sm-5 h5 m-0 d-flex align-items-center">ISBN</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="isbn" name="isbn">
                                </div>
                            </div>
                        </div>
                        <div class="shadow p-3 mb-5 bg-white rounded about-book">
                            <h3>Author information</h3>
                            <div class="form-group row">
                                <label for="author" class="col-sm-5 h5 m-0 d-flex align-items-center">Full name</label>
                                <div class="col-sm-7">
                                    <select class="custom-select" id="author" required name="author">
                                        <option selected disabled value="">---Author---</option>
                                        <option value="1">ID - 1: Tiếng Việt</option>
                                        <option value="2">ID - 2: Tiếng Việt</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="author-biography" class="col-sm-5 h5 m-0 d-flex align-items-center">Biography</label>
                                <p class="col-sm-7 my-1" id="author-biography">None</p>
                            </div>
                            <div class="form-group row">
                                <label for="author-address" class="col-sm-5 h5 m-0 d-flex align-items-center">Address</label>
                                <p class="col-sm-7 my-1" id="author-address">None</p>
                            </div>
                        </div>
                        <div class="shadow p-3 mb-5 bg-white rounded about-book">
                            <h3>Publisher information</h3>
                            <div class="form-group row">
                                <label for="publisher" class="col-sm-5 h5 m-0 d-flex align-items-center">Name</label>
                                <div class="col-sm-7">
                                    <select class="custom-select" id="publisher" required name="publisher">
                                        <option selected disabled value="">---Publisher---</option>
                                        <option value="1">ID - 1: Tiếng Việt</option>
                                        <option value="2">ID - 2: Tiếng Việt</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="publisher-address" class="col-sm-5 h5 m-0 d-flex align-items-center">Address</label>
                                <p class="col-sm-7 my-1" id="publisher-address">None</p>
                            </div>
                            <div class="form-group row">
                                <label for="publisher-email" class="col-sm-5 h5 m-0 d-flex align-items-center">Email</label>
                                <p class="col-sm-7 my-1" id="publisher-address">None</p>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary mb-1">Create</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

