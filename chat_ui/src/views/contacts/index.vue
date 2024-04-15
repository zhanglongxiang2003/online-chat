<script setup>
import { useFrameThemeStore } from "@/stores/frameTheme";
import serchBox from "@/components/searchBox/index.vue";
import { ref } from "vue";
import router from "@/router";

const frameThemeStore = useFrameThemeStore()

if (router.currentRoute.value.path === "/contacts") {
    frameThemeStore.setIsShowFriendDetail(false)
}

//好友栏宽度
const LeftWidth = ref(200)
LeftWidth.value = frameThemeStore.myFrameLeftWidth

//跳转页面
const toPage = (path) => {
    if (location.pathname + location.search === path) {
        return
    }

    //是否展示详情页面
    if (!frameThemeStore.isShowFriendDetail) {
        frameThemeStore.setIsShowFriendDetail(true)
    }
    router.push(path)
}

// 当前是好友还是群聊好友
const current = ref("friend")



//选择好友 查看好友详情
const selectFriend = (e, friendId) => {
    try {
        document.querySelector(".wrapper-box .left-friend-bar .friends-box .contacts-category-box-list .is-friend-active").classList.remove("is-friend-active")
    } catch (error) { }
    e.currentTarget.classList.add("is-friend-active")

    //是否展示详情页面
    if (!frameThemeStore.isShowFriendDetail) {
        frameThemeStore.setIsShowFriendDetail(true)
    }
    toPage("/contacts/friendDetail?friendId=" + friendId)
}

//选择群聊 查看群聊详情
const selectGroup = (e, id) => {
    try {
        document.querySelector(".wrapper-box .left-friend-bar .friends-box .contacts-category-box-list .is-friend-active").classList.remove("is-friend-active")
    } catch (error) { }
    e.currentTarget.classList.add("is-friend-active")

    //是否展示详情页面
    if (!frameThemeStore.isShowFriendDetail) {
        frameThemeStore.setIsShowFriendDetail(true)
    }
    toPage("/contacts/groupDetail?groupId=" + id)
}

//移动端title
const title = ref("")
const setTitle = (t) => {
    title.value = t
}

</script>

<template>
    <div class="wrapper-box">
        <div class="left-friend-bar"><!-- :style="'width:' + LeftWidth + 'px;'" -->

            <serchBox @toPage="toPage"></serchBox>
            <div class="friends-box">
                <div class="forest-active notification-item forest-hover"
                    @click="toPage('/contacts/notificationList?type=friend')">
                    <span class="text">好友通知</span>
                    <el-icon>
                        <ArrowRight />
                    </el-icon>
                </div>
                <div class="forest-active notification-item forest-hover"
                    @click="toPage('/contacts/notificationList?type=group')">
                    <span class="text">群通知</span>
                    <el-icon>
                        <ArrowRight />
                    </el-icon>
                </div>

                <div class="friends-and-group-box">
                    <el-radio-group v-model="current">
                        <el-radio-button label="好友" value="friend" />
                        <el-radio-button label="群聊" value="group" />
                    </el-radio-group>
                </div>

                <el-collapse v-show="current === 'friend'" class="contacts-category-box-list">
                    <el-collapse-item title="我的好友" name="1">
                        <div class="contacts-category-box-list-item forest-hover" v-for="item in 3"
                            @click.stop="selectFriend($event, item)">
                            <img src="@/assets/image/header-img.jpg">
                            <div class="right-box-name">
                                <div class="name-and-time-up">
                                    <span class="name">Wife{{ item }}(天才阿梓)</span>
                                </div>
                                <div class="latest-message">
                                    <span class="friend-status-tool-text">[ 在线<i class="circle"></i>]</span>
                                    烂片也祝杀青快乐{{ item }}
                                </div>
                            </div>
                        </div>
                    </el-collapse-item>
                    <el-collapse-item title="家人" name="2">
                        <div class="contacts-category-box-list-item forest-hover" v-for="item in [4, 5, 6, 7]"
                            @click.stop="selectFriend($event, item)">
                            <img src="@/assets/image/header-img.jpg">
                            <div class="right-box-name">
                                <div class="name-and-time-up">
                                    <span class="name">Wife{{ item }}(天才阿梓)</span>
                                </div>
                                <div class="latest-message">
                                    <span class="friend-status-tool-text">[ 在线<i class="circle"></i>]</span>
                                    烂片也祝杀青快乐{{ item }}
                                </div>
                            </div>
                        </div>
                    </el-collapse-item>
                </el-collapse>
                <el-collapse v-show="current === 'group'" class="contacts-category-box-list">
                    <el-collapse-item title="我的群聊" name="1">
                        <div class="contacts-category-box-list-item forest-hover" v-for=" item  in  3 "
                            @click.stop="selectGroup($event, item)">
                            <img src="@/assets/image/OIP-C.jpeg">
                            <div class="right-box-name group-name">
                                <div class="name-and-time-up">
                                    <span class="name">这是我的群聊{{ item }}</span>
                                </div>
                            </div>
                        </div>

                    </el-collapse-item>

                </el-collapse>
            </div>
        </div>

        <div class="right-detail-box" :class="{ 'is-back': frameThemeStore.isShowFriendDetail }">
            <div class="header-box">
                <div class="left-back-icon-box" @click="frameThemeStore.setIsShowFriendDetail(false)">
                    <el-icon>
                        <ArrowLeft />
                    </el-icon>
                    <div>返回</div>
                </div>

                <div class="title">
                    {{ title }}
                </div>
            </div>
            <router-view @setTitle="setTitle"></router-view>
        </div>

    </div>
</template>

<style>
.wrapper-box {
    height: 100%;
    width: 100%;
    display: flex;

    .left-friend-bar {
        background-color: #fff;
        border-right: 2px solid var(--border-color);
        height: 100%;
        width: 300px;


        .friends-box {
            overflow-y: scroll;
            overflow-x: hidden;
            max-height: calc(100% - 60px);
            padding: 10px 0;

            .notification-item {
                display: flex;
                align-items: center;
                color: var(--his-text-color);
                font-size: 15px;
                height: 30px;
                justify-content: space-between;
                padding: 20px;

                &:last-child {
                    border-bottom: 1px solid #ededed;
                }
            }


            &::-webkit-scrollbar {
                width: 0px;
            }

            .friends-and-group-box {
                width: 100%;
                height: 50px;
                display: flex;
                align-items: center;
                justify-content: center;


                .el-radio-button__inner {
                    padding: 8px 32px;
                }
            }

            .contacts-category-box-list {
                .contacts-category-box-list-item {
                    height: 60px;
                    display: flex;
                    padding: 10px 20px;
                    background-color: #fff;
                    cursor: pointer;
                    user-select: none;

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
                                font-size: 13px;
                                display: -webkit-box;
                                -webkit-line-clamp: 1;
                                -webkit-box-orient: vertical;
                                overflow: hidden;
                                text-overflow: ellipsis;
                            }


                        }

                        .latest-message {
                            font-size: 12px;
                            display: flex;


                            .friend-status-tool-text {
                                display: flex;
                                justify-content: center;
                                align-items: center;
                                font-size: 11px;
                                margin-right: 5px;
                                position: relative;


                                .circle {
                                    width: 8px;
                                    height: 8px;
                                    border-radius: 50%;
                                    background-color: var(--my-online-color);
                                    margin-right: 1.5px;
                                    margin-left: 2px;
                                }
                            }
                        }
                    }

                    .group-name {
                        justify-content: center;
                        font-size: 15px;
                    }
                }
            }

        }
    }

    .right-detail-box {
        height: 100%;
        width: calc(100% - 300px);
        
        .header-box {
            display: none;
        }


    }
}
</style>