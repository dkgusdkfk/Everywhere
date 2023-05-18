<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<nav class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top">
    <div class="container">
        <a class="navbar-brand text-brand" href="${root}/index">
            <span class="color-a">Enjoy</span><span class="color-b">Trip</span>
        </a>

        <ul class="navbar-nav me-5 ms-5 d-flex w-75 align-items-center justify-content-center ">
            <li class="nav-item dropdown ms-5 me-5">
                <a class="nav-link" href="${root}/trip/recommend" aria-haspopup="true" aria-expanded="false">지역별 여행지
                    추천</a>
            </li>

            <li class="nav-item dropdown me-5 ms-5">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">커뮤니티</a>
                <div class="dropdown-menu">
                    <a class="dropdown-item " href="${root}/board/list">게시판</a>
                    <a class="dropdown-item " href="${root}/trip/hotPlace">Hot Place</a>
                </div>
            </li>
        </ul>

        <button type="button"
                class="btn btn-b-n navbar-toggle-box navbar-toggle-box-collapse"
                data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01">
            <i class="bi bi-person-circle"></i>
        </button>

    </div>
</nav>