<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../common/header.jsp" %>

<body>
<%@ include file="../common/SignIn.jsp" %>
<%@ include file="../common/nav.jsp" %>

<!-- ======= Property Search Section ======= -->
<div class="click-closed"></div>

<main id="main"> <!-- ======= Intro Single ======= -->
    <section class="intro-single">
        <div class="container">
            <div class="row">
                <div class="col-md-12 col-lg-8">
                    <div class="title-single-box">
                        <h1 class="title-single">회원 정보 수정</h1>
                    </div>
                </div>
                <div class="col-md-12 col-lg-4">
                    <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">
                                <a href="MyPage.html">My Page</a></li>
                            <li class="breadcrumb-item active" aria-current="page">
                                <a href="UserInfoUpdate.html">Update Profile</a></li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </section>
    <!-- End Intro Single--> <!-- ======= Property Grid ======= -->
    <div class="container">
        <div class="input-form-backgroud row">
            <div class="input-form col-md-12 mx-auto">
                <form action="${root}/user/update" method="post">
                    <div class="mb-3">
                        <label for="id">id</label>
                        <input type="text" class="form-control" id="id" name="id" value="${userInfo.id}" readonly
                               required>
                        <div class="invalid-feedback">비밀번호를 입력해주세요.</div>
                    </div>
                    <div class="mb-3">
                        <label for="password">새 비밀번호</label>
                        <input type="text" class="form-control" id="password" name="password" placeholder="새 비밀번호"
                               required>
                        <div class="invalid-feedback">비밀번호를 입력해주세요.</div>
                    </div>
                    <div class="mb-3">
                        <label for="pwCheck">비밀번호 확인</label>
                        <input type="text" class="form-control" id="pwCheck" name="pwCheck" placeholder="비밀번호 확인"
                               required>
                        <div class="invalid-feedback">비밀번호를 입력해주세요.</div>
                    </div>
                    <div class="mb-3">
                        <label for="email">이름</label>
                        <input type="text" class="form-control" id="name" name="name" value="${userInfo.name}" readonly
                               required>
                    </div>
                    <div class="mb-3">
                        <label for="email">이메일</label>
                        <input type="email" class="form-control" id="email" name="email" value="${userInfo.email}"
                               required>
                        <div class="invalid-feedback">이메일을 입력해주세요.</div>
                    </div>
                    <div class="mb-3">
                        <label for="address1">주소</label>
                        <select id="address1" name="address1" class="form-select me-2 w-100">
                            <option value="0" selected>지역 선택</option>
                            <c:forEach items="${sidoList}" var="sido">
                                <option value="${sido.sidoCode}">${sido.sidoName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="address2">상세주소</label>
                        <select id="address2" name="address2" class="form-select me-2 w-100">
                            <option value="0" selected>상세 지역 선택</option>
                        </select>
                    </div>
                    <button class="btn btn-primary btn-lg btn-block" type="submit">수정하기</button>
                </form>
            </div>
        </div>
    </div>
    <script>
        document.getElementById("address1").addEventListener("change", () => {

            let searchUrl = "http://localhost:80${root}/trip/gugun";
            let sidoCode = document.getElementById("address1").value;
            searchUrl += "?sidoCode=" + sidoCode;

            fetch(searchUrl)
                .then((response) => response.json())
                .then((data) => {
                    let html = "";
                    data.gugunList.forEach(item => {
                        html += "<option value=" + item.gugunCode + ">" + item.gugunName + "</option>";
                    })

                    document.getElementById("address2").innerHTML = html;
                });
        })
    </script>

</main>
<!-- End #main -->

<%@ include file="../common/footer.jsp" %>

</body>

</html>