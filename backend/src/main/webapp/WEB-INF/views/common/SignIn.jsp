<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--/ Form Search Star /-->
<c:choose>
    <c:when test="${empty userInfo}">
        <div class="box-collapse w-25">
            <div class="title-box-d">
                <h3 class="title-d">Sign In</h3>
            </div>
            <span class="close-box-collapse right-boxed bi bi-x"></span>
            <div class="box-collapse-wrap form">
                <form class="form-a" method="POST" action="${root}/user/login">
                    <input type="hidden" name="action" value="login"/>
                    <input type="hidden" name="referer" value="${referer}">
                    <div class="row border border-1 p-4 rounded-4">
                        <div class="col-md-12 mb-2">
                            <div class="form-group">
                                <label class="pb-2" for="id">ID</label>
                                <input type="text" class="form-control form-control-lg form-control-a" id="id"
                                       name="id" value="${cookie.saveId.value}" placeholder="ID"/>
                            </div>
                        </div>
                        <div class="col-md-12 mb-2">
                            <div class="form-group">
                                <label class="pb-2" for="password">Password</label>
                                <input type="password" class="form-control form-control-lg form-control-a" id="password"
                                       name="password" placeholder="Password"/>
                            </div>
                        </div>
                        <div class="d-flex flex-column align-items-center justify-content-center">
                            <button type="submit" id="btn-login"
                                    class="btn btn-b w-100 border border-2 rounded-4 text-light mt-3">
                                <strong>로그인</strong>
                            </button>
                        </div>
                    </div>
                    <div class="d-flex align-content-center justify-content-center">
                        <a href="${root}/user/findPassword" class="ms-5 me-5 mt-3 md-3 text-black-50">
                            ID/Password 찾기</a>
                        <a href="${root}/user/regist" class="ms-5 me-5 mt-3 md-3 text-black-50">회원가입</a>
                    </div>
                </form>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="box-collapse w-25">
            <form class="form-a" method="GET" action="${root}/user/detail?id=${userinfo.id}">
                <input type="hidden" name="id" value="${userInfo.id}"/>
                <div class="title-box-d">
                    <h3 class="title-d">내 정보</h3>
                </div>
                <span class="close-box-collapse right-boxed bi bi-x"></span>
                <div class="box-collapse-wrap form">
                    <div class="row border border-1 p-4 rounded-4">
                        <div class="col-md-12 mb-2">
                            <div class="form-group">
                                <strong>${userInfo.name}</strong> (${userInfo.id})님 안녕하세요.
                            </div>
                        </div>
                        <div class="d-flex flex-column align-items-center justify-content-center">
                            <button type="submit" id="btn-login"
                                    class="btn btn-b w-100 border border-2 rounded-4 text-light mt-3">
                                <strong>My page</strong>
                            </button>
                        </div>
                    </div>
                    <div class="d-flex align-content-center justify-content-center">
                        <a href="${root}/user/logout" class="ms-5 me-5 mt-3 md-3 text-black-50">로그아웃</a>
                    </div>
                </div>
            </form>
        </div>
    </c:otherwise>
</c:choose>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>