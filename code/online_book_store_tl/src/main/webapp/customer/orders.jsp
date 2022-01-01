<%-- 
    Document   : orders
    Created on : Jan 1, 2022, 5:31:06 PM
    Author     : Administrator
--%>

<%@page import="model.order.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap-4.6.1-dist/css/bootstrap.min.css">
        <link href="<%=request.getContextPath()%>/assets/font/fontawesome-free-5.15.4-web/css/all.css" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/header.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/side-bar.css">
        <title>Order</title>
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
                            <a href="BookItemList" class="nav-link">
                                <i class="fas fa-book-open"></i>
                                Book items
                            </a>
                        </li>
                        <li class="nav-item pt-2">
                            <a href="OrderCreate?action=viewCart" class="nav-link">
                                <i class="fas fa-shopping-cart"></i>
                                Cart
                            </a>
                        </li>
                        <li class="nav-item pt-2">
                            <a href="OrderDetails" class="nav-link active">
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

                <div class="flex-fill" style="margin-left: 300px;">
                    <div class="container-fluid pr-4 pt-2">
                        <div class="form-group d-flex flex-column">
                            <select class="form-select mb-2 mt-2 order-list" aria-label="Default select example">
                                <option value="0">Select an order</option>
                                <%  ArrayList<Order> listOrders = (ArrayList<Order>) request.getAttribute("listOrders");
                                    for (Order order: listOrders) {
                                %>
                                <option value="<%=order.getId()%>"> <%=order.getCreateDate()%> - unconfirmed</option>
                                <%
                                    }
                                %>
                            </select>
                            
                            <div class="result"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="<%=request.getContextPath()%>/assets/js/jquery-3.5.1.min.js"></script>
        <script type="text/javascript">
            $(".order-list").change(function() {
                var orderId = $(this).val().trim();
                if (orderId !== "0") {
                    console.log(orderId);
                    $.get("OrderDetails", {
                        "id": orderId
                    }, function(result) {
                        $(".result div").remove();
                        $(".result").append(result);
                    });
                }
            });
        </script>
    </body>
</html>
