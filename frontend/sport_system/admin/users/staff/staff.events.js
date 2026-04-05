import { deleteUser, detailUser, saveUser } from "./staff.api.js";
import { getUserDataForm, renderForm, resetForm } from "./staff.service.js";

export function handleEdit() {
    document.querySelector(".table").addEventListener("click", async (e) => {
        const btn = e.target.closest(".btn-edit");
        if(btn) {
            const user = await detailUser(btn.dataset.id);
            renderForm(user);
        }
    })
}

export function handleSave() {
    document.querySelector(".btnSave").addEventListener("click", (e) => {
        e.preventDefault();
        const user = getUserDataForm();
        saveUser(user);
    })
}

export function handleDelete() {
    document.querySelector(".table").addEventListener("click", (e) => {
        const btnDelete = e.target.closest(".btn-delete");
        if(btnDelete) {
            const dialog = document.querySelector("#confirm-box");
            dialog.showModal();
            buttonNo(dialog);
            dialog.value = btnDelete.dataset.id;
            buttonYes(dialog.value);
        }
    })
}

//xử lý button NO trong dialog delete
function buttonNo(dialog) {
    let btnNo = document.querySelector("#btnNO");
    btnNo.addEventListener("click", function() {
        dialog.close();
    })
}
//xử lý button YES trong dialog delete
function buttonYes(id) {
    let btnYes = document.querySelector("#btnYES");
    btnYes.addEventListener("click", function () {
        deleteUser(id);
    })
}

export function handleReset() {
    document.querySelector(".btnReset").addEventListener("click", (e) => {
        resetForm();
    })
}

export function handleAdd() {
    document.querySelector(".btnAdd").addEventListener("click", (e) => {
        resetForm();
    })
}

export function checkUsernameExists() {
    document.querySelector("#username").addEventListener("blur", async function() {
        const username =  this.value.trim();
        const formGroup = this.closest(".form-group");
        const errorElement = formGroup.querySelector(".form-messenger");
        if(!username) return;
        const res = await fetch(`http://localhost:8026/api/admin/users/exists?username=${username}`);
        if(!res.ok) {
            errorElement.innerText = "Tên đăng nhập đã tồn tại";
            formGroup.classList.add("invalid");
        } else {
            formGroup.classList.remove("invalid");
        }

    })
}