<template>
  <b-tr>
    <b-td>{{ noticeId }}</b-td>
    <b-th class="text-left">
      {<router-link :to="{ name: 'noticeview', params: { noticeId: noticeId } }">{{ title }}</router-link>}
    </b-th>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ adminId }}</b-td>
    <b-td>{{ registerTime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "NoticeListItem",
  props: {
    noticeId: Number,
    adminId: String,
    title: String,
    hit: Number,
    registerTime: String,
  },
  filters: {
    dateFormat(registerTime) {
      return moment(new Date(registerTime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
