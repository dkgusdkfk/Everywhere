<template>
    <div>
        <!-- <div v-if="${empty userInfo}"> -->
            <div class="w-25">
                <div class="title-box-d">
                    <h3 class="title-d">Sign In</h3>
                </div>
                <div>
                    <b-form>
                        <div class="row border border-1 p-4 rounded-4">
                            <div class="col-md-12 mb-2">
                                <div class="form-group">
                                    <label class="pb-2" for="id">ID</label>
                                    <b-form-input
                                                id="id" v-model="user.id" placeholder="ID" required @keyup.enter="confirm"></b-form-input>
                                </div>
                            </div>
                            <div class="col-md-12 mb-2">
                                <div class="form-group">
                                    <label class="pb-2" for="password">Password</label>
                                    <b-form-input
                                                id="password" v-model="user.password" placeholder="Password" required @keyup.enter="confirm"></b-form-input>
                                </div>
                            </div>
                            <div class="d-flex flex-column align-items-center justify-content-center">
                                <b-button type="button" id="btn-login"
                                    class="btn btn-b w-100 border border-2 rounded-4 text-light mt-3" @click="confirm">
                                    <strong>로그인</strong>
                                </b-button>
                            </div>
                        </div>
                        <div class="d-flex align-content-center justify-content-center">
                            <b-button class="ms-5 me-5 mt-3 md-3 text-black-50" @click="findPW">
                                ID/Password 찾기</b-button>
                            <b-button class="ms-5 me-5 mt-3 md-3 text-black-50" @click="moveRegistPage">회원가입</b-button>
                        </div>
                    </b-form>
                </div>
            </div>
        <!-- </div> -->
        <!-- <div v-else>
            <div class="box-collapse w-25">
                <form class="form-a" method="GET" action="${root}/user/detail?id=${userinfo.id}">
                    <input type="hidden" name="id" value="${userInfo.id}" />
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
        </div> -->
    </div>
</template>

<script>
import http from "@/api/http"

export default {
    name: 'SignIn',
    data() {
        return {
            isLoginError: false,
            user: {
                id: null,
                password: null,
            }
        };
    },
    methods: {
        confirm() {
            alert("로그인!!!");
            console.log("==============================password" + this.user.password)
            http.post(`rest/user/login`, {
                id: this.user.id,
                password: this.user.password,
            })
                .then(({ data }) => {
                    console.log(data);
                    let msg = "로그인시 문제가 발생했습니다.";
                    if (data.message === "success") {
                        msg = "로그인 성공";
                    }
                    alert(msg);
            })
        },
        moveRegistPage() {
            this.$router.push({name: "join"})
        }
    },
};
</script>

<style scoped></style>