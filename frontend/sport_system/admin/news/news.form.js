import { populateSelectOptionsNewsEditForm, populateSelectOptions } from "../../utils/select.util.js";
import { getApiEdit, getApiFormData, getApiSaveNews } from "./news.api.js";
import { ckeditorConfig } from "./news.ckeditor.js";
import {Validator} from "../assets/validator.js"

const PARAMS = new URLSearchParams(window.location.search);

async function loadHeader() {
    const res = await fetch("../components/header.html");
    const html = await res.text();
    document.querySelector(".header").innerHTML = html;
}
loadHeader();

async function newsEditFormResponse() {
    // lấy dữ liệu trên param 
    const id = PARAMS.get("id"); //lấy id trên param
    const responses = await getApiEdit(id);
    populateSelectOptionsNewsEditForm(responses.categories, responses.newsDetailResponse.categories, "#category");
    populateSelectOptionsNewsEditForm(responses.status, responses.newsDetailResponse.status, "#status");
    populateSelectOptionsNewsEditForm(responses.tags, responses.newsDetailResponse.tags, "#tag");
    loadNewsDetail(responses.newsDetailResponse);
}

function loadNewsDetail(data) {
    const IMG_URL = "http://localhost:8026";
    Object.keys(data).forEach(item => {
        let input = document.querySelector(`#${item}`);
        if (input) {
            if (input.type === "checkbox") {
                input.checked = data[item];
            } else {
                input.value = data[item];
            }
        } 
    })
    const preview = document.querySelector("#thumbnailPreview");
    if (preview && data.thumbnailPreview) {
        preview.src = IMG_URL + "/images/" + data.thumbnailPreview;
    }
    if (editorInstance) {
        editorInstance.setData(data.content);
    }

}

async function loadOptionForm() {
    const response = await getApiFormData();
    populateSelectOptions(response.categories, "#category");
    populateSelectOptions(response.tags, "#tag");
    populateSelectOptions(response.status, "#status");
}

//có id thì trả về dữ liệu response từ server, k id thì trả về form tạo mới
export function renderFormNews() {
    if (PARAMS.get("id") != null) {
        newsEditFormResponse();
    } else {
        loadOptionForm();
    }
}
renderFormNews();

export async function getSaveNews() {
    const categories = document.querySelector("#category");
    const status = document.querySelector("#status").value;
    const tags = document.querySelector("#tag");
    const data = {
        id: PARAMS.get("id") || null,
        title: document.querySelector("#title").value,
        slug: document.querySelector("#slug").value,
        content: editorInstance.getData(),
        summary: document.querySelector("#summary").value,
        thumbnail: document.querySelector("#thumbnail"),
        category: Array.from(categories.selectedOptions).map(
            (option) => option.value,
        ),
        status: status,
        tag: Array.from(tags.selectedOptions).map((option) => option.value),
        isFeatured: document.querySelector("#isFeatured").checked,
    };
    getApiSaveNews(data);
}


//lưu ảnh vào server khi chọn ảnh trong skeditor
let editorInstance;
ckeditorConfig((e) => {
    editorInstance = e;
});

//xử lý validate trong form news
Validator({
    form: '#form-news',
    formGroupSelector: '.form-group',
    rules: [
        Validator.isRequired("#title"),
        Validator.isRequired("#slug"),
        Validator.isRequired("#summary"),
        Validator.isRequiredEditor("#content", function () {
            return editorInstance.getData();
        }),
        Validator.isRequired("#category"),
        Validator.isRequired("#status"),
        Validator.isRequired("#tag")
    ],
    onSubmit: function() {
        getSaveNews()
    }
})