<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>

<body>
	<%@ include file="../common/SignIn.jsp"%>
	<%@ include file="../common/nav.jsp"%>

	<main id="main"> <!-- ======= Intro Single ======= -->
	<section class="intro-single">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-8">
					<div class="title-single-box">
						<h1 class="title-single">비밀번호 찾기</h1>
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page"><a
								href="user/findPwForm">Find Password</a></li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Intro Single--> <!-- ======= Property Grid ======= --> <c:choose>
		<c:when test="${empty findUser}">
			<div class="container">
				<div class="input-form-backgroud row">
					<div class="input-form col-md-12 mx-auto">
						<form action="${root}/user/findPassword" method="post">
							<div class="mb-3">
								<label for="id">아이디</label> <input type="text"
									class="form-control" id="id" name="id"
									placeholder="id를 입력해주세요" required>
								<div class="invalid-feedback">id를 입력해주세요.</div>
							</div>
							<div class="mb-3">
								<label for="email">이메일</label> <input type="email"
									class="form-control" id="email" name="email"
									placeholder="${findUser.email}" required>
								<div class="invalid-feedback">이메일을 입력해주세요.</div>
							</div>
							<button class="btn btn-primary btn-lg btn-block" type="submit">확인하기</button>
						</form>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="container">
				<div class="input-form-backgroud row">
					<div class="input-form col-md-12 mx-auto">
						<h4 class="title-2 mt-4">
							<strong>ID: </strong> <span class="color-text-a">${findUser.id}</span>
						</h4>
						<h4 class="title-2 mt-4">
							<strong>PW: </strong> <span class="color-text-a">${findUser.password}</span>
						</h4>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose> </main>
	<!-- End #main -->

	<%@ include file="../common/footer.jsp"%>

</body>

</html>