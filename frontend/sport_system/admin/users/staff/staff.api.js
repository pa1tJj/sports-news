import { responseResult } from "./staff.js";

export async function fetchStaff(page) {
    const api = page ? `http://localhost:8026/api/admin/users?page=${page}` : `http://localhost:8026/api/admin/users`;
    const res = await fetch(api);
    return res.json();
}

export async function detailUser(id) {
    const api = `http://localhost:8026/api/admin/users/${id}`;
    const res = await fetch(api);
    return await res.json();
}

export async function saveUser(user) {
    const api = user.id ? `http://localhost:8026/api/admin/users/${user.id}` : `http://localhost:8026/api/admin/users`;
    const posts = {
        method: user.id ? "PUT" : "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    }
    const res = await fetch(api, posts);
    if(res.ok) {
        responseResult();
    }
}

export async function deleteUser(id) {
    const api = `http://localhost:8026/api/admin/users/${id}`;
    const posts = {
        method: "DELETE"
    }
    const res = await fetch(api, posts);
    if(res.ok) {
        responseResult();
    }
}