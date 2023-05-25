<template>
    <div>
        <div class="modal-dialog modal-xl">
            <div class="modal-content border-0">
                <div class="modal-header border-0">
                    <button @click="$emit('close')" class="btn-close" type="button" data-bs-dismiss="modal"
                        aria-label="Close"></button>
                </div>
                <div class="modal-body text-center pb-5">
                    <div class="container">
                        <div class="row justify-content-center">
                            <h1>{{ result.title }}</h1><br>
                            <div class="col-lg-8">
                                <div style="height:460px; overflow:auto;">
                                    <table class="table table-hover">
                                        <tbody id="trip-list">
                                            <tr v-for="(attraction, index) in plans" :key="index">
                                                <td><b-img :src="attraction.imgPath"
                                                        style="width: 100px; height: 70px" v-if="attraction.imgPath"></b-img>
                                                    <b-img :src="attraction.firstImage"
                                                        style="width: 100px; height: 70px" v-if="attraction.firstImage"></b-img>
                                                </td>
                                                <td>{{ attraction.title }}</td>
                                                <td>{{ attraction.address1 }} {{ attraction.address2 }}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <p class="mb-4">
                                    총거리 : {{ result.totalDistance }}m
                                </p>
                                <p>
                                    도보 : {{ result.walkTime }}분
                                </p>
                                <p>
                                    자전거 : {{ result.cycleTime }}분
                                </p>
                                <!-- <button @click="$emit('complete')" class="btn btn-danger" data-bs-dismiss="modal">
                                    <i class="fas fa-xmark fa-fw"></i>
                                    완료
                                </button> -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    name: 'PlanModal',
    props: {
        plans: [],
        result: Object,
    },
};
</script>

<style scoped></style>