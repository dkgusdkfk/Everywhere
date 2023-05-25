<template>
    <b-container class="bv-example-row intro-single" id="main">
        <div>
            <div class="row">
                <div class="col-md-12 col-lg-8 w">
                    <div class="title-single-box">
                        <h1 class="title-single">{{ notice.title }}</h1>
                    </div>
                </div>
                <div class="col-md-12 col-lg-4">
                    <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item">
                                <a href="/">Home</a>
                            </li>
                            <li class="breadcrumb-item">
                                <a href="">커뮤니티</a>
                            </li>
                            <li class="breadcrumb-item">
                                <a href="/notice/list">공지사항</a>
                            </li>
                        </ol>
                    </nav>
                </div>
            </div>
            <div>
                <div class="post-information">
                    <ul class="list-inline text-center color-a d-flex justify-content-end" id="info">
                        <li class="list-inline-item mr-2">
                            <strong>작성자 : </strong>
                            <span class="color-text-a">{{ notice.adminId }}</span>
                        </li>
                        <li class="list-inline-item mr-2">
                            <strong>작성일 : </strong>
                            <span class="color-text-a">{{ notice.registerTime }}</span>
                        </li>
                        <li class="list-inline-item">
                            <strong>조회 : </strong>
                            <span class="color-text-a">{{ notice.hit }}</span>
                        </li>
                    </ul>
                </div>
            </div>

        </div>
        <div class="container">
            <div class="row d-flex ">
                <div class="col-sm-12 d-flex flex-column align-content-center justify-content-center w-75">
                    <div class="post-content color-text-a">
                        <pre class="mt-3">
                        {{ notice.content }}

                    </pre>
                    </div>
                    <div class="d-flex justify-content-end">
                        <b-button variant="outline-info" size="sm" @click="moveList" class="mr-2">목록</b-button>
                        <div v-if="userInfo != null && userInfo.id == notice.adminId">
                            <b-button variant="outline-primary" size="sm" @click="moveModifyNotice"
                                class="mr-2">글수정</b-button>
                            <b-button variant="outline-danger" size="sm" @click="deleteNotice">글삭제</b-button>
                        </div>
                    </div>
                    <div class="post-footer">
                        <div class="post-share d-flex justify-content-end">
                            <span>Share: </span>
                            <ul class="list-inline socials">
                                <li class="list-inline-item">
                                    <a href="#">
                                        <i class="bi bi-facebook" aria-hidden="true"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a href="#">
                                        <i class="bi bi-twitter" aria-hidden="true"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a href="#">
                                        <i class="bi bi-instagram" aria-hidden="true"></i>
                                    </a>
                                </li>
                                <li class="list-inline-item">
                                    <a href="#">
                                        <i class="bi bi-linkedin" aria-hidden="true"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </b-container>
</template>

<script>
// import moment from "moment";
import { mapState } from "vuex";
import http from "@/api/http";

const memberStore = "memberStore";

export default {
    name: "NoticeView",
    data() {
        return {
            notice: {},
        };
    },
    created() {
        http.get(`/notice/${this.$route.params.noticeId}`).then(({ data }) => {
            this.notice = data;
        });
    },
    computed: {
        message() {
            if (this.notice.content) return this.notice.content.split("\n").join("<br>");
            return "";
        },
        ...mapState(memberStore, ["userInfo"]),
    },
    methods: {
        moveModifyNotice() {
            this.$router.replace({
                name: "noticemodify",
                params: { noticeId: this.notice.noticeId },
            });
            //   this.$router.push({ path: `/notice/modify/${this.article.articleno}` });
        },
        deleteNotice() {
            if (confirm("정말로 삭제?")) {
                this.$router.replace({
                    name: "noticedelete",
                    params: { noticeId: this.notice.noticeId },
                });
            }
        },
        moveList() {
            this.$router.push({ name: "noticelist" });
        },
    },
    // filters: {
    //   dateFormat(regtime) {
    //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
    //   },
    // },
};
</script>

<style>
#main {
    text-align: left;
}

#info {
    text-align: right;
}
</style>