<template>
  <el-menu
    default-active="1"
    class="el-menu-vertical-demo">
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-timer"></i>
        <span>Timers</span>
      </template>
      <el-menu-item-group title="Default">
        <el-menu-item index="1-1" @click="$router.push('/')">Home</el-menu-item>
        <el-menu-item index="1-2" @click="$router.push('/about')">About</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<script>
  export default {
    name: "Nav"
  }
</script>

<style scoped>

</style>
