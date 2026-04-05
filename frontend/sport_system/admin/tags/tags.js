import { fetchTags } from "./tags.api.js";
import { handleAdd, handleDelete, handleEdit, handleReset, handleSave } from "./tags.events.js";
import { initPaging, renderPaging, renderTableTags } from "./tags.service.js";

export async function initTags() {
    const response = await fetchTags(1);
    renderTableTags(response.list, 1);
    renderPaging(response.totalPage);
    initPaging();
    handleEdit();
    handleSave();
    handleReset();
    handleAdd();
    initDialog();
} 

export function renderTagForm(tag) {
    Object.keys(tag).forEach(it => {
        let input = document.querySelector(`#${it}`);
        if(input) {
            input.value = tag[it];
        }
    })
}

async function initDialog() {
    const res = await fetch("../components/dialog.confirm.html");
    document.querySelector(".dialog-box").innerHTML = await res.text();
    handleDelete();
}

export async function responseResult() {
    const res = await fetch("../components/dialog.success.html");
    document.querySelector(".dialog-box").innerHTML = await res.text();
    const dialog = document.querySelector("#success-box");
    dialog.showModal();
    btnOK(dialog);
}

function btnOK(dialog) {
    document.querySelector("#btnOK").addEventListener("click", async (e) => {
        dialog.close();
        const response = await fetchTags(1);
        renderTableTags(response.list, 1);
    })
}
