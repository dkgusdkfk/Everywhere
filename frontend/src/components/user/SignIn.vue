<template>
    <div>
        <div v-if="userInfo == null">
            <div>
                <div class="title-box-d" style="top: 2rem;">
                    <h3 class="title-d" style="text-align: left;">Sign In</h3>
                </div>
                <div>
                    <b-form>
                        <div class="row border border-1 p-4 rounded-4">
                            <div class="col-md-12 mb-2">
                                <div class="form-group">
                                    <label class="pb-2" for="id">ID</label>
                                    <b-form-input id="id" v-model="user.id" placeholder="ID" required
                                        @keyup.enter="confirm"></b-form-input>
                                </div>
                            </div>
                            <div class="col-md-12 mb-2">
                                <div class="form-group">
                                    <label class="pb-2" for="password">Password</label>
                                    <b-form-input type="password" id="password" v-model="user.password"
                                        placeholder="Password" required @keyup.enter="confirm"></b-form-input>
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
                            <a class="ms-3 me-3 mt-3 md-3 text-black-50" @click="findPW">
                                Password 찾기</a>
                            <a class="ms-3 me-3 mt-3 md-3 text-black-50" @click="moveRegistPage">회원가입</a>
                        </div>
                    </b-form>
                </div>
            </div>
        </div>
        <div v-else>
            <div>
                <b-form>
                    <div class="title-box-d" style="top: 2rem;">
                        <h3 class="title-d" style="text-align: left;">내 정보</h3>
                    </div>
                    <div>
                        <div class="row border border-1 p-4 rounded-4">
                            <div class="col-md-12 mb-2">
                                <div class="form-group">
                                    <strong>{{userInfo.name}}</strong> ({{userInfo.id}})님 안녕하세요.
                                </div>
                            </div>
                            <div class="d-flex flex-column align-items-center justify-content-center">
                                <router-link :to="{ name: 'mypage' }">
                                    <button id="btn-login"
                                        class="btn btn-b w-100 border border-2 rounded-4 text-light mt-3">
                                        <strong>My page</strong>
                                    </button>
                                </router-link>

                            </div>
                        </div>
                        <div class="d-flex align-content-center justify-content-center">
                            <a href="#" class="ms-5 me-5 mt-3 md-3 text-black-50" @click="onClickLogout">로그아웃</a>
                        </div>
                    </div>
                </b-form>
            </div>
        </div>
    </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";

const memberStore = "memberStore";

export default {
    name: 'SignIn',
    data() {
        return {
            // isLoginError: false,
            user: {
                id: null,
                password: null,
            }
        };
    },
    computed: {
        ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
        ...mapGetters(["checkUserInfo"])
    },
    methods: {
        ...mapActions(memberStore, ["userConfirm", "getUserInfo", "userLogout"]),
        async confirm() {
            await this.userConfirm(this.user);
            let token = sessionStorage.getItem("access-token");
            if (this.isLogin) {
                await this.getUserInfo(token);
                this.$router.go();
            } else {
                alert("아이디 또는 비밀번호를 확인해주세요.")
            }
        },
        moveRegistPage() {
            this.$router.push({ name: "register" })
        },
        findPW() {
            this.$router.push({ name: "findPW" })
        },
        onClickLogout() {
            console.log(this.userInfo);
            this.userLogout(this.userInfo.id);
            sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
            sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
            if (this.$route.path != "/") this.$router.push({ name: "main" });
        }
    },
};
</script>

<style scoped></style>