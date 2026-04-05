export async function getAPINews(params) {
    var api = "http://localhost:8026/api/admin/news";
    if(params) {
        api += "?" + params; 
    }
    const response = await fetch(api);
    return await response.json(); 
}

export async function getApiEdit(id) {
    const api = `http://localhost:8026/api/admin/news/${id}`;
    const posts = {
        method: "GET",
    }
    const response = await fetch(api, posts);
    return await response.json();
}

export async function getApiFormData() {
    const api = "http://localhost:8026/api/admin/news/form-data";
    const response = await fetch(api);
    return await response.json(); 
}

export async function getApiSaveNews(data) {
    const api = data.id ? `http://localhost:8026/api/admin/news/${data.id}` : `http://localhost:8026/api/admin/news`;
    const request = new FormData();
    const { thumbnail, ...newsData } = data;
    if (thumbnail.files.length > 0) {
        request.append("thumbnailNews", thumbnail.files[0]);
    }
    request.append("newsRequest", new Blob(
        [JSON.stringify(newsData)], 
        { type: "application/json" }
    ));
    const posts = {
        method: data.id ? "PUT" : "POST",
        body: request
    }
    const response = await fetch(api, posts);
    return await response.json();
}

export async function getApiDelete(id) {
    const api = `http://localhost:8026/api/admin/news/${id}`;
    const posts = {
        method: "DELETE"
    }
    const res = await fetch(api, posts);
}