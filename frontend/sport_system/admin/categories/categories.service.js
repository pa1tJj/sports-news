import { fetchCategories } from "./categories.api.js";
import { handleDelete } from "./categories.events.js";

export async function renderCategoriesTable() {
    const tbody = document.querySelector("tbody");
    tbody.innerHTML = "";
    const categories = await fetchCategories();
    tbody.innerHTML = categories.map((element, index) => 
        `
            <tr>
                <td>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" data-id="${element.id}">
                    </div>
                </td>
                <td>${index + 1}</td>
                <td>${element.name}</td>
                <td>${element.slug}</td>
                <td>${element.description}</td>
                <td>
                    <button class="btn btn-warning btn-sm btn-edit" data-id="${element.id}"><i
                        class="bi bi-pencil"></i></button>
                    <button class="btn btn-danger btn-sm btn-delete" data-id="${element.id}"><i
                       class="bi bi-trash"></i></button>
                </td>
            </tr>
        `
    ).join("");
}

export function renderCategoryForm(category) {
    Object.keys(category).forEach(it => {
        let input = document.querySelector(`#${it}`);
        if(input) {
            input.value = category[it];
        }
    })
}
export function getCategoryDataForm() {
    return {
        id: document.querySelector("#id").value,
        name: document.querySelector("#name").value,
        slug: document.querySelector("#slug").value,
        description: document.querySelector("#description").value
    }
}

export async function initDialog() {
    const res = await fetch("../components/dialog.confirm.html");
    document.querySelector(".dialog-box").innerHTML = await res.text();
    handleDelete();
}

