import Vue from 'vue';
import Vuex from 'vuex';

import todoListStore from './todoListStore.js';
import editModeStore from './editModeStore.js';
import _ from 'lodash';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: _.merge(
    {
      '/todoListStore': todoListStore,
      '/editModeStore': editModeStore,
    }
  )
});
