<script setup>
import { getUserInfo } from '@/api/user';
import router from '@/router';
import { useUserStore } from '@/stores/user';
import { ref } from 'vue';

// 获取用户信息存储
const userStore = useUserStore()
getUserInfo().then(res => {
    if (res.data.code === 200) {
        userStore.setUserInfo(res.data.data)
    }
})



const currentRouter = ref(location.pathname)

//路由切换后执行
router.afterEach((to) => {
    if (to == currentRouter.value) {
        return
    }
    currentRouter.value = to.path
})

const changeRouter = (path) => {
    if (path == currentRouter.value) {
        return
    }
    router.push(path)
}


// const userAgent = navigator.userAgent
// console.log(userAgent);
// if (userAgent.match(/Mobile/i) || userAgent.match(/iPhone/i)) {
//     console.log('手机')
// } else if (userAgent.match(/Windows/i)) {
//     console.log("电脑");
// } else {
//     console.log("平板");
// }



</script>
<template>
    <div class="wrapper">
        <div class="left-tool-bar">
            <div class="up-bar">
                <div class="header">
                    <img :src="userStore.userInfo.avatar">
                    <span class="status-circle"></span>
                </div>

                <div class="tool-item" v-if="!currentRouter.includes('/message')" @click="changeRouter('/message')">
                    <span class="iconfont icon-xinxi1" style="font-size: 20px;"></span>
                </div>
                <div class="tool-item forest-router-isActive" v-else>
                    <el-icon>
                        <Comment />
                    </el-icon>
                </div>
                <div class="tool-item" v-if="!currentRouter.includes('/contacts')" @click="changeRouter('/contacts')">
                    <el-icon>
                        <User />
                    </el-icon>
                </div>
                <div class="tool-item forest-router-isActive" v-else>
                    <el-icon>
                        <UserFilled />
                    </el-icon>
                </div>
                <div class="tool-item" v-if="!currentRouter.includes('/circleOfFriends')"
                    @click="changeRouter('/circleOfFriends')">
                    <el-icon>
                        <Compass />
                    </el-icon>
                </div>
                <div class="tool-item forest-router-isActive" v-else>
                    <span class="iconfont icon-zhinanzhen" style="font-size: 25px;"></span>
                </div>

            </div>
            <div class="setting">
                <div class="tool-item" v-if="!currentRouter.includes('/themeCenter')"
                    @click="changeRouter('/themeCenter')">
                    <el-icon>
                        <Operation />
                    </el-icon>
                </div>
                <div class="tool-item  forest-router-isActive" v-else style="color: var(--my-frame-color);">
                    <el-icon>
                        <Operation />
                    </el-icon>
                </div>
            </div>
        </div>
        <div class="right-box-wrapper">
            <router-view></router-view>
        </div>
    </div>
</template>
<style>
body {
    background-color: #fff;
}

/* Webkit浏览器（如Chrome、Safari） */
::-webkit-scrollbar {
    width: 6px;
}

/* 滚动条轨道 */
::-webkit-scrollbar-track {
    background-color: #ffffff00;
}

/* 滚动条 */
::-webkit-scrollbar-thumb {
    background-color: #BBBBBB;
    border-radius: 10px;
    position: fixed;
}

.wrapper {
    width: 1200px;
    height: 800px;
    background-color: #F5F5F5;
    margin: 100px auto;
    border: 1px solid #DFDDDD;
    box-shadow: 1px 1px 10px 5px #cccccc67;
    display: flex;

    .left-tool-bar {
        background-color: #F2F2F2;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: space-between;
        border-right: 1px solid #E9E9E9;
        width: 50px;

        .up-bar {
            display: flex;
            flex-direction: column;
            align-items: center;

            .header {
                margin-top: 30px;
                width: 32px;
                height: 32px;
                cursor: pointer;
                position: relative;

                img {
                    width: 100%;
                    height: 100%;
                    border-radius: 5px;
                }

                .status-circle {
                    width: 12px;
                    height: 12px;
                    position: absolute;
                    right: -2px;
                    bottom: -2px;
                    border-radius: 50%;
                    background-color: var(--my-online-color);
                    border: 2px solid #F2F2F2;
                }
            }
        }

        .tool-item {
            cursor: pointer;
            margin-top: 15px;
            font-size: 24px;
            height: 25px;
            width: 34px;
            height: 34px;
            display: flex;
            justify-content: center;
            align-items: center;
            color: var(--tool-icon-color);

            &:hover {
                background-color: #E8E8E8;
                border-radius: 5px;
            }
        }

        .setting {
            margin-bottom: 30px;

            .tool-item {
                cursor: pointer;
                margin-top: 15px;
                font-size: 23px;
                height: 25px;
                width: 34px;
                height: 34px;
                display: flex;
                justify-content: center;
                align-items: center;
                color: var(--tool-icon-color);

                &:hover {
                    background-color: #E8E8E8;
                    border-radius: 5px;
                }
            }

        }
    }
}

.right-box-wrapper {
    height: 100%;
    width: calc(100% - 50px);
    background-color: #F2F2F2;
}
</style>