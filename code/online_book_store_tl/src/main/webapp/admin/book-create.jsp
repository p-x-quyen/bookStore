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
                        <div class="shadow p-3 mb-5 bg-white rounded">
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
                                <button type="button" class="btn-primary px-3 border-0 rounded create-author-btn ml-1" style="box-shadow: none!important;">
                                    New author
                                </button>
                            </div>
                            <div class="d-flex flex-column mb-2 create-author-form" style="display: none!important">
                                <div class="form-group row">
                                    <label for="full-name" class="col-sm-5 h5 m-0 d-flex align-items-center">Full name</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="full-name" name="full-name">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="address" class="col-sm-5 h5 m-0 d-flex align-items-center">Address</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="address" name="address">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="biography" class="col-sm-5 h5 m-0 d-flex align-items-center">Biography</label>
                                    <div class="col-sm-7">
                                        <input type="text" class="form-control" id="biography" name="biography">
                                    </div>
                                </div>
                                <div>
                                    <button type="button" class="btn-primary px-3 border-0 rounded cancel-btn ml-1 float-right" style="box-shadow: none!important;">
                                        Cancel
                                    </button>
                                    <button type="button" class="btn-primary px-3 border-0 rounded confirm-btn ml-1 float-right" style="box-shadow: none!important;">
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
                        <button class="btn btn-primary mb-2 float-right">Create book</button>
                    </div>
                </div>
            </div>
        </div>
        <script src="<%=request.getContextPath()%>/assets/js/jquery-3.5.1.min.js"></script>
        <script type="text/javascript">
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
            
            var listAuthorIds = [];
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
            
            $(".create-author-btn").click(function() {
                $(".create-author-form").show();
            });
            
            $(".cancel-btn").click(function() {
                $(".create-author-form").attr("style", "display: none!important");
            });
        </script>
    </body>
</html>

