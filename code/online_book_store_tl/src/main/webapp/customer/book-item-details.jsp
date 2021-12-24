<%-- 
    Document   : book-item-details
    Created on : Dec 23, 2021, 7:39:41 PM
    Author     : Administrator
--%>

<%@page import="model.Pair"%>
<%@page import="model.order.Cart"%>
<%@page import="java.util.List"%>
<%@page import="model.book.Author"%>
<%@page import="model.book.BookItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap-4.6.1-dist/css/bootstrap.min.css">
        <link href="<%=request.getContextPath()%>/assets/font/fontawesome-free-5.15.4-web/css/all.css" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/header.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/side-bar.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/book-item-details.css">
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
                <%  
                    BookItem bookItem = (BookItem) request.getAttribute("bookItem");
                    List<Author> listAuthors = bookItem.getBook().getListAuthors();
                    float discount = Float.parseFloat(bookItem.getDiscount().trim());
                    float priceCurrent = bookItem.getPrice() - (bookItem.getPrice() * (discount / 100));
                %>
                <div class="flex-fill" style="margin-left: 300px;">
                    <div class="container-fluid pr-4 pt-2">
                        <div class="row m-0">
                            <div class="col-lg-4 p-0 rounded text-center">
                                <img src="<%=request.getContextPath()%>/bookItemImages/<%=bookItem.getImage()%>" class="rounded-top book-item-img" alt="book" style="width: 100%; height: auto">
                            </div>
                            <div class="col-lg-8 pl-3 pr-0">
                                <div class="d-flex justify-content-between mb-2">
                                    <h5>Name</h5>
                                    <p><%=bookItem.getBook().getName()%></p>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <h5>Summary</h5>
                                    <p class="ml-3"><%=bookItem.getBook().getSummary()%></p>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <h5>Number of pages</h5>
                                    <p><%=bookItem.getBook().getNumberOfPages()%></p>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <h5>Language</h5>
                                    <p><%=bookItem.getBook().getLanguage()%></p>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <h5>Author</h5>
                                    <select class="form-select mb-1" aria-label="Default select example">
                                        <%
                                            for (Author author: listAuthors) {
                                        %>
                                        <option><%=author.getFullName()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <h5>Publisher</h5>
                                    <p><%=bookItem.getBook().getPublisher().getName()%></p>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <h5>Price</h5>
                                    <p><%=priceCurrent%></p>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <h5>Quantity</h5>
                                    <input type="text" class="p-2 quantity-input" value = "1">
                                </div>
                                <div class="mb-2">
                                    <div class="p-2 bg-primary text-white rounded float-right" id="add-to-cart" style="display: inline-block; cursor: pointer;">Add to cart</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="<%=request.getContextPath()%>/assets/js/jquery-3.5.1.min.js"></script>
        <script>
            
            $('#add-to-cart').click(function(){ 
                var quantity = $(".quantity-input").val().trim();
                if (quantity === "" || !isNaN(quantity) || !Number.isInteger(parseInt(quantity)) || parseInt(quantity) < 1) {
                    alert("quantity must be more than 0");
                    return;
                }
                $.get("AddToCart", {
                    "bookItemId": <%=bookItem.getId()%>,
                    "quantity": quantity
                }, function(result) {
                    alert(result);
                });
            });
        </script> 
    </body>
</html>
