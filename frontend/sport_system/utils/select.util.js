
export function populateSelectOptions(list, id) {
    let dataSelect = document.querySelector(id);
    dataSelect.innerHTML = "";
    let defaultOption = document.createElement("option");
    defaultOption.value = "";
    defaultOption.text = "--Choose--";
    defaultOption.selected = true;
    // defaultOption.disabled = true;
    dataSelect.appendChild(defaultOption);
    Object.entries(list).forEach(function(item) {
        let option = document.createElement("option");
        option.value = item[0];
        option.text = item[1];
        dataSelect.appendChild(option);
    })
}

export function populateSelectOptionsNewsEditForm(options1, options2, id) {
    let dataSelect = document.querySelector(id);
    dataSelect.innerHTML = "";
    let defaultOption = document.createElement("option");
    defaultOption.value = "";
    defaultOption.text = "--Choose--";
    dataSelect.appendChild(defaultOption);
    Object.entries(options1).forEach(([key, value]) => {
        let option = document.createElement("option");
        option.value = key;
        option.text = value;
        // Object.entries(options2).forEach(it => {
        //     if(it[0] === item[0]) {
        //         option.selected = true;
        //     }
        // })
        if (options2 && options2[key] !== undefined) {
            option.selected = true;
        }
        dataSelect.appendChild(option);
    })
}