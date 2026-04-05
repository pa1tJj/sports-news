import { populateSelectOptions } from "../../../utils/select.util.js";
import { fetchStaff } from "./staff.api.js";

export function renderStaffTable(staff, currentPage) {
    const tbody = document.querySelector("tbody");
    tbody.innerHTML = staff.map((item, index) => 
        `
            <tr>
                <td><input type="checkbox"></td>
                <td>${(currentPage - 1)* 2 + index + 1}</td>
                <td>${item.username}</td>
                <td>${item.email}</td>
                <td><span class="role-badge role-admin">${item.role}</span></td>
                <td><span class="status-active">${item.status}</span></td>
                <td>
                    <button class="btn btn-warning btn-sm btn-edit" data-id="${item.id}">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button class="btn btn-danger btn-sm btn-delete" data-id="${item.id}">
                        <i class="bi bi-trash"></i>
                    </button>
                </td>
            </tr>
        `
    ).join("");
}

export function renderOptionsForm(options) {
    populateSelectOptions(options.roles, "#role");
    populateSelectOptions(options.status, "#status")
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
export function initPaging(totalPage) {
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
        console.log(currentPage);
        const response = await fetchStaff(currentPage);
        renderStaffTable(response.list, currentPage);
    })
}

export function renderForm(staff) {
    Object.keys(staff).forEach(it => {
        let input = document.querySelector(`#${it}`);
        if(input) {
            if(input.tagName === "SELECT") {
                input.value = staff[it];
            } 
            input.value = staff[it];
        }
    })
}

export function getUserDataForm() {
    return {
        id: document.querySelector("#id").value,
        username: document.querySelector("#username").value,
        email: document.querySelector("#email").value,
        password: document.querySelector("#password").value,
        role: document.querySelector("#role").value,
        status: document.querySelector("#status").value,
        phone: document.querySelector("#phone").value
    }
}

export function resetForm() {
    const form = document.querySelector("#form-staff");
    // reset toàn bộ form (native)
    form.reset();
    // clear error message
    form.querySelectorAll(".form-messenger").forEach(el => {
        el.innerText = "";
    });
    // remove invalid class
    form.querySelectorAll(".form-group").forEach(el => {
        el.classList.remove("invalid");
    });
}