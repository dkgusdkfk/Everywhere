<template>
  <div>
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">지역별 여행지 추천</h1>
            </div>
          </div>
        </div>
      </div>
    </section>

    <div class="d-flex justify-content-center search-spot w-75">
      <form class="d-flex justify-content-center w-100" @submit="search" role="search">
        <div class="mb-3">
          <label for="address1">주소</label>
          <select-sido @select-sido="selectSido"></select-sido>
        </div>
        <div class="mb-3">
          <label for="address2">상세주소</label>
          <select-gugun :sidoCode=sidoCode @select-gugun="selectGugun"></select-gugun>
        </div>
        <div class="mb-3">
          <label for="address2">관광지 유형</label>
          <select class="form-select me-2 w-100" @select-contentTypeId="selectContentType">
            <option value="0" selected>관광지 유형</option>
            <option value="12">관광지</option>
            <option value="14">문화시설</option>
            <option value="15">축제공연행사</option>
            <option value="25">여행코스</option>
            <option value="28">레포츠</option>
            <option value="32">숙박</option>
            <option value="38">쇼핑</option>
            <option value="39">음식점</option>
          </select>
        </div>
        <button class="btn btn-outline-success w-50" @click="search" style="width: 200px">검색</button>
      </form>
    </div>

    <div class="d-flex align-items-top result-spot w-75">
      <div id="map" class="mt-3" style="width: 700px; height: 700px"></div>
      <div class="row w-50">
        <table class="table table-hover">
          <thead>
            <tr style="color: #2eca6a; font-weight: bolder;">
              <th>대표이미지</th>
              <th>관광지명</th>
              <th>주소</th>
            </tr>
          </thead>
          <tbody id="trip-list">
            <!-- <tr v-for="attraction in attractionList" :key="attraction.contentId">
              <td><img :src="attraction.firstImage" alt="attraction" style="width: 100px; height: 100px"></td>
              <td>{{ attraction.title }}</td>
              <td>{{ attraction.addr1 }}</td>
            </tr> -->
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations } from "vuex";
import SelectSido from "@/components/item/SelectSido.vue";
import SelectGugun from "@/components/item/SelectGugun.vue";
import http from "@/api/http";
const itemStore = "itemStore";

export default {
  name: "KakaoMap",
  components: {
    SelectSido,
    SelectGugun,
  },

  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      contentTypeId: null,
      markerPositions1: [
        [33.452278, 126.567803],
        [33.452671, 126.574792],
        [33.451744, 126.572441],
      ],
      markerPositions2: [
        [37.499590490909185, 127.0263723554437],
        [37.499427948430814, 127.02794423197847],
        [37.498553760499505, 127.02882598822454],
        [37.497625593121384, 127.02935713582038],
        [37.49629291770947, 127.02587362608637],
        [37.49754540521486, 127.02546694890695],
        [37.49646391248451, 127.02675574250912],
      ],
      markers: [],
      infowindow: null,
      attractionList: [],
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" + process.env.VUE_APP_KAKAO_MAP_API_KEY;
      document.head.appendChild(script);
    }

  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    ...mapActions(itemStore, ["getGugun"]),
    ...mapMutations(itemStore, ["CLEAR_GUGUN_LIST"]),
    selectSido(sidoCode) {
      this.sidoCode = sidoCode;
    },
    selectGugun(gugunCode) {
      this.gugunCode = gugunCode;
    },
    selectContentType(contentTypeId) {
      this.contentTypeId = contentTypeId;
    },

    search() {
      console.log("===========" + this.sidoCode + this.gugunCode + this.contentTypeId)
      // 검색 버튼 클릭 시 호출되는 함수
      // API 호출 및 검색 결과 데이터를 attractionList에 할당하는 로직
      // const sidoCode = this.sidoCode;
      // const gugunCode = this.gugunCode;
      // const contentTypeId = this.contentTypeId;
      // if (sidoCode !== '0' && gugunCode !== '0' && contentTypeId !== '0') {
      //   // API 호출을 위한 요청 파라미터 설정
      // }

      http.get(`trip/search`, {
        sidoCode: this.sidoCode,
        gugunCode: this.gugunCode,
        contentTypeId: this.contentTypeId
      })
        .then(({ data }) => {
          console.log(data);
          this.attractionList = data;
        })
        .catch(({ response }) => {
          alert('오류 메세지: ' + response.data);
        })

    },
    // watch: {
    //   selectedSido(newValue) {
    //     if (newValue !== '0') {
    //       this.loadGugunList(newValue);
    //     } else {
    //       this.selectedGugun = '0';
    //     }
    //   }
    // }
  }
}
</script>