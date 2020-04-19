import {TimerInstance} from "@/model/TimerInstance";
import {actions} from "@/store/Actions";
import {mutations} from "@/store/Mutations";
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export interface StateType {
  timers: TimerInstance[];
}

export default new Vuex.Store<StateType>({
  state: {
    timers: []
  },
  mutations,
  actions,
  modules: {
  },
});
