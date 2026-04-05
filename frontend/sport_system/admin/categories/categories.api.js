
export async function fetchCategories() {
    const api = "http://localhost:8026/api/admin/categories";
    const response = await fetch(api);
    return await response.json();
}

export async function detailCategory(id) {
    const api = `http://localhost:8026/api/admin/categories/${id}`;
    const response = await fetch(api);
    return await response.json();
}

export async function saveCategory(category) {
    const api = category.id ? `http://localhost:8026/api/admin/categories/${category.id}`: `http://localhost:8026/api/admin/categories`;
    const posts = {
        method: category.id ? "PUT" : "POST",
        headers: { "Content-type": "application/json"},
        body: JSON.stringify(category)
    }
    const res = await fetch(api, posts);
    return await res.json();
}

export async function deleteCategory(id) {
    const api = `http://localhost:8026/api/admin/categories/${id}`;
    const posts = {
        method: "DELETE"
    }
    const res = await fetch(api, posts);
}