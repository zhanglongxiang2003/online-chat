<script setup>
import { ref } from 'vue';
import { Search } from '@element-plus/icons-vue'
import { searchFriendAndGroup } from '@/api/friend'
import friendList from './friendList.vue'
import groupChatList from './groupChatList.vue'
const emits = defineEmits(['setTitle'])
emits('setTitle', '添加好友/群聊')

//搜索框的内容
const searchValue = ref('10003')

// 搜索结果
const searchResult = ref({
    friendList: [],
    groupChatList: []
})

//当前选择的搜索模式
const ModelActive = ref(1);
function onClickTab({ title }) {
    toSearch()
}

// 搜索
function toSearch() {
    if (searchValue.value === '') {
        return
    }
    let friendId = 0
    let groupId = 0
    if (ModelActive.value == 0) {
        friendId = searchValue.value
        groupId = searchValue.value
    } else if (ModelActive.value == 1) {
        friendId = searchValue.value
    } else if (ModelActive.value == 2) {
        groupId = searchValue.value
    }
    searchFriendAndGroup(friendId, groupId).then(res => {
        if (res.data.code == 200) {
            searchResult.value = res.data.data
            return
        }
        ElMessage.error(res.data.data)
    })
}


</script>


<template>
    <div class="contaner-box">
        <div class="titele-bar">
            <span>添加好友/群聊</span>
        </div>
        <div class="header-tool-serch-box">
            <el-input class="serch-box" v-model="searchValue" clearable placeholder="搜点什么吧..." :prefix-icon="Search"
                @keydown.enter="toSearch()" />
            <el-button class="serch-button" type="primary" @click="toSearch()">搜索</el-button>
        </div>
        <div class="nav-box-bar">
            <van-tabs v-model:active="ModelActive" @click-tab="onClickTab">
                <van-tab title="全部">
                    <div class="content-box">
                        <p class="list-title" v-show="searchResult.friendList.length > 0"><span>社交好友</span></p>
                        <friendList :list="searchResult.friendList"></friendList>
                        <p class="list-title" v-show="searchResult.groupChatList.length > 0"><span>社交群聊</span></p>
                        <groupChatList :list="searchResult.groupChatList"></groupChatList>
                    </div>
                </van-tab>
                <van-tab title="好友">
                    <div class="content-box">
                        <friendList :list="searchResult.friendList"></friendList>
                    </div>
                </van-tab>
                <van-tab title="群聊">
                    <div class="content-box">
                        <groupChatList :list="searchResult.groupChatList"></groupChatList>
                    </div>
                </van-tab>
            </van-tabs>

        </div>
        <div class="content-bar">

        </div>
    </div>
</template>
<style scoped>
.contaner-box {
    padding: 0 50px;
    height: calc(100%);

    .titele-bar {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 50px;

        span {}
    }

    .header-tool-serch-box {
        width: 100%;
        height: 70px;
        background-color: transparent;
        display: flex;
        flex-direction: row;
        align-items: center;
        padding: 0px 10px;

        .serch-box {
            width: calc(100% - 80px);
            height: 30px;
            margin-right: 10px;
            position: relative;

        }

        .serch-button {
            height: 29px;
            margin-right: 10px;
        }



    }


    .forest-active {}

    .iconfont {}

    .icon-tianjiahaoyou {}

    .icon-chuangjianqunliao {}

}

.nav-box-bar {
    height: calc(100% - 120px);
    overflow-y: scroll;

    .content-box {
        padding: 20px;
        height: 100%;

        .list-title {
            height: 30px;
            span {
                font-size: 12px;
                color: #666;
                border-left: 2px solid var(--my-frame-color);
                padding: 0 3px;
                border-radius: 2px;
            }
        }
    }
}

.content-bar {}
</style>