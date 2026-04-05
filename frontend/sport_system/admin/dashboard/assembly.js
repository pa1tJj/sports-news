import { clickContentsSidebar } from "../components/sidebar.js";

export function setupHeader() {
    fetch("../components/header.html")
        .then(function(res) {
            return res.text();
        })
        .then(function(res) {
            document.querySelector(".my-header").innerHTML = res;
        })
}

export function loadSidebar(onContent) {
    fetch("../components/sidebar.html")
        .then(function(res) {
            return res.text();
        })
        .then(function(res) {
            document.querySelector(".sidebar").innerHTML = res;
            clickContentsSidebar();
        })
}