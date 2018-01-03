(function () {
    var ctx = document.getElementById("myChart").getContext('2d');
    $.get("/api/employees/?size=500", function (employeesData) {
        var employees = employeesData._embedded.employees;
        var data = {
            labels: [],
            data: []
        };
        employees.forEach(function (employee) {
            data.labels.push(employee.firstName + " " + employee.lastName);
            data.data.push(employee.salary);
        });
        var myChart = new Chart(ctx, {
            type: 'horizontalBar',
            data: {
                labels: data.labels,
                datasets: [{
                    label: 'Salary',
                    data: data.data,
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }
        });
    });
}());
