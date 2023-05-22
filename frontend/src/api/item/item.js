import { apiInstance } from "../index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/trip/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/trip/gugun`, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList };
