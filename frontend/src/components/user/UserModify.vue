<template>
    <div>
        <section class="intro-single">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-lg-8">
                        <div class="title-single-box">
                            <h1 class="title-single">회원 정보 수정</h1>
                        </div>
                    </div>
                    <div class="col-md-12 col-lg-4">
                        <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">
                                    <a href="MyPage.html">My Page</a>
                                </li>
                                <li class="breadcrumb-item active" aria-current="page">
                                    <a href="UserInfoUpdate.html">Update Profile</a>
                                </li>
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
                    <b-form>
                        <div class="mb-3">
                            <label for="id">id</label>
                            <b-form-input type="text" class="form-control" id="id" name="id" v-model="userInfo.id" readonly
                                required></b-form-input>
                        </div>
                        <div class="mb-3">
                            <label for="password">비밀번호</label>
                            <b-form-input type="password" class="form-control" id="password" name="password" v-model="userInfo.password"
                                required></b-form-input>
                            <div class="invalid-feedback">비밀번호를 입력해주세요.</div>
                        </div>
                        <div class="mb-3">
                            <label for="pwCheck">비밀번호 확인</label>
                            <b-form-input type="password" class="form-control" id="pwCheck" name="pwCheck" placeholder="비밀번호 확인"
                                required></b-form-input>
                            <div class="invalid-feedback">비밀번호를 입력해주세요.</div>
                        </div>
                        <div class="mb-3">
                            <label for="email">이름</label>
                            <b-form-input type="text" class="form-control" id="name" name="name" v-model="userInfo.name" readonly
                                required></b-form-input>
                        </div>
                        <div class="mb-3">
                            <label for="email">이메일</label>
                            <b-form-input type="email" class="form-control" id="email" name="email" v-model="userInfo.email"
                                required></b-form-input>
                            <div class="invalid-feedback">이메일을 입력해주세요.</div>
                        </div>
                        <div class="mb-3">
                            <label for="address1">주소</label>
                            <select-sido @select-sido="selectSido"></select-sido>
                        </div>
                        <div class="mb-3">
                            <label for="address2">상세주소</label>
                            <select-gugun :sidoCode=this.userInfo.address1 @select-gugun="selectGugun"></select-gugun>
                        </div>
                        <button class="btn btn-primary btn-lg btn-block" type="button" @click="update">수정하기</button>
                    </b-form>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import { mapState,mapActions, mapMutations } from "vuex";
import SelectSido from "@/components/item/SelectSido.vue";
import SelectGugun from "@/components/item/SelectGugun.vue";

const memberStore = "memberStore";
const itemStore = "itemStore";

export default {
    name: 'UserModify',
    components: {
        SelectSido,
        SelectGugun,
    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    methods: {
        ...mapActions(itemStore, ["getGugun"]),
        ...mapActions(memberStore, ["userUpdate"]),
        ...mapMutations(itemStore, ["CLEAR_GUGUN_LIST"]),
        selectSido(sidoCode) {
            this.userInfo.address1 = sidoCode;
            console.log(this.userInfo.address1)
        },
        selectGugun(gugunCode) {
            this.userInfo.address2 = gugunCode;
        },
        async update() {
            await this.userUpdate(this.userInfo);
            this.$router.push({ name: "mypage" });
        }
    }
}

</script>
