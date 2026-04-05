import { initCategories } from "../categories/categories.js";
import { initCharts } from "../dashboard/charts.js";
import { initDashboard } from "../dashboard/dashboard.js";
import { getAllNews} from "../news/news.js";
import { initTags } from "../tags/tags.js";
import { initCustomers } from "../users/customers/customers.js";
import { initStaff } from "../users/staff/staff.js";

export function clickContentsSidebar() {

    document.querySelector(".btn-manager #dashboard").addEventListener("click", function (e) {
        e.preventDefault();
        fetch("../dashboard/dashboard.html")
            .then(function (res) {
                return res.text();
            })
            .then(function (res) {
                document.querySelector(".content").innerHTML = res;
                initCharts();
                initDashboard();
            });
    });

    document.querySelector(".btn-manager #news").addEventListener("click", function (e) {
        e.preventDefault();
        fetch("../news/news.html")
            .then(function (res) {
                return res.text();
            })
            .then(function (res) {
                document.querySelector(".content").innerHTML = res;
                getAllNews();
            });
    });

    document.querySelector(".btn-manager #categories").addEventListener("click", function (e) {
        e.preventDefault();
        fetch("../categories/categories.html")
            .then(function (res) {
                return res.text();
            })
            .then(function (res) {
                document.querySelector(".content").innerHTML = res;
                initCategories();
            });
    });

    document.querySelector(".btn-manager #tags").addEventListener("click", function (e) {
        e.preventDefault();
        fetch("../tags/tags.html")
            .then(function (res) {
                return res.text();
            })
            .then(function (res) {
                document.querySelector(".content").innerHTML = res;
                initTags();
            });
    });

    document.querySelector(".btn-manager #staff").addEventListener("click", function (e) {
        e.preventDefault();
        fetch("../users/staff/staff.html")
            .then(function (res) {
                return res.text();
            })
            .then(function (res) {
                document.querySelector(".content").innerHTML = res;
                initStaff();
            });
    });

    document.querySelector(".btn-manager #customers").addEventListener("click", function (e) {
        e.preventDefault();
        fetch("../users/customers/customers.html")
            .then(function (res) {
                return res.text();
            })
            .then(function (res) {
                document.querySelector(".content").innerHTML = res;
                initCustomers();
            });
    });

    //giữ hover khi chọn trên sidebar
    const allLinks = document.querySelectorAll(".sidebar a");
    allLinks.forEach(link => {
        link.addEventListener("click", function () {
            allLinks.forEach(item => item.classList.remove("active"));
            this.classList.add("active");
        });
    });
}


