<template>
  <b-tr>
    <b-td>{{ boardId }}</b-td>
    <b-th class="text-left">
      {<router-link :to="{ name: 'boardview', params: { boardId: boardId } }">{{ title }}</router-link>}
    </b-th>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ userId }}</b-td>
    <b-td>{{ registerTime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    boardId: Number,
    userId: String,
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
