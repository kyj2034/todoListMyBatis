
const state = () => ({
  chkIdx: null,
  chkUpdIdx: [],
  chkDelIdx: [],
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

