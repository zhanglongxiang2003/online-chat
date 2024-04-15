import { ref } from "vue";
import { defineStore } from "pinia";
//框架全局样式
export const useUserStore = defineStore(
    "userStore",
    () => {
        //用户信息
        const userInfo = ref({})
        function setUserInfo(value) {
            userInfo.value = value
        }

        //Token信息 assessToken refreshToken
        const token = ref(null)
        function setToken(value) {
            token.value = value
        }

        return { userInfo, setUserInfo, token, setToken };
    },
    {
        persist: true
    }
)