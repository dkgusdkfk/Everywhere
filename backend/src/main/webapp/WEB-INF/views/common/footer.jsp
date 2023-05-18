<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="section-footer">
	<div class="container">
		<div style="margin: 0% 1% 2% 45%;">
			<h3 class="w-title-a text-brand"
				style="color: #2eca6a; text-decoration-line: underline;">Site
				Map</h3>
		</div>
	</div>
	<div class="container">
		<div class="row" style="text-align: center;">
			<div class="col-sm-12 col-md-4 section-md-t3">
				<div class="widget-a">
					<div class="w-header-a">
						<p style="font-size: x-large;">
							<b>여행지 찾기</b>
						</p>
					</div>
					<div class="w-body-a">
						<div class="w-body-a">
							<ul class="list-unstyled">
								<li class="item-list-a"><i class="bi bi-chevron-right"></i>
									<a href="${root}/trip/recommend">지역별 여행지 추천</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 col-md-4 section-md-t3">
				<div class="widget-a">
					<div class="w-header-a">
						<p style="font-size: x-large;">
							<b>커뮤니티</b>
						</p>
					</div>
					<div class="w-body-a">
						<ul class="list-unstyled">
							<li class="item-list-a"><i class="bi bi-chevron-right"></i>
								<a href="${root}/trip/hotPlace">핫플레이스</a></li>
							<li class="item-list-a"><i class="bi bi-chevron-right"></i>
								<a href="${root}/board/list">게시판</a></li>
						</ul>
					</div>
				</div>
			</div>
			<c:choose>
				<c:when test="${not empty userInfo}">
					<div class="col-sm-12 col-md-4 section-md-t3">
						<div class="widget-a">
							<div class="w-header-a">
								<p style="font-size: x-large;">
									<b>마이페이지</b>
								</p>
							</div>
							<div class="w-body-a">
								<ul class="list-unstyled">
									<!--<li class="item-list-a"><i class="bi bi-chevron-right"></i>
										<a href="#">나만의 여행 계획</a></li>-->
									<li class="item-list-a"><i class="bi bi-chevron-right"></i>
										<a href="user/mypage">마이페이지</a></li>
								</ul>
							</div>
						</div>
					</div>
				</c:when>
			</c:choose>
		</div>
	</div>
</section>
<footer>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="copyright-footer">
					<p class="copyright color-text-a">
						&copy; Copyright <span class="color-a">SSAFY</span> All Rights
						Reserved.
					</p>
				</div>
			</div>
		</div>
	</div>
</footer>
<!-- End  Footer -->

<div id="preloader"></div>
<a href="#"
	class="back-to-top d-flex align-items-center justify-content-center"><i
	class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="${root}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${root}/assets/vendor/swiper/swiper-bundle.min.js"></script>
<script src="${root}/assets/vendor/php-email-form/validate.js"></script>

<!-- Template Main JS File -->
<script src="${root}/assets/js/main.js"></script>
</html>