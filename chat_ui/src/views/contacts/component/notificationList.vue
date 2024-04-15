<script setup>
import router from '@/router';
import { ref } from 'vue';
import { useRoute } from 'vue-router';
const emits = defineEmits(['setTitle'])

// 动态显示列表
const route = useRoute()
const type = ref(route.query.type)
router.beforeEach((to, from, next) => {
    if (to.query.type === 'friend') {
        emits('setTitle', '好友通知')
    } else {
        emits('setTitle', '群通知')
    }
    type.value = to.query.type
    next()
})
</script>

<template>
    <div class="friend-detail-wrapped-box">
        <div v-show="type === 'friend'">
            <div class="header-bar">
                <span>好友通知</span>
            </div>
            <div class="notification-wrapper-item">
                <div class="left-detail">
                    <el-avatar :size="46" class="forest-active"
                        src="https://tse4-mm.cn.bing.net/th/id/OIP-C.5zdMEWczpI1r1V93yHgZPQAAAA?w=211&h=211&c=7&r=0&o=5&dpr=1.3&pid=1.7" />
                    <div class="info-box">
                        <div class="up">
                            <span class="forest-active">与风说心事</span>
                            <span>请求加为好友</span>
                            <span>昨天</span>
                        </div>
                        <div class="down">留言: 我是与风说心事</div>
                    </div>
                </div>
                <div class="right-extend">
                    <!-- <span>等待认证</span> -->
                    <el-button type="primary">同意</el-button>
                    <el-button type="danger">拒绝</el-button>
                </div>
            </div>
        </div>
        <div v-show="type === 'group'">
            <div class="header-bar">
                <span>群通知</span>
            </div>
            <div class="notification-wrapper-item">
                <div class="left-detail">
                    <el-avatar :size="46" class="forest-active"
                        src="https://tse4-mm.cn.bing.net/th/id/OIP-C.5zdMEWczpI1r1V93yHgZPQAAAA?w=211&h=211&c=7&r=0&o=5&dpr=1.3&pid=1.7" />
                    <div class="info-box">
                        <div class="up">
                            <span class="forest-active">与风说心事</span>
                            <span>昨天</span>
                        </div>
                        <div class="down">
                            <span>邀请你加入</span>
                            <span class="forest-active">前端交流群3</span>
                        </div>
                    </div>
                </div>
                <div class="right-extend">
                    <!-- <span>等待认证</span> -->
                    <el-button type="primary">同意</el-button>
                    <el-button type="danger">拒绝</el-button>
                    <el-button>发消息</el-button>
                </div>
            </div>
        </div>
    </div>
</template>
<style scoped>
.friend-detail-wrapped-box {
    display: flex;
    width: 100%;
    height: 100%;
    flex-direction: column;
    background-color: #F2F2F2;
    color: #333;
    padding: 0 30px;

    .header-bar {
        height: 50px;
        display: flex;
        align-items: center;
        justify-content: center;
        margin-top: 20px;
    }

    .notification-wrapper-item {
        margin-top: 20px;
        height: 80px;
        width: 100%;
        display: flex;
        align-items: center;
        justify-content: space-between;
        background-color: #fff;
        padding: 0 20px;
        border-radius: 10px;

        .left-detail {
            display: flex;
            align-items: center;
            justify-content: space-between;
            height: 100%;

            .info-box {
                display: flex;
                height: 100%;
                flex-direction: column;
                justify-content: center;
                margin-left: 10px;

                .up {
                    margin-bottom: 2px;
                    font-size: 14px;
                    display: flex;
                    flex-wrap: wrap;

                    span {
                        margin-right: 5px;

                        &:nth-child(1) {
                            color: var(--my-frame-color);
                        }

                        &:nth-child(2) {}

                        &:nth-child(3) {
                            color: #999;
                        }
                    }

                }

                .down {
                    margin-top: 2px;
                    font-size: 13px;
                    color: #999;

                    span {
                        margin-right: 5px;

                        &:nth-child(1) {
                            color: #333;
                        }

                        &:nth-child(2) {
                            color: var(--my-frame-color);
                        }
                    }
                }
            }
        }

        .right-extend {}
    }
}
</style>