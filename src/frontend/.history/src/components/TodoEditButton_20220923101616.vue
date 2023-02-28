<template>
  <div class="group" >
    <div class="editBtnGroup">
      <span
        class="openContainer"
        id="editGroupOpen"
        @click="showEditBtnGroup"
      >
        <i
          class="btn fas fa-bars"
          aria-hidden="true"
        ></i>
      </span>
      <transition name="fade">
        <div
          class="btnGroup"
          v-show="editOpen"
        >
          <span
            class="btnContainer"
            id="add"
            @click="showAddForm()"
          >
            <span class="btn"> 등록 </span>
          </span>
          <span
            class="btnContainer"
            id="upd"
            @click="openUpdModal"
          >
            <span class="btn"> 수정 </span>
          </span>
          <span
            class="btnContainer"
            id="del"
            @click="openDelModal"
          >
            <span class="btn"> 삭제 </span>
          </span>
        </div>
      </transition>
    </div>
    <!--  경고 모달  -->
    <modal v-if="showWarnModal" @close="showWarnModal = false">
      <h3 slot="header">잠깐!</h3>
      <span slot="footer" @click="showWarnModal = false">
        먼저 할 일 목록을 선택해 주세요.
        <i class ="closeModalBtn fas fa-times" aria-hidden="true"></i>
      </span>
    </modal>
    <!--  수정 중 경고 모달  -->
    <modal
      v-if="showUpdWarnModal"
      @close="showUpdWarnModal = false"
    >
      <h3 slot="header">잠깐!</h3>
      <span slot="footer" @click="showUpdWarnModal = false">
        할 일을 입력하세요.
        <i class ="closeModalBtn fas fa-times" aria-hidden="true"></i>
      </span>
    </modal>
    <!--  수정 모달  -->
    <modal v-if="showUpdModal" @close="showUpdModal = false">
      <h3 slot="header"> 수정 </h3>
      <div slot="body">
        <input
          class="modalInput"
          type="text"
          v-model="updTodoVal"
          @keyup.enter="updTodo"
          placeholder="할 일을 적어주세요"
        >
      </div>
      <span slot="footer">
        <div>
          <span class="fixModalBtn" @click="updTodo">
            등록
          </span>
          <span class="fixModalBtn" @click="showUpdModal = false">
            취소
          </span>
        </div>
      </span>
    </modal>
    <!-- 삭제 모달 -->
    <modal v-if="showDelModal" @close="showDelModal = false">
      <h3 slot="header"> 삭제 </h3>
      <div slot="body">
        삭제하시겠습니까?
      </div>
      <span slot="footer">
        <div>
          <span class="delModalBtn" @click="delTodo">
            확인
          </span>
          <span class="delModalBtn" @click="showDelModal = false">
            취소
          </span>
        </div>
      </span>
    </modal>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import Modal from './common/ModalForm.vue';

export default {
  components: {
    Modal,
  },
  data: () => ({
    updTodoVal: '',
    updIdx: Number,
    editOpen: false,
    showWarnModal: false,
    showUpdWarnModal: false,
    showUpdModal: false,
    showDelModal: false,
  }),
  computed: {
    ...mapGetters([
      'getChkUpdIdx',
      'getChkDelIdx',
      'getShowAddForm',
      'getUpdating',
      'getDeleting',
      'getTodoList',
    ]),
  },
  methods: {
    ...mapActions([
      'setChkUpdIdxAct',
      'setChkDelIdxAct',
      'setShowAddFormAct',
      'setUpdatingAct',
      'setDeletingAct',
      'updateTodoAct',
      'removeTodoAct',
    ]),
    showEditBtnGroup() {
      this.editOpen = !this.editOpen;
      this.setUpdatingAct();
      this.setDeletingAct();
      if (!this.editOpen) {
        this.setShowAddFormAct(false);
      }
    },
    showAddForm() {
      if (this.getShowAddForm === true) {
        this.setShowAddFormAct(false);
      } else {
        this.setShowAddFormAct(true);
      }
    },
    openUpdModal() {
      console.debug(this.getChkUpdIdx);
      this.updIdx = this.getChkUpdIdx;
      if (this.getChkUpdIdx == null) {
        this.showWarnModal = !this.showWarnModal;
      } else {
        this.showUpdModal = !this.showUpdModal;
      }
    },
    openDelModal() {
      if (this.getChkDelIdx.length !== 0) {
        this.showDelModal = !this.showDelModal;
      } else {
        this.showWarnModal = !this.showWarnModal;
      }
    },
    /**
     * 선택한 할 일 목록 수정
     *
     */
    updTodo() {
      if (this.updTodoVal !== '') {
        const todo = this.updTodoVal && this.updTodoVal.trim();
        const item = {
          id: this.getTodoList[this.getChkUpdIdx].id,
          todo,
          finish: false,
          key: this.getTodoList[this.getChkUpdIdx].key };
        this.updateTodoAct(item);
      } else {
        this.showUpdWarnModal = !this.showUpdWarnModal;
      }
      this.updTodoVal = '';
      this.showUpdModal = !this.showUpdModal;
    },
    /**
     * 선택한 할 일 목록 삭제
     *
     */
    delTodo() {
      const delKeyItems = [];
      const delIdxList  = this.getChkDelIdx;
      
      console.debug(delIdxList);

      for(const idx of delIdxList){
        delKeyItems.push({key : this.getTodoList[idx].key, idx : idx}); 
        console.debug(delKeyItems);
      }

      this.removeTodoAct(delKeyItems);
      this.setChkDelIdxAct([]);
      this.showDelModal = !this.showDelModal;
    },
  },
};

</script>

<style>
  .btn {
    text-align: center;
    line-height: 30px;
  }
  .group {
    margin-left: 50%;
    padding-bottom: 3% ;
    display:block;
  }
  .openContainer {
    margin-left:65%;
    color : aliceblue;
  }
  .btnContainer {
    cursor: pointer;
    color: aliceblue;
    font-weight: 900;
    height: 2em;
    border-radius: 12px;
    margin-left: 0.3em;
  }
  .display {
    display : none;
  }
  .fade-enter { opacity: 0;}
  .fade-enter-active, .fade-leave-active { transition: opacity 0.5s ease-out;}
  .fade-leave-to { opacity: 0;}
  .closeModalBtn{
    display: block;
    margin-top: 1em;
  }
  .fixModalBtn, .delModalBtn{
    color: rgb(125, 125, 125);
    font-weight: 900;
    height: 2em;
    border-radius: 12px;
    margin-left: 0.3em;
    border : 2px solid rgb(110, 110, 110);
    padding: 0 1em 0 1em;
  }
  #add {
    background: linear-gradient(to right, #6478FB, #8763FB);
    padding: 0 2em 0 2em;
  }
  #upd {
    background: linear-gradient(to right, rgb(61, 186, 184), rgb(101, 206, 284));
    padding: 0 1em 0 1em;
  }
  #del {
    background: linear-gradient(to right, rgb(233, 100, 100), rgb(273, 140, 140));;
      padding: 0 1em 0 1em;
  }
  #editGroupOpen{
    background: linear-gradient(to right, rgb(100, 100, 100),rgb(100, 100, 100));;
    padding: 0 2em 0 2em;
  }
</style>
