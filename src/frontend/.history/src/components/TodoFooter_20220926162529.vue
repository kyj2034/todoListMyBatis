<template>
  <div
    class="clearAllContainer"
    @click="clearAllAct"
  >
    <span class="clearAllBtn" >전체삭제</span>
  </div>
</template>

<script>
import { mapActions } from 'vuex';

const todoItemStorePrefix = '/todoItemStore';

export default {
  data: () => ({
    showClearBtn: false,
  }),
  methods: {
    ...mapActions(todoItemStorePrefix, ['clearAllAct']),

  },
};
</script>

<style scoped>
  .clearAllContainer {
    width: 8.5rem;
    height: 50px;
    line-height: 50px;
    background-color: white;
    border-radius: 5px;
    margin: 0 auto;
  }
  .clearAllContainer {
    box-shadow: 3px 3px 3px rgb(141, 138, 138);
    transition-duration: 0.3s;
    font-weight: 800;
    color: #e20303;
    display: black;
    cursor: pointer;
  }
  .clearAllContainer:active{
    box-shadow:none;
  }
</style>
