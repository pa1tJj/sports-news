import { getDataSearch } from "./customers.service.js";

export async function fetchCustomers(page) {
    var api = `http://localhost:8026/api/admin/customers`;
    const params = getDataSearch(page);
    if(params) {
        api += "?" + params;
    }
    const res = await fetch(api);
    return await res.json();
}