import { deleteTag, detailTag, saveTags } from "./tags.api.js";
import { renderTagForm } from "./tags.js";
import { getTagDataForm, resetValueForm } from "./tags.service.js";

export function handleEdit() {
    document.querySelector(".table").addEventListener("click", async (e) => {
        const btnEdit = e.target.closest(".btn-edit");
        if(btnEdit) {
            const tag = await detailTag(btnEdit.dataset.id);
            renderTagForm(tag);
        }
    })
}

export function handleSave() {
    document.querySelector("#btnSave").addEventListener("click", (e) => {
        e.preventDefault();
        const tag = getTagDataForm();
        saveTags(tag);
    })
}

export function handleReset() {
    document.querySelector("#btnReset").addEventListener("click", (e) => {
        resetValueForm();
    })
}

export function handleAdd() {
    document.querySelector(".btnAdd").addEventListener("click", (e) => {
        resetValueForm();
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
        deleteTag(id);
    })
}

