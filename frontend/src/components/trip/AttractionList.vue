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
        <select v-model="selectedSido" class="form-select me-2 w-100">
          <option value="0" selected>검색 할 지역 선택</option>
          <option v-for="sido in sidoList" :value="sido.sidoCode" :key="sido.sidoCode">{{ sido.sidoName }}</option>
        </select>
        <select v-model="selectedGugun" class="form-select me-2 w-100">
          <option value="0" selected>검색 할 지역 선택</option>
        </select>
        <select v-model="selectedContentType" class="form-select me-2 w-100">
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
        <button class="btn btn-outline-success w-50" type="submit" style="width: 200px">검색</button>
      </form>
    </div>

    <div class="d-flex align-items-top result-spot w-75">
      <!-- kakao map start -->
      <div id="map" class="mt-3" style="width: 700px; height: 700px"></div>
      <!-- kakao map end -->
      <div class="row w-50">
        <table class="table table-hover" v-show="showResults">
          <thead>
            <tr style="color: #2eca6a; font-weight: bolder;">
              <th>대표이미지</th>
              <th>관광지명</th>
              <th>주소</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="spot in searchResults" :key="spot.contentid" @click="goToDetail(spot.contentid)">
              <td><img :src="spot.firstimage" alt="Spot Image" width="80" height="80"></td>
              <td>{{ spot.title }}</td>
              <td>{{ spot.addr1 }}</td>
            </tr>
          </tbody>
        </table>
        <div class="alert alert-warning mt-3" role="alert" v-show="noResults">
          검색 결과가 없습니다.
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "KakaoMap",
  data() {
    return {
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
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
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
  },
};
</script>