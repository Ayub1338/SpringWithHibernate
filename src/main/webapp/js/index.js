 $(document).ready(function(){
  
 cache = false;
   $("#searchButton").click(function(event) {
	   $("#tableElements").empty();
	   var searchKey = $("#searchKey").val();
	   $.ajax({
	        url: '/SpringMavenMvc/getActorDetails/'+searchKey,
	        dataType: "json",
	        type: "GET",
	        contentType: 'application/json',
	   success: function(data){
		   var tableCellValues = "<tr width = '20%' >" ;
		   dataInJsonFormat = JSON.stringify(data);
		   $.each(data, function(key, value) {
		        tableCellValues = tableCellValues + "<td>" + value + "</td>";
		       
		    });
		    tableCellValues = tableCellValues +"</tr><br/>";
		    	$("#tableElements").append(tableCellValues);
		    
		   
		   

	   },
	      error: function(xhr, status, error) {
		   alert(status+error)
		   }
	   
	      });
   });
   $("#getAllActors").click(function(event) {
	   $("#tableElements").empty();
	   $.ajax({
	        url: '/SpringMavenMvc/getAllActors',
	        dataType: "json",
	        type: "GET",
	        contentType: 'application/json',
	   success: function(data){
		   var tableCellValues = null;
		   dataInJsonFormat = JSON.stringify(data);
		   $.each(data, function(key, value) {
			     tableCellValues = tableCellValues+"<tr width = '20%' >" ;
		        tableCellValues = tableCellValues + "<td>" + value.actorId + "</td>";
		        tableCellValues = tableCellValues + "<td>" + value.firstName + "</td>";
		        tableCellValues = tableCellValues + "<td>" + value.lastName + "</td>";
		        tableCellValues = tableCellValues + "<td>" + value.lastUpdate + "</td>";
		    tableCellValues = tableCellValues +"</tr><br/>";
		    });
		    	$("#tableElements").append(tableCellValues);
		    
		   
		   

	   },
	      error: function(xhr, status, error) {
		   alert(status+error)
		   }
	   
	      });
   });
  
   
   
  });