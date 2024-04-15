<script setup>
const emit = defineEmits(['toPage'])

function toAddFriendOrGroup(data) {
    emit('toPage', data)
}
</script>
<template>

    <div class="header-tool-serch-box">
        <div class="serch-box">
            <input type="search" class="search" placeholder="搜索"></input>
            <span class="iconfont icon-sousuo search-icon"></span>
        </div>
        <el-dropdown trigger="click" @command="toAddFriendOrGroup">
            <div class="search-btn forest-active">
                <el-icon>
                    <Plus />
                </el-icon>
            </div>
            <template #dropdown>
                <el-dropdown-menu>
                    <el-dropdown-item command="/contacts/createGroup">
                        <span class="iconfont icon-tianjiahaoyou" style="margin-right: 2px;"></span>
                        创建群聊
                    </el-dropdown-item>
                    <el-dropdown-item command="/contacts/addFriend">
                        <span class="iconfont icon-chuangjianqunliao" style="margin-right: 2px;"></span>
                        添加好友/群
                    </el-dropdown-item>
                </el-dropdown-menu>
            </template>
        </el-dropdown>
    </div>

</template>

<style>
.header-tool-serch-box {
    width: 100%;
    height: 60px;
    background-color: #fff;
    display: flex;
    flex-direction: row;
    align-items: center;

    .serch-box {
        width: calc(100% - 60px);
        height: 30px;
        margin-left: 10px;
        position: relative;

        .search {
            position: relative;
            width: 100%;
            height: 100%;
            border-radius: 5px;
            border: none;
            outline: none;
            text-indent: 15px;
            font-size: 12px;
            font-family: Courier;
            display: flex;
            align-items: center;
            line-height: 24px;
            background-color: #F5F5F5;

            padding: 0;
            padding-left: 11px;
            border: 1px solid transparent;
            caret-color: var(--my-frame-color);
            caret-shape: square;

            &::placeholder {
                color: #ACACAC;
            }

            &:hover {
                border-color: var(--my-frame-color);
                transition: all .3s;

            }

            &:focus {
                border-color: var(--my-frame-color);
            }

            &::-webkit-search-cancel-button {
                /* 设置清除按钮的样式 */
                transition: all .3s;
                appearance: none;
                width: 15px;
                height: 15px;
                margin: 0 8px 0 5px;
                background-image: url("@/assets/icon/png/empty1.83cb522.png");
                background-size: cover;
                cursor: pointer;
            }
        }

        .search-icon {
            width: 20px;
            height: 20px;
            position: absolute;
            top: calc(50% - 8.8px);
            font-weight: 700;
            left: 7px;
            color: #ACACAC;
            font-size: 16px;
            background-color: #F5F5F5;
        }
    }

    .search-btn {
        width: 30px;
        height: 30px;
        border-radius: 5px;
        margin-left: 10px;
        background-color: #F5F5F5;
        font-size: 16px;
        color: #ABA0AB;
        display: flex;
        justify-content: center;
        align-items: center;
        font-weight: 100;
        cursor: pointer;


    }


}
</style>