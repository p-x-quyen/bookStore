<%-- 
    Document   : book-details
    Created on : Dec 10, 2021, 3:48:59 PM
    Author     : Administrator
--%>

<%@page import="java.util.List"%>
<%@page import="model.book.Author"%>
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
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/admin/book-details.css">
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
                            <a href="BookList" class="nav-link active">
                                <i class="fas fa-book-open"></i>
                                Books
                            </a>
                        </li>
                        <li class="nav-item pt-2">
                            <a href="BookItemList" class="nav-link">
                                <i class="fas fa-store"></i>
                                Book items
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
                <%  Book book = (Book) request.getAttribute("book"); 
                    int hasBookItem = (int) request.getAttribute("hasBookItem");
                %>
                <div class="flex-fill" style="margin-left: 300px;">
                    <div class="container-fluid pr-4 pt-2">
                        <div class="shadow p-3 mb-5 bg-white rounded about-book">
                            <h3>Book information</h3>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">ID</h5>
                                <p><%=book.getId()%></p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Name</h5>
                                <p><%=book.getName()%></p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Summary</h5>
                                <p><%=book.getSummary()%></p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Number of pages</h5>
                                <p><%=book.getNumberOfPages()%></p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Language</h5>
                                <p><%=book.getLanguage()%></p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">ISBN</h5>
                                <p><%=book.getIsbn()%></p>
                            </div>
                        </div>
                        <div class="shadow p-3 mb-5 bg-white rounded about-book">
                            <h3>Author information</h3>
                            <div class="d-flex flex-row mb-2">
                                <h5 class="book-attribute">Full name</h5>
                                <select id="list-authors" class="form-select mb-1" aria-label="Default select example">
                                </select>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Biography</h5>
                                <p id="author-biography" ></p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Address</h5>
                                <p id="author-address"></p>
                            </div>
                        </div>
                        <div class="shadow p-3 mb-5 bg-white rounded about-book">
                            <h3>Publisher information</h3>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">ID</h5>
                                <p><%=book.getPublisher().getId()%></p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Name</h5>
                                <p><%=book.getPublisher().getName()%></p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Address</h5>
                                <p><%=book.getPublisher().getAddress()%></p>
                            </div>
                            <div class="d-flex flex-row">
                                <h5 class="book-attribute">Email</h5>
                                <p><%=book.getPublisher().getEmail()%></p>
                            </div>
                        </div>
                        <%  if (hasBookItem != 0) { 
                        %>
                        <a class="btn btn-primary mt-0 mb-2 view-book-item-btn" href="BookItemDetails?id=<%=hasBookItem%>" role="button">
                            View book item
                        </a>
                        <%
                            } else {
                        %>
                        <button class="btn btn-primary mt-0 mb-2 new-book-item-btn">New book item</button>        
                        <%
                            }
                        %>
                        
                        <form class="shadow p-3 mb-5 bg-white rounded book-item" style="display: none" action="BookItemCreate" method="POST" enctype="multipart/form-data">
                            <h3>Book Item</h3>
                            <div class="d-flex flex-row">
                                <input type="hidden" name="bookId" value="<%=book.getId()%>">
                                <input type="file" id="input-img" name="img" accept="image/*" style="display:none" value="" required>
                                <div class="col col-3 pl-0 book-attribute">
                                    <div id="open-file" class="btn btn-primary mt-0 mb-2 mr-2">Select photo</div>
                                </div>
                                <p class="pt-1 img-path">no image selected</p>
                            </div>
                            <div class="d-flex flex-row mb-2">
                                <h5 class="col col-3 pl-0 book-attribute">Price (VND)</h5>
                                <input type="text" class="px-2 price" placeholder="Price" name="price" required>
                            </div>
                            <div class="d-flex flex-row mb-2">
                                <h5 class="col col-3 pl-0 book-attribute">Discount (%)</h5>
                                <input type="text" class="px-2 discount" placeholder="Discount (%)" name="discount" value="0">
                            </div>
                            <div class="d-flex justify-content-end">
                                <button type="sumbit" class="btn btn-primary mr-1 create-book-item-btn ">Create</button>
                                <div class="btn btn-primary cancel-btn ">Cancel</div> 
                            </div>
                        </form>
                    </div>
                </div>
            </div>     
        </div>
        <script src="<%=request.getContextPath()%>/assets/js/jquery-3.5.1.min.js"></script>
        <script type="text/javascript">
            var authors = [];
            <%  List<Author> listAuthors = book.getListAuthors();
                for (int i = 0; i < listAuthors.size(); i++) { %>
                    $('#list-authors').append($('<option>', {
                        value: <%=i%>,
                        text: "<%=listAuthors.get(i).getFullName()%>" + " (ID - " + "<%=listAuthors.get(i).getId()%>" + ")"
                    }));
                    
                    authors[<%=i%>] = {
                        "id": "<%=listAuthors.get(i).getId()%>",
                        "fullName": "<%=listAuthors.get(i).getFullName()%>",
                        "biography": "<%=listAuthors.get(i).getBiography()%>",
                        "address": "<%=listAuthors.get(i).getAddress()%>"
                    };
            <%
                    if (i == 0) {
            %>
                        $('#author-biography').text("<%=listAuthors.get(i).getBiography()%>");
                        $('#author-address').text("<%=listAuthors.get(i).getAddress()%>");
            <%
                    }
                }
            %>
            
//            console.log(authors)
            $('#list-authors').on('change', function () {
                var index = $("#list-authors").val();
//                console.log(index)
                $('#author-biography').text(authors[index]["biography"]);
                $('#author-address').text(authors[index]["address"]);
            });
            
            $('.new-book-item-btn').click(function() {
               $(".book-item").show(); 
               $(".new-book-item-btn").hide();
            });
            
            $('.cancel-btn').click(function() {
               $(".book-item").hide(); 
               $(".new-book-item-btn").show(); 
            });
            
            $('#open-file').click(function(){ 
                $('#input-img').trigger('click');
            });
            
            $('#input-img').on('change', function() {
                $(".img-path").text($('#input-img').val());
            });  
            
            $('.create-book-item-btn').click(function() {
               if ($('#input-img').val().trim() === "") {
                   alert("Select photo");
               }
            });
            
//            $(".input-search").keyup(function() {
//                var authorName = $(".input-search").val();
//                if (authorName.trim() === "") {
//                    $("#search-result tr").remove();
//                } 
//            });
        </script>
    </body>
</html>
