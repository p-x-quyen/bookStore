<%-- 
    Document   : book-item-details
    Created on : Dec 10, 2021, 5:16:44 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../assets/css/bootstrap-4.6.1-dist/css/bootstrap.min.css">
        <link href="../assets/font/fontawesome-free-5.15.4-web/css/all.css" rel="stylesheet">
        <link rel="stylesheet" href="../assets/css/header.css">
        <link rel="stylesheet" href="../assets/css/side-bar.css">
        <link rel="stylesheet" href="../assets/css/admin/book-item-details.css">
        <title>Book item details</title>
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
                            <a href="#" class="nav-link">
                                <i class="fas fa-book-open"></i>
                                Books
                            </a>
                        </li>
                        <li class="nav-item pt-2">
                            <a href="#" class="nav-link active">
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
                        <div class="row m-0">
                            <div class="col-lg-4 p-0 rounded text-center">
                                <img src="../assets/img/book-item/img1.jpg" class="rounded-top book-item-img" alt="book">
                                <input type="file" id="input-img" name="img" accept="image/*" style="display:none" value="img1">
                                <div id="open-file" class="p-2 bg-primary text-white rounded my-button my-2" style="display:none">Select photo</div>
                            </div>
                            <div class="col-lg-8 pl-3 pr-0">
                                <div class="d-flex justify-content-between mb-2">
                                    <p>ID</p>
                                    <p>1</p>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <p>Book name</p>
                                    <a href="#" class="text-dark"><i>Đắc nhân tâm</i></a>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <p>Author</p>
                                    <p>Đắc nhân tâm</p>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <p>Publisher</p>
                                    <p>Đắc nhân tâm</p>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <p>Price</p>
                                    <p class="saved-info">1000 VND</p>
                                    <input type="text" value="1000" class="px-2 change-input" style="display:none">
                                </div> 
                                <div class="d-flex justify-content-between mb-2">
                                    <p>Discount</p>
                                    <p class="saved-info">90%</p>
                                    <input type="text" value="90%" class="px-2 change-input" style="display:none">
                                </div>
                                <div class="mb-2">
                                    <div class="p-2 bg-primary text-white rounded my-button" id="update-btn">Update</div>
                                    <div class="p-2 bg-primary text-white rounded my-button" id="confirm-btn" style="display:none">Confirm update</div>
                                    <div class="p-2 bg-primary text-white rounded my-button" id="cancel-btn" style="display:none">Cancel</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="../assets/js/jquery-3.5.1.min.js"></script>
        <script>
            $('#open-file').click(function(){ 
                $('#input-img').trigger('click'); 
            });
            $('#update-btn').click(function(){ 
                $('#update-btn').hide();
                $('.saved-info').hide();
                $('#confirm-btn').show();
                $('#cancel-btn').show();
                $('#open-file').show();
                $('.change-input').show();
            });
            $('#cancel-btn').click(function(){ 
                //delete all input and set photo, input again
                $('#update-btn').show();
                $('.saved-info').show();
                $('#confirm-btn').hide();
                $('#cancel-btn').hide();
                $('#open-file').hide();
                $('.change-input').hide();
            });
        </script>
    </body>
</html>
