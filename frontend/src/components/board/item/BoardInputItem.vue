<template>
  <div class="row justify-content-center" style="margin-top:200px" id="main">
    <div class="col-lg-8 col-md-10 col-sm-12">
        <b-form @submit="onSubmit" @reset="onReset">
          <table class="table table-hover">
            <thead>
              <tr style="color: #ff4400; font-weight: bolder;">
                <th>제목</th>
                <th>거리</th>
                <th>도보</th>
                <th>자전거</th>
              </tr>
            </thead>
            <tbody id="trip-list">
              <tr v-for="plan in plans" :key="plan.planId" @click="selectPlan(plan.planId, plan.title)">
                <td>{{ plan.title }}</td>
                <td>{{ plan.distance }}</td>
                <td>{{ plan.walkTime }}</td>
                <td>{{ plan.cycleTime }}</td>
              </tr>
            </tbody>
          </table>
          <div style="text-align: right; padding-bottom: 30px;">
            선택된 여행 계획 <input id="selectTitle" v-model="selectTitle" readonly/>
          </div>
            <div class="mb-3">
                <label for="title" class="form-label">제목 : </label>
                <b-form-input type="text" class="form-control" id="title" v-model="board.title" required/>
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">내용 : </label>
                <textarea class="form-control" id="content" rows="7" v-model="board.content"></textarea>

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
  name: "BoardInputItem",
  data() {
    return {
      board: {
        boardId: 0,
        userId: "",
        title: "",
        content: "",
        registerTime: "",
        planId: null,
      },
      selectTitle: null,
      plans: []
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      http.get(`/board/${this.$route.params.boardId}`).then(({ data }) => {
        this.board = data;
        if (this.board.planId != 0) {
          http.get(`/trip/plan/${this.board.planId}`).then(({ data }) => {
            this.selectTitle = data.title;
          })
        }
      });
    }
    http.get(`/trip/plan/all`).then(({ data }) => {
        this.plans = data;
    })
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      err && !this.board.title && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err && !this.board.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.registBoard() : this.modifyBoard();
    },
    onReset(event) {
      event.preventDefault();
      this.board.title = "";
      this.board.content = "";
      this.board.planId = "";
      this.selectTitle = null;
    },
    registBoard() {
      console.log(this.userInfo);
      http
        .post(`/board/write`, {
          userId: this.userInfo.id,
          title: this.board.title,
          content: this.board.content,
          planId: this.board.planId,
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
    modifyBoard() {
      http
        .put(`/board/update`, {
          boardId: this.board.boardId,
          userId: this.board.userId,
          title: this.board.title,
          content: this.board.content,
          registerTime: this.board.registerTime,
          planId: this.board.planId,
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
      this.$router.push({ name: "boardlist" });
    },
    selectPlan(planId, title) {
      this.board.planId = planId;
      this.selectTitle = title;
    }
  },
};
</script>

<style>
  #main {
    flex: 0 0 100%;
    max-width: 100%;
  }

  #selectTitle {
    border: 1px solid #ced4da;
    border-radius: 0.25rem;
  }
</style>
