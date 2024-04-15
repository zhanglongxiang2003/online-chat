import request from "@/utils/request";

export function searchFriendAndGroup(friendId, groupId) {
    return request({
        url: `/online/friend/search?friendId=${friendId}&groupId=${groupId}`,
        method: "GET",
    })
}