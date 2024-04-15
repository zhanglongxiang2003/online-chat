<script setup>
import router from '@/router';
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import groupDetailMemberPie from './groupDetailMemberPie.vue'
const emits = defineEmits(['setTitle'])
emits('setTitle', '群聊信息')

// 动态显示列表
const route = useRoute()
const groupId = ref(route.query.groupId)
router.beforeEach((to, from, next) => {
    groupId.value = to.query.groupId
    next()
})

//群介绍是否显示
const isShowGroupIntro = ref(false)

//群公告是否显示
const isShowAnnouncement = ref(false)

</script>

<template>
    <div class="contaner-box">
        <div class="friend-detail-wrapped-box">
            <div class="header-box">
                <el-avatar class="left-avatar" :size="100" src="/src/assets/image/OIP-C.jpeg" />
                <div class="right-info-box">
                    <div class="info-deatil">
                        <div class="nick-name">这是群号为{{ groupId }}的群聊</div>
                        <div class="forest-id">
                            群号: GROUP00{{ groupId }}
                            <span class="iconfont icon-fuzhi" @click="$copy('GROUP00' + groupId)"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="main-tool-wrapper-box">
                <div class="cell-box">
                    <div class="left-tool-name-box">
                        <span class="iconfont icon-box icon-beizhu"
                            style="font-size:13px;font-weight: 700;color: #555;"></span>
                        <span class="text overflow-text">群备注</span>
                    </div>
                    <div class="extend-box forest-active overflow-text">这是备注为{{ groupId }}的群</div>
                </div>
                <div class="cell-box">
                    <div class="left-tool-name-box">
                        <span class="iconfont icon-box icon-geren" style="font-weight: 700;color: #444;"></span>
                        <span class="text overflow-text">我在本群昵称</span>
                    </div>
                    <div class="extend-box forest-active overflow-text">前端练习时长两年半</div>
                </div>
                <div class="cell-box">
                    <div class="left-tool-name-box overflow-text">
                        <span class="iconfont icon-box icon-guizewendang"
                            style="font-size:16px;font-weight: 700;color: #555;"></span>
                        <span class="text overflow-text">群介绍</span>
                    </div>
                    <div class="extend-box forest-active overflow-text" @click="isShowGroupIntro = !isShowGroupIntro">
                        一起探讨前端知识搞钱钱！
                        <el-icon>
                            <ArrowRight />
                        </el-icon>
                    </div>

                    <el-dialog v-model="isShowGroupIntro" :title="`这是备注为${groupId}的群 — 群介绍`" width="800" draggable>
                        <span>一起探讨前端知识搞钱钱！</span>
                    </el-dialog>
                </div>
                <div class="cell-box">
                    <div class="left-tool-name-box">
                        <span class="iconfont icon-box icon-guangbo"
                            style="font-size:17px;font-weight: 700;color: #555;"></span>
                        <span class="text overflow-text">群公告</span>
                    </div>
                    <div class="extend-box forest-active" @click="isShowAnnouncement = !isShowAnnouncement">
                        <span class="overflow-text">本群是聊天学习的群，群里面禁止发布一些违法犯罪的信息。禁止黄赌毒</span>
                        <el-icon>
                            <ArrowRight />
                        </el-icon>
                    </div>
                    <el-dialog v-model="isShowAnnouncement" :title="`这是备注为${groupId}的群 — 群公告`" width="800" draggable>
                        <span>本群是聊天学习的群，群里面禁止发布一些违法犯罪的信息。禁止黄赌毒</span>
                    </el-dialog>
                </div>
                <div class="forest-community-tool-box">
                    <div class="cell-box">
                        <div class="left-tool-name-box">
                            <span class="iconfont icon-box icon-fenzu"
                                style="font-size:14px;font-weight: 700;color: #555;margin-left: 1px;"></span>
                            <span class="text overflow-text">群成员 (8人)</span>
                        </div>
                        <div class="extend-box">
                            <span class="look-cheng-yuan forest-active">查看</span>
                            <el-icon>
                                <ArrowRight />
                            </el-icon>
                        </div>
                    </div>
                    <div class="contaner-box-content">
                        <div class="avatar-box">
                            <el-avatar :size="38" src="/src/assets/image/20200219184554_wwnvh.jpg" class="avatar" />
                        </div>
                        <div class="avatar-box">
                            <el-avatar :size="38"
                                src="https://tse4-mm.cn.bing.net/th/id/OIP-C.5zdMEWczpI1r1V93yHgZPQAAAA?w=211&h=211&c=7&r=0&o=5&dpr=1.3&pid=1.7"
                                class="avatar" />
                        </div>
                        <div class="avatar-box">
                            <el-avatar :size="38"
                                src="https://tse2-mm.cn.bing.net/th/id/OIP-C.wk8Qob1shSazjYdxC3bw2gAAAA?w=211&h=211&c=7&r=0&o=5&dpr=1.3&pid=1.7"
                                class="avatar" />
                        </div>
                        <div class="avatar-box">
                            <el-avatar :size="38"
                                src="https://tse3-mm.cn.bing.net/th/id/OIP-C.7ucdAki8h_JKuI1ahpMNZAAAAA?w=211&h=211&c=7&r=0&o=5&dpr=1.3&pid=1.7"
                                class="avatar" />
                        </div>
                        <div class="avatar-box">
                            <el-avatar :size="38"
                                src="https://tse3-mm.cn.bing.net/th/id/OIP-C.0TuH0Yxo3P9mjbvhSDMNgwAAAA?w=147&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7"
                                class="avatar" />
                        </div>
                        <div class="avatar-box">
                            <el-avatar :size="38"
                                src="https://tse3-mm.cn.bing.net/th/id/OIP-C.hAJGtgEXnxcV22FC1KG6dQAAAA?w=89&h=89&c=1&rs=1&qlt=90&r=0&dpr=1.3&pid=InlineBlock"
                                class="avatar" />
                        </div>
                        <div class="avatar-box">
                            <el-avatar :size="38"
                                src="https://tse3-mm.cn.bing.net/th/id/OIP-C.HkO7MAxd9Vsrs1zVxbRkEgAAAA?w=89&h=90&c=1&rs=1&qlt=90&r=0&dpr=1.3&pid=InlineBlock"
                                class="avatar" />
                        </div>
                        <div class="avatar-box">
                            <el-avatar :size="38"
                                src="https://tse2-mm.cn.bing.net/th/id/OIP-C.xqFCKj_DmwNUx_g-LQ5KAQHaHa?w=147&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7"
                                class="avatar" />
                        </div>
                        <div class="avatar-box">
                            <el-avatar :size="38"
                                src="https://tse4-mm.cn.bing.net/th/id/OIP-C.ApkTFDhbAiNzqjqR-12kEwHaHa?w=89&h=89&c=1&rs=1&qlt=90&r=0&dpr=1.3&pid=InlineBlock"
                                class="avatar" />
                        </div>
                        <div class="avatar-box">
                            <el-avatar :size="38"
                                src="https://tse1-mm.cn.bing.net/th/id/OIP-C.2TklLhiZpJcsDj7JNIWaGQHaHa?w=89&h=90&c=1&rs=1&qlt=90&r=0&dpr=1.3&pid=InlineBlock"
                                class="avatar" />
                        </div>
                        <div class="avatar-box">
                            <el-avatar :size="38"
                                src="data:image/jpeg;base64,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"
                                class="avatar" />
                        </div>
                    </div>
                </div>
                <div class="forest-community-tool-box">
                    <div class="cell-box">
                        <div class="left-tool-name-box">
                            <span class="iconfont icon-box icon-bingtu"
                                style="font-size:18px;font-weight: 700;color: #555;"></span>
                            <span class="text">成员分布</span>
                        </div>
                    </div>

                    <groupDetailMemberPie></groupDetailMemberPie>

                </div>
            </div>

            <div class="footer-bar-box-tool">
                <button class="forest-active">分享</button>
                <button class="forest-active">发消息</button>
            </div>
        </div>
    </div>
</template>
<style scoped>
.contaner-box {
    background-color: #fff;
    width: 100%;
    height: 100%;

    .friend-detail-wrapped-box {
        display: flex;
        width: 100%;
        height: 100%;
        flex-direction: column;
        padding: 0 30px;
        padding-top: 50px;
        background-color: #fff;
        color: #333;
        max-width: 600px;
        margin: 0 auto;

        .header-box {
            display: flex;
            border-bottom: 2px solid #F5F5F5;
            padding-bottom: 20px;

            .left-avatar {
                flex: none;
            }

            .right-info-box {
                color: var(--his-text-color);
                display: flex;
                align-items: center;
                flex: 1;
                justify-content: space-between;

                .info-deatil {
                    display: flex;
                    height: 100%;
                    padding-left: 20px;
                    flex-direction: column;
                    justify-content: center;

                    .nick-name {
                        font-size: 18px;
                        margin-bottom: 10px;
                    }

                    .forest-id {
                        color: #7F7F7F;
                        font-size: 13px;
                    }

                    .status-box {
                        position: relative;
                        padding-left: 16px;

                        .circle {
                            width: 13px;
                            height: 13px;
                            border-radius: 50%;
                            background-color: var(--my-online-color);
                            position: absolute;
                            top: calc(50% - 6.3px);
                            left: 0px;
                        }
                    }
                }
            }
        }

        .main-tool-wrapper-box {
            .cell-box {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-top: 20px;

                .left-tool-name-box {
                    display: flex;
                    align-items: flex-end;
                    line-height: 19px;
                    height: 20px;
                    /* width:30%; */


                    .iconfont {}

                    .icon-box {
                        font-size: 14px;
                        margin-right: 5px;
                    }

                    .icon-beizhu {}

                    .text {}
                }


                .extend-box {
                    display: flex;
                    align-items: center;
                    justify-content: flex-end;
                    width: 70%;

                    .look-cheng-yuan {
                        font-size: 14px;
                        color: #7F7F7F;
                    }
                }
            }
        }

        .forest-community-tool-box {
            user-select: none;
            margin-top: 20px;

            .cell-box {}

            .member-pie-box {
                height: 128px;
                width: 100%;
            }

            .member-pie-box {}

            .contaner-box-content {
                padding: 15px 0;
                padding-bottom: 0;
                display: flex;
                flex-wrap: wrap;

                .avatar-box {
                    display: inline-block;
                    height: 38px;
                    width: 38px;
                    position: relative;
                    margin-right: 10px;
                    margin-bottom: 10px;

                    &:first-child {
                        margin-right: 20px;

                        &::before {
                            content: "";
                            width: 2px;
                            border-radius: 100px;
                            height: 30px;
                            background-color: #F2F2F2;
                            position: absolute;
                            right: -10px;
                            top: 4px;
                        }
                    }



                    .avatar {
                        margin-right: 10px;
                    }
                }

            }

            .forest-active:hover {
                color: var(--my-frame-color);
                transition: all 0.3s;
            }
        }

        .footer-bar-box-tool {
            display: flex;
            align-items: center;
            margin-top: 50px;
            justify-content: center;

            button {
                display: flex;
                justify-content: center;
                align-items: center;
                background-color: transparent;
                border: 1px solid #CCCCCC;
                padding: 5px 38px;
                margin-right: 15px;
                font-size: 14px;
                border-radius: 5px;

                &:last-child {
                    background-color: var(--my-frame-color);
                    color: white;
                    border-color: var(--my-frame-color);
                }
            }
        }
    }
}
</style>