import { ref, computed } from "vue";
import { defineStore } from "pinia";
//框架全局样式
export const useFrameThemeStore = defineStore(
  "frameTheme",
  () => {
    //主题颜色
    const myFrameColor = ref("#3d6fff");
    //对方背景颜色
    const hisFrameColor = ref("#ffffff");
    //左边好友栏 宽度
    const myFrameLeftWidth = ref("300");

    function setMyFrameColor(params) {
      myFrameColor.value = params;
    }
    function setMyFrameLeftWidth(params) {
      myFrameLeftWidth.value = params;
    }
    function setHisFrameColor(params) {
      hisFrameColor.value = params;
    }


    // 当前是否展示好友详情 聊天页 加好友页面...等副页面
    const isShowFriendDetail = ref(false)
    function setIsShowFriendDetail(params) {
      isShowFriendDetail.value = params
    }


    return { myFrameColor, myFrameLeftWidth, setMyFrameColor, setMyFrameLeftWidth, hisFrameColor, setHisFrameColor, isShowFriendDetail, setIsShowFriendDetail };
  },
  {
    persist: true
  }
)