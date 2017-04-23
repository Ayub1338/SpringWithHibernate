 $(document).ready(function(){
   alert("inside index.js");
  
 cache = false;
   $("#searchButton").click(function(event) {
	   var searchKey = $("#searchKey").val();
	   alert(searchKey);
	   $.ajax({
	        url: '/SpringMavenMvc/getActorDetails/'+searchKey,
	        dataType: "json",
	        type: "GET",
	        contentType: 'application/json',
	   success: function(data){
		   var tableCellValues = "<tr width = '20%' >" ;
		   dataInJsonFormat = JSON.stringify(data);
		   alert(dataInJsonFormat);
		   $.each(data, function(key, value) {
		        alert(key + ' ' + value);
		        tableCellValues = tableCellValues + "<td>" + value + "</td>";
		        alert(tableCellValues);
		       
		    });
		    tableCellValues = tableCellValues +"</tr><br/>";
		    alert(tableCellValues);
		    	$("#table").append(tableCellValues);
		    
		   
		   

	   },
	      error: function(xhr, status, error) {
		   alert(status+error)
		   }
	   
	      });
   });
   
  
   
   
  });