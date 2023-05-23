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
          <select v-model="contentTypeId" class="custom-select">
            <option value="null" disabled>선택하세요</option>
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
            <tr v-for="attraction in attractionList" :key="attraction.contentId">
              <td><b-img :src="attraction.imgPath" style="width: 100px; height: 100px"></b-img></td>
              <td>{{ attraction.title }}</td>
              <td>{{ attraction.address1 }} {{ attraction.address2 }}</td>
              <td><input type='button' @click = "increaseLikeCount(attraction.contentId)" id="hotplaceBtn" style="background-color: #00DE38;" value='핫플 등록!'/></td>
            </tr>
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
      attractionList: [],
      map: null,
      positions: [],
      markers: [],
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  watch: {
    attractionList() {
      console.log("관광지", this.attractionList);
      this.positions = [];
      this.attractionList.forEach((attraction) => {
        let obj = {};
        obj.title = attraction.title;
        obj.latlng = new kakao.maps.LatLng(attraction.latitude, attraction.longitude);

        this.positions.push(obj);
      });
      this.loadMaker();
    },
  },
  methods: {
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
    },
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=" +
        process.env.VUE_APP_KAKAO_MAP_API_KEY +
        "&autoload=false";
      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
    },
    loadMaker() {
      // 현재 표시되어있는 marker들이 있다면 marker에 등록된 map을 없애준다.
      console.log("1111");
      this.deleteMarker();
      console.log("2222");
      // 마커 이미지를 생성합니다
      //   const imgSrc = require("@/assets/map/markerStar.png");
      // 마커 이미지의 이미지 크기 입니다
      //   const imgSize = new kakao.maps.Size(24, 35);
      //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      // 마커를 생성합니다
      this.markers = [];
      this.positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: position.latlng, // 마커를 표시할 위치
          title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          //   image: markerImage, // 마커의 이미지
        });
        this.markers.push(marker);
      });
      console.log("마커수 ::: " + this.markers.length);

      // 4. 지도를 이동시켜주기
      // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
      const bounds = this.positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
      );

      this.map.setBounds(bounds);
    },
    deleteMarker() {
      console.log("마커 싹 지우자!!!", this.markers.length);
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          console.log(item);
          item.setMap(null);
        });
      }
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
      const params = {
        sidoCode: this.sidoCode,
        gugunCode: this.gugunCode,
        contentTypeId: this.contentTypeId
      };
      http.get(`trip/search`, {params: params})
        .then(({ data }) => {
          this.attractionList = data.attractionList;
          console.log(this.attractionList)
        })
        .catch(({ response }) => {
          alert('오류 메세지: ' + response.data);
        })
    },
    increaseLikeCount(id) {
			console.log(id)
            http.post(`/trip/hotRegist/${id}`).then(({ data }) => {
                let msg = "문제가 발생했습니다.";
                if (data === "success") {
                    msg = "추천되었습니다.";
                }
                alert(msg);
            })
		}
  },

}
</script>
<style scoped>
#select {
  display: inline-block;
  width: 100%;
  height: calc(1.5em + 0.75rem + 2px);
  padding: 0.375rem 1.75rem 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  vertical-align: middle;
  background: #fff ;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}
</style>