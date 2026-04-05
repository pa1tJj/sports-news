import { check } from "../../../utils/checkDataUtil.js";

export function buildParams(page = 1) {
    const params = new URLSearchParams();
    let dataRequest = {
        title: document.querySelector("#title").value,
        sortBy: document.querySelector("#sortBy").value,
        category: document.querySelector("#category").value,
        status: document.querySelector("#status").value,
        user: document.querySelector("#user").value,
        date: document.querySelector("#date").value
    }
    if (check(page)) params.append("page", page);
    if(check(dataRequest.title)) params.append("title", dataRequest.title);
    if(check(dataRequest.sortBy)) params.append("sortBy", dataRequest.sortBy);
    if(check(dataRequest.category)) params.append("categoryId", dataRequest.category);
    if(check(dataRequest.status)) params.append("status", dataRequest.status);
    if(check(dataRequest.author)) params.append("userId", dataRequest.user);
    if(check(dataRequest.date)) params.append("date", dataRequest.date);
    return params.toString();
}