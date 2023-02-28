
const state = () => ({
  updating: false,
  deleting: false,
  showModal: false,
  showAddForm: false,
});

const actions = {
  setUpdatingAct: ({ commit }) => commit('setUpdating'),
  setDeletingAct: ({ commit }) => commit('setDeleting'),

  setChkIdxAct: ({ commit }, payload) => commit('setChkIdx', payload),
  setChkUpdIdxAct: ({ commit }, payload) => commit('setChkUpdIdx', payload),
  setChkDelIdxAct: ({ commit }, payload) => commit('setChkDelIdx', payload),
  
  setShowModalAct: ({ commit }, payload) => commit('setShowModal', payload),
  setShowAddFormAct: ({ commit }, payload) => commit('setShowAddForm', payload),
  
}

const mutations = {
  setUpdating: (state) => { state.updating = !state.updating; },
  setDeleting: (state) => { state.deleting = !state.deleting; },

  setShowModal: (state, payload) => { state.showModal = payload; },
  setShowAddForm: (state, payload) => { state.showAddForm = payload; },
  
} 


const getters = {
  getChkIdx: (state) => state.chkIdx,
  getChkUpdIdx: (state) => state.chkUpdIdx,
  getChkDelIdx: (state) => state.chkDelIdx,
  getUpdating: (state) => state.updating,
  getDeleting: (state) => state.deleting,
  getShowModal: (state) => state.showModal,
  getShowAddForm: (state) => state.showAddForm,
}


export default {
  namespaced:true,
  state,
  actions,
  mutations,
  getters,
};
