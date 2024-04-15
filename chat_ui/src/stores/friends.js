import { ref, computed } from "vue";
import { defineStore } from "pinia";
//框架全局样式
export const useFriendsStore = defineStore(
    "friends",
    () => {
        //当前选择的好友
        const currentSelectFriendIndex = ref(0);
        //当前选择的好友Id
        const currentSelectFriendId = ref(0);


        function setCurrentSelectFriendIndex(params) {
            currentSelectFriendIndex.value = params;
        }
        function setCurrentSelectFriendId(params) {
            currentSelectFriendId.value = params;
        }



        return { currentSelectFriendIndex, setCurrentSelectFriendIndex, currentSelectFriendId, setCurrentSelectFriendId};
    },
    {
        persist: true
    }
)