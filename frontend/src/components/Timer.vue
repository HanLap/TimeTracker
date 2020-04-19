<template>
  <el-card class="box-card clearfix">
    <div slot="header">
      {{timer.name}}
      <el-button class="delete-button"
                 type="danger" size="mini"
                 icon="el-icon-delete" circle
                 v-on:click="remove" style="padding: 4px"></el-button>
    </div>
    {{time}}
    <el-button class="play-button" type="success"
               v-bind:icon="icon"
               circle
               v-on:click="togglePause"></el-button>
  </el-card>
</template>

<script lang="ts">
  import {formatDuration} from "@/util/DurationUtil";
  import moment from 'moment';
  import {Vue, Component, Prop} from 'vue-property-decorator';
  import {TimerInstance} from '@/model/TimerInstance';
  import store from '@/store';

  @Component
  export default class Timer extends Vue {
    @Prop() timer!: TimerInstance;

    time = '00:00:00';

    get isRunning(): boolean {
      return !this.timer.endTime;
    }

    get icon() {
      return this.isRunning ? 'el-icon-video-pause' : 'el-icon-video-play';
    }


    constructor() {
      super();

      this.calcTime();
      setInterval(() => this.calcTime(), 1000);
    }

    calcTime() {

      const max = this.timer.endTime ? this.timer.endTime : moment().utc();
      this.time = formatDuration(moment.duration(max.diff(this.timer.startTime)));
      formatDuration(moment.duration(max.diff(this.timer.startTime)));
    }

    async remove() {
      await store.dispatch('remove', this.timer);
    }

    togglePause() {
      store.dispatch('toggle', this.timer);
    }
  }
</script>

<style lang="scss">
  .box-card {
    margin: .5rem;
    width: 10em;
    position: relative;
  }

  .delete-button {
    position: absolute;
    top: .75rem;
    right: .5rem;
  }

  .clearfix {
    overflow: auto;
  }

  .play-button {
    position: absolute;

    right: 0.5rem;
    bottom: 0.5rem;

    padding: 0px !important;

    > i {
      font-size: 2.5rem;
    }
  }
</style>
