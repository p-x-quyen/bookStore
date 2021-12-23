<%-- 
    Document   : book-create
    Created on : Dec 11, 2021, 3:38:45 PM
    Author     : Administrator
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.book.Publisher"%>
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

                <div class="flex-fill" style="margin-left: 300px;">
                    <div class="container-fluid pr-4 pt-2">
                        <div class="shadow p-3 mb-5 bg-white rounded">
                            <h3>Book information</h3>
                            <div class="form-group row">
                                <label for="book-name" class="col-sm-5 h5 m-0 d-flex align-items-center">Name</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="book-name">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="book-summary" class="col-sm-5 h5 m-0 d-flex align-items-center">Summary</label>
                                <div class="col-sm-7">
                                    <textarea class="form-control" id="book-summary"></textarea>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="number-of-pages" class="col-sm-5 h5 m-0 d-flex align-items-center">Number of pages</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="number-of-pages">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="book-language" class="col-sm-5 h5 m-0 d-flex align-items-center">Language</label>
                                <div class="col-sm-7">
                                    <select class="custom-select" id="book-language">
                                        <option value="Tiếng Việt">Tiếng Việt</option>
                                        <option value="Tiếng Anh">Tiếng Anh</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="isbn" class="col-sm-5 h5 m-0 d-flex align-items-center">ISBN</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="isbn">
                                </div>
                            </div>
                        </div>
                        <div class="shadow p-3 mb-5 bg-white rounded">
                            <h3>Author information</h3>
                            <div class="d-flex flex-row flex-grow-1 mb-2">
                                <input type="text" placeholder="Enter name" class="border flex-grow-1 px-2 py-1 rounded-left border-right-0 input-search">
                                <button type="button" class="btn-primary px-3 border-0 rounded-right btn-search" style="box-shadow: none!important;">
                                    <i class="fas fa-search"></i>
                                </button>
                                <button type="button" class="btn-primary px-3 border-0 rounded list-all-authors-btn ml-1" style="box-shadow: none!important;">
                                    List all authors
                                </button>
                                <button type="button" class="btn-primary px-3 border-0 rounded new-author-btn ml-1" style="box-shadow: none!important;">
                                    New author
                                </button>
                            </div>
                            <div class="d-flex flex-column mb-2 create-author-form" style="display: none!important">
                                <div class="form-group row">
                                    <label for="author-full-name" class="col-sm-5 h5 m-0 d-flex align-items-center">Full name</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="author-full-name">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="author-address" class="col-sm-5 h5 m-0 d-flex align-items-center">Address</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="author-address">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="author-biography" class="col-sm-5 h5 m-0 d-flex align-items-center">Biography</label>
                                    <div class="col-sm-7">
                                        <textarea type="text" class="form-control" id="author-biography"></textarea>
                                    </div>
                                </div>
                                <div>
                                    <button type="button" class="btn-primary px-3 border-0 rounded cancel-btn ml-1 float-right" style="box-shadow: none!important;">
                                        Cancel creating author
                                    </button>
                                    <button type="button" class="btn-primary px-3 border-0 rounded confirm-btn ml-1 float-right create-author-btn" style="box-shadow: none!important;">
                                        Create author
                                    </button>
                                </div>
                            </div>
                            <div class="d-flex flex-row flex-grow-1 mb-2">
                                <table class="table table-bordered mb-2">
                                    <thead>
                                        <tr>
                                            <th class="border-bottom-0">
                                                ID
                                            </th>
                                            <th class="border-bottom-0">
                                                Full name
                                            </th>
                                            <th class="border-bottom-0">
                                                Address
                                            </th>
                                            <th class="border-bottom-0">
                                                Action
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody id="search-result"></tbody>
                                </table>
                            </div>
                            <label class="col-sm-5 h5 m-0 d-flex align-items-center p-0 mb-2">Added</label>
                            <table class="table table-bordered mb-2">
                                <thead>
                                    <tr>
                                        <th class="border-bottom-0">
                                            ID
                                        </th>
                                        <th class="border-bottom-0">
                                            Full name
                                        </th>
                                        <th class="border-bottom-0">
                                            Address
                                        </th>
                                        <th class="border-bottom-0">
                                            Action
                                        </th>
                                    </tr>
                                </thead>
                                <tbody id="added-authors"></tbody>
                            </table>
                        </div>
                        <div class="shadow p-3 mb-3 bg-white rounded">
                            <h3>Publisher information</h3>
                            <div class="form-group row">
                                <label for="publisher" class="col-sm-5 h5 m-0 d-flex align-items-center">Name</label>
                                <div class="col-sm-7">
                                    <select class="custom-select" id="list-publishers">
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="publisher-address" class="col-sm-5 h5 m-0 d-flex align-items-center">Address</label>
                                <p class="col-sm-7 my-1" id="publisher-address"></p>
                            </div>
                            <div class="form-group row">
                                <label for="publisher-email" class="col-sm-5 h5 m-0 d-flex align-items-center">Email</label>
                                <p class="col-sm-7 my-1" id="publisher-email"></p>
                            </div>
                        </div>
                        <div class="btn btn-primary mb-2 float-right create-book-btn">Create book</div>
                    </div>
                </div>
            </div>
        </div>
        <script src="<%=request.getContextPath()%>/assets/js/jquery-3.5.1.min.js"></script>
        <script type="text/javascript">
            // AUTHOR
            var listAuthorIds = [];
            function deleteAuthorEvent() {
                $(".btn-delete-author").click(function() {
                    var addedId = $(this).closest("tr").find('td:eq(0)').text().trim();
                    if (listAuthorIds.includes(addedId)) {
                        listAuthorIds.splice(listAuthorIds.indexOf(addedId), 1);
                    }
                    $(this).closest("tr").remove();
//                        console.log(listAuthorIds);
                });
            }
            
            function addSearchResultHtml(result) {
                $("#search-result tr").remove();
                $("#search-result").append(result);
                $(".btn-add-author").click(function() {
                    var id = $(this).closest("tr").find('td:eq(0)').text().trim();
                    var fullName = $(this).closest("tr").find('td:eq(1)').text().trim();
                    var address = $(this).closest("tr").find('td:eq(2)').text().trim();
                    var html = '<tr>' +
                                    '<td class="align-middle">' + id + '</td>' +
                                    '<td class="align-middle">' + fullName + '</td>' +
                                    '<td class="align-middle">' + address + '</td>' +
                                    '<td class="align-middle">' +
                                        '<button type="button" class="btn-primary px-3 border-0 rounded btn-delete-author" style="box-shadow: none!important;">Delete</button>' +
                                    '</td>' +
                                '</tr>';
                    if (listAuthorIds.includes(id)) {
                        alert("The author have already been added");
                    } else if (!isNaN(id)) {
                        listAuthorIds.push(id);
    //                    console.log(listAuthorIds);
                        $("#added-authors").append(html);

                        deleteAuthorEvent();
                    } else {
                        alert("Error author id");
                    }
                });
            }
            
            $(".btn-search").click(function() {
                var authorName = $(".input-search").val().trim();
                if (authorName !== "") {
                    $.get("Search", {
                        "object": "author",
                        "value": authorName
                    }, function(result) {
//                        console.log(result);
                        addSearchResultHtml(result);
                    });
                } else {
                    alert("Enter author name to search");
                }
            });
            
            $(".input-search").keyup(function() {
                var authorName = $(".input-search").val();
                if (authorName.trim() === "") {
                    $("#search-result tr").remove();
                } 
            });
            
            $(".list-all-authors-btn").click(function() {
                $.get("Search", {
                    "object": "author",
                    "value": ""
                }, function(result) {
                    addSearchResultHtml(result);
                });
            });
            
            $(".new-author-btn").click(function() {
                $(".create-author-form").show();
                $("#author-full-name").focus();
            });
            
            $(".cancel-btn").click(function() {
                $(".create-author-form").attr("style", "display: none!important");
                $("#author-full-name").val("");
                $("#author-address").val("");
                $("#author-biography").val("");
            });
            
            $(".create-author-btn").click(function() {
                var fullName = $("#author-full-name").val().trim();
                var address = $("#author-address").val().trim();
                var biography = $("#author-biography").val().trim();
                console.log(fullName, address, biography);
                if (fullName === "" || address === "" || biography === "") {
                    alert("The information of the author can be empty");
                } else {
                    $.post("AuthorCreate", {
                        "fullName": fullName,
                        "address": address,
                        "biography": biography
                    }, function(result) {
                        alert(result);
                        $("#author-full-name").val("");
                        $("#author-address").val("");
                        $("#author-biography").val("");
                        $(".create-author-form").attr("style", "display: none!important");
                    });
                }
            })
            
            // PUBLISHER
            var publishers = [];
            <%  List<Publisher> listPublishers = (ArrayList<Publisher>) request.getAttribute("listPublishers");
                for (int i = 0; i < listPublishers.size(); i++) { %>
                    $('#list-publishers').append($('<option>', {
                        value: <%=listPublishers.get(i).getId()%>,
                        text: "<%=listPublishers.get(i).getName()%>" + " (ID - " + "<%=listPublishers.get(i).getId()%>" + ")"
                    }));
                    
                    publishers[<%=listPublishers.get(i).getId()%>] = {
                        "id": "<%=listPublishers.get(i).getId()%>",
                        "name": "<%=listPublishers.get(i).getName()%>",
                        "address": "<%=listPublishers.get(i).getAddress()%>",
                        "email": "<%=listPublishers.get(i).getEmail()%>"
                    };
            <%
                    if (i == 0) {
            %>
                        $('#publisher-address').text("<%=listPublishers.get(i).getAddress()%>");
                        $('#publisher-email').text("<%=listPublishers.get(i).getEmail()%>");
            <%
                    }
                }
            %>
            
            $('#list-publishers').on('change', function () {
                var index = $("#list-publishers").val();
                $('#publisher-email').text(publishers[index]["email"]);
                $('#publisher-address').text(publishers[index]["address"]);
            });
            
            // BOOK
            $(".create-book-btn").click(function() {
                var bookName = $("#book-name").val().trim();
                if (bookName === "") {
                    alert("Enter the name of the book");
                    return;
                }
                var bookSummary = $("#book-summary").val().trim();
                if (bookSummary === "") {
                    alert("Enter the summary of the book");
                    return;
                }
                var numberOfPages = $("#number-of-pages").val().trim();
                if (numberOfPages === "" || isNaN(numberOfPages)) {
                    alert("Enter the number of pages of the book");
                    return;
                }
                var bookLanguage = $("#book-language").val().trim();
                if (bookLanguage === "") {
                    alert("Select the language of the book");
                    return;
                }
                var isbn = $("#isbn").val().trim();
                if (isbn === "") {
                    alert("Enter the isbn of the book");
                    return;
                }

                if (listAuthorIds.length === 0) {
                    alert("Select authors");
                    return;
                }

                var publiserId = $("#list-publishers").val().trim();
                if (publiserId === "" || isNaN(publiserId)) {
                    alert("Select the publiser of the book");
                    return;
                }
                
                $.post("BookCreate", {
                    "action": "create",
                    "bookName": bookName,
                    "bookSummary": bookSummary,
                    "numberOfPages": numberOfPages,
                    "bookLanguage": bookLanguage,
                    "isbn": isbn,
                    "listAuthorIds": listAuthorIds.join(";"),
                    "publisherId": publiserId
                }, function(result) {
                    var direct = confirm("Result: " + result + "\nDo you want to move to book list ?");
                    if (direct === true) {
                        console.log(confirm)
                        document.location.href = "BookList";
                    } else {
                        return;
                    }
                });
            })
        </script>
    </body>
</html>

