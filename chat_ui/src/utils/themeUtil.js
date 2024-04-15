import { useFrameThemeStore } from '@/stores/frameTheme';


export function updateTheme() {



    // 加载完毕后设置主题样式
    const frameThemeStore = useFrameThemeStore()


    // frameThemeStore.setMyFrameColor("#22CC9F")
    // frameThemeStore.setHisFrameColor("#fcd3d39e")
    // frameThemeStore.setMyFrameLeftWidth(250)
    const root = document.documentElement;
    // 修改根元素的 CSS 变量
    root.style.setProperty('--el-color-primary', "var(--my-frame-color)");
    root.style.setProperty('--van-primary-color', "var(--my-frame-color)");
    root.style.setProperty('--el-border-radius-small', "10px");
    root.style.setProperty('--my-frame-color', frameThemeStore.myFrameColor);
    root.style.setProperty('--his-frame-color', frameThemeStore.hisFrameColor);
}