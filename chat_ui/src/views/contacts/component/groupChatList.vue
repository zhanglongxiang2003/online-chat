<script setup>
import { useFrameThemeStore } from '@/stores/frameTheme';


const props = defineProps({
    list: {
        type: Array,
        default: () => []
    }
})

const frameThemeStore = useFrameThemeStore()

</script>

<template>
    <div class="container-box">
        <div class="notification-wrapper-item" v-for="item in list">
            <div class="left-detail">
                <div class="avatar">
                    <el-avatar :size="46" class="forest-active " :src="item.avatar" />
                </div>
                <div class="info-box">
                    <div class="up">
                        <span class="forest-active">{{ item.name }}</span>
                    </div>
                    <div class="down">
                        <p class="overflow-text" style="width: 100%;">{{ item.introduction }}</p>
                        群号:
                        <span>
                            {{ item.id }}
                        </span>
                    </div>
                </div>
            </div>
            <div class="right-extend">
                <!-- <span>等待认证</span> -->
                <el-button type="primary" size="small" :color="frameThemeStore.myFrameColor" style="color: aliceblue;">加入群聊</el-button>
            </div>
        </div>
    </div>
</template>

<style scoped>
.container-box {
    .notification-wrapper-item {
        margin-bottom: 10px;
        height: 80px;
        width: 100%;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0 20px;
        border-radius: 10px;

        &:hover {
            background-color: #f2f2f2;
            transition: all 0.3s;
        }

        .left-detail {
            display: flex;
            align-items: center;
            justify-content: space-between;
            height: 100%;

            .avatar {
                width: 50px;
            }

            .info-box {
                display: flex;
                height: 100%;
                flex-direction: column;
                justify-content: center;
                margin-left: 10px;

                .up {
                    margin-bottom: 2px;
                    font-size: 14px;

                    .forest-active:hover {
                        color: var(--my-frame-color);
                        transition: all 0.3s;
                    }

                }

                .down {
                    margin-top: 2px;
                    font-size: 13px;

                    span {
                        color: var(--my-frame-color);
                    }
                }
            }
        }

        .right-extend {}
    }
}
</style>