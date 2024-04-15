import { refreshToken } from "@/api/user";
import router from "@/router";
import { useUserStore } from "@/stores/user";
import axios from "axios";
import { ElMessage } from "element-plus";

const request = axios.create({
    baseURL: 'http://172.26.1.213:9098',
    timeout: 10000,
})
const userStore = useUserStore()

// 添加请求拦截器
request.interceptors.request.use(async function (config) {

    //获取新tokne
    if (config.url === "/online/user/refreshToken") {
        config.headers["Authorization"] = `Bearer ${userStore.token.refreshToken}`
        config.headers["isRefreshToken"] = "true"
        return config
    }

    // 不加上token的请求
    const dischargedUrlList = ["/online/user/login", "/online/user/register", "/online/user/code"]
    dischargedUrlList.forEach(item => {
        if (config.url === item) {
            return config
        }
    })

    // 加上token的请求
    if (userStore.token !== null && userStore.token.assessToken !== null) {
        config.headers["Authorization"] = 'Bearer ' + userStore.token.assessToken
        return config
    }


    return config;
}, function (error) {
    // 对请求错误做些什么
    // console.log(error);
    return Promise.reject(error);
})

// 添加响应拦截器
request.interceptors.response.use(
    async function (response) {

        // asscssToken过期
        if (response.data.code === 403 && response.config.headers["isRefreshToken"] !== "true") {
            // 重新获取token
            return await refreshToken().then(async res => {
                userStore.setToken({
                    assessToken: res.data.data,
                    refreshToken: userStore.token.refreshToken
                })
                // 重新发送 token 过期的请求
                return request(response.config)
            }).then(res => {
                return res
            })
        } else if (response.data.code === 403 && response.config.headers["isRefreshToken"] === "true") {
            ElMessage.error("登录过期！请重新登录")
            useUserStore().setToken(null)
            router.push("/login")
        }

        //无法跟换时
        if (response.status === 401) {
            ElMessage.error("登录过期！请重新登录")
            useUserStore().setToken(null)
            router.push("/login")
        }


        // 响应成功
        // response.data = response.data.data

        return response
    },
    async function (error) {// 超出 2xx 范围的状态码都会触发该函数。

        // 后端未连接
        if (error.code === "ERR_NETWORK") {
            ElMessage.error("我靠，后台崩溃了！")
        }




        return Promise.reject(error);
    })


export default request;