<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>

<body>
	<%@ include file="../common/SignIn.jsp"%>
	<%@ include file="../common/nav.jsp"%>

	<div class="container p-4">
		오류가 발생했습니다.
		<div>
			<a href="${root}/index" class='btn btn-secondary'>메인 화면으로 이동</a>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>