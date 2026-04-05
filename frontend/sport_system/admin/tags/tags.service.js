import { fetchTags } from "./tags.api.js";

export async function renderTableTags(tags, currentPage) {
    const tbody = document.querySelector("tbody");
    tbody.innerHTML = "";
    tbody.innerHTML = tags.map((it, index) => 
        `
            <tr>
                <td>
                    <input type="checkbox">
                </td>
                <td>${(currentPage - 1)* 5 + index + 1}</td>
                <td>${it.name}</td>
                <td>${it.slug}</td>
                <td>
                    <button class="btn btn-warning btn-sm btn-edit"  data-id="${it.id}">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button class="btn btn-danger btn-sm btn-delete" data-id="${it.id}">
                        <i class="bi bi-trash"></i>
                     </button>
                </td>
            </tr>

        `
    ).join("");
}

export async function renderPaging(totalPage) {
    const ul = document.querySelector(".pagination");
    ul.innerHTML = "";
    let html = `
        <li class="page-item">
            <a class="page-link" href="#" aria-label="Previous" id="previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
    `
    for(let i = 0; i < totalPage; i++) {
        html += `<li class="page-item"><a class="page-link" href="#" data-page="${i + 1}">${i + 1}</a></li>`
    }
    html += `
        <li class="page-item">
            <a class="page-link" href="#" aria-label="Next" id="next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    `
    ul.innerHTML = html;
}

let currentPage = 1;
let totalPage = 1;
export function initPaging() {
    const pagination = document.querySelector(".pagination");
    pagination.addEventListener("click", async (e) => {
        e.preventDefault();
        const page = e.target.dataset.page;
        if(e.target.id === "previous" && currentPage > 1) {
            currentPage--;
        } else if(e.target.id === "next" && currentPage < totalPage) {
            currentPage++;
        } else if(page) {
            currentPage = Number(page);
        }
        const response = await fetchTags(currentPage);
        renderTableTags(response.list, currentPage);
    })
}

export function getTagDataForm() {
    return {
        id: document.querySelector("#id").value,
        name: document.querySelector("#name").value,
        slug: document.querySelector("#slug").value
    }
}

export function resetValueForm() {
    document.querySelector("#id").value = "";
    document.querySelector("#name").value = "";
    document.querySelector("#slug").value = "";
}