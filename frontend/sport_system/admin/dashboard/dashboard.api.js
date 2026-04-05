export async function fetchDashboard() {
    const api = `http://localhost:8026/api/admin/dashboard`;
    const res = await fetch(api);
    return await res.json();
}