<script setup>
import { useFrameThemeStore } from "@/stores/frameTheme";
import { useFriendsStore } from "@/stores/friends";
import serchBox from "@/components/searchBox/index.vue";
import { onMounted, ref } from "vue";
import router from "@/router";
const userAgent = navigator.userAgent   // 设备标识
const frameThemeStore = useFrameThemeStore()
const friendsStore = useFriendsStore()

//好友栏宽度
const LeftWidth = ref(200)
LeftWidth.value = frameThemeStore.myFrameLeftWidth

//好友栏DOM
const friendList = ref([])
const selectFriend = (index) => {

    for (let i = 0; i < friendList.value.length; i++) {
        const item = friendList.value[i];
        item.classList.remove("is-friend-active")
    }
    friendList.value[index].classList.add("is-friend-active")
    useFriendsStore().setCurrentSelectFriendIndex(index);

    router.push("/message/wrapper?id=" + index)
}
//跳转添加好友/群 页面
const toPage = (path) => {
    if (!frameThemeStore.isShowFriendDetail) {
        frameThemeStore.setIsShowFriendDetail(true)
    }
    router.push(path)
}

onMounted(() => {
    //好友栏DOM
    friendList.value = document.querySelectorAll(".left-friend-bar > .friends-box > .friend-item")
    friendList.value[friendsStore.currentSelectFriendIndex].classList.add("is-friend-active")
    if (!userAgent.match(/Mobile/i) && !userAgent.match(/iPhone/i)) {
        router.push("/message/wrapper?id=" + friendsStore.currentSelectFriendIndex)
    }
})


</script>

<template>
    <div class="wrapper-box">
        <div class="left-friend-bar"><!-- :style="'width:' + LeftWidth + 'px;'" -->

            <serchBox @toPage="toPage"></serchBox>

            <div class="friends-box">


                <div class="friend-item" v-for="item in 3" @click="selectFriend(item - 1)">
                    <img src="@/assets/image/header-img.jpg">
                    <div class="right-box-name">
                        <div class="name-and-time-up">
                            <span class="name">Wife{{ item }}</span>
                            <span class="relative-time">昨天</span>
                        </div>
                        <div class="latest-message">
                            你在干嘛？{{ item }}</div>
                    </div>
                </div>


            </div>
        </div>

        <router-view></router-view>

    </div>
</template>

<style scoped>
.wrapper-box {
    height: 100%;
    width: 100%;
    display: flex;

    .left-friend-bar {
        background-color: #fff;
        border-right: 1px solid var(--border-color);
        height: 100%;
        flex: none;
        width: 300px;


        .friends-box {
            overflow-y: scroll;
            overflow-x: hidden;
            max-height: calc(100% - 60px);

            &::-webkit-scrollbar {
                width: 0px;
            }


            .friend-item {
                display: flex;
                padding: 10px;
                background-color: #fff;
                cursor: pointer;
                /* padding-right: -20px;
                margin-right: -10px; */

                img {
                    width: 42px;
                    height: 42px;
                    flex: none;
                    border-radius: 5px;
                }

                .right-box-name {
                    flex: 1;
                    display: flex;
                    flex-direction: column;
                    justify-content: space-between;
                    margin-left: 10px;
                    color: #999999;

                    .name-and-time-up {
                        display: flex;
                        justify-content: space-between;
                        height: 20px;

                        .name {
                            width: calc(100% - 30px);
                            color: #000000;
                            font-size: 15px;
                            display: -webkit-box;
                            -webkit-line-clamp: 1;
                            -webkit-box-orient: vertical;
                            overflow: hidden;
                            text-overflow: ellipsis;
                        }

                        .relative-time {
                            font-size: 13px;
                            margin-left: -30px;
                        }
                    }

                    .latest-message {
                        font-size: 12px;
                        display: -webkit-box;
                        -webkit-line-clamp: 1;
                        -webkit-box-orient: vertical;
                        overflow: hidden;
                        text-overflow: ellipsis;
                    }
                }
            }
        }
    }
}
</style> ./component/friendBarBox.vue