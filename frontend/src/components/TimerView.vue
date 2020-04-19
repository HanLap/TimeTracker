<template>
  <div class="timer-container">
    <div v-for="timer in timers" :key="timer.id">
      <Timer v-bind:timer="timer"/>
    </div>
    <el-card class="box-card" shadow="hover" >
      <i class="add-icon el-icon-plus" v-on:click="addNewTimer"/>
    </el-card>
  </div>
</template>

<script lang="ts">

  import Timer from '@/components/Timer.vue';
  import store from '@/store';
  import moment from 'moment';
  import {Component, Vue} from 'vue-property-decorator';

  @Component({
    components: {Timer},
  })
  export default class TimerView extends Vue {

    showNewForm = false;

    get timers() {
      return store.state.timers
    }

    mounted() {
      store.dispatch('fetch')
        .catch(console.log);
    }


    addNewTimer() {
      const timer = {name: 'Timer', startTime: moment().utc()};
      store.dispatch('add', timer)
    }
  }
</script>

<style lang="scss">
  .timer-container {
    max-width: 100%;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
  }

  .add-icon {
    width: 100%;
    height: 100%;
    font-size: 5rem;
    text-align: center;
  }
</style>
