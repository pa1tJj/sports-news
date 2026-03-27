import { getAllNews} from "../news/news.js";

export function clickContentsSidebar() {

    document.querySelector(".btn-manager #news").addEventListener("click", function (e) {
        e.preventDefault();
        fetch("../news/news.html")
            .then(function (res) {
                return res.text();
            })
            .then(function (res) {
                document.querySelector(".content").innerHTML = res;
            });
    });

    document.querySelector(".btn-manager #article").addEventListener("click", function (e) {
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

    document.querySelector(".btn-manager #users").addEventListener("click", function (e) {
        e.preventDefault();
        fetch("../users/users.html")
            .then(function (res) {
                return res.text();
            })
            .then(function (res) {
                document.querySelector(".content").innerHTML = res;
            });
    });
}
