import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain";

import store from "@/store";

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "main" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const onlyAdmin = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];

  if (checkUserInfo == null || checkUserInfo.id != "admin") {
    alert("관리자만 이용할 수 있습니다");
    router.push({ name: "main" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "main",
    component: AppMain,
  },
  {
    path: "/user",
    name: "user",
    component: () => import(/* webpackChunkName: "user" */ "@/views/AppUser"),
    children: [
      {
        path: "register",
        name: "register",
        component: () =>
          import(
            /* webpackChunkName: "user" */ "@/components/user/UserRegister"
          ),
      },
      {
        path: "findPW",
        name: "findPW",
        component: () =>
          import(
            /* webpackChunkName: "user" */ "@/components/user/FindPassword"
          ),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/user/UserMyPage"),
      },
      {
        path: "modify",
        name: "usermodify",
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/user/UserModify"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import(/* webpackChunkName: "board" */ "@/views/AppBoard"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardList"
          ),
      },
      {
        path: "write",
        name: "boardwrite",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardWrite"
          ),
      },
      {
        path: "view/:boardId",
        name: "boardview",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardView"
          ),
      },
      {
        path: "modify",
        name: "boardmodify",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardModify"
          ),
      },
      {
        path: "delete/:boardId",
        name: "boarddelete",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/board/BoardDelete"
          ),
      },
    ],
  },
  {
    path: "/trip",
    name: "trip",
    component: () => import(/* webpackChunkName: "trip" */ "@/views/AppTrip"),
    children: [
      {
        path: "triplist",
        name: "TripList",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/trip/AttractionList"
          ),
      },
      {
        path: "hotplace",
        name: "HotPlace",
        component: () =>
          import(/* webpackChunkName: "trip" */ "@/components/trip/HotPlace"),
      },
      {
        path: "tripplan",
        name: "TripPlan",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "trip" */ "@/components/trip/TripPlan"),
      },
    ],
  },
  {
    path: "/notice",
    name: "notice",
    component: () => import(/* webpackChunkName: "board" */ "@/views/AppBoard"),
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "noticelist",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/notice/NoticeList"
          ),
      },
      {
        path: "write",
        name: "noticewrite",
        beforeEnter: onlyAdmin,
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/notice/NoticeWrite"
          ),
      },
      {
        path: "view/:noticeId",
        name: "noticeview",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/notice/NoticeView"
          ),
      },
      {
        path: "modify",
        name: "noticemodify",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/notice/NoticeModify"
          ),
      },
      {
        path: "delete/:noticeId",
        name: "noticedelete",
        component: () =>
          import(
            /* webpackChunkName: "board" */ "@/components/notice/NoticeDelete"
          ),
      },
    ],
  },
  {
    path: "/qna",
    name: "qnalist",
    component: () => import(/* webpackChunkName: "board" */ "@/views/AppBoard"),
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "qna",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/qna/QnaList"),
      },
      {
        path: "write",
        name: "qnawrite",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/qna/QnaWrite"),
      },
      {
        path: "view/:qnaId",
        name: "qnaview",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/qna/QnaView"),
      },
      {
        path: "modify",
        name: "qnamodify",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/qna/QnaModify"),
      },
      {
        path: "delete/:qnaId",
        name: "qnadelete",
        component: () =>
          import(/* webpackChunkName: "board" */ "@/components/qna/QnaDelete"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
