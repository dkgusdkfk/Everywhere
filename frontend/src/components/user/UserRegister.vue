<template>
  <div>
    <section class="intro-single">
    <div class="container">
      <div class="row">
        <div class="col-md-12 col-lg-8">
          <div class="title-single-box">
            <h1 class="title-single">회원 가입</h1>
          </div>
        </div>
        <div class="col-md-12 col-lg-4">
          <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active" aria-current="page"><a href="SignUp.jsp">Sign-Up</a></li>
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
        <form id="frm" action="${root}/user/regist" method="post">
          <div class="row">
            <div class="col-md-6 mb-3">
              <label for="userid">아이디</label>
              <input type="text" class="form-control" id="userid" name="id" placeholder="" value="" required>
            </div>
            <div class="col-md-6 mb-3">
              <label for="idcheck-result" class="col form-label"></label>
              <div class="col form-control me-3" style="background-color:lightgray" id="idcheck-result">아이디는 6자 이상 16자
                이하입니다.</div>
            </div>

          </div>
          <div class="row">
            <div class="col-md-3 mb-3">
              <label for="pw">비밀번호</label>
              <input type="password" class="form-control" id="pw" name="password" placeholder="비밀번호 입력" required />
            </div>
            <div class="col-md-3 mb-3">
              <label for="pwdcheck" class="col-3 form-label"></label>
              <input type="password" class="form-control" placeholder="비밀번호 확인" id="pwdcheck" required/>
            </div>
            <div class="col-md-6 mb-3">
              <label for="pwdcheck-result" class="col form-label"></label>
              <div class="col form-control me-3" style="background-color:lightgray" id="pwdcheck-result">비밀번호가 일치하지 않습니다.
              </div>
            </div>
          </div>
          <div class="mb-3">
            <label for="name">이름</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="이름" required>
          </div>
          <div class="mb-3">
            <label for="email">이메일</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="you@example.com" required>
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
          <button class="btn btn-primary btn-lg btn-block" id="registBtn" type="button">가입하기</button>
        </form>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
export default {
  name: "UserRegister",
};
</script>

<style></style>
