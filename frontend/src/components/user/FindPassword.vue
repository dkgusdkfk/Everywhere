<template>
    <div>
        <section class="intro-single">
            <div>
                <div class="row">
                    <div class="col-md-12 col-lg-8">
                        <div class="title-single-box">
                            <h1 class="title-single">비밀번호 찾기</h1>
                        </div>
                    </div>
                    <div class="col-md-12 col-lg-4">
                        <nav aria-label="breadcrumb"
                            class="breadcrumb-box d-flex justify-content-lg-end">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                                <li class="breadcrumb-item active" aria-current="page"><a
                                    href="user/findPwForm">Find Password</a></li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
            <div v-if="!this.findUser">
                <div class="input-form-backgroud row">
                    <div class="input-form col-md-12 mx-auto">
                        <div class="mb-3">
                            <label for="id">아이디</label> <input type="text"
                                class="form-control" id="id" name="id"
                                placeholder="id를 입력해주세요" v-model="id" required>
                        </div>
                        <div class="mb-3">
                            <label for="email">이메일</label> <input type="email"
                                class="form-control" id="email" name="email"
                                placeholder="이메일을 입력해주세요" v-model="email" required>
                        </div>
                        <button class="btn btn-primary btn-lg btn-block" @click="find">확인하기</button>
                    </div>
                </div>
            </div>

            <div v-else>
                <div class="input-form-backgroud row">
                    <div class="input-form col-md-12 mx-auto">
                        <h4 class="title-2 mt-4">
                            <strong>ID: </strong> <span class="color-text-a">{{findUser.id}}</span>
                        </h4>
                        <h4 class="title-2 mt-4">
                            <strong>PW: </strong> <span class="color-text-a">{{findUser.password}}</span>
                        </h4>
                    </div>
                </div>
            </div>
    </div>
</template>

<script>
import http from "@/api/http";

export default {
    name: 'FindPassword',
    components: {},
    data() {
        return {
            findUser: null,
            id: null,
            email: null,
        };
    },
    created() {},
    methods: {
        find() {
            http.post(`/user/find`, {
                id: this.id,
                email: this.email,
            }).then(({ data }) => {
                this.findUser = data;
            });
        }
    },
};
</script>

<style scoped></style>