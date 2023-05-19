import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain";

import store from "@/store"

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
    router.push({ name: "login" });
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
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserRegister"),
      },
      {
        path: "login",
        name: "login",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserLogin"),
      },
      {
        path: "findPW",
        name: "findPW",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/FindPassword"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserMyPage"),
      }
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
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardList"),
      },
      {
        path: "write",
        name: "boardwrite",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardWrite"),
      },
      {
        path: "view/:boardId",
        name: "boardview",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardView"),
      },
      {
        path: "modify",
        name: "boardmodify",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardModify"),
      },
      {
        path: "delete/:boardId",
        name: "boarddelete",
        component: () => import(/* webpackChunkName: "board" */ "@/components/board/BoardDelete"),
      },
    ],
  },
  // {
  //   path: "/trip",
  //   name: "trip",
  //   component: () => import(/* webpackChunkName: "trip" */ "@/views/AppTrip"),
  //   children: [
  //     {
  //       path: ""
  //     }
  //   ]
  // }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
