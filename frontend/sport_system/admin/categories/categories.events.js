import { deleteCategory, detailCategory, saveCategory } from "./categories.api.js";
import { getCategoryDataForm, renderCategoryForm } from "./categories.service.js";


export async function handleEditCategory() {
    document.querySelector(".table").addEventListener("click", async (e) => {
        const btnEdit = e.target.closest(".btn-edit");
        if(btnEdit) {
            // document.querySelector("#id").value = btnEdit.dataset.id;
            const category = await detailCategory(btnEdit.dataset.id);
            renderCategoryForm(category);
        }
    })
}

export async function handleSaveCategoryForm() {
    document.querySelector(".btn-save").addEventListener("click", (e) => {
        e.preventDefault();
        const categoryDataForm = getCategoryDataForm();
        saveCategory(categoryDataForm);
    })
}

export async function handleDelete() {
    document.querySelector(".table").addEventListener("click", async (e) => {
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
        deleteCategory(id);
    })
}