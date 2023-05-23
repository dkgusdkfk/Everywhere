<template>
  <b-container class="bv-example-row mt-3">
      <b-col><b-alert show variant="danger">삭제처리중...</b-alert></b-col>
  </b-container>
</template>

<script>
import http from "@/api/http";

export default {
  name: "NoticeDelete",
  created() {
    http.delete(`/notice/${this.$route.params.noticeId}`).then(({ data }) => {
      let msg = "삭제 처리시 문제가 발생했습니다.";
      if (data === "success") {
        msg = "삭제가 완료되었습니다.";
      }
      alert(msg);
      // 현재 route를 /list로 변경.
      this.$router.push({ name: "noticelist" });
    });
  },
};
</script>

<style></style>
