import { responseResult } from "./tags.js";


export async function fetchTags(page) {
    var api = "http://localhost:8026/api/admin/tags?name";
    if(page !== null) {
        api += `&page=${page}`;
    }
    const res = await fetch(api);
    return await res.json();
}

export async function saveTags(tag) {
    const api = tag.id ? `http://localhost:8026/api/admin/tags/${tag.id}` : `http://localhost:8026/api/admin/tags`;
    const posts = {
        method: tag.id ? "PUT" : "POST",
        body: JSON.stringify(tag),
        headers: {
            "Content-Type": "application/json"
        }
    }
    const res = await fetch(api, posts);
    if(res.ok) {
        responseResult();
    }
}

export async function detailTag(id) {
    const api = `http://localhost:8026/api/admin/tags/${id}`;
    const res = await fetch(api);
    return res.json();
}

export async function deleteTag(id) {
    const api = `http://localhost:8026/api/admin/tags/${id}`;
    const posts = {
        method: "DELETE"
    }
    const res = await fetch(api, posts);
    if(res.ok) {
        responseResult();
    }
}

