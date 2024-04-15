<script setup>
import router from '@/router';
import { ref } from 'vue';
import { useRoute } from 'vue-router';
const route = useRoute()
const emits = defineEmits(['setTitle'])
emits('setTitle', '好友信息')



// 动态显示列表
const friendId = ref(route.query.friendId)
router.beforeEach((to, from, next) => {
    friendId.value = to.query.friendId
    next()
})


//好友分组
const priendGroup = ref('我的好友')
const options = [
    {
        value: '我的好友',
        label: '我的好友',
    },
    {
        value: '同学',
        label: '同学',
    },
]


</script>

<template>
    <div class="contaner-box">
        <div class="friend-detail-wrapped-box">
            <div class="header-box">
                <el-avatar class="left-avatar" :size="100" src="/src/assets/image/20200219184554_wwnvh.jpg" />
                <div class="right-info-box">
                    <div class="info-deatil">
                        <div class="nick-name">wife{{ friendId }}</div>
                        <div class="forest-id">
                            ID: FRIEND00{{ friendId }}
                            <span class="iconfont icon-fuzhi" @click="$copy('FRIEND00' + friendId)"></span>
                        </div>
                        <div class="status-box">在线<i class="circle"></i></div>
                    </div>
                    <div class="upvote-box">
                        <i class="iconfont icon-praise upvote-icon"></i>
                        <span class="upvote-number">8888</span>
                    </div>
                </div>
            </div>
            <div class="main-tool-wrapper-box">
                <div class="cell-box">
                    <div class="left-tool-name-box">
                        <span class="iconfont icon-box icon-beizhu"></span>
                        <span class="text">备注</span>
                    </div>
                    <div class="extend-box">WiFi</div>
                </div>
                <div class="cell-box">
                    <div class="left-tool-name-box">
                        <span class="iconfont icon-box icon-fenzu"></span>
                        <span class="text">好友分组</span>
                    </div>
                    <el-select v-model="priendGroup" placeholder="选择分组" style="width: 150px">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>

                </div>
                <div class="cell-box">
                    <div class="left-tool-name-box">
                        <span class="iconfont icon-box icon-qianming"></span>
                        <span class="text">签名</span>
                    </div>
                    <div class="extend-box overflow-text">即使我们现在冷得像块冰 也不要忘那些炙热的曾经</div>
                </div>
            </div>


            <div class="forest-community-tool-box">
                <div class="main-tool-wrapper-box forest-active" style="cursor: pointer;">
                    <div class="cell-box">
                        <div class="left-tool-name-box">
                            <el-icon class="iconfont icon-box" style="font-size: 18px;margin-right: 1px;">
                                <Compass />
                            </el-icon>
                            <span class="text">林子社区</span>
                        </div>
                        <div class="extend-box">
                            <el-icon>
                                <ArrowRight />
                            </el-icon>
                        </div>
                    </div>
                </div>
                <div class="contaner-box-content">
                    <el-image class="image-cover" style="width: 100px; height: 100px"
                        src="https://ts1.cn.mm.bing.net/th/id/R-C.e53510f1e937fb42d16b3d2b6f93f373?rik=Y4oRn3L0HgIjig&riu=http%3a%2f%2fimg1.qunarzz.com%2ftravel%2fd7%2f1808%2f31%2f550156d0426f25b5.jpg%3fver%3d1&ehk=XKQpiGPlL32oZ7Ct2tKrD%2fPPPhqepFvppvromczDYfY%3d&risl=&pid=ImgRaw&r=0" />
                </div>
            </div>

            <div class="footer-bar-box-tool">
                <button class="forest-active">分享</button>
                <button class="forest-active">语音通话</button>
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

        .back-button {
            display: none;
        }

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
                    flex-direction: column;
                    height: 100%;
                    justify-content: space-evenly;
                    padding: 10px;
                    padding-left: 20px;

                    .nick-name {
                        font-size: 18px;
                    }

                    .forest-id {
                        color: #7F7F7F;
                        font-size: 13px;

                        &:hover>.iconfont {
                            color: var(--my-frame-color);
                            transition: all 0.3s
                        }

                        .iconfont {
                            margin-left: 5px;
                            cursor: pointer;


                        }
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

                .upvote-box {
                    display: flex;
                    flex-direction: column;
                    align-items: center;

                    .iconfont {}

                    .icon-praise {}

                    .upvote-icon {
                        font-size: 21px;
                        margin-bottom: -2px;
                        cursor: pointer;
                    }

                    .upvote-number {
                        font-size: 13px;
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
                    width: 100px;

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
                }
            }
        }

        .forest-community-tool-box {
            user-select: none;
            border-top: 2px solid #F5F5F5;
            margin-top: 20px;

            .cell-box {}

            .contaner-box-content {
                .image-cover {
                    border-radius: 5px;
                    margin-top: 10px;
                    cursor: pointer;
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
                padding: 5px 20px;
                margin-right: 15px;
                font-size: 14px;
                border-radius: 5px;

                &:nth-child(3) {
                    background-color: var(--my-frame-color);
                    color: white;
                    border-color: var(--my-frame-color);
                }
            }
        }
    }
}
</style>