import axios from '../plugin/axios';

const state = {
  newTodoItem: '',
  todoList: [],
}

const actions = {


  setTodoItemAct: ({ commit }, payload) => commit('setTodoItem', payload),
  setTodoListAct: ({ commit }, payload) => commit('setTodoList', payload),

    /**
     * TodoInput에서 받아온 todoItem을 localStorage에 set하고 todoList 배열에 push
     *
     * @param {*} commit
     * @param {Object} payload ::: Add 할 todoItem {val: String, finish: Boolean, finish: false}
     */
        // 부울이랑 데이터 메시지 받아오기
        async addTodoAct({commit}, payload) { // action에서는 데이터를 푸시하고 삭제하는 기능만!!

          const addRes = await axios.instance({
            method: 'POST',
            data: payload,
          });
          commit('saveListData', addRes.data.data);
          return addRes.data.success;
      },
  
      /**
       * localStorage에 있는 모든 데이터를 todoList 안에 담긴 todoItem의 key값으로 삭제
       * 
       * @param {*} commit
       */
      async clearAllAct({commit}) {

        commit('setTodoList', []);
        const clearRes = await axios.instance({
          method:'DELETE',
          url:'/all'
        })
        return clearRes.data.success;
      },
  
      /**
       * 선택한 todoList의 key List를 받아와 todoItem 삭제
       *
       * @param {*} commit
       * @param {List<String>} payload ::: 삭제할 투두리스트의 key List
       */
        // 부울이랑 데이터 메시지 받기
      async removeTodoAct({commit}, payload) {

        commit('removeListData');
        const removeRes = await axios.instance({
          method:'DELETE',
          url:`/${payload}`
        });

        return removeRes.data.success;
      },
  
      /**
       * todoList에서 선택된 index에 위치한 todoItem의 val 값을 수정한다.
       *
       * @param {*} state
       * @param {Object} payload ::: Update할 TodoItem {key: String, val:String, finish:Boolen}
       */
      // 부울이랑 데이터, 메시지 받기
      async updateTodoAct({commit}, payload) {

        commit('updateListDataTodo', payload.todo);
        
        const updRes = await axios.instance({
          method: 'PUT',
          url:`/${payload.key}`,
          data: payload
        });

        return updRes.data.success;
      },
  
      /**
       *완료한 todoList의 Object를 받아와 해당 todoItem의 finish를 false 에서 true로 변경하여 todoItem의 style을 변경시킴
       *
       * @param {*} state
       * @param {Object} payload ::: finish 변경된 TodoItem {key: String, val: String, finish: Boolean}
       */
        // 부울 메시지 받기 
      async finishTodoAct({commit}, { key, todo, finish, idx}) {
        const finItem = { todo, finish, key };

        commit('updateListDataFin', idx);

        const updRes = await axios.instance({
          method:'PUT',
          url:`/${key}`,
          data: finItem
        });

        return updRes.data.success;
      },
    /**
     * localStorage에 저장되어 있는 데이터를 받아와 todoList 배열에 담는다
     *
     * @param {*} state
     */
    async loadTodoAct({ commit }) {
      
      const loadRes = await axios.instance({
        method:'GET',
        url:'/all',
      });
      commit('setTodoList', loadRes.data.data);
      return loadRes.data.success;
    },

}

const mutations = {
    setTodoList: (state, payload) => { state.todoList = payload; },
}
