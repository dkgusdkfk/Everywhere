<template>
    <div>
        <section class="intro-single">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-lg-8">
                        <div class="title-single-box">
                            <h1 class="title-single">계정 상세 정보</h1>
                        </div>
                    </div>
                    <div class="col-md-12 col-lg-4">
                        <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item"><a href="#">My Page</a></li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Intro Single --> <!-- ======= Agent Single ======= -->
        <section class="agent-single">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="row align-content-center">
                            <div class="col-md-6 d-flex justify-content-center">
                                <img src="@/assets/img/defaultProfile.jpg" alt=""
                                    class="agent-avatar img-fluid rounded-circle">
                            </div>
                            <div
                                class="col-md-5 section-md-t3 d-flex flex-column align-content-center justify-content-center">
                                <div class="agent-info-box">
                                    <div class="agent-title">
                                        <div class="title-box-d"></div>
                                    </div>
                                    <div class="agent-content mb-3">
                                        <div class="info-agents color-a">
                                            <h4 class="title-2 mb-2">
                                                <strong>이름 : </strong> <span class="color-text-a">{{ userInfo.name }}</span>
                                            </h4>
                                            <h4 class="title-2 mt-4">
                                                <strong>ID: </strong> <span class="color-text-a">{{ userInfo.id }}</span>
                                            </h4>
                                            <h4 class="title-2 mt-4">
                                                <strong>Email: </strong> <span
                                                    class="color-text-a">{{ userInfo.email }}</span>
                                            </h4>
                                            <h4 class="title-2 mt-4">
                                                <strong>Address : </strong>
                                                <span class="color-text-a">{{ address.sidoName }} {{ address.gugunName }}
                                                </span>
                                            </h4>
                                        </div>
                                    </div>
                                    <div class="socials-footer">
                                        <ul class="list-inline">
                                            <li class="list-inline-item"><a href="https://www.facebook.com/"
                                                    class="link-one"> <i class="bi bi-facebook" aria-hidden="true"></i>
                                                </a></li>
                                            <li class="list-inline-item"><a href="https://twitter.com/?lang=ko"
                                                    class="link-one"> <i class="bi bi-twitter" aria-hidden="true"></i>
                                                </a></li>
                                            <li class="list-inline-item"><a href="https://www.instagram.com/"
                                                    class="link-one"> <i class="bi bi-instagram" aria-hidden="true"></i>
                                                </a></li>
                                            <li class="list-inline-item"><a href="https://kr.linkedin.com/"
                                                    class="link-one"> <i class="bi bi-linkedin" aria-hidden="true"></i>
                                                </a></li>
                                        </ul>
                                    </div>
                                    <div class="d-flex align-items-center justify-content-end">
                                        <a class="btn w-25 border border-2 rounded-3 text-bg-secondary" href="#"
                                            @click="moveModifyPage">수정하기</a>
                                        <a class="btn w-25 border border-2 rounded-3 text-bg-danger" href='#'
                                            @click="unregister">회원 탈퇴</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12 section-t8 mt-1 d-flex p-1 mt-5">
                        <div class="title-box-d">
                            <h3 class="title-d">내 여행 계획</h3>
                        </div>
                    </div>
                    <div class="row property-grid grid">
                        <div class="col-md-4" v-for="plan in plans" :key="plan.planId">
                            <div class="card-box-a card-shadow">
                                <div class="img-box-a">
                                    <img src="@/assets/img/logo.png" alt="" class="img-a img-fluid">
                                </div>
                                <div class="card-overlay">
                                    <div class="card-overlay-a-content">
                                        <div class="card-header-a">
                                            <h2 class="card-title-a">
                                                <a href="#">{{ plan.title }}
                                                </a>
                                            </h2>
                                        </div>
                                        <div class="card-body-a">
                                            <div class="price-box d-flex">
                                                <span class="price-a">rent | $ 12.000</span>
                                            </div>
                                            <a href="#" class="link-a">Click here to view <span
                                                    class="bi bi-chevron-right"></span>
                                            </a>
                                        </div>
                                        <div class="card-footer-a">
                                            <ul class="card-info d-flex justify-content-around">
                                                <li>
                                                    <h4 class="card-info-title">distance</h4> <span>{{ plan.distance
                                                    }}</span>
                                                </li>
                                                <li>
                                                    <h4 class="card-info-title">도보</h4> <span>{{ plan.walkTime }}분</span>
                                                </li>
                                                <li>
                                                    <h4 class="card-info-title">자전거</h4> <span>{{ plan.cycleTime }}</span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12 section-t8 mt-1 d-flex p-1 mt-5">
                            <div class="title-box-d">
                                <h3 class="title-d">보관함</h3>
                            </div>
                        </div>
                        <div class="row property-grid grid">
                            <div class="col-sm-12 d-flex justify-content-end align-content-center p-1">
                                <div class="grid-option">
                                    <form>
                                        <select class="custom-select">
                                            <option selected>All</option>
                                            <option value="1">지역별 여행지 추천</option>
                                            <option value="2">정보 게시판</option>
                                            <option value="3">후기 게시판</option>
                                        </select>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card-box-a card-shadow">
                                <div class="img-box-a">
                                    <img src="@/assets/img/logo.png" alt="" class="img-a img-fluid">
                                </div>
                                <div class="card-overlay">
                                    <div class="card-overlay-a-content">
                                        <div class="card-header-a">
                                            <h2 class="card-title-a">
                                                <a href="#">204 Mount <br /> Olive Road Two
                                                </a>
                                            </h2>
                                        </div>
                                        <div class="card-body-a">
                                            <div class="price-box d-flex">
                                                <span class="price-a">rent | $ 12.000</span>
                                            </div>
                                            <a href="#" class="link-a">Click here to view <span
                                                    class="bi bi-chevron-right"></span>
                                            </a>
                                        </div>
                                        <div class="card-footer-a">
                                            <ul class="card-info d-flex justify-content-around">
                                                <li>
                                                    <h4 class="card-info-title">Area</h4> <span>340m <sup>2</sup>
                                                    </span>
                                                </li>
                                                <li>
                                                    <h4 class="card-info-title">Beds</h4> <span>2</span>
                                                </li>
                                                <li>
                                                    <h4 class="card-info-title">Baths</h4> <span>4</span>
                                                </li>
                                                <li>
                                                    <h4 class="card-info-title">Garages</h4> <span>1</span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card-box-a card-shadow">
                                <div class="img-box-a">
                                    <img src="@/assets/img/logo.png" alt="" class="img-a img-fluid">
                                </div>
                                <div class="card-overlay">
                                    <div class="card-overlay-a-content">
                                        <div class="card-header-a">
                                            <h2 class="card-title-a">
                                                <a href="#">204 Mount <br /> Olive Road Two
                                                </a>
                                            </h2>
                                        </div>
                                        <div class="card-body-a">
                                            <div class="price-box d-flex">
                                                <span class="price-a">rent | $ 12.000</span>
                                            </div>
                                            <a href="#" class="link-a">Click here to view <span
                                                    class="bi bi-chevron-right"></span>
                                            </a>
                                        </div>
                                        <div class="card-footer-a">
                                            <ul class="card-info d-flex justify-content-around">
                                                <li>
                                                    <h4 class="card-info-title">Area</h4> <span>340m <sup>2</sup>
                                                    </span>
                                                </li>
                                                <li>
                                                    <h4 class="card-info-title">Beds</h4> <span>2</span>
                                                </li>
                                                <li>
                                                    <h4 class="card-info-title">Baths</h4> <span>4</span>
                                                </li>
                                                <li>
                                                    <h4 class="card-info-title">Garages</h4> <span>1</span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card-box-a card-shadow">
                                <div class="img-box-a">
                                    <img src="@/assets/img/logo.png" alt="" class="img-a img-fluid">
                                </div>
                                <div class="card-overlay">
                                    <div class="card-overlay-a-content">
                                        <div class="card-header-a">
                                            <h2 class="card-title-a">
                                                <a href="#">204 Mount <br /> Olive Road Two
                                                </a>
                                            </h2>
                                        </div>
                                        <div class="card-body-a">
                                            <div class="price-box d-flex">
                                                <span class="price-a">rent | $ 12.000</span>
                                            </div>
                                            <a href="#" class="link-a">Click here to view <span
                                                    class="bi bi-chevron-right"></span>
                                            </a>
                                        </div>
                                        <div class="card-footer-a">
                                            <ul class="card-info d-flex justify-content-around">
                                                <li>
                                                    <h4 class="card-info-title">Area</h4> <span>340m <sup>2</sup>
                                                    </span>
                                                </li>
                                                <li>
                                                    <h4 class="card-info-title">Beds</h4> <span>2</span>
                                                </li>
                                                <li>
                                                    <h4 class="card-info-title">Baths</h4> <span>4</span>
                                                </li>
                                                <li>
                                                    <h4 class="card-info-title">Garages</h4> <span>1</span>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import http from "@/api/http"

const memberStore = "memberStore";

export default {
    name: 'UserMyPage',
    data() {
        return {
            address: {
                sidoName: null,
                gugunName: null,
            },
            plans: []
        }
    },
    components: {},
    created() {
        http.get(`/trip/getAddress/${this.userInfo.address1}/${this.userInfo.address2}`).then(({ data }) => {
            this.address.sidoName = data.sidoName;
            this.address.gugunName = data.gugunName;
        });
        http.get(`/trip/plan/all`).then(({ data }) => {
            this.plans = data;
        })
    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    methods: {
        ...mapActions(memberStore, ["userUnregist"]),

        moveModifyPage() {
            this.$router.replace({
                name: "usermodify",
            })
        },
        unregister() {
            this.userUnregist(this.userInfo.id);
            sessionStorage.removeItem("access-token");
            sessionStorage.removeItem("refresh0token");
            if (this.$route.path != "/") this.$router.push({ name: "main" });
        }
    },
};
</script>

<style scoped></style>