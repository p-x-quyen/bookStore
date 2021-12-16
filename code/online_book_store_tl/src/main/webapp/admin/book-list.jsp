<%-- 
    Document   : book-management
    Created on : Dec 8, 2021, 3:58:12 PM
    Author     : Administrator
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.book.Book"%>
<%@page import="model.book.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/bootstrap-4.6.1-dist/css/bootstrap.min.css">
        <link href="<%=request.getContextPath()%>/assets/font/fontawesome-free-5.15.4-web/css/all.css" rel="stylesheet">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/header.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/side-bar.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/book-list.css">
        <title>List books</title>
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
                            <a href="BookList" class="nav-link active">
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
                        <div class="d-flex flex-row mb-2">
                            <div class="d-flex flex-row flex-grow-1">
                                <input type="text" placeholder="Enter name" class="border flex-grow-1 px-2 py-1 rounded-left border-right-0 input-search">
                                <button type="button" class="btn-primary px-3 border-0 rounded-right btn-search">
                                    <i class="fas fa-search"></i>
                                </button>
                            </div>
                            <a class="btn btn-primary ml-1" href="BookCreate" role="button">New book</a>
                        </div>
                        <table class="table table-bordered table-striped mb-2 book-table">
                            <thead>
                                <tr>
                                    <th class="border-bottom-0">
                                        ID
                                    </th>
                                    <th class="border-bottom-0">
                                        Name
                                    </th>
                                    <th class="border-bottom-0">
                                        ISBN
                                    </th>
                                    <th class="border-bottom-0">
                                        Language
                                    </th>
                                    <th class="border-bottom-0">
                                        Publisher
                                    </th>
                                </tr>
                            </thead>
                            <tbody id="my-table">
                                <%  ArrayList<Book> listBooks = (ArrayList<Book>) request.getAttribute("listBooks");
                                    for (Book book:listBooks) {
                                %>
                                <tr>
                                    <td class="align-middle">
                                        <%=book.getId()%>
                                    </td>
                                    <td class="align-middle">
                                        <a href="BookDetails?id=<%=book.getId()%>" class="text-dark">
                                            <%=book.getName()%>
                                        </a>
                                    </td>
                                    <td class="align-middle">
                                        <%=book.getIsbn()%>
                                    </td>
                                    <td class="align-middle">
                                        <%=book.getLanguage()%>
                                    </td>
                                    <td class="align-middle">
                                        <%=book.getPublisher().getName()%>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                            <tbody id="search-result" style="display: none"></tbody>
                        </table>
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
                        "object": "book",
                        "value": bookName
                    }, function(result) {
//                        console.log(result);
                        $("#my-table").hide();
                        $("#search-result tr").remove();
                        $("#search-result").append(result);
                        $("#search-result").show();
                    });
                } else {
                    alert("Enter book name to search");
                }
            });
            
            $(".input-search").keyup(function() {
                var bookName = $(".input-search").val();
//                console.log(bookName);
                if (bookName.trim() === "") {
                    $("#my-table").show();
                    $("#search-result").hide();
                } 
            });
        </script>
    </body>
</html>
