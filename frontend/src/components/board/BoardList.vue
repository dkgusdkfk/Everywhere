<template>
  <div>
    <section class="intro-single" id="main">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">여행 정보 게시판</h1>
            </div>
          </div>
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="${root}/index.jsp">Home</a></li>
                <li class="breadcrumb-item"><a href="#">커뮤니티</a></li>
                <li class="breadcrumb-item"><a href="#">여행 정보 게시판</a></li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
    </section>
    <section class="news-single nav-arrow-b">
      <div class="container">

        <div class="d-flex justify-content-end align-content-center p-2 m-auto">
          <b-button type="submit" variant="secondary" class="m-1" @click="writeBoard">작성하기</b-button>
        </div>
        <div class="d-flex flex-column justify-content-end align-content-center p-2 m-auto">
          <div class="row">
            <div class="col-2"></div>
            <div class="col-2">
              <b-form-select v-model="key" :options="keys"></b-form-select>
            </div>
            <div class="col-4">
              <b-form-input v-model="word" />
            </div>
            <div class="col-2">
              <b-button variant="primary" @click="search">검색</b-button>
            </div>
            <div class="col-2"></div>
          </div>
          <b-table hover :items="boards" :fields="fields" @row-clicked="viewBoard"></b-table>
          <div v-if="boards == null">
            <table class='table table-hover'>
              <tr>
                <td>게시글이 없습니다.</td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import http from "@/api/http"

export default {
  name: "BoardList",
  data() {
    return {
      boards: [],
      key: 'all',
      word: '',
      keys: [
        { value: 'all', text: '---선택하세요---' },
        { value: 'title', text: '제목' },
        { value: 'userId', text: '작성자' },
      ],
      pageNo: '1',
      fields: [
        { key: "boardId", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdTitle" },
        { key: "userId", label: "작성자", tdClass: "tdClass" },
        { key: "registerTime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    http.get(`/board/all`).then(({ data }) => {
      this.boards = data.boards;
    });
  },
  watch: {
    pageNo: function () {
      this.search();
    }
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },
    viewBoard(board) {
      this.$router.push({
        name: "boardview",
        params: { boardId: board.boardId },
      });
    },
    writeBoard() {
      this.$router.push({ name: "boardwrite" });
    },
    search() {
      http.get(`board/all?pageNo=${this.pageNo}&word=${this.word}&key=${this.key}`)
        .then(({ data }) => {
          console.log(data);
          this.boards = data.boards;
        })
        .catch(({ response }) => {
          alert('오류 메세지: ' + response.data);
        })
    }
  },
};
</script>

<style scope>
.tdClass {
  width: 100px;
  text-align: center;
}

.tdTitle {
  width: 200px;
  text-align: center;
}

#main {
  text-align: left;
}
</style>
