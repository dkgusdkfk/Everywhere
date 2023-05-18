import Vue from "vue";
import Vuex from "vuex";
import http from "@/api/http";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    board: {},
    boards: [],
    searchBoards: [],
    comments: [],
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,
    todos: [
      // { title: '할 일1', completed: false },
      // { title: '할 일2', completed: false },
    ],
  },
  getters: {
    board(state) {
      return state.board;
    },
    boards(state) {
      return state.boards;
    },
    searchBoards(state) {
      return state.searchBoards;
    },
    comments(state) {
      return state.comments;
    },
    allTodosCount(state) {
      return state.todos.length;
    },
    completedTodosCount(state) {
      return state.todos.filter((todo) => {
        return todo.completed === true;
      }).length;
    },
    unCompletedTodosCount(state) {
      return state.todos.filter((todo) => {
        return todo.completed === false;
      }).length;
    },
  },
  mutations: {
    setBoard(state, payload) {
      state.board = payload;
    },
    setBoards(state, payload) {
      state.boards = payload;
    },
    setSearchBoards(state, payload) {
      state.searchBoards = payload;
    },
    setComments(state, payload) {
      state.comments = payload;
    },
    /////////////////////////////// House start /////////////////////////////////////
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      // console.log("Mutations", house);
      state.house = house;
    },
    /////////////////////////////// House end /////////////////////////////////////

    //////////////////////////// Todo List start //////////////////////////////////
    CREATE_TODO(state, todoItem) {
      // console.log('mutation CREATE_TODO Call');
      // console.log(state);
      // console.log(todoItem);
      state.todos.push(todoItem);
    },
    DELETE_TODO(state, todoItem) {
      const index = state.todos.indexOf(todoItem);
      state.todos.splice(index, 1);
    },
    UPDATE_TODO_STATUS(state, todoItem) {
      // console.log(state);
      // console.log(todoItem);
      state.todos = state.todos.map((todo) => {
        if (todo === todoItem) {
          return {
            // title: todoItem.title,
            ...todo,
            completed: !todoItem.completed,
          };
        }
        return todo;
      });
    },
    //////////////////////////// Todo List end //////////////////////////////////
  },
  actions: {
    getBoards({ commit }) {
      http.get("rest/board/all?pageNo=1")
        .then(({ data }) => {
          commit('setBoards', data.boards);
          commit('setSearchBoards', data.boards);
        })
        .catch(({ response }) => {
          alert('오류 메세지: ' + response.data);
        })
    },
    getBoard(store, payload) {
      http.get(`rest/board/${payload.isbn}`)
        .then(({ data }) => {
          store.commit('setBoard', data);
        })
    },
    getSearchBoards(store, payload) {
      http.get(`rest/board/all?pageNo=${payload.pageNo}&word=${payload.word}&key=${payload.key}`)
        .then(({ data }) => {
          store.commit('setSearchBoards', data.boards);
        })
        .catch(({ response }) => {
          alert('오류 메세지: ' + response.data);
        })
      // getBooks에서 나의 상태 정보를 모두 가져온 경우
      // getBooks내에서 검색
      // console.log('[actions:getSearchs]...................store', store)
      // console.log('[actions:getSearchs]...................payload', payload)
      // var key = payload.key;
      // var word = payload.word;
      // var find = [];

      // if (key == 'all') {
      //   find = store.state.books;
      // } else if (key == 'isbn') {
      //   find = store.state.books.filter((book) => {
      //     if (book.isbn == word) {
      //       return book;
      //     }
      //   })
      // } else if (key == 'title') {
      //   find = store.state.books.filter((book) => {
      //     if (book.title.includes(word)) {
      //       return book;
      //     }
      //   })
      // } else if (key == 'author') {
      //   find = store.state.books.filter((book) => {
      //     if (book.author.includes(word)) {
      //       return book;
      //     }
      //   })
      // }
      // store.commit('setSearchs', find);
    },
    getComments(store, payload) {
      http.get(`/rest/board/comment/${payload.boardId}`)
        .then(({ data }) => {
          store.commit('setComments', data.comments);
        })
      .catch(({ response }) => {
        alert('오류 메세지: ' + response.data);
      })
    },
    /////////////////////////////// House start /////////////////////////////////////
    getSido({ commit }) {
      http
        .get(`/map/sido`)
        .then(({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      http
        .get(`/map/gugun`, { params })
        .then(({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getHouseList({ commit }, gugunCode) {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      // const SERVICE_KEY =
      //   "========== service key ==========";
      const SERVICE_URL =
        "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
      const params = {
        LAWD_CD: gugunCode,
        DEAL_YMD: "202304",
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      http
        .get(SERVICE_URL, { params })
        .then(({ data }) => {
          // console.log(commit, data);
          commit("SET_HOUSE_LIST", data.response.body.items.item);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    detailHouse({ commit }, house) {
      // 나중에 house.일련번호를 이용하여 API 호출
      // console.log(commit, house);
      commit("SET_DETAIL_HOUSE", house);
    },
    /////////////////////////////// House end /////////////////////////////////////

    //////////////////////////// Todo List start //////////////////////////////////
    // createTodo(context, todoItem) {
    //   console.log(context);
    //   console.log('actions createTodo Call');
    //   context.commit('CREATE_TODO', todoItem);
    // },
    // destructuring 활용
    createTodo({ commit }, todoItem) {
      commit("CREATE_TODO", todoItem);
    },
    deleteTodo({ commit }, todoItem) {
      // console.log(context);
      // console.log(todoItem);
      commit("DELETE_TODO", todoItem);
    },
    updateTodoStatus({ commit }, todoItem) {
      commit("UPDATE_TODO_STATUS", todoItem);
    },
    //////////////////////////// Todo List end //////////////////////////////////
  },
  modules: {},
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
