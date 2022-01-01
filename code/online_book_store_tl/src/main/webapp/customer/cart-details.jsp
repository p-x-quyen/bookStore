<%-- 
    Document   : cart-details
    Created on : Dec 25, 2021, 6:46:26 PM
    Author     : Administrator
--%>

<%@page import="model.book.BookItem"%>
<%@page import="model.Pair"%>
<%@page import="java.util.List"%>
<%@page import="model.order.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap-4.6.1-dist/css/bootstrap.min.css">
        <link href="<%=request.getContextPath()%>/assets/font/fontawesome-free-5.15.4-web/css/all.css" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/header.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/side-bar.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/customer/cart-details.css">
        <title>Cart details</title>
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
                            <a href="OrderCreate?action=viewCart" class="nav-link active">
                                <i class="fas fa-shopping-cart"></i>
                                Cart
                            </a>
                        </li>
                        <li class="nav-item pt-2">
                            <a href="OrderDetails" class="nav-link">
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
                        <table class="table table-bordered table-striped mb-2 book-table">
                            <thead>
                                <tr>
                                    <th class="border-bottom-0">
                                        Image
                                    </th>
                                    <th class="border-bottom-0">
                                        Name
                                    </th>
                                    <th class="border-bottom-0">
                                        Price
                                    </th>
                                    <th class="border-bottom-0">
                                        Quantity
                                    </th>
                                    <th class="border-bottom-0">
                                        Remove
                                    </th>
                                </tr>
                            </thead>
                            <tbody id="">
                                <% 
                                    Cart cart = (Cart)httpSession.getAttribute("cart");
                                    List<Pair<BookItem, Integer>> listBookItems = cart.getListBookItems();

                                    for (Pair<BookItem, Integer> bookItemPair: listBookItems) {
                                        BookItem bookItem = bookItemPair.getKey();
                                        int quantity = bookItemPair.getValue();
                                        float discount = Float.parseFloat(bookItem.getDiscount().trim());
                                        float priceCurrent = bookItem.getPrice() - (bookItem.getPrice() * (discount / 100));
                                %>
                                <tr>
                                    <td class="align-middle">
                                        <img src="<%=request.getContextPath()%>/bookItemImages/<%=bookItem.getImage()%>" class="book-item-img">
                                    </td>
                                    <td class="align-middle">
                                        <a href="BookItemDetails?id=<%=bookItem.getId()%>" class="text-dark">
                                            <i><%=bookItem.getBook().getName()%></i>
                                        </a>
                                    </td>
                                    <td class="align-middle">
                                        <%=priceCurrent%>
                                    </td>
                                    <td class="align-middle">
                                        <%=quantity%>
                                    </td>
                                    <td class="align-middle">
                                        <a class="btn btn-primary" href="CartAddBookItem?action=remove&bookItemId=<%=bookItem.getId()%>" role="button">remove</a>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                                <tr>
                                    <td class="align-middle">
                                        Total of books
                                    </td>
                                    <td class="align-middle">
                                    </td>
                                    <td class="align-middle">
                                        <%=cart.getTotalPrice()%>
                                    </td>
                                    <td class="align-middle">
                                        <%=cart.getTotalQuantity()%>
                                    </td>
                                    <td class="align-middle">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="shadow p-3 mb-3 mt-3 bg-white rounded shipment">
                            <h5>Shipment</h5>
                            <div class="form-group d-flex flex-column">
                                <label for="shipment-type">Type</label>
                                <select class="form-select mb-2 mt-2" id="shipment-type" aria-label="Default select example">
                                    <option value="EXW">EXW - 30000 VND</option>
                                    <option value="FCA">FCA - 30000 VND</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="shipment-address">Address</label>
                                <input type="text" class="form-control" id="shipment-address" placeholder="Address">
                            </div>
                        </div>
                        <div class="shadow p-3 mb-3 mt-3 bg-white rounded check-out">
                            <h5>Check out</h5>
                            <div class="form-group d-flex flex-column">
                                <label for="check-out-type">Type</label>
                                <select class="form-select mb-2 mt-2 payment-type" aria-label="Default select example">
                                    <option value="check">Check</option>
                                    <option value="credit">Credit</option>
                                    <option value="cash">Cash</option>
                                </select>
                            </div>
                            <div class="check-form">
                                <div class="form-group">
                                    <label for="check-name">Name</label>
                                    <input type="text" class="form-control" id="check-name" placeholder="Name">
                                </div>
                                <div class="form-group">
                                    <label for="check-bank-id">Bank ID</label>
                                    <input type="text" class="form-control" id="check-bank-id" placeholder="Bank ID">
                                </div>
                            </div>
                            <div class="credit-form" style="display: none">
                                <div class="form-group">
                                    <label for="credit-number">Number</label>
                                    <input type="text" class="form-control" id="credit-number" placeholder="Number">
                                </div>
                                <div class="form-group">
                                    <label for="credit-type">Type</label>
                                    <input type="text" class="form-control" id="credit-type" placeholder="Type">
                                </div>
                                <div class="form-group">
                                    <label for="credit-expDate">Expire date</label>
                                    <input type="date" id="credit-expDate" class="form-control">
                                </div>
                            </div>
                            <div class="cash-form" style="display: none">
                                <div class="form-group">
                                    <label for="cash-tendered">Cash tendered</label>
                                    <input type="text" class="form-control" id="cash-tendered" placeholder="Cash tendered">
                                </div>
                            </div>
                        </div> 
<!--                        <div class="shadow p-3 mb-3 mt-3 bg-white rounded shipment d-flex align-items-end">
                            <h5 class="m-0 pr-3">Bill total: </h5>
                            <h6 class="m-0 bill-total">Bill total: </h6>
                        </div>-->
                        <button class="btn p-2 bg-primary text-white float-right" id="order-btn">Order</button>
                    </div>
                </div>
            </div>
        </div>
        <script src="<%=request.getContextPath()%>/assets/js/jquery-3.5.1.min.js"></script>
        <script type="text/javascript">
            $(".payment-type").change(function() {
                var paymentType = $(this).val().trim();
                if (paymentType === "check") {
                    $(".check-form").show();
                    $(".credit-form").hide();
                    $(".cash-form").hide();
                } else if (paymentType === "credit") {
                    $(".check-form").hide();
                    $(".credit-form").show();
                    $(".cash-form").hide();
                } else if (paymentType === "cash") {
                    $(".check-form").hide();
                    $(".credit-form").hide();
                    $(".cash-form").show();
                } 
            });
            $("#order-btn").click(function() {
                var paymentType = $(".payment-type").val().trim();
                var shipmentType = $("#shipment-type").val().trim();
                var shipmentAddress = $("#shipment-address").val().trim();
                
                if (shipmentAddress === "") {
                    alert("enter address");
                    return;
                }
                
                if (paymentType === "check") {
                    var name = $("#check-name").val().trim();
                    if (name === "") {
                        alert("enter name");
                        return;
                    }
                    var bankId = $("#check-bank-id").val().trim();
                    if (bankId === "") {
                        alert("enter bank id");
                        return;
                    }
                    
                    $.post("OrderCreate", {
                        "action": "create",
                        "shipmentType": shipmentType,
                        "shipmentAddress": shipmentAddress,
                        "paymentType": paymentType,
                        "name": name,
                        "bankId": bankId
                    }, function(result) {
                        alert(result);
                        location.reload();
                    });
                    
                } else if (paymentType === "credit") {
                    var number = $("#credit-number").val().trim();
                    if (number === "") {
                        alert("enter number");
                        return;
                    }
                    var type = $("#credit-type").val().trim();
                    if (type === "") {
                        alert("enter type");
                        return;
                    }
                    var expDate = $("#credit-expDate").val().trim();
                    if (expDate === "") {
                        alert("select expire date");
                        return;
                    }
                    
//                    $.post("CreateOrder", {
//                        "action": "create",
//                        "shipmentType": shipmentType,
//                        "shipmentAddress": shipmentAddress,
//                        "paymentType": paymentType,
//                        "number": number,
//                        "type": type,
//                        "expDate": expDate
//                    }, function(result) {
//                        alert(result);
//                    });
                } else if (paymentType === "cash") {
                    var cashTendered = $("#cash-tendered").val().trim();
                    if (cashTendered === "") {
                        alert("enter cash tendered");
                        return;
                    }
                    
//                    $.post("CreateOrder", {
//                        "action": "create",
//                        "shipmentType": shipmentType,
//                        "shipmentAddress": shipmentAddress,
//                        "paymentType": paymentType,
//                        "cashTendered": cashTendered
//                    }, function(result) {
//                        alert(result);
//                    });
                } 
            });
        </script>
    </body>
</html>
