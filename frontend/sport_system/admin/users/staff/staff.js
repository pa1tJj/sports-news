import { Validator } from "../../assets/validator.js";
import { fetchStaff } from "./staff.api.js";
import { checkUsernameExists, handleAdd, handleDelete, handleEdit, handleReset, handleSave } from "./staff.events.js";
import { initPaging, renderOptionsForm, renderPaging, renderStaffTable } from "./staff.service.js";

export async function initStaff() {
    const res = await fetchStaff();
    renderStaffTable(res.list, 1);
    renderOptionsForm(res.object);
    renderPaging(res.totalPage);
    initPaging(res.totalPage);
    handleEdit();
    handleSave();
    initDialog();
    handleReset();
    handleAdd();
    validation();
    checkUsernameExists();
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
        const response = await fetchStaff(1);
        renderStaffTable(response.list, 1);
    })
}

function validation() {
    Validator({
    form: '#form-staff',
    formGroupSelector: '.form-group',
    rules: [
        Validator.isRequired("#username"),
        Validator.isRequired("#email"),
        Validator.isEmail("#email"),
        Validator.isRequired("#password"),
        Validator.isRequired("#role"),
        Validator.isRequired("#status"),
        Validator.isRequired("#phone")
    ],
    onsubmit: function() {
        handleSave();
    }
    })
}