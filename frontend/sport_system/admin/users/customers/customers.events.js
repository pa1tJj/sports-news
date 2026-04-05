import { fetchCustomers } from "./customers.api.js"

export function handleSearch() {
    document.querySelector(".btnSearch").addEventListener("click", async (e) => {
        fetchCustomers(1);
    })
}