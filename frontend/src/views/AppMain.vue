<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-steelblue"><b-icon icon="house"></b-icon> SSAFY</h3>
    <b-row>
      <b-col></b-col>
      <b-col cols="10">
        <b-jumbotron bg-variant="muted" text-variant="dark" border-variant="dark">
          <template #header>SSAFY Home</template>

          <template #lead>
            슬기로운 싸피 생활 (:9기편👍)<br />
            1학기동안 모두 고생 & 수고많으셨어요😍~~<br />
            2학기 가서도 행복한 하루 ⭐️ 하루 되길 바랄께요.<br />
            구~~~~~뤠~~~~~잇 9기 화이팅!!!.🌈<br />
            그리고 각자 생각하는 곳에 취업!!! 꼭!꼭!꼭! 하세용.🐹
          </template>

          <hr class="my-4" />

          <p>Vue + Bootstrap활용.</p>
          <p>Bootstrap-vue는 버전 <b>4.6.1</b>을 권장합니다.</p>
          <p><b>BoardList.vue</b>를 바꿔가면서 테스트하세요.</p>
          <p>Bootstrap의 <b>table</b> 사용법을 익히게됩니다.</p>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "AppMain",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}
</style>
