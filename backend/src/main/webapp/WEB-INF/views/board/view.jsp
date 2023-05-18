<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>EstateAgency Bootstrap Template - Index</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Favicons -->
    <link href="${root}/assets/img/favicon.png" rel="icon">
    <link href="${root}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="${root}/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
    <link href="${root}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${root}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="${root}/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="${root}/assets/css/style.css" rel="stylesheet">

    <!-- =======================================================
    * Template Name: EstateAgency
    * Updated: Mar 10 2023 with Bootstrap v5.2.3
    * Template URL: https://bootstrapmade.com/real-estate-agency-bootstrap-template/
    * Author: BootstrapMade.com
    * License: https://bootstrapmade.com/license/
    ======================================================== -->
</head>

<body>

<%@ include file="../common/SignIn.jsp" %>
<%@ include file="../common/nav.jsp" %>


<main id="main">

    <!-- ======= Intro Single ======= -->
    <section class="intro-single">
        <div class="container w-75">
            <div class="row w-75">
                <div class="col-md-12 col-lg-8 w">
                    <div class="title-single-box">
                        <h1 class="title-single">${board.title}</h1>
                    </div>
                </div>
                <div class="col-md-12 col-lg-4">
                    <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item">
                                <a href="index.html">Home</a>
                            </li>
                            <li class="breadcrumb-item">
                                <a href="#">커뮤니티</a>
                            </li>
                            <li class="breadcrumb-item">
                                <a href="#">여행 정보 게시판</a>
                            </li>
                        </ol>
                    </nav>
                </div>
            </div>
            <div class="d-flex justify-content-center">
                <div class="post-information w-75">
                    <ul class="list-inline text-center color-a d-flex justify-content-end">
                        <li class="list-inline-item mr-2">
                            <strong>작성자 : </strong>
                            <span class="color-text-a">${board.userId}</span>
                        </li>
                        <li class="list-inline-item mr-2">
                            <strong>작성일 : </strong>
                            <span class="color-text-a">${board.registerTime}</span>
                        </li>
                        <li class="list-inline-item">
                            <strong>조회 : </strong>
                            <span class="color-text-a">${board.hit}</span>
                        </li>
                        <li class="list-inline-item">
                            <strong>댓글 : </strong>
                            <span class="color-text-a">4</span>
                        </li>
                    </ul>
                </div>
            </div>

        </div>
    </section><!-- End Intro Single-->

    <!-- ======= Blog Single ======= -->
    <section class="news-single nav-arrow-b">
        <div class="container">
            <div class="row d-flex justify-content-center">
                <div class="col-sm-12 d-flex flex-column align-content-center justify-content-center w-75">
                    <div class="post-content color-text-a">
              <pre class="mt-3">
                  ${board.content}

              </pre>
                    </div>
                    <div class="d-flex justify-content-end">
                        <button type="button" id="btn-list" class="btn btn-outline-primary mb-3">
                            글목록
                        </button>
                        <c:if test="${userInfo.id eq board.userId}">
                            <button type="button" id="btn-mv-modify" class="btn btn-outline-success mb-3 ms-1">
                                글수정
                            </button>
                            <button type="button" id="btn-delete" class="btn btn-outline-danger mb-3 ms-1">
                                글삭제
                            </button>
                        </c:if>
                    </div>
                    <div class="post-footer">
                        <div class="post-share d-flex justify-content-end">
                            <span>Share: </span>
                            <ul class="list-inline socials">
                                <li class="list-inline-item">
                                    <a href="#">
                                        <i class="bi bi-facebook" aria-hidden="true"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a href="#">
                                        <i class="bi bi-twitter" aria-hidden="true"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a href="#">
                                        <i class="bi bi-instagram" aria-hidden="true"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a href="#">
                                        <i class="bi bi-linkedin" aria-hidden="true"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-10 w-75">
                    <div class="title-box-d">
                        <h3 class="title-d">Comments</h3>
                    </div>
                    <div class="box-comments">
                        <table class="table table-hover">
                            <colgroup>
                                <col width="90%">
                            </colgroup>
                            <tbody>
                            <c:forEach var="comment" items="${comments}">
                            <tr>
                                <div class="d-flex">
                                    <div class="d-flex flex-column justify-content-center align-items-start">
                                        <div class="d-flex">
                                            <div class="d-flex flex-column justify-content-center align-items-start">
                                                <span><h4 class="ms-1 mt-0 mb-0">${comment.userId}</h4></span><span class="ms-1 mt-0 mb-0">${comment.registerTime}</span>
                                                <div style="padding: 5px" id="cmt">
                                                    <pre class="ms-1 mt-0 mb-0" style="font-size: 20px">${comment.content}</pre>
                                                </div>
                                                <div style="padding: 5px" id="cmtUpdate" hidden>
                                                    <textarea id="content_update" class="ms-1 mt-0 mb-0"
                                                              name="content" cols="45" style="border: 0; font-family: var(--bs-font-monospace); color: #555555; font-size: 20px;"
                                                              rows="5" required>${comment.content}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <c:if test="${userInfo.id eq comment.userId}">
                                            <div>
                                                <button type="button" id="btn-mv-modify-comment"
                                                        class="btn btn-outline-success mb-3 ms-1" hidden>
                                                    댓글수정
                                                </button>
                                                <button type="button" id="btn-delete-comment"
                                                        class="btn btn-outline-danger mb-3 ms-1"
                                                        onclick="commentDelete(${comment.boardId}, ${comment.commentId})">
                                                    댓글삭제
                                                </button>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
            <div class="form-comments">
                <div class="title-box-d">
                    <h3 class="title-d"> Leave a Reply</h3>
                </div>
                <form class="form-a" action="${root}/board/writeComment" method="post">
                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <div class="form-group">
                                <input type="hidden" id="userId" value="${userInfo.id}" name="userId">
                                <input type="hidden" id="boardId" value="${board.boardId}" name="boardId">
                                <label for="content">댓글을 입력하세요.</label>
                                <textarea id="content" class="form-control" placeholder="Comment *"
                                          name="content" cols="45"
                                          rows="8" required></textarea>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <button type="submit" class="btn btn-a">댓글 달기</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        </div>
        </div>
    </section><!-- End Blog Single-->

</main><!-- End #main -->

<script>
    document.querySelector("#btn-list").addEventListener("click", function () {
        location.href = "${root}/board/list";
    });
    document.querySelector("#btn-mv-modify").addEventListener("click", function () {
        alert("글 수정 ");
        location.href = "${root}/board/modify?boardId=" + ${board.boardId};
    });
    document.querySelector("#btn-delete").addEventListener("click", function () {
        alert("글이 삭제되었습니다.");
        location.href = "${root}/board/delete?boardId=" + ${board.boardId};
    });
    /*    document.querySelector("#btn-mv-modify-comment").addEventListener("click", function () {
            alert("댓글 수정 ");
            location.href = "${root}/board/modifyComment?boardId=" + ${comment.boardId};
    });
    document.querySelector("#btn-delete-comment").addEventListener("click", function () {
        // alert("댓글이 삭제되었습니다.");
        alert(this.href)
        <%--location.href = "${root}/board/deleteComment?boardId=" + ${comment.boardId} + "&commentId=" + ${comment.commentId};--%>
    });*/
    function commentUpdate(boardId, commentId) {

    }
    function commentDelete(boardId, commentId) {
        location.href = "${root}/board/deleteComment?boardId=" + boardId + "&commentId=" + commentId;
    }

</script>

<!-- ======= Footer ======= -->
<section class="section-footer">
    <div class="container">
        <div style="margin: 0% 1% 2% 45%;">
            <h3 class="w-title-a text-brand" style="color :#2eca6a; text-decoration-line: underline;">Site Map</h3>
        </div>
    </div>
    <div class="container">
        <div class="row" style="text-align: center;">
            <div class="col-sm-12 col-md-4 section-md-t3">
                <div class="widget-a">
                    <div class="w-header-a">
                        <p style="font-size: x-large;"><b>여행지 찾기</b></p>
                    </div>
                    <div class="w-body-a">
                        <div class="w-body-a">
                            <ul class="list-unstyled">
                                <li class="item-list-a">
                                    <i class="bi bi-chevron-right"></i> <a href="recommendByLocation.html">지역별 관광지
                                    조회</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-4 section-md-t3">
                <div class="widget-a">
                    <div class="w-header-a">
                        <p style="font-size: x-large;"><b>커뮤니티</b></p>
                    </div>
                    <div class="w-body-a">
                        <ul class="list-unstyled">
                            <li class="item-list-a">
                                <i class="bi bi-chevron-right"></i> <a href="#">핫플 등록</a>
                            </li>
                            <li class="item-list-a">
                                <i class="bi bi-chevron-right"></i> <a href="#">게시판</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-4 section-md-t3">
                <div class="widget-a">
                    <div class="w-header-a">
                        <p style="font-size: x-large;"><b>마이페이지</b></p>
                    </div>
                    <div class="w-body-a">
                        <ul class="list-unstyled">
                            <li class="item-list-a">
                                <i class="bi bi-chevron-right"></i> <a href="#">나만의 여행 계획</a>
                            </li>
                            <li class="item-list-a">
                                <i class="bi bi-chevron-right"></i> <a href="#">회원 정보 수정/탈퇴</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="socials-a">
                    <ul class="list-inline">
                        <li class="list-inline-item">
                            <a href="#">
                                <i class="bi bi-facebook" aria-hidden="true"></i>
                            </a>
                        </li>
                        <li class="list-inline-item">
                            <a href="#">
                                <i class="bi bi-twitter" aria-hidden="true"></i>
                            </a>
                        </li>
                        <li class="list-inline-item">
                            <a href="#">
                                <i class="bi bi-instagram" aria-hidden="true"></i>
                            </a>
                        </li>
                        <li class="list-inline-item">
                            <a href="#">
                                <i class="bi bi-linkedin" aria-hidden="true"></i>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="copyright-footer">
                    <p class="copyright color-text-a">
                        &copy; Copyright
                        <span class="color-a">SSAFY</span> All Rights Reserved.
                    </p>
                </div>
            </div>
        </div>
    </div>
</footer><!-- End  Footer -->

<%--<div id="preloader"></div>--%>
<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
        class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="${root}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${root}/assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="${root}/assets/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="${root}/assets/js/main.js"></script>

</body>

</html>