<template>
  <b-container class="bv-example-row">
    <b-row class="mt-3">
      <b-col cols="11" class="bg-light p-2 pl-5 text-left">
        <span :class="{ completed: todo.completed }" @click="updateTodoStatus">{{ todo.title }}</span>
      </b-col>
      <b-col class="p-2"><b-button variant="danger" @click="deleteTodo">X</b-button></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "TodoListItem",
  props: {
    todo: Object,
  },
  methods: {
    deleteTodo() {
      console.log("deleteTodo Call");
      this.$store.dispatch("deleteTodo", this.todo);
    },
    updateTodoStatus() {
      this.$store.dispatch("updateTodoStatus", this.todo);
    },
  },
};
</script>

<style scoped>
.completed {
  text-decoration: line-through;
  font-style: italic;
  font-weight: bold;
}
</style>
