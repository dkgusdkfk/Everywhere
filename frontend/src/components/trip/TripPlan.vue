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

    <b-modal ref="planModal" centered hide-footer hide-header size="lg">
      <plan-modal :plans="plans" :result="result" @close="closeModal" v-if="plans"></plan-modal>
      <button @click="send" class="btn btn-danger" data-bs-dismiss="modal" style="align-content: center;">
          완료
      </button>
    </b-modal>

    <b-row style="margin: auto">
      <b-col cols="8">
        <div class="map_wrap">
          <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;" @mousedown.right="reset"></div>
          <span id="menu_wrap" class="bg_white">
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
              <button class="btn btn-outline-danger w-50" @click="search" style="width: 200px">검색</button>
            </div>

            <ul id="placesList">
              <li class="item" v-for="attraction in attractionList" :key="attraction.contentId"
                @click="moveCenter(attraction.latitude, attraction.longitude)">
                <span><b-img :src="attraction.imgPath"
                    style="float:left;width:70px; height:50px;margin:10px 0 0 10px;"></b-img></span>
                <div class="info">
                  <h5>{{ attraction.title }}</h5>
                  <span>{{ attraction.address1 }} {{ attraction.address2 }}</span>
                  <span class="tel">{{ attraction.tel }}</span>
                </div>
              </li>
            </ul>
          </span>
        </div>
      </b-col>
      <b-col cols="4">
        <b-row style="height:460px; overflow:auto;">
          <table class="table table-hover">
            <thead>
              <tr style="color: #ff4400; font-weight: bolder;">
                <th>대표이미지</th>
                <th>관광지명</th>
                <th>주소</th>
                <th></th>
              </tr>
            </thead>
            <tbody id="trip-list">
              <tr v-for="(attraction, index) in plans" :key="index">
                <td><b-img :src="attraction.imgPath" style="width: 100px; height: 70px"></b-img></td>
                <td>{{ attraction.title }}</td>
                <td>{{ attraction.address1 }} {{ attraction.address2 }}</td>
                <td><i class="bi bi-x-lg" aria-hidden="true" @click="cancel(index)"></i></td>
              </tr>
            </tbody>
          </table>
        </b-row>
        <b-row>
          <b-col cols="8">
            <input type="text" class="form-control" id="title" v-model="result.title" placeholder="여행 계획 제목을 입력해주세요" value="" required>
          </b-col>
          <b-col cols="4">
            <b-button @click="complete" style="background-color: #fa3939; width:100%">계획 완료</b-button>
          </b-col>
        </b-row>
      </b-col>
    </b-row>



  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import SelectSido from "@/components/item/SelectSido.vue";
import SelectGugun from "@/components/item/SelectGugun.vue";
import PlanModal from "@/components/item/PlanModal.vue";

import http from "@/api/http";

const itemStore = "itemStore";
const memberStore = "memberStore";

export default {
  name: "KakaoMap",
  components: {
    SelectSido,
    SelectGugun,
    PlanModal,
  },

  data() {
    return {
      // 검색
      sidoCode: null,
      gugunCode: null,
      contentTypeId: null,
      attractionList: [],

      // 마커
      map: null,
      positions: [],
      markers: [],

      // 선의 거리 계산
      drawingFlag: false,
      moveLine: null,
      clickLine: null,
      distanceOverlay: null,
      dots: [],

      // 계획
      plans: [],
      result: {
        title: "",
        totalDistance: 0,
        walkTime: 0,
        cycleTime: 0,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"])
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

        // 마커 클릭 시
        kakao.maps.event.addListener(marker, 'click', () => {
          if (!this.drawingFlag) {
            this.plans = [];
          }
          this.plans.push(position.attraction);
          this.clickMarker(position.latlng);
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

    // 선의 거리 계산
    clickMarker(clickPosition) {
      if (!this.drawingFlag) {
        this.drawingFlag = true;
        this.deleteClickLine();
        this.deleteDistance();
        this.deleteCircleDot();

        this.clickLine = new kakao.maps.Polyline({
          map: this.map,
          path: [clickPosition],
          strokeWeight: 3,
          strokeColor: '#db4040',
          strokeOpacity: 1,
          strokeStyle: 'solid',
        })

        this.moveLine = new kakao.maps.Polyline({
          strokeWeight: 3,
          strokeColor: '#db4040',
          strokeOpacity: 0.5,
          strokeStyle: 'solid'
        })

        this.displayCircleDot(clickPosition, 0);
      } else {
        var path = this.clickLine.getPath();
        path.push(clickPosition);
        this.clickLine.setPath(path);
        console.log(this.clickLine.getPath())
        var distance = Math.round(this.clickLine.getLength());
        this.displayCircleDot(clickPosition, distance);
      }
    },

    cancel(index) {
      this.plans.splice(index, 1);

      this.finish();
      for (var i = 0; i < this.plans.length; i++) {
        this.clickMarker(new kakao.maps.LatLng(this.plans[i].latitude, this.plans[i].longitude))
      }
      this.search()
    },

    finish() {
      if (this.drawingFlag) {
        this.moveLine.setMap(null);
        this.moveLine = null;

        var path = this.clickLine.getPath();
        if (path.length > 1) {
          if (this.dots[this.dots.length - 1].distance) {
            this.dots[this.dots.length - 1].distance.setMap(null);
            this.dots[this.dots.length - 1].distance = null;
          }
        }
        this.deleteClickLine();
        this.deleteCircleDot();
        this.deleteDistance();

        this.drawingFlag = false;
      }
    },

    reset() {
      this.finish();
      this.plans = [];
    },

    deleteClickLine() {
      if (this.clickLine) {
        this.clickLine.setMap(null);
        this.clickLine = null;
      }
    },

    showDistance(content, position) {
      if (this.distanceOverlay) {
        this.distanceOverlay.setPosition(position);
        this.distanceOverlay.setContent(content);
      } else {
        this.distanceOverlay = new kakao.maps.CustomOverlay({
          map: this.map,
          content: content,
          position: position,
          xAnchor: 0,
          yAnchor: 0,
          zIndex: 3,
        })
      }
    },

    deleteDistance() {
      if (this.distanceOverlay) {
        this.distanceOverlay.setMap(null);
        this.distanceOverlay = null;
      }
    },

    displayCircleDot(position, distance) {
      var circleOverlay = new kakao.maps.CustomOverlay({
        content: '<span class="dot"></span>',
        position: position,
        zIndex: 1
      });

      circleOverlay.setMap(this.map);

      if (distance > 0) {
        var distanceOverlay = new kakao.maps.CustomOverlay({
          content: '<div class="dotOverlay">거리 <span class="number">' + distance + '</span>m</div>',
          position: position,
          yAnchor: 1,
          zIndex: 2
        });

        distanceOverlay.setMap(this.map);
      }

      this.dots.push({ circle: circleOverlay, distance: distanceOverlay });
    },

    deleteCircleDot() {
      var i;

      for (i = 0; i < this.dots.length; i++) {
        if (this.dots[i].circle) {
          this.dots[i].circle.setMap(null);
        }

        if (this.dots[i].distance) {
          this.dots[i].distance.setMap(null);
        }
      }

      this.dots = [];
    },

    getTime(distance) {
      this.result.totalDistance = distance;

      var walkkTime = distance / 67 | 0;
      this.result.walkTime = walkkTime;

      var bycicleTime = distance / 227 | 0;
      this.result.cycleTime = bycicleTime;
    },

    // 계획 완료
    complete() {
      var distance = Math.round(this.clickLine.getLength());
      this.getTime(distance);
      this.openModal();
    },

    send() {
      console.log(this.userInfo.id)
      http.post(`trip/plan`, {
        userId: this.userInfo.id,
        title: this.result.title,
        distance: this.result.totalDistance,
        cycleTime: this.result.cycleTime,
        walkTime: this.result.walkTime,
        planList: this.plans,
      }).then(({ data }) => {
        let msg = "계획 완료 중 오류 발생"
        if (data === "success") {
          msg = "완료되었습니다.";
        }
        alert(msg);
      })
    },

    // Modal method
    openModal() {
      this.$refs['planModal'].show()

    },
    closeModal() {
      this.$refs['planModal'].hide()
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

.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
  font-size: 12px;
}

.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}

.map_wrap {
  position: relative;
  width: 100%;
  height: 500px;
}

#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 400px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}

.bg_white {
  background: #fff;
}

#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5F5F5F;
  margin: 3px 0;
}

#menu_wrap .option {
  text-align: center;
}

#menu_wrap .option p {
  margin: 10px 0;
}

#menu_wrap .option button {
  margin-left: 5px;
}

#placesList li {
  list-style: none;
}

#placesList .item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}

#placesList .item span {
  display: block;
  margin-top: 4px;
}

#placesList .item h5,
#placesList .item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

#placesList .item .info {
  padding: 10px 0 10px 0;
}

#placesList .info .gray {
  color: #8a8a8a;
}

#placesList .info .jibun {
  padding-left: 26px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;
}

#placesList .info .tel {
  color: #009900;
}

.dot {
  overflow: hidden;
  float: left;
  width: 12px;
  height: 12px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/mini_circle.png');
}

.dotOverlay {
  position: relative;
  bottom: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
  font-size: 12px;
  padding: 5px;
  background: #fff;
}

.dotOverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}

.number {
  font-weight: bold;
  color: #ee6152;
}

.dotOverlay:after {
  content: '';
  position: absolute;
  margin-left: -6px;
  left: 50%;
  bottom: -8px;
  width: 11px;
  height: 8px;
  background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white_small.png')
}

.distanceInfo {
  position: relative;
  top: 5px;
  left: 5px;
  list-style: none;
  margin: 0;
}

.distanceInfo .label {
  display: inline-block;
  width: 50px;
}

.distanceInfo:after {
  content: none;
}

div label {
  font-weight: bold;
}
</style>