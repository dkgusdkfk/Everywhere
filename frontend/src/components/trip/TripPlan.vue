<template>
  <div>
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">여행 계획</h1>
            </div>
          </div>
        </div>
      </div>
    </section>

    <div class="map_wrap">
      <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
      <div id="menu_wrap" class="bg_white">
        <div class="option">
          <div>
            <label for="address1">주소</label>
            <select-sido @select-sido="selectSido"></select-sido>
          </div>
          <div>
            <label for="address2">상세주소</label>
            <select-gugun :sidoCode=sidoCode @select-gugun="selectGugun"></select-gugun>
          </div>
          <div>
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

        <ul id="placesList">
            <li class="item" v-for="attraction in attractionList" :key="attraction">
              <span><b-img :src="attraction.imgPath" style="float:left;width:70px; height:50px;margin:10px 0 0 10px;"></b-img></span>
              <div class="info">
                <h5>{{ attraction.title }}</h5>
                <span>{{ attraction.address1 }} {{ attraction.address2 }}</span>
                <span class="tel">{{ attraction.tel }}</span>
              </div>
            </li>
        </ul>
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
        obj.attraction = attraction;
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
      this.deleteMarker();

      this.markers = [];
      this.positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: position.latlng,
        });

        kakao.maps.event.addListener(marker, 'click', () => {
          this.selectAttraction = position.attraction;
          this.openModal();
        })

        this.markers.push(marker);
      });
      console.log("마커수 ::: " + this.markers.length);

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
    moveCenter(lat, lng) {
      this.map.setCenter(new kakao.maps.LatLng(lat, lng));
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
      http.get(`trip/search`, { params: params })
        .then(({ data }) => {
          this.attractionList = data.attractionList;
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
    },

    // Modal method
    openModal() {
      this.$refs['attractionModal'].show()

    },
    closeModal() {
      this.$refs['attractionModal'].hide()
    },
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
  background: #fff;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
}

.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {position:relative;width:100%;height:500px;}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:400px;margin:10px 0 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}

#placesList li {list-style: none;}
#placesList .item {position:relative;border-bottom:1px solid #888;overflow: hidden;cursor: pointer;min-height: 65px;}
#placesList .item span {display: block;margin-top:4px;}
#placesList .item h5, #placesList .item .info {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
#placesList .item .info{padding:10px 0 10px 0;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .info .jibun {padding-left:26px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
#placesList .info .tel {color:#009900;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(http://t1.daumcdn.net/mapjsapi/images/transparent.gif) no-repeat;}
#placesList .item .marker {background-position: 0 -10px;}

div label {
  font-weight: bold;
}
</style>