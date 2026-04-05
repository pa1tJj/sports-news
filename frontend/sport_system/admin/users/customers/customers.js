import { fetchCustomers } from "./customers.api.js";
import { handleSearch } from "./customers.events.js";
import { initPaging, renderOptionForm, renderPaging, renderTableCustomers } from "./customers.service.js";

export async function initCustomers() {
    const response = await fetchCustomers();
    renderTableCustomers(response.list, 1);
    renderOptionForm(response.object);
    renderPaging(response.totalPage);
    initPaging(response.totalPage);
    handleSearch();
}