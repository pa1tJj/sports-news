import { fetchDashboard } from "./dashboard.api.js";

export async function initCharts() {
    const res = await fetchDashboard();
    // Cấu hình chung cho Chart.js
    Chart.defaults.font.family = "Segoe UI, Roboto, Helvetica, Arial";

    // Biểu đồ lượt xem (Line Chart)
    const ctxViews = document.getElementById("viewsChart");
    new Chart(ctxViews, {
        type: "line",
        data: {
            labels: Object.keys(res.viewStats),
            datasets: [
                {
                    label: "Views",
                    tension: 0.3,
                    backgroundColor: "rgba(78, 115, 223, 0.05)",
                    borderColor: "rgba(78, 115, 223, 1)",
                    pointRadius: 3,
                    pointBackgroundColor: "rgba(78, 115, 223, 1)",
                    pointBorderColor: "rgba(78, 115, 223, 1)",
                    data: Object.values(res.viewStats),
                },
            ],
        },
        options: {
            maintainAspectRatio: false,
            responsive: true,
            plugins: { legend: { display: false } },
        },
    });

    // Biểu đồ danh mục (Doughnut Chart)
    const ctxCat = document.getElementById("categoryChart");
    new Chart(ctxCat, {
        type: "doughnut",
        data: {
            labels: res.categoryStats.map(it => it.name),
            datasets: [
                {
                    data: res.categoryStats.map(it => it.total),
                    backgroundColor: res.categoryStats.map((_, index) => {
                        const colors = ["#4e73df", "#1cc88a", "#36b9cc", "#f6c23e", "#e74a3b"];
                        return colors[index % colors.length];
                    }),
                    hoverBackgroundColor: ["#4e73df", "#1cc88a", "#36b9cc", "#f6c23e", "#e74a3b"],
                    hoverBorderColor: "rgba(234, 236, 244, 1)",
                },
            ],
        },
        options: {
            maintainAspectRatio: false,
            plugins: { legend: { position: "bottom" } },
            cutout: "70%",
        },
    });

};
