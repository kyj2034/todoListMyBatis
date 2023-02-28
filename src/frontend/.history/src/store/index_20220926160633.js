import Vue from 'vue';
import Vuex from 'vuex';

import todoListStore from './todoListStore';
import editModeStore from './editModeStore';
import { _ } from 'core-js';

Vue.use(Vuex);

export default vue Vuex.Store({
  modules: _.merge(
    {
      '/todoItemStore': todoListStore,
      '/editmodeStore': 
    }
  )
})
