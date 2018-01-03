(function () {
    var ctx = document.getElementById("myChart").getContext('2d');
    $.get("/api/custom/employees/deciles/?size=500", function (employeesData) {
    	var employees = employeesData;
    	var data = {
                labels: [],
                data: []
    	};
    	
    	$.each(employees, function (i, employee) {
    		
    		s=0;
    		$.each(employees, function (j, employee) {
        		s=s+Object.keys(employee).length;
        	 });
    		            data.labels.push("Decile :"+i);   
    		            data.data.push((((Object.keys(employee).length)/s)*100).toFixed(2));
    		        });
    	
    	 var myChart = new Chart(ctx, {
             type: 'bar',
             data: {
                 labels: data.labels,
                 datasets: [{
                     label: 'The percentage of employees per decile',
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
