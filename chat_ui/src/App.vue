<script setup>
import { updateTheme } from '@/utils/themeUtil';

updateTheme()
</script>

<template>
  <div class="contaner-wrapper-box-vue">
    <router-view></router-view>
    <!-- <video src="@/assets/video/bg.mp4" muted autoplay loop ref="home-video-bgv" class="backgound-vedio-box">
      <source src="@/assets/video/bg.mp4" type="video/mp4">
    </video> -->
    <div class="backgound-shade"></div>
  </div>
</template>
<style>
.contaner-wrapper-box-vue {
  width: 100%;
  height: 100%;
  overflow: hidden;

  .backgound-vedio-box {
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    object-fit: cover;
    z-index: -2;
  }

  .backgound-shade {
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    object-fit: cover;
    z-index: -1;
    background-color: rgba(255, 255, 255, 0);
    backdrop-filter: blur(4px);
    -webkit-backdrop-filter: blur(4px);
    border: 0.8px solid rgba(255, 255, 255, 0.18);
    box-shadow: rgba(142, 142, 142, 0.19) 0px 6px 15px 0px;
    -webkit-box-shadow: rgba(142, 142, 142, 0.19) 0px 6px 15px 0px;
    border-radius: 0px;
    -webkit-border-radius: 0px;
    color: rgba(255, 255, 255, 0.45);
  }

}
</style>