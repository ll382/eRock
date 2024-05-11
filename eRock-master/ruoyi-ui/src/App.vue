<template>
  <div id="app">
    <router-view />
    <theme-picker />
  </div>
</template>

<script>
import ThemePicker from "@/components/ThemePicker";

export default {
  name: "App",
  components: { ThemePicker },
  metaInfo() {
    return {
      title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
      titleTemplate: title => {
        return title ? `${title} - ${process.env.VUE_APP_TITLE}` : process.env.VUE_APP_TITLE
      }
    }
  }
};
</script>
<style>
#app .theme-picker {
  display: none;
}
.el-menu.el-menu--horizontal {
  border-bottom: 0px solid black;
}
html, body { /* 隐藏滚动条 */ /* 确保HTML和BODY元素占满整个视口高度 */
  overflow: hidden; 
  height: 100%; 
}
</style>
