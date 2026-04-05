import { buildParams } from "./custom/params.search.build.js";
import { getApiDelete } from "./news.api.js";
import { getTableData} from "./news.js";


//khi nhấn button search
export function initSearch(i) {
    document.querySelector("#btnSearch").addEventListener("click", () => {
        const params = buildParams(1);
        getTableData(params);
    })
}

//sự kiện edit
export async function clickButtonEdit() {
    document.querySelector(".table").addEventListener("click", async (e) => {
        if(e.target.classList.contains("btn-edit")) {  
            const newsId = e.target.dataset.id;   
            window.location.href = `../news/news.form.html?id=${newsId}`;
        }
    })
}

export function clickAddNews() {
    document.querySelector(".table-data #btnAdd").addEventListener("click", (e) => {
        window.location.href = "../news/news.form.html";
    })
}

//xử lý hộp thoại dialog delete
export async function initDialog() {
    const res = await fetch("../components/dialog.confirm.html");
    document.querySelector(".dialog-box").innerHTML = await res.text();
    clickButtonDelete();
}
export function clickButtonDelete() {
    document.querySelector(".table").addEventListener("click", (e) => {
        if(e.target.classList.contains("btn-delete")) {  
            const id = e.target.dataset.id;
            const dialog = document.querySelector("#confirm-box");
            dialog.showModal();
            buttonNo(dialog);
            dialog.value = id;
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
        getApiDelete(id);
    })
}

//sự kiện chọn checkbox để xóa
let ids = [];
export function initCheckbox() {
    document.querySelectorAll(".form-check-input").forEach(cb => {
        cb.addEventListener("change", (e) => {
            const id = e.target.dataset.id;
            if(e.target.checked) {
                ids.push(id);
            } else {
                ids = ids.filter(item => item !== id);
            }
        })
        deleteSelectedItems(ids);
    })
}
export function deleteSelectedItems(ids) {
    document.querySelector("#btndeleteMultiple").addEventListener("click", (e) => {
        const dialog = document.querySelector("#confirm-box");
        dialog.showModal();
        buttonNo(dialog);
        dialog.value = ids;
        buttonYes(dialog.value);
    })
}