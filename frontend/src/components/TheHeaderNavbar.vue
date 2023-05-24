<template>
  <div>
    <nav class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top" id="nav">
      <div class="container">
        <a class="navbar-brand text-brand">
          <router-link :to="{ name: 'main' }" class="link">
            <img src="@/assets/img/logo.png" id="logo"/>
            <!-- <span class="color-a">Enjoy</span>
            <span class="color-b">Trip</span> -->
          </router-link>
        </a>
        <ul class="navbar-nav me-5 ms-5 d-flex w-75 align-items-center justify-content-center ">
          <!-- <li class="nav-item dropdown ms-5 me-5 nav-link">
            <router-link :to="{ name: 'TripList' }" class="link">지역별 여행지 추천</router-link>
          </li> -->

          <li class="nav-item dropdown me-5 ms-5"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
              role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">여행</a>
            <div class="dropdown-menu">
              <b-dropdown-item>
                <router-link :to="{ name: 'TripList' }" class="link">
                  지역별 여행지 추천
                </router-link>
              </b-dropdown-item>
              <b-dropdown-item>
                <router-link :to="{ name: 'TripPlan' }" class="link">
                  여행 계획
                </router-link>
              </b-dropdown-item>
            </div>
          </li>

          <li class="nav-item dropdown me-5 ms-5"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
              role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">커뮤니티</a>
            <div class="dropdown-menu">
              <b-dropdown-item>
                <router-link :to="{ name: 'board' }" class="link">
                  게시판
                </router-link>
              </b-dropdown-item>
              <b-dropdown-item>
                <router-link :to="{ name: 'HotPalce' }" class="link">
                  Hot Place
                </router-link>
              </b-dropdown-item>
            </div>
          </li>
        </ul>

        <div>
          <b-button v-b-toggle.signInBar>
            <i class="bi bi-person-circle"></i>
          </b-button>
          <b-sidebar id = "signInBar" right no-header>
            <template #default="{hide}">
              <span class="close-box-collapse right-boxed"><b-button class="bi bi-x" @click="hide"></b-button></span>
              <sign-in-view></sign-in-view>
            </template>
          </b-sidebar>
        </div>

        <!-- <button type="button" class="btn btn-b-n navbar-toggle-box navbar-toggle-box-collapse" data-bs-toggle="collapse"
          data-bs-target="#navbarTogglerDemo01">
          <i class="bi bi-person-circle"></i>
        </button> -->
        <!-- <img src="@/assets/img/favicon.png" />
              <img src="@/assets/img/apple-touch-icon.png" /> -->

      </div>
    </nav>
  </div>
</template>

<script>
/* Favicons  */
//     import MainIcon from "@/assets/img/favicon.png"
//     import AppleTouchIcon from "@/assets/img/apple-touch-icon.png"
// import { component } from "vue/types/umd";
import SignInView from "@/components/user/SignIn.vue"

export default {
  name: "HeaderBar",
  components: {
    SignInView
  }
};
</script>

<style scope>
/* 

  /* Vendor CSS Files */
@import '@/assets/vendor/animate.css/animate.min.css';
@import '@/assets/vendor/bootstrap/css/bootstrap.min.css';
@import '@/assets/vendor/bootstrap-icons/bootstrap-icons.css';
@import '@/assets/vendor/swiper/swiper-bundle.min.css';

/* Font */
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");

/* Google Fonts */
@import url("https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700");

/* Template Main CSS File */
@import '@/assets/css/style.css';

/* Service Key */
/* @import '@/assets/js/key.js'; */


</style>
<style scoped>
.link {
  text-decoration: none;
  color: black;
}

#logo {
  width: 300px;
}

#nav {
  height: 130px;
}
</style>
