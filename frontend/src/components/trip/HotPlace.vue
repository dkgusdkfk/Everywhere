<template>
    <div>
        <section class="intro-single">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-lg-8">
                        <div class="title-single-box">
                            <h1 class="title-single">Hot Place</h1>
                            <span class="color-text-a">숨겨진 핫 플레이스에 방문해보세요!</span>
                        </div>
                    </div>
                    <div class="col-md-12 col-lg-4">
                        <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="${root}/index.jsp">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page">커뮤니티
                                </li>
                                <li class="breadcrumb-item active" aria-current="page">Hot
                                    Place</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>

        <b-modal ref="attractionModal" centered hide-footer hide-header size="lg">
            <attraction-modal :attraction="selectAttraction" @close="closeModal" v-if="selectAttraction"></attraction-modal>
        </b-modal>

        <section class="agents-grid grid">
            <b-container>
                <b-row>
                    <b-card-group deck>
                        <div v-for="place in hotPlaces" :key="place.contentId">
                            <b-col cols="3">
                                <b-card class="card-box-d" :img-src="place.imgPath" style="width:310px; height:205px">
                                    <div class=" card-overlay card-overlay-hover">
                                        <div class="card-header-d">
                                            <div class="card-title-d align-self-center">
                                                <h3 class="title-d">
                                                    <a href="#" class="link-two" @click="openModal(place.contentId)"> {{ place.title }} </a>
                                                </h3>
                                            </div>
                                        </div>
                                        <div class="card-body-d">
                                            <div class="info-agents color-a">
                                                <p>
                                                    <strong>주소 : </strong> {{ place.address1 }} {{ place.address2 }}
                                                </p>
                                                <p>
                                                    <strong>추천수 : </strong> {{ place.count }}
                                                </p>
                                            </div>
                                        </div>
                                        <div class="card-footer-d">
                                            <div class="socials-footer d-flex justify-content-center">
                                                <ul class="list-inline">
                                                    <li class="list-inline-item"><a href="#"
                                                            @click="increaseLikeCount(place.contentId)" class="link-one">
                                                            <i class="bi bi-heart-fill" aria-hidden="true"></i>
                                                        </a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </b-card>
                            </b-col>
                        </div>
                    </b-card-group>
                </b-row>
            </b-container>
        </section>
    </div>
</template>

<script>
import { mapState } from "vuex";
import http from "@/api/http";
import AttractionModal from "@/components/item/AttractionModal.vue";

const memberStore = "memberStore";

export default {
    name: 'HotPlace',
    components: {
        AttractionModal
    },
    data() {
        return {
            hotPlaces: [],
            selectAttraction: null,
        };
    },
    created() {
        http.get(`/trip/hotPlace`).then(({ data }) => {
            this.hotPlaces = data;
        })
    },
    computed: {
        ...mapState(memberStore, ["userInfo"])
    },
    methods: {
        increaseLikeCount(id) {
            http.post(`/trip/hotRegist`, {
                contentId: id,
                userId: this.userInfo.id,
            })
                .then(({ data }) => {
                    let msg = "문제가 발생했습니다.";
                    if (data === "success") {
                        msg = "추천되었습니다.";
                    }
                    alert(msg);
                })
            this.$router.go();
        },

        // Modal method
        openModal(contentId) {
            http.get(`/trip/${contentId}`).then(({ data }) => {
                console.log(data)
                this.selectAttraction = data;
            })
            this.$refs['attractionModal'].show()
        },
        closeModal() {
            this.$refs['attractionModal'].hide()
        },
    },
};
</script>

<style scoped></style>