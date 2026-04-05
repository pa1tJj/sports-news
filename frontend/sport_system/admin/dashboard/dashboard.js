import { fetchDashboard } from "./dashboard.api.js";

export async function initDashboard() {
    const res = await fetchDashboard();
    renderDashboard(res);
}

function renderDashboard(data) {
    document.querySelector("#totalPosts").innerText = data.totalPosts;
    document.querySelector("#totalViews").innerText = data.totalViews;
    document.querySelector("#totalUsers").innerText = data.totalUsers;
    renderTablePosts(data.topPosts);
}

function renderTablePosts(data) {
    const tbody = document.querySelector("tbody");
    tbody.innerHTML = "";
    tbody.innerHTML = data.map(item => 
        `
            <tr>
                <td>${item.title}</td>
                <td><span class="badge bg-primary">${item.views}</span></td>
                <td><span class="badge bg-success">${item.status}</span></td>
            </tr>
        `
    ).join("");
}

function renderTablePostsComments(data) {
    const tbody = document.querySelector("tbody");
    tbody.innerHTML = "";
    tbody.innerHTML = data.map(item => 
        `
            <tr>
                <td>Phân tích rất hay!</td>
                <td><span class="badge bg-warning text-dark">Pending</span></td>
                <td><button class="btn btn-sm btn-outline-success">Duyệt</button></td>
            </tr>
        `
    ).join("");
}