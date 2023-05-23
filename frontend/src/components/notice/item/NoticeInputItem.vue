<template>
  <div class="row justify-content-center" style="margin-top:200px" id="main">
    <div class="col-lg-8 col-md-10 col-sm-12">
        <b-form @submit="onSubmit" @reset="onReset">
            <div class="mb-3">
                <label for="title" class="form-label">제목 : </label>
                <b-form-input type="text" class="form-control" id="title" v-model="notice.title" required/>
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">내용 : </label>
                <textarea class="form-control" id="content" rows="7" v-model="notice.content"></textarea>

            </div>
            <div class="col-auto text-center">
                <b-button type="submit" variant="primary" class="m-1" v-if="this.type === 'register'">글작성</b-button>
                <b-button type="submit" variant="primary" class="m-1" v-else>글수정</b-button>
                <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
                <b-button variant="info" class="m-1" @click="moveList">
                    목록으로이동...
                </b-button>
            </div>
        </b-form>
    </div>
</div>
</template>

<script>
import { mapState } from "vuex";
import http from "@/api/http";

const memberStore = "memberStore";

export default {
  name: "NoticeInputItem",
  data() {
    return {
      notice: {
        noticeId: 0,
        adminId: "",
        title: "",
        content: "",
        registerTime: "",
      },
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      http.get(`/notice/${this.$route.params.noticeId}`).then(({ data }) => {
        this.notice = data;
      });
    }
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      err && !this.notice.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err && !this.notice.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.registNotice() : this.modifyNotice();
    },
    onReset(event) {
      event.preventDefault();
      // this.notice.noticeId = 0;
      this.notice.title = "";
      this.notice.content = "";
      // this.moveList();
    },
    registNotice() {
      console.log(this.userInfo);
      http
        .post(`/notice/write`, {
          adminId: this.userInfo.id,          // ------------------------------수정 필수--------------------------------
          title: this.notice.title,
          content: this.notice.content,
        })
        .then(({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    modifyNotice() {
      http
        .put(`/notice/update`, {
          noticeId: this.notice.noticeId,
          adminId: this.notice.adminId,
          title: this.notice.title,
          content: this.notice.content,
          registerTime: this.notice.registerTime,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        });
    },
    moveList() {
      this.$router.push({ name: "noticelist" });
    },
  },
};
</script>

<style>
  #main {
    flex: 0 0 100%;
    max-width: 100%;
  }
</style>
