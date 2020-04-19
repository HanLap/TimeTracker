import {TimerImpl, TimerInstance} from "@/model/TimerInstance";
import {StateType} from "@/store/index";
import API from "@/util/API";
import moment from "moment";


const fetch = async ({commit}: any) => {
  try {
    const res = await API.get<TimerInstance[]>('/timer');

    commit('add', res.data.map(TimerImpl.parse));
  } catch (e) {

  }
}

const add = async ({commit}: any, timer: TimerInstance) => {
  try {
    const res = await API.post<TimerInstance>('/timer', timer)

    commit('add', [TimerImpl.parse(res.data)]);
  } catch (e) {
    console.log(e)
  }
}

const remove = async ({commit}: any, timer: TimerInstance) => {
  try {
    await API.delete(`/timer/${timer.id}`)
    commit('remove', timer)
  } catch (e) {
    console.log(e)
  }
}

const toggle = ({commit}: any, payload: TimerInstance) => {
  const now = moment.utc();

  if (payload.endTime) {
    commit('start', {timer: payload, instant: now});
    API.put(`/timer/${payload.id}/start`, {instant: now});
  } else {
    commit('pause', {timer: payload, instant: now});
    API.put(`/timer/${payload.id}/stop`, {instant: now});
  }
}

const rename = async ({commit}: any, payload: TimerInstance) => {
  commit('rename', payload);

  try {
    await API.put(`/timer/${payload.id}`, {name: payload.name})
  } catch (e) {
    console.log(e)
  }
}

export const actions = {
  add,
  remove,
  fetch,
  toggle,
  rename
}
