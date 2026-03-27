export function renderTable(element, index, currentPage) {
    return `
        <tr>
            <td>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" data-id="${element.id}">
                </div>
            </td>
            <td class="stt">${(currentPage -1)* 5 + index + 1}</td>
            <td>${element.title}</td>
            <td>${element.categoryName}</td>
            <td>${element.status}</td>
            <td>${element.views}</td>
            <td>${element.createdDate}</td>
            <td>
                <button class="btn btn-warning btn-sm btn-edit" data-id="${element.id}"><i class="bi bi-pencil"></i></button>
                <button class="btn btn-danger btn-sm btn-delete" data-id="${element.id}"><i class="bi bi-trash"></i></button>
            </td>
        </tr>
    `;
}