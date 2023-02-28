import axios from 'axios';
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    newTodoItem: '',
    todoList: [],
    chkIdx: null,
    chkUpdIdx: null,
    chkDelIdx: [],
    Updating: false,
    deleting: false,
    showModal: false,
    showAddForm: false,
  },
  getters: {
    getTodoList: (state) => state.todoList,
    getChkIdx: (state) => state.chkIdx,
    getChkUpdIdx: (state) => state.chkUpdIdx,
    getChkDelIdx: (state) => state.chkDelIdx,
    getUpdating: (state) => state.Updating,
    getDeleting: (state) => state.deleting,
    getShowModal: (state) => state.showModal,
    getShowAddForm: (state) => state.showAddForm,
    getUuid: (state) => state.uuid,
  },
  mutations: {
    /* Setter 기능을 하는 mutation */
    setShowModal: (state, payload) => { state.showModal = payload; },
    setShowAddForm: (state, payload) => { state.showAddForm = payload; },
    setTodoList: (state, payload) => { state.todoList = payload; },
    setUpdating: (state) => { state.Updating = !state.Updating; },
    setDeleting: (state) => { state.deleting = !state.deleting; },
    setChkIdx: (state, payload) => {
      state.chkIdx = payload;
    },
    setChkUpdIdx: (state, payload) => {
      state.chkUpdIdx = payload;
    },
    setChkDelIdx: (state, payload) => {
      state.chkDelIdx = payload;
    },
    setTodayDate: (state, payload) => {
      state.todayDate = payload;
    },
  },
  actions: {
      /**
     * TodoInput에서 받아온 todoItem을 localStorage에 set하고 todoList 배열에 push
     *
     * @param {*} state
     * @param {Object} payload ::: Add 할 todoItem {val: String, finish: Boolean, finish: false}
     */
       async addTodoAct(state, payload) { // action에서는 데이터를 푸시하고 삭제하는 기능만!!
          const data = await axios.post('/todolist', payload).then(
          (response) => {
            console.debug(response);
            return response.data.data;
          },).catch((e) => {
            return e;
          });
          state.todolist.push(data);
      },
  
      /**
       * localStorage에 있는 모든 데이터를 todoList 안에 담긴 todoItem의 key값으로 삭제
       * 
       * @param {*} state
       */
      async clearAllAct({commit}) {
        // localStorage.clear() 는 위험함. 키 값으로 로컬 스토리지의 투두리스트 삭제
          // 부울 받기
          const clear = await axios.delete('/todolist/all').then(
          (response) => {
            console.log(response);
            commit('setTodoList', []);
            return response;
          },
        ).catch((e) => {
          console.debug(e);
          return e;
        });
      },
  
      /**
       * 선택한 todoList의 key List를 받아와 todoItem 삭제
       *
       * @param {*} state
       * @param {List<String>} payload ::: 삭제할 투두리스트의 key List
       */
      async removeTodoAct({commit}, payload) {
        await axios.delete(`/todolist/${payload}`).then(
          (response) => {
            console.debug(response);
            const list = commit('getTodoList');
            for(const idx of commit('getChkDelIdx')){
              list.splice(idx, 1);
            }
            commit('setTodoList', list);
            commit('setChkDelIdx', []);
          },
        );
      },
  
      /**
       * todoList에서 선택된 index에 위치한 todoItem의 val 값을 수정한다.
       *
       * @param {*} state
       * @param {Object} payload ::: Update할 TodoItem {key: String, val:String, finish:Boolen}
       */
      async updateTodoAct({commit}, payload) {
        // 수정 axios 메소드로 제작
        const alert = await axios.put(`/todolist/${payload.key}`, payload).then(
          (response) => {
            console.debug(response);
            const list =commit('getTodoList');
            list[commit('getChkUpdIdx')].todo = response.data.data.todo;
            commit('setTodoList', list);
            return response;
          },
        ).catch((e) => {
          console.debug(e);
          return e;
        })
  
        return alert;
      },
  
      /**
       *완료한 todoList의 Object를 받아와 해당 todoItem의 finish를 false 에서 true로 변경하여 todoItem의 style을 변경시킴
       *
       * @param {*} state
       * @param {Object} payload ::: finish 변경된 TodoItem {key: String, val: String, finish: Boolean}
       */
      async finishTodoAct(state, { key, todo, finish, idx }) {
        const finItem = { todo, finish, key };
  
        // 수정 axios 메소드로 제작.
        await axios.put(`/todolist/${key}`, finItem).then(
          (response) => {
            console.debug(response);
            state.todoList[idx].finish = !state.todoList[idx].finish;
          },
        ).catch((e) => {
          console.debug(e);
        });
      },
    /**
     * localStorage에 저장되어 있는 데이터를 받아와 todoList 배열에 담는다
     *
     * @param {*} state
     */
    async loadTodoAct({ commit }) {
      // 메소드로 제작하자
      const items = await axios.get('/todolist/all').then(
        (response) => {
          console.log(response.data.data);
          return response.data.data;
        },
      ).catch((e) => {
        console.debug(e);
      });

      commit('setTodoList', items);
    },
    /* 각 mutation method를 return 하는 action */
    setShowModalAct: ({ commit }, payload) => commit('setShowModal', payload),
    setShowAddFormAct: ({ commit }, payload) => commit('setShowAddForm', payload),
    setTodoItemAct: ({ commit }, payload) => commit('setTodoItem', payload),
    setTodoListAct: ({ commit }, payload) => commit('setTodoList', payload),
    setUpdatingAct: ({ commit }) => commit('setUpdating'),
    setDeletingAct: ({ commit }) => commit('setDeleting'),
    setChkIdxAct: ({ commit }, payload) => commit('setChkIdx', payload),
    setChkUpdIdxAct: ({ commit }, payload) => commit('setChkUpdIdx', payload),
    setChkDelIdxAct: ({ commit }, payload) => commit('setChkDelIdx', payload),
  },
});
