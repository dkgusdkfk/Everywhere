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
                                <!-- Portfolio Modal - Title-->
                                <h1 class="portfolio-modal-title text-secondary text-uppercase mb-0">
                                    {{attraction.title}}
                                </h1>
                                <!-- Icon Divider-->
                                <div class="divider-custom">
                                    <div class="divider-custom-line"></div>
                                    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
                                    <div class="divider-custom-line"></div>
                                </div>
                                <!-- Portfolio Modal - Image-->
                                <b-img class="img-fluid rounded mb-5" :src="attraction.imgPath" alt="..."
                                    style="width: 70%"></b-img>
                                <!-- Portfolio Modal - Text-->
                                <p class="mb-4">
                                    우편번호 : {{attraction.zipcode}}
                                </p>
                                <p>
                                    주소 : {{attraction.address1}} {{attraction.address2}}
                                </p>
                                <p v-if="attraction.tel">
                                    전화번호 : {{attraction.tel}}
                                </p>
                                <p>
                                    {{attraction.overview}}
                                </p>
                                <button @click="$emit('close')" class="btn btn-danger" data-bs-dismiss="modal">
                                    <i class="fas fa-xmark fa-fw"></i>
                                    Close Window
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
    name: 'AttractionModal',
    props: {
        attraction: Object,
    }
};
</script>

<style scoped></style>