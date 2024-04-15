<script setup>
import router from '@/router';
import { useFrameThemeStore } from '@/stores/frameTheme';
import { ref } from 'vue';
import { useRoute } from 'vue-router';
const frameThemeStore = useFrameThemeStore()
const route = useRoute();


const value1 = ref('');

// 右侧固定栏 控制是否显示。
const fixedWrapperOffsetRight = ref(-400);
const openRightFixedWrapper = (num) => {
    if (num == 0) {
        fixedWrapperOffsetRight.value = -400
    } else {
        fixedWrapperOffsetRight.value = 0
    }
}

//刷新当前聊天的人
const friendId = ref(route.query.id);
router.afterEach((to, from) => {
    friendId.value = route.query.id
    frameThemeStore.setIsShowFriendDetail(true)
})

// 举报好友弹出框
const isReportFriendDislog = ref(false);
const reportForm = ref({
    content: '',
    name: '',
    images: [],
    type: '',
    typeOptions: [
        {
            label: '发布色情/违法信息',
            value: 1
        },
        {
            label: '存在欺诈骗钱行为',
            value: 2
        },
        {
            label: '侵犯未成年人权益',
            value: 3
        },
        {
            label: '水军/黑公关',
            value: 4
        },
        {
            label: '无底线追星',
            value: 5
        },
        {
            label: '账号被盗',
            value: 6
        }, {
            label: '其他',
            value: 7
        }
    ],
});
function toReportFriend(name) {
    reportForm.value.name = name
    isReportFriendDislog.value = true;
}
function reportFriend(item) {
    console.log(item)
    ElMessage.success('举报成功！');
    isReportFriendDislog.value = false;
}
const dialogImageUrl = ref('')
const dialogVisible = ref(false)
function handleRemove(uploadFile, uploadFiles) {
    console.log(uploadFile, uploadFiles)
}
function handlePictureCardPreview(uploadFile) {
    dialogImageUrl.value = uploadFile.url | ''
    dialogVisible.value = true
}
</script>
<template>
    <div class="message-wrapper" :class="{ 'is-back': frameThemeStore.isShowFriendDetail }">
        <div class="top-tool-bar">
            <div class="forest-active" @click="frameThemeStore.setIsShowFriendDetail(false)">
                <el-icon class="back-button">
                    <ArrowLeft />
                </el-icon>
                返回
            </div>
            <div>Wife</div>
            <div class="left-tool">
                <span class="item iconfont icon-phone forest-active" style="margin-bottom: 1px;"></span>
                <span class="item iconfont icon-shipin forest-active"></span>
                <span class="item iconfont icon-gengduo forest-active"
                    @click="openRightFixedWrapper(fixedWrapperOffsetRight)"></span>
            </div>
        </div>
        <div class="main-wrapper-box">
            <div class="message-box">
                <div class="message-item">
                    <div class="time">
                        <span>2017-03-14 15:25</span>
                    </div>
                    <div class="item">
                        <img src="@/assets/image/20200219184554_wwnvh.jpg">
                        <div class="text-wrapper">
                            你在干嘛？我是ID为{{ friendId }}的好友奥！
                        </div>
                    </div>
                    <div class="division"></div>
                </div>
                <div class="message-item message-item-my">
                    <div class="time">
                        <span>2017-03-14 15:25</span>
                    </div>
                    <div class="item">
                        <img src="@/assets/image/20200219184554_wwnvh.jpg">
                        <div class="text-wrapper">
                            你在干嘛？asfdafdsfadsfasasdfdsf 撒打发阿帆鼓风机房更好，阿斯蒂芬艾师傅，阿萨法大师傅。阿斯蒂芬阿斯蒂芬啊，暗室逢灯艾师傅。
                        </div>
                    </div>
                    <div class="division"></div>
                </div>

            </div>
            <div class="bottom-message-wrapper">
                <div class="message-tool-bar">
                    <span class="forest-active iconfont icon-xiaolian item"></span>
                    <span class="forest-active iconfont icon-tupian item"></span>
                    <span class="forest-active iconfont icon-wenjianjia item"></span>
                </div>
                <textarea placeholder="说点什么吧..." class="message-input"></textarea>
                <div class="bottm-bar">
                    <span>温馨提示: 按回车发送 shift+回车换行</span>
                    <button class="forest-active">发送</button>
                </div>
            </div>
            <!-- 右侧弹出框 -->
            <div class="right-fiexd-tool-wrapper" :style="'right: ' + fixedWrapperOffsetRight + 'px;'">
                <div class="right-fiexd-tool-item">
                    <div class="item">
                        <span>设为置顶</span>
                        <el-switch v-model="value1" class="ml-2" size="small"
                            style="--el-switch-on-color: var(--my-frame-color); --el-switch-off-color:#CCCCCC" />
                    </div>
                    <div class="item">
                        <span>消息免打扰</span>
                        <el-switch v-model="value1" class="ml-2" size="small"
                            style="--el-switch-on-color: var(--my-frame-color); --el-switch-off-color:#CCCCCC" />
                    </div>
                </div>
                <div class="right-fiexd-tool-item">
                    <div class="item">
                        <span>屏蔽此人</span>
                        <el-switch v-model="value1" class="ml-2" size="small"
                            style="--el-switch-on-color: var(--my-frame-color); --el-switch-off-color:#CCCCCC" />
                    </div>
                </div>

                <div class="right-fiexd-tool-item">
                    <div class="item">
                        <span>屏蔽此人</span>
                        <el-switch v-model="value1" class="ml-2" size="small"
                            style="--el-switch-on-color: var(--my-frame-color); --el-switch-off-color:#CCCCCC" />
                    </div>
                </div>

                <div class="right-fiexd-tool-item" style="text-align: center;width: 100%;">
                    <div class="item">
                        <span>删除聊天记录</span>
                    </div>
                </div>

                <div class="right-fiexd-tool-item right-fiexd-tool-item2" style="text-align: center;width: 100%;">
                    <div class="item">
                        <span>删除好友</span>
                    </div>
                </div>

                <div @click="toReportFriend('卡里的事件发生')" class="right-fiexd-tool-item delete-item"
                    style="text-align: center;width: 100%;">
                    被骚扰了？举报该用户
                </div>

            </div>
            <!-- 左侧空白按钮 -->
            <div v-show="fixedWrapperOffsetRight == 0" @click="fixedWrapperOffsetRight = -400"
                class="placeholder-overlay-button">
            </div>

        </div>

        <el-dialog v-model="dialogVisible" title="图片预览" width="500" align-center>
            当时法国
        </el-dialog>
        <el-dialog v-model="isReportFriendDislog" title="温馨提示" width="500" align-center>
            <div class="report-friend-dislog">
                <div class="title">
                    <el-icon style="color: #FFBA00;font-size:25px;margin-right:5px;">
                        <WarningFilled />
                    </el-icon>
                    <span>确定要举报用户“{{ reportForm.name }}”吗？</span>
                </div>

                <div class="report-item">
                    <p class="title-p">违规类型</p>
                    <el-select v-model="reportForm.type" multiple placeholder="Select" style="width:100%;">
                        <el-option v-for="item in reportForm.typeOptions" :key="item.value" :label="item.label"
                            :value="item.value" />
                    </el-select>
                </div>

                <div class="report-item">
                    <p class="title-p">详情内容</p>
                    <el-input class="content-box" v-model="reportForm.content" style="width:100%;"
                        :autosize="{ minRows: 3, maxRows: 10 }" type="textarea" placeholder="请输入详情内容" :maxlength="250"
                        show-word-limit />
                </div>

                <div class="report-item">
                    <p class="title-p">辅助图片</p>
                    <el-upload v-model:file-list="reportForm.images"
                        action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15" list-type="picture-card"
                        :on-preview="handlePictureCardPreview" :on-remove="handleRemove">
                        <el-icon>
                            <Plus />
                        </el-icon>
                    </el-upload>
                </div>

            </div>
            <template #footer>
                <div class="dialog-footer">
                    <el-button @click="isReportFriendDislog = false">
                        算啦吧
                    </el-button>
                    <el-button type="danger" @click="reportFriend()">
                        就要举报
                    </el-button>
                </div>
            </template>
        </el-dialog>
    </div>
</template>
<style>
.message-wrapper {
    background-color: #F9F9F9;
    flex: 1;
    position: relative;
    overflow: hidden;

    .report-friend-dislog {
        display: flex;
        flex-direction: column;

        .title {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }

        .report-item {
            margin-bottom: 20px;

            .title-p {
                margin-left: 5px;
                margin-bottom: 10px;
                padding-left: 5px;
                border-left: 2px solid var(--my-frame-color)
            }

            .content-box {}
        }

    }


    .top-tool-bar {
        height: 60px;
        color: #000000;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0 20px;
        border-bottom: 1px solid var(--border-color);
        width: 100%;



        div {
            width: 105px;

            .back-button {
                font-size: 18px;
                margin-right: -3px;
                font-weight: 700;
            }

            &:nth-child(1) {
                display: none;
            }
        }

        .left-tool {
            color: var(--tool-icon-color);
            display: flex;
            align-items: center;

            .item {
                font-size: 20px;
                font-weight: 600;
                margin-left: 15px;
                display: flex;
                justify-content: center;
                align-items: center;
                width: 20px;

                &:hover {
                    color: var(--my-frame-color);
                }
            }
        }
    }

    .main-wrapper-box {
        height: calc(100% - 60px);
        position: relative;
        overflow: hidden;



        .message-box {
            margin: 30px;
            height: calc(100% - 180px - 60px - 60px);

            .message-item {
                margin: 10px 0;

                .item {
                    display: flex;

                    img {
                        width: 40px;
                        height: 40px;
                        border-radius: 5px;
                    }

                    .text-wrapper {
                        background-color: var(--his-frame-color);
                        color: var(--his-text-color);
                        line-height: 20px;
                        margin-left: 10px;
                        font-size: 14px;
                        border-radius: 5px 25px 25px 25px;
                        padding: 10px;
                        max-width: 300px;
                        box-sizing: border-box;
                        position: relative;
                        letter-spacing: .5px;
                        box-shadow: 0px 1px 3px 2px rgb(1 1 1 / 5%);

                        /* &::before {
                            content: "";
                            width: 10px;
                            height: 10px;
                            transform: rotate(45deg);
                            position: absolute;
                            top: calc(15px);
                            left: -5px;
                            background-color: #436EFF;
                        } */
                    }
                }

                .time {
                    height: 50px;
                    color: #B9B9B9;
                    display: flex;
                    justify-content: center;
                    align-items: center;

                    span {
                        font-size: 13px;
                    }
                }

                .division {}
            }

            .message-item-my {
                display: flex;
                flex-direction: column;

                .item {
                    flex-direction: row-reverse;

                    .text-wrapper {
                        background-color: var(--my-frame-color);
                        color: var(--my-text-color);
                        margin-left: 0px;
                        margin-right: 10px;
                        border-radius: 25px 5px 25px 25px;
                    }
                }
            }
        }

        .bottom-message-wrapper {
            width: 100%;
            height: 238px;
            background-color: #F9F9F9;
            border-top: 1px solid #EFEFEF;
            display: flex;
            flex-direction: column;
            padding: 0 20px;

            .message-tool-bar {
                height: 36px;
                display: flex;
                align-items: center;
                justify-content: flex-start;
                /* justify-content: flex-end; */

                .item {
                    color: var(--tool-icon-color);
                    font-size: 25px;
                    cursor: pointer;
                    display: flex;
                    width: 25px;
                    justify-content: center;

                    &:hover {
                        color: var(--my-frame-color);
                    }
                }

                .item:nth-child(2) {
                    font-size: 22px;
                    margin: 0 8px;
                }

                .item:nth-child(3) {
                    font-size: 23px;
                }


            }

            .message-input {
                margin-left: 1px;
                height: calc(100% - 36px - 56px);
                color: var(--his-text-color);
                outline: 0;
                border: none;
                font-size: 14px;
                resize: none;
                background-color: transparent;

                &::placeholder {
                    color: #d3d3d3;
                }
            }

            .bottm-bar {
                height: 56px;
                display: flex;
                align-items: center;
                justify-content: flex-end;

                span {
                    font-size: 12px;
                    color: #999999;
                    margin: 0 10px;
                }

                button {

                    text-align: -moz-center !important;
                    text-align: center;
                    color: #fff;
                    border: 0;
                    border-radius: 10px;
                    outline: 0;
                    cursor: pointer;
                    background-color: var(--my-frame-color);
                    font-size: 13px;
                    line-height: unset;
                    padding: 4px 10px;
                }
            }
        }

        .right-fiexd-tool-wrapper {
            width: 300px;
            height: 100%;
            background-color: #F9F9F9;
            position: absolute;
            top: 0px;
            padding: 30px;
            box-shadow: 1px 0px 10px 0px rgba(0, 0, 0, 0.2);
            transition: all .3s;

            .right-fiexd-tool-item {
                background-color: #fff;
                display: flex;
                align-items: center;
                flex-direction: column;
                padding: 0 20px;
                border-radius: 8px;
                margin-bottom: 20px;
                cursor: pointer;
                box-shadow: 1px 0px 10px 0px rgba(159, 159, 159, 0.2);

                &:last-child {
                    box-shadow: none;
                    background-color: transparent;
                }

                span {
                    color: #000000;
                    font-size: 14px;
                }

                .item {
                    height: 40px;
                    width: 100%;
                    display: flex;
                    align-items: center;
                    justify-content: space-between;

                    &:nth-child(2) {
                        border-top: 1px solid #E6E6E6;
                    }
                }
            }

            .right-fiexd-tool-item2 {
                .item {
                    align-items: center;
                    justify-content: center;

                    span {
                        color: red;
                    }
                }
            }

            .delete-item {
                align-items: center;
                justify-content: center;
                background-color: transparent;
                color: var(--my-frame-color);
                font-size: 13px;
            }
        }

        .placeholder-overlay-button {
            width: calc(100% - 300px);
            height: calc(100%);
            background-color: transparent;
            position: absolute;
            top: 0;
            left: 0;
        }
    }
}
</style>