<script setup>
import { ref } from 'vue';
import { login, code, register } from '@/api/user';
import { useUserStore } from '@/stores/user';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
const userStore = useUserStore()
const router = useRouter()

//是否是登录页面
const isLoginPage = ref(true)

//表单
const form = ref({
    nickName: '',
    email: '3065983144@qq.com',
    password: '123456',
    code: '',
    noSignIn: false,
})

if (userStore.token) {
    router.push('/message')
}
//登录方法
function loginHandle() {
    login(form.value).then(res => {
        if (res.data.code === 200) {
            userStore.setToken(res.data.data.token)
            router.push('/message')
            return
        }
        ElMessage.error(res.data.data)
    }).catch(err => {
        ElMessage.error(err.message)
    })
}

// 发送验证码
const sendButtonText = ref('发送验证码')
const sendButtonDisabled = ref(false)
function sendCode() {
    sendButtonDisabled.value = true
    let time = 60
    sendButtonText.value = `${time}秒后重发`
    const timer = setInterval(() => {
        time--
        sendButtonText.value = `${time}秒后重发`
        if (time === 0) {
            sendButtonText.value = '发送验证码'
            sendButtonDisabled.value = false
            clearInterval(timer)
        }
    }, 1000);
    code(form.value.email).then(res => {
        if (res.data.code === 200) {
            ElMessage.success(res.data.data)
            return
        }
        ElMessage.error(res.data.data)
    }).catch(err => {
        ElMessage.error(err.message)
        sendButtonText.value = '发送验证码'
        sendButtonDisabled.value = false
        clearInterval(timer)
    })
}

//注册账号
function toRegister() {
    register(form.value).then(res => {
        if (res.data.code === 200) {
            ElMessage.success("注册成功！")
        }
        if (res.data.code === 400) {
            ElMessage.error(res.data.data)
        }
    }).catch(err => {
        ElMessage.error("注册失败！" + err.data)
    })
}

</script>
<template>
    <div class="login_register-wrapper-box">
        <img src="../../assets/image/logo.png" class="logo">

        <div class="auth-container">
            <img class="auth-image" src="../../assets/image/home_wrapper.png">
            <img class="auth-image auth-image2" src="../../assets/image/home_wrapper.png">

            <form @submit.prevent="loginHandle" class="auth-form-container" id="login-container"
                :style="!isLoginPage ? 'display: none;' : ''">
                <div class="qrcode-login-icon" onmouseover="layer.tips('不支持扫码登录', this)" onmouseout="layer.closeAll()">
                </div>
                <h2 class="loginbox-title">帐号登录</h2>
                <input class="logininput" v-model="form.email" name="email" type="email" placeholder="邮箱账号"
                    id="login-mobile">
                <input class="logininput" v-model="form.password" name="password" type="password" placeholder="密码"
                    id="login-pwd">
                <div class="day30Status">
                    <el-checkbox v-model="form.noSignIn" name="noSignIn">30天内免登录</el-checkbox>
                </div>
                <button class="login-btn" type="submit">登 录</button>
                <p class="tool-bar-btn">
                    <a class="forgetpwd" style="color: #d2d1d1;margin-right:20px;">忘记密码</a>
                    <a class="registry" @click="isLoginPage = false">注册新账号</a>
                </p>
            </form>
            <form @submit.prevent="toRegister()" class="auth-form-container" id="registry-container"
                :style="isLoginPage ? 'display: none;' : ''">
                <h2 class="loginbox-title" style="margin-bottom: 10px">新账号注册</h2>
                <input class="logininput" v-model="form.nickName" placeholder="昵称" id="registry-username">
                <input class="logininput" v-model="form.email" placeholder="邮箱账号" id="registry-email">
                <input class="logininput" v-model="form.code" placeholder="验证码" id="registry-pic-code">
                <div class="pic-valid-code" id="pic-valid-code">
                    <el-button type="primary" @click.prevend="sendCode"
                        :disabled="form.email.length === 0 || sendButtonDisabled" link>{{
                            sendButtonText }}</el-button>
                </div>
                <!-- <img class="pic-valid-code" src="http://8.140.184.12:8101/auth/api/valid-code/code" id="pic-valid-code"> -->
                <input class="logininput" type="password" v-model="form.password" placeholder="密码" id="registry-pwd">
                <button class="login-btn" style="margin-top: 15px;" type="submit">立即注册</button>
                <p class="tool-bar-btn">
                    <a class="login" @click="isLoginPage = true">有账号去登录</a>
                </p>
            </form>
        </div>

    </div>
</template>


<style scoped>
body {
    background-color: whitesmoke;
}

.logo {
    width: 142px;
    position: fixed;
    top: 40px;
    left: 100px;
}

.auth-container {
    max-width: 1100px;
    height: 500px;
    box-sizing: border-box;
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin: 0 auto;
    margin-top: calc(200px);
}

.auth-image {
    width: 650px;
}

.auth-image2 {
    display: none;
}

.auth-form-container {
    width: 400px;
    height: 488px;
    background-color: white;
    border-radius: 12px;
    box-shadow: 0 2px 6px 0 rgb(0 0 0 / 10%);
    pointer-events: all;
    display: flex;
    flex-direction: column;
    align-items: center;
    position: relative;

    .tool-bar-btn {
        width: 350px;
        margin-top: 22px;
        display: flex;
        justify-content: center;
    }
}

.loginbox-title {
    padding: 48px 0 0;
    color: #141414;
    font-size: 24px;
    line-height: 32px;
    text-align: center;
    margin-bottom: 20px;
}

.logininput {
    position: relative;
    width: 350px;
    height: 50px;
    border-radius: 6px;
    border: 1px solid #d9dbe0;
    margin: 15px 0px 0px 0px;
    text-indent: 15px;
    font-size: 16px;
    font-family: Courier;
    line-height: 50px;
    padding-right: 10px;
}

.day30Status {
    height: 25px;
    margin-top: 15px;
    text-align: left;
    width: 350px;
    font-size: 14px;
    display: flex;
    align-items: center;

    #un-login {
        margin-right: 5px;

        &:checked {
            background-color: var(--my-frame-color)
        }
    }
}

.login-btn {
    position: relative;
    bottom: 0px;
    width: 350px;
    height: 50px;
    line-height: 50px;
    font-size: 20px;
    border: none;
    border-radius: 4px;
    background: var(--my-frame-color);
    color: #fff;
    cursor: pointer;
    text-align: center;
    display: block;
    margin-top: 30px;
}

.login-btn:hover {
    opacity: 0.8;
}

.forgetpwd,
.registry,
.login {
    position: relative;
    float: none;
    color: #585E6D;
    font-size: 14px;
    line-height: 20px;
    user-select: none;
}

.forgetpwd:after {
    content: '';
    padding-left: 1px;
    width: 0;
    height: 13px;
    background-color: #B7BCC7;
    position: absolute;
    top: 3px;
    right: -10px;
    color: #666;
}

.qrcode-login-icon {
    position: absolute;
    top: 5px;
    right: 5px;
    display: inline-block;
    vertical-align: middle;
    width: 72px;
    height: 72px;
    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAJAAAACQBAMAAAAVaP+LAAAAElBMVEVHcEx6pf96pf96o/96o/97o/8Pt1s1AAAABXRSTlMAfz/dXxFC4+EAAAEeSURBVGje7djRbcJAEEXReYgO0DSAlApcAiWg7b8VfpwI22sSdm8sVnr3M6CjQZDJhAjnnHMDlqWUiCjraj97emybKCiSgkRBkRQkCoqkIFFQvICqTzwE2sMNfTSU+2//4oP4KyQKWo/UDomCViN1QKKg5Ug90Ok6l53QT1+GXnSbpgmBVMqdeWlJQaKgSAoSBcVlAzXu7DMFhaFDIWH3Ubaffn+6vd+Hdm7vBkgUVL+9WyBRUHwk5Aat/j+goc2hS0CioPqXOdE4EgJV/6BE40gM5Eb+rvL5d6UZEgV9j9QPiYLmkQBIFLRcldE7EgEtVmX0joRA86oEErYZk4KE7eqkIP3HwWRoJCgpSNg1khQk7D7yXhp3LznnnDuqB9Ji/xpvUnncAAAAAElFTkSuQmCC) 50% no-repeat;
    -webkit-background-size: 100% 100%;
    background-size: 100% 100%;
}

.pic-valid-code {
    width: 100px;
    height: 50px;
    position: absolute;
    right: 37px;
    bottom: 204px;
    line-height: 50px;
    text-align: right;
}
</style>