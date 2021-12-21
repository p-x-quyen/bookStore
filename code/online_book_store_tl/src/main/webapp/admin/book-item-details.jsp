<%-- 
    Document   : book-item-details
    Created on : Dec 10, 2021, 5:16:44 PM
    Author     : Administrator
--%>

<%@page import="model.book.BookItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap-4.6.1-dist/css/bootstrap.min.css">
        <link href="<%=request.getContextPath()%>/assets/font/fontawesome-free-5.15.4-web/css/all.css" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/header.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/side-bar.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/book-item-details.css">
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
                            <a href="BookList" class="nav-link">
                                <i class="fas fa-book-open"></i>
                                Books
                            </a>
                        </li>
                        <li class="nav-item pt-2">
                            <a href="BookItemList" class="nav-link active">
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
                <%  BookItem bookItem = (BookItem) request.getAttribute("bookItem");%>
                <div class="flex-fill" style="margin-left: 300px;">
                    <div class="container-fluid pr-4 pt-2">
                        <form class="row m-0" action="BookItemUpdate" method="POST" enctype="multipart/form-data">
                            <input type="hidden" name="bookItemId" value="<%=bookItem.getId()%>">
                            <!--<input type="hidden" name="changeImage" value="false" class="change-image">-->
                            <div class="col-lg-4 p-0 rounded text-center">
                                <img src="<%=request.getContextPath()%>/uploads/<%=bookItem.getImage()%>" class="rounded-top book-item-img" alt="book" style="width: 100%; height: auto">
                                <input type="file" id="input-img" name="img" accept="image/*" style="display:none">
                                <div id="open-file" class="p-2 bg-primary text-white rounded my-button my-2" style="display:none">Select photo</div>
                                <p class="pt-1 img-path" style="display:none"></p>
                            </div>
                            <div class="col-lg-8 pl-3 pr-0">
                                <div class="d-flex justify-content-between mb-2">
                                    <p>ID</p>
                                    <p><%=bookItem.getId()%></p>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <p>Book name</p>
                                    <a href="BookDetails?id=<%=bookItem.getBook().getId()%>" class="text-dark">
                                        <i><%=bookItem.getBook().getName()%></i>
                                    </a>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <p>Language</p>
                                    <p><%=bookItem.getBook().getLanguage()%></p>
                                </div>    
                                <div class="d-flex justify-content-between mb-2">
                                    <p>Publisher</p>
                                    <p><%=bookItem.getBook().getPublisher().getName()%></p>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <p>Price (VND)</p>
                                    <p class="saved-info"><%=bookItem.getPrice()%></p>
                                    <input type="text" value="<%=bookItem.getPrice()%>" class="px-2 change-input" style="display:none" name="price" required>
                                </div> 
                                <div class="d-flex justify-content-between mb-2">
                                    <p>Discount (%)</p>
                                    <p class="saved-info"><%=bookItem.getDiscount()%></p>
                                    <input type="text" value="<%=bookItem.getDiscount()%>" class="px-2 change-input" style="display:none" name="discount">
                                </div>
                                <div class="mb-2">
                                    <div class="p-2 bg-primary text-white rounded my-button" id="update-btn">Update</div>
                                    <button class="btn p-2 bg-primary text-white" id="confirm-btn" style="display:none">Confirm update</button>
                                    <div class="btn p-2 bg-primary text-white rounded my-button" id="cancel-btn" style="display:none">Cancel</div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="<%=request.getContextPath()%>/assets/js/jquery-3.5.1.min.js"></script>
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
                 $(".img-path").show();
            });
            $('#cancel-btn').click(function(){ 
                //delete all input and set photo, input again
                $('#update-btn').show();
                $('.saved-info').show();
                $('#confirm-btn').hide();
                $('#cancel-btn').hide();
                $('#open-file').hide();
                $('.change-input').hide();
                 $(".img-path").hide();
            });
            
            $('#input-img').on('change', function() {
//               console.log($('#input-img').val()); 
                $(".img-path").text($('#input-img').val());
//                $(".change-image").val("true");
            });
        </script> 
    </body>
</html>
