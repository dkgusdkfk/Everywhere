<template>
  <div>
    <div>
      <b-carousel id="carousel-fade" style="text-shadow: 0px 0px 2px #000" fade indicators img-width="1024"
        img-height="480">
        <b-carousel-slide caption="First Slide" img-src="https://picsum.photos/1024/480/?image=10"></b-carousel-slide>
        <b-carousel-slide caption="Second Slide" img-src="https://picsum.photos/1024/480/?image=12"></b-carousel-slide>
        <b-carousel-slide caption="Third Slide" img-src="https://picsum.photos/1024/480/?image=22"></b-carousel-slide>
      </b-carousel>
    </div>
    <!-- End Intro Section -->
    <div v-if="userInfo">
      <section class="section-property section-t8">
        <b-container>
          <b-row>
            <b-col>
              <div class="title-wrap d-flex justify-content-between">
                <div class="title-box">
                  <h2 class="title-a">우리 지역 여행지 추천</h2>
                </div>
                <div class="title-link">
                  <a href="/trip/triplist">더 보기 <span class="bi bi-chevron-right"></span>
                  </a>
                </div>
              </div>
            </b-col>
          </b-row>
          <b-card-group deck>
            <div v-for="attraction in attractions.slice(0, 6)" :key="attraction.contentId">
              <b-card class="card-box-a card-shadow" style="width: 20rem;">
                <div class="img-box-a" style="height: 480px;">
                  <b-img :src="attraction.firstImage" alt="" class="img-a img-fluid"></b-img>
                </div>
                <div class="card-overlay">
                  <div class="card-overlay-a-content">
                    <div class="card-header-a">
                      <h2 class="card-title-a">
                        <a href="property-single.jsp">
                          {{ attraction.title }}
                        </a>
                      </h2>
                    </div>
                    <div class="card-body-a">
                      <div class="price-box d-flex">
                        <span class="price-a">주소</span>
                      </div>
                      <div style="color: white">{{ attraction.address1 }} {{ attraction.address2 }}</div>
                      <a href="#" class="link-a">Click here to view <span class="bi bi-chevron-right"></span>
                      </a>
                    </div>
                    <div class="card-footer-a">
                      <ul class="card-info d-flex justify-content-around">
                        <li>
                          <h4 class="card-info-title">Area</h4> <span>340m <sup>2</sup>
                          </span>
                        </li>
                        <li>
                          <h4 class="card-info-title">Beds</h4> <span>2</span>
                        </li>
                        <li>
                          <h4 class="card-info-title">Baths</h4> <span>4</span>
                        </li>
                        <li>
                          <h4 class="card-info-title">Garages</h4> <span>1</span>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </b-card>
            </div>
          </b-card-group>


          <div class="propery-carousel-pagination carousel-pagination"></div>

        </b-container>
      </section>
    </div>
    <!-- End Latest Properties Section --> <!-- ======= Latest News Section ======= -->
    <section class="section-news section-t8">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="title-wrap d-flex justify-content-between">
              <div class="title-box">
                <h2 class="title-a">Hot Place</h2>
              </div>
              <div class="title-link">
                <a href="/trip/hotPlace">더보기 <span class="bi bi-chevron-right"></span>
                </a>
              </div>
            </div>
          </div>
        </div>

        <div>
          <b-card-group deck>
            <div v-for="place in hotPlaces.slice(0, 6)" :key="place.contentId">
              <b-col cols="3">
                <b-card class="card-box-b card-shadow news-box" :img-src="place.imgPath" style="width:300px; height:200px"
                  @click="openModal(place.contentId)">
                  <div class="card-overlay">
                    <div class="card-header-b">
                      <div class="card-category-b">
                        <a class="category-b">♥ {{ place.count }}</a>
                      </div>
                      <div class="card-title-b">
                        <h2 class="title-2">
                          <a>{{ place.title }}</a>
                        </h2>
                      </div>
                      <div class="card-date">
                        <span class="date-b">{{ place.address1 }} {{ place.address2 }}</span>
                      </div>
                    </div>
                  </div>
                </b-card>
              </b-col>
            </div>
          </b-card-group>
        </div>
      </div>
    </section>

  </div>
</template>

<script>
import { mapState } from "vuex";
import http from "@/api/http";

const memberStore = "memberStore";

export default {
  name: "AppMain",
  props: {
    msg: String,
  },
  data() {
    return {
      attractions: [],
      hotPlaces: [],
      slide: 0,
      sliding: null,
    }
  },
  created() {
    if (this.userInfo) {
      http.get(`/trip/recommend/${this.userInfo.address1}/${this.userInfo.address2}`).then(({ data }) => {
        this.attractions = data;
      });
    }
    http.get(`/trip/hotPlace`).then(({ data }) => {
      this.hotPlaces = data;
    });
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    onSlideStart() {
      this.sliding = true
    },
    onSlideEnd() {
      this.sliding = false
    }
  }
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}
</style>
