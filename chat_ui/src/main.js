import 'element-plus/dist/index.css'
import '../src/assets/style/css/common.css'
import '../src/assets/style/css/root.css'
import '../src/assets/icon/iconfont/iconfont.css'
import '../src/assets/style/UI/vant-ui.css'
import '../src/assets/style/UI/element-plus.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import ElementPlus from 'element-plus'
import App from './App.vue'
import router from './router'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import { forestCopyText } from './utils/common'
import { Tab, Tabs } from 'vant';



const app = createApp(App)
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)
app.use(pinia)

//全局注册element-plus图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

//复制文本 全局方法
app.config.globalProperties.$copy = forestCopyText



app.use(ElementPlus)
app.use(router)
app.use(Tab);
app.use(Tabs);


app.mount('#app')
