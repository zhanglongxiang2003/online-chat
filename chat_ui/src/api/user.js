import request from "@/utils/request";

export function login(data) {
    return request({
        url: "/online/user/login",
        method: "POST",
        data
    })
}

export function code(email) {
    return request({
        url: "/online/user/code?email=" + email,
        method: "GET",
    })
}

export function register(data) {
    return request({
        url: "/online/user/register",
        method: "POST",
        data,
    })
}

export function getUserInfo() {
    return request({
        url: "/online/user/userInfo",
        method: "GET",
    })
}

export function refreshToken() {
    return request({
        url: "/online/user/refreshToken",
        method: "PATCH"
    })
}