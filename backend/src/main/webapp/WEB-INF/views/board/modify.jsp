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

<div class="row justify-content-center" style="margin-top:200px">
    <div class="col-lg-8 col-md-10 col-sm-12">
        <form id="form-modify" method="POST" action="${root}/board/modify">
            <input type="hidden" name="boardId" value="${board.boardId}">
            <input type="hidden" name="userId" value="${board.userId}">
            <input type="hidden" name="hit" value="${board.hit}">
            <input type="hidden" name="registerTime" value="${board.registerTime}">
            <div class="mb-3">
                <label for="title" class="form-label">제목 : </label>
                <input type="text" class="form-control" id="title" name="title" value="${board.title}"/>
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">내용 : </label>
                <%-- <input type="text" class="form-control" id="content" name="content" value="${article.content}" style="height:200px"> --%>
                <textarea class="form-control" id="content" name="content" rows="7">${board.content}</textarea>

            </div>
            <div class="col-auto text-center">
                <button type="button" id="btn-modify" class="btn btn-outline-primary mb-3">
                    글수정
                </button>
                <button type="button" id="btn-list" class="btn btn-outline-danger mb-3">
                    목록으로이동...
                </button>
            </div>
        </form>
    </div>
</div>
<script>
    document.querySelector("#btn-modify").addEventListener("click", function () {
        if (!document.querySelector("#title").value) {
            alert("제목 입력!!");
            return;
        } else if (!document.querySelector("#content").value) {
            alert("내용 입력!!");
            return;
        } else {
            let form = document.querySelector("#form-modify");
            form.submit();
        }
    });
    document.querySelector("#btn-list").addEventListener("click", function () {
        location.href = "${root}/board/list";
    });
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