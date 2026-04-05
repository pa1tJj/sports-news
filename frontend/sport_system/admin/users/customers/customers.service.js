import { check } from "../../../utils/checkDataUtil.js";
import { populateSelectOptions } from "../../../utils/select.util.js";
import { fetchCustomers } from "./customers.api.js";

export function renderTableCustomers(customers, currentPage) {
    const tbody = document.querySelector("tbody");
    tbody.innerHTML = "";
    tbody.innerHTML = customers.map((it, index) => 
        `
            <tr>
                <td>${(currentPage - 1)* 2 + index + 1}</td>
                <td>${it.username}</td>
                <td>${it.email}</td>
                <td>${it.phone}</td>
                <td>${it.status}</td>
                <td>
                    <button class="btn btn-danger btn-sm" data-id="${it.id}">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>
        `
    ).join("");
}

export function getDataSearch(page = 1) {
    const params = new URLSearchParams();
    const data = {
        username: document.querySelector("#username").value,
        email: document.querySelector("#email").value,
        phone: document.querySelector("#phone").value,
        status: document.querySelector("#status").value
    }
    if (check(page)) params.append("page", page);
    if(check(data.username)) params.append("username", data.username);
    if(check(data.email)) params.append("email", data.email);
    if(check(data.phone)) params.append("phone", data.phone);
    if(check(data.status)) params.append("status", data.status);
    return params.toString();
}

export function renderOptionForm(options) {
    populateSelectOptions(options.status, "#status");
}

export function renderPaging(totalPage) {
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
export async function initPaging(totalPage) {
    const pagination = document.querySelector(".pagination");
    pagination.addEventListener("click", async (e) => {
        e.preventDefault();
        const target = e.target.closest("a");
        if (!target) return;
        const page = target.dataset.page;
        if(e.target.id === "previous" && currentPage > 1) {
            currentPage--;
        } else if(e.target.id === "next" && currentPage < totalPage) {
            currentPage++;
        } else if(page) {
            currentPage = Number(page);
        }
        const response = await fetchCustomers(currentPage);
        renderTableCustomers(response.list, currentPage);
    })
}
