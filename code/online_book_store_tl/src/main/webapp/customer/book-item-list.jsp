<%-- 
    Document   : book-item-list
    Created on : Dec 22, 2021, 4:12:41 PM
    Author     : Administrator
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.book.BookItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap-4.6.1-dist/css/bootstrap.min.css">
        <link href="<%=request.getContextPath()%>/assets/font/fontawesome-free-5.15.4-web/css/all.css" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/header.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/side-bar.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/book-item-list.css">
        <title>List book items</title>
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
                            <% 
                                HttpSession httpSession = request.getSession(false);
                                String username = (String)httpSession.getAttribute("username");
                            %>
                            <%=username%>
                        </p>
                    </div>
                </div>
            </header>
            <div class="row ml-0 d-flex" style="margin-top: 70px;">
                <div class="pl-3 pr-3 side-bar position-fixed">
                    <ul class="nav nav-pills flex-column mb-auto">
                        <li class="nav-item pt-2">
                            <a href="BookItemList" class="nav-link active">
                                <i class="fas fa-book-open"></i>
                                Book items
                            </a>
                        </li>
                        <li class="nav-item pt-2">
                            <a href="" class="nav-link">
                                <i class="fas fa-shopping-cart"></i>
                                Cart
                            </a>
                        </li>
                        <li class="nav-item pt-2">
                            <a href="#" class="nav-link">
                                <i class="fas fa-scroll"></i>
                                Orders
                            </a>
                        </li>
                        <li class="nav-item pt-2">
                            <a href="Logout" class="nav-link">
                                <i class="fas fa-sign-out-alt"></i>
                                Log out
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="flex-fill" style="margin-left: 300px">
                    <div class="container-fluid pr-4 pt-2">
                        <div class="d-flex flex-row mb-2">
                            <div class="d-flex flex-row flex-grow-1">
                                <input type="text" placeholder="Enter name" class="border flex-grow-1 px-2 py-1 rounded-left border-right-0 input-search">
                                <button type="button" class="btn-primary px-3 border-0 rounded-right btn-search">
                                    <i class="fas fa-search"></i>
                                </button>
                            </div>
                        </div>
                        
                        <div class="row" id="book-list">
                            <%  
                                ArrayList<BookItem> listBookItems = (ArrayList<BookItem>) request.getAttribute("listBookItems");
                                for (BookItem bookItem: listBookItems) {
                                    float discount = Float.parseFloat(bookItem.getDiscount().trim());
                                    float priceCurrent = bookItem.getPrice() - (bookItem.getPrice() * (discount / 100));
                                
                            %>
                            <div class="col col-sm-4">
                                <a class="book-item" href="BookItemDetails?id=<%=bookItem.getId()%>">
                                    <div class="book-item__img" style="background-image: url(<%=request.getContextPath()%>/bookItemImages/<%=bookItem.getImage()%>)"></div>
                                    <h4 class="book-item__name">
                                        <%=bookItem.getBook().getName()%>
                                    </h4>
                                    <div class="book-item__price d-flex flex-column">
                                        <span class="book-item__price-old "><%=bookItem.getPrice()%> (VND)</span>
                                        <span class="book-item__price-current"><%=priceCurrent%> (VND)</span>
                                    </div>
                                    <div class="book-item__origin">
                                        <span class="book-item__brand ">Book store</span>
                                        <span class="book-item__origin-name ">Ba Đình - Hà Nội</span>
                                    </div>
                                    <div class="book-item__favourite">
                                        <i class="fas fa-check"></i>
                                        <span>Favorite</span>
                                    </div>
                                    <div class="book-item__sale-off">
                                        <span class="book-item__sale-off-label">Off</span>
                                        <span class="book-item__sale-off-percent"><%=bookItem.getDiscount()%>%</span>
                                    </div>
                                </a>
                            </div>
                            <%
                                }
                            %>
                        </div>
                        
                        <div class="row" id="search-result" style="display: none"></div>
                    </div>
                </div>
            </div>
        </div>
                            
        <script src="<%=request.getContextPath()%>/assets/js/jquery-3.5.1.min.js"></script>
        <script type="text/javascript">
            $(".btn-search").click(function() {
                var bookName = $(".input-search").val().trim();
//                console.log(bookName);
                if (bookName !== "") {
                    $.get("Search", {
                        "object": "bookItem",
                        "value": bookName
                    }, function(result) {
//                        console.log(result);
                        $("#book-list").hide();
                        $("#search-result div").remove();
                        $("#search-result").append(result);
                        $("#search-result").show();
                    });
                } else {
                    alert("Enter book name to search");
                }
            });
//            
            $(".input-search").keyup(function() {
                var bookName = $(".input-search").val();
//                console.log(bookName);
                if (bookName.trim() === "") {
                    $("#book-list").show();
                    $("#search-result").hide();
                } 
            });
//            
        </script>
    </body>
</html>
