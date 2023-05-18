<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp" %>
<!DOCTYPE html>

<html>
<head>
    <script type="text/javascript">
        window.onload = function () {
            document.querySelector("#search").addEventListener('click', function () {
                let form = document.querySelector("#frm");
                document.querySelector("#pageNo").value = '1'
                form.submit();
            })
            document.querySelector("#word").value = '${param.word}'

            <c:choose>
            <c:when test='${!empty param.pageNo}'>
            document.querySelector("#pageNo").value = '${param.pageNo}'
            </c:when>
            <c:otherwise>
            document.querySelector("#pageNo").value = '1'
            </c:otherwise>
            </c:choose>
            <c:if test='${!empty param.key}'>
            document.querySelector("#key").value = '${param.key}'
            </c:if>

            let pages = document.querySelectorAll(".page-link");
            pages.forEach(function (page) {
                page.addEventListener("click", function () {
                    document.querySelector("#pageNo").value = this.getAttribute("data-pg");
                    let form = document.querySelector("#frm");
                    form.setAttribute("action", "${root}/board/list");
                    form.submit();
                });
            });
        }
    </script>
</head>
<body>
<%@ include file="../common/SignIn.jsp" %>
<%@ include file="../common/nav.jsp" %>

<main id="main"> <!-- ======= Intro Single ======= -->
    <section class="intro-single">
        <div class="container">
            <div class="row">
                <div class="col-md-12 col-lg-8">
                    <div class="title-single-box">
                        <h1 class="title-single">여행 정보 게시판</h1>
                    </div>
                </div>
                <div class="col-md-12 col-lg-4">
                    <nav aria-label="breadcrumb"
                         class="breadcrumb-box d-flex justify-content-lg-end">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="${root}/index.jsp">Home</a></li>
                            <li class="breadcrumb-item"><a href="#">커뮤니티</a></li>
                            <li class="breadcrumb-item"><a href="#">여행 정보 게시판</a></li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </section>
    <!-- End Intro Single--> <!-- ======= Blog Single ======= -->
    <section class="news-single nav-arrow-b">
        <div class="container">
            <div
                    class="d-flex justify-content-end align-content-center p-2 m-auto">
                <button id="btn-write" type="button" class="btn btn-secondary">작성하기</button>
            </div>
            <div class="d-flex flex-column justify-content-end align-content-center p-2 m-auto">
                <div>
                    <form class="d-flex" id="frm" action="${root}/board/list" style="float:left">
                        <input type="hidden" name="pageNo" id="pageNo"/>
                        <div class="row">
                            <div class="col">
                                <select name="key"
                                        id="key" class="form-select form-select-sm"
                                        aria-label="조회조건">
                                    <option value="title" selected>제목</option>
                                    <option value="userId">아이디</option>
                                </select>
                            </div>
                            <div class="col">
                                <input type="text" class="form-control" placeholder="검색어 입력" name="word" id="word">
                            </div>
                            <div class="col">
                                <button id="search" class="btn btn-dark" type="button">조회</button>
                            </div>
                        </div>
                    </form>
                </div>
                <table class="table table-hover table-group-divider border-1">
                    <%--             <colgroup>
              <col width="5%">
              <col width="45%" href="/board/detail?userId=${userId}">
              <col width="20%">
              <col width="20%">
              <col width="5%">
              <col width="5%">
            </colgroup> --%>
                    <thead>
                    <tr class="text-center">
                        <th class="text-black">No.</th>
                        <th class="text-black">제목</th>
                        <th class="text-black">작성자</th>
                        <th class="text-black">날짜</th>
                        <th class="text-black">조회수</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="board" items="${boards}">
                        <tr class="text-center">
                            <th scope="row">${board.boardId}</th>
                            <td class="text-start">
                                <a href="${root}/board/view?boardId=${board.boardId}&pageNo=${pageBean.pageNo}&word=${pageBean.word}&key=${pageBean.key}"
                                   class="article-title link-dark"
                                   style="text-decoration: none">${board.title}</a>
                            </td>
                            <td>${board.userId}</td>
                            <td>${board.registerTime}</td>
                            <td>${board.hit}</td>

                        </tr>
                    </c:forEach>
                    </tbody>
                    <c:if test="${empty boards}">
                        <table class='table table-hover'>
                            <tr>
                                <td>게시글이 없습니다.</td>
                            </tr>
                        </table>
                    </c:if>
                </table>
            </div>
            <div class="row">
                <div class="col">
                    ${pageBean.pageLink}
                </div>
            </div>
        </div>
        </div>
    </section>
    <script>

        /*      document.querySelector("#btn-sort").addEventListener("click", function () {
                  console.log("sort called");
                  let form = document.querySelector("#form-sort");
                  form.setAttribute("action", "${root}/board");
          form.submit();
      });*/

        document.querySelector("#btn-write").addEventListener("click", function () {
            location.href = "${root}/board/write";
        });

    </script>
</main>
<!-- End #main -->

<%@ include file="../common/footer.jsp" %>

</body>
</html>