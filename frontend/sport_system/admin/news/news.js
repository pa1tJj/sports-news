import { populateSelectOptions } from "../../utils/select.util.js";
import { buildParams } from "./custom/params.search.build.js";
import { renderTable } from "./news.table.js";
import {getAPINews} from "./news.api.js";
import { initSearch, clickButtonEdit, clickAddNews } from "./news.evants.js";

export async function getAllNews() {
    const responses = await getAPINews();
    totalPage = responses.totalPage; 
    renderSelectSearch(responses.object);
    renderPaging(responses.totalPage);
    initSearch();
    initPaging();
    getTableData();
    clickAddNews();
}
function renderSelectSearch(responses) {
    populateSelectOptions(responses.sortBy, ".news-sortby-filter .form-select");
    populateSelectOptions(responses.categoryName, ".news-category-filter .form-select");
    populateSelectOptions(responses.status, ".news-status-filter .form-select")
    populateSelectOptions(responses.users, ".news-author-filter .form-select");
    populateSelectOptions(responses.dateOptions, ".news-date-filter .form-select");
}
//tạo bảng và hiển thị dữ liệu ra bảng 
function newsList(news, currentPage) {
    const tbody = document.querySelector(".tbody");
    tbody.innerHTML = news.map((element, index) => renderTable(element, index, currentPage)).join("");
}

//hiển thị phân trang
function renderPaging(totalPage) {
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

//cập nhật bảng dữ liệu
export async function getTableData(params = "") {
    var response = await getAPINews(params);
    newsList(response.list, currentPage);
    clickButtonEdit();
}

let currentPage = 1;
let totalPage = 1;
//thực hiện phân trang
export function initPaging() {
    const pagination = document.querySelector(".pagination");
    pagination.addEventListener("click", (e) => {
        e.preventDefault();
        const page = e.target.dataset.page;
        if(e.target.id === "previous" && currentPage > 1) {
            currentPage--;
        } else if(e.target.id === "next" && currentPage < totalPage) {
            currentPage++;
        } else if(page) {
            currentPage = Number(page);
        }
        const param = buildParams(currentPage);
        getTableData(param);
    })
}
