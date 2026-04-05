import { handleEditCategory, handleSaveCategoryForm } from "./categories.events.js";
import { initDialog, renderCategoriesTable } from "./categories.service.js";


export async function initCategories() {
    renderCategoriesTable();
    handleEditCategory();
    handleSaveCategoryForm();
    initDialog();
}


