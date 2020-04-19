import {TimerInstance} from "@/model/TimerInstance";
import {Moment} from "moment";
import {StateType} from '.';


const add = (state: StateType, payload: TimerInstance[]) => {
  state.timers.push(...payload)
}

const remove = (state: StateType, payload: TimerInstance) => {
  state.timers = state.timers.filter(t => payload.id !== t.id)
}

const pause = (state: StateType, {timer: {id}, instant}:
  { timer: TimerInstance; instant: Moment }) => {
  const timer = state.timers.find(t => t.id === id);
  if (!timer) {
    return
  }

  timer.endTime = instant;
}

const start = (state: StateType, {timer: {id}, instant}:
  { timer: TimerInstance; instant: Moment }) => {
  const timer = state.timers.find(t => t.id === id)
  if (!timer) {
    return
  }
  const duration = timer.endTime?.diff(timer.startTime);

  timer.startTime = instant.subtract(duration);
  timer.endTime = undefined;
}

const rename = (state: StateType, {id, name}: TimerInstance) => {
  const timer = state.timers.find(t => t.id = id);
  if (!timer) {
    return;
  }

  timer.name = name;
}

export const mutations = {
  add,
  remove,
  pause,
  start,
  rename,
}
