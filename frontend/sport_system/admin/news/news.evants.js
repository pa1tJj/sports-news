import { buildParams } from "./custom/params.search.build.js";
import { getTableData } from "./news.js";


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