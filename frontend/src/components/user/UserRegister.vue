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
                <input type="text" class="form-control" id="userid" v-model="user.id" placeholder="" value="" @keyup="checkId" required>
              </div>
              <div class="col-md-6 mb-3">
                <label for="idcheck-result" class="col form-label"></label>
                <div class="col form-control me-3" style="background-color:lightgray" id="idcheck-result">{{ idMessage }}</div>
              </div>

            </div>
            <div class="row">
              <div class="col-md-3 mb-3">
                <label for="pw">비밀번호</label>
                <input type="password" class="form-control" id="pw" v-model="user.password" placeholder="비밀번호 입력"
                @keyup="checkPw" required />
              </div>
              <div class="col-md-3 mb-3">
                <label for="pwdcheck" class="col-3 form-label"></label>
                <input type="password" class="form-control" placeholder="비밀번호 확인" id="pwdcheck" v-model="pwdcheck" @keyup="checkPw" required />
              </div>
              <div class="col-md-6 mb-3">
                <label for="pwdcheck-result" class="col form-label"></label>
                <div class="col form-control me-3" style="background-color:lightgray" id="pwdcheck-result">{{ pwMessage }}</div>
              </div>
            </div>
            <div class="mb-3">
              <label for="name">이름</label>
              <input type="text" class="form-control" id="name" v-model="user.name" placeholder="이름" required>
            </div>
            <div class="mb-3">
              <label for="email">이메일</label>
              <input type="email" class="form-control" id="email" v-model="user.email" placeholder="you@example.com"
                required>
              <div class="invalid-feedback">이메일을 입력해주세요.</div>
            </div>
            <div class="mb-3">
              <label for="address1">주소</label>
              <select-sido @select-sido="selectSido"></select-sido>
            </div>
            <div class="mb-3">
              <label for="address2">상세주소</label>
              <select-gugun :sidoCode=this.user.address1 @select-gugun="selectGugun"></select-gugun>
            </div>
            <button class="btn btn-primary btn-lg btn-block" type="button" @click="regist">가입하기</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations } from "vuex";
import http from "@/api/http";
import SelectSido from "@/components/item/SelectSido.vue";
import SelectGugun from "@/components/item/SelectGugun.vue";

const itemStore = "itemStore";

export default {
  name: "UserRegister",
  components: {
    SelectSido,
    SelectGugun,
  },
  data() {
    return {
      user: {
        id: null,
        password: null,
        email: null,
        address1: null,
        address2: null
      },
      pwdcheck: null,
      isUseId: false,
      idMessage: "아이디는 6자 이상 16자 이하입니다.",
      isUsePw: false,
      pwMessage: "비밀번호가 일치하지 않습니다.",
    };
  },
  methods: {
    ...mapActions(itemStore, ["getGugun"]),
    ...mapMutations(itemStore, ["CLEAR_GUGUN_LIST"]),
    selectSido(sidoCode) {
      this.user.address1 = sidoCode;
    },
    selectGugun(gugunCode) {
      this.user.address2 = gugunCode;
    },
    regist() {
      if (!this.isUseId) {
        alert("아이디를 확인해주세요.")
      } else if(!this.isUsePw) {
        alert("비밀번호를 확인해주세요.")
      } else {
        http.post(`/user`, this.user)
          .then(({ data }) => {
            console.log(data)
            let msg = "등록 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "등록이 완료되었습니다.";
            }
            alert(msg);
            if (this.$route.path != "/") this.$router.push({ name: "main" });
          })
      }

    },
    checkId() {
      if (this.user.id.length < 6 || this.user.id.length > 16) {
        this.idMessage = "아이디는 6자 이상 16자 이하입니다.";
        this.isUseId = false;
      } else {
        http.get(`/user/idCheck/${this.user.id}`)
          .then(({ data }) => {
            console.log(data);
            if (data == 0) {
              this.idMessage = this.user.id + "는 사용할 수 있습니다.";
              this.isUseId = true;
            } else {
              this.idMessage = this.user.id + "는 사용할 수 없습니다.";
              this.isUseId = false;
            }
          })
      }
    },
    checkPw() {
      if (this.user.password == this.pwdcheck) {
        this.pwMessage = "비밀번호가 일치합니다.";
        this.isUsePw = true;
      } else {
        this.pwMessage = "비밀번호가 일치하지 않습니다.";
        this.isUsePw = false;
      }
    }
  }
};

</script>

<style></style>
