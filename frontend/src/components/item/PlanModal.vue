<template>
    <div>
        <div class="modal-dialog modal-xl">
            <div class="modal-content border-0">
                <div class="modal-header border-0">
                    <button @click="$emit('close')" class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body text-center pb-5">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-8">
                                <div style="height:460px; overflow:auto;">
                                <table class="table table-hover">
                                    <thead>
                                    <tr style="color: #2eca6a; font-weight: bolder;">
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
                            </div>
                                <p class="mb-4">
                                    총거리 : {{result.totalDistance}}m
                                </p>
                                <p>
                                    도보 : {{ result.walkTime }}분
                                </p>
                                <p>
                                    자전거 : {{ result.cycleTime }}분
                                </p>
                                <button @click="complete" class="btn btn-danger" data-bs-dismiss="modal">
                                    <i class="fas fa-xmark fa-fw"></i>
                                    완료
                                </button>
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
    methods: {
        complete() {

        }
    }
    
};
</script>

<style scoped></style>