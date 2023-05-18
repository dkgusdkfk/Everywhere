<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../common/header.jsp"%>
<!DOCTYPE html>
<html lang="en">

<body>
	<%@ include file="../common/SignIn.jsp"%>
	<%@ include file="../common/nav.jsp"%>


	<main id="main"> <!-- =======Intro Single ======= -->
	<section class="intro-single">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-lg-8">
					<div class="title-single-box">
						<h1 class="title-single">Hot Place</h1>
						<span class="color-text-a">숨겨진 핫 플레이스에 방문해보세요!</span>
					</div>
				</div>
				<div class="col-md-12 col-lg-4">
					<nav aria-label="breadcrumb"
						class="breadcrumb-box d-flex justify-content-lg-end">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${root}/index.jsp">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">커뮤니티
							</li>
							<li class="breadcrumb-item active" aria-current="page">Hot
								Place</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</section>
	<!-- End Intro Single--> <!-- ======= Agents Grid ======= -->
	<section class="agents-grid grid">
		<div class="container">
			<div class="row">
				<c:forEach var="place" items="${places}">
					<div class="col-md-4">
						<div class="card-box-d">
							<div class="card-img-d">
								<c:choose>
									<c:when test="${empty place.imgPath}">
										<img src="${root}/assets/img/noimg.gif" alt="" class="img-d img-fluid" style="width:500px; height:250px">
									</c:when>
									<c:otherwise>
										<img src="${place.imgPath}" alt="" class="img-d img-fluid">
									</c:otherwise>
								</c:choose>
							</div>
							<div class=" card-overlay card-overlay-hover">
								<div class="card-header-d">
									<div class="card-title-d align-self-center">
										<h3 class="title-d">
											<a href="#" class="link-two"> ${place.title} 
										</h3>
									</div>
								</div>
								<div class="card-body-d">
									<div class="info-agents color-a">
										<p>
											<strong>주소 : </strong> ${place.address1} ${place.address2}
										</p>
										<p>
											<strong>추천수 : </strong> ${place.count}
										</p>
									</div>
								</div>
								<div class="card-footer-d">
									<div class="socials-footer d-flex justify-content-center">
										<ul class="list-inline">
											<li class="list-inline-item"><a href="#" onclick="increaseLikeCount(${place.contentId})" class="link-one">
													<i class="bi bi-heart-fill"
													aria-hidden="true"></i>
											</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="row">
					<div class="col-sm-12">
						<nav class="pagination-a">
							<ul class="pagination justify-content-end">
								<li class="page-item disabled"><a class="page-link"
									href="#" tabindex="-1"> <span class="bi bi-chevron-left"></span>
								</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item active"><a class="page-link" href="#">2</a>
								</li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item next"><a class="page-link" href="#">
										<span class="bi bi-chevron-right"></span>
								</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End Agents Grid-->
	</main>
	<!-- End #main -->

	<%@ include file="../common/footer.jsp"%>

	<script>
		function increaseLikeCount(id) {
			console.log(id)
			url = "${root}/trip/hotRegist?id=" + id;
			fetch(url)
		}
	</script>

</body>
</html>