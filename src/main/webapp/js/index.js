 $(document).ready(function(){
  
 cache = false;
   $("#searchButton").click(function(event) {
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
		    	$("#table").append(tableCellValues);
		    
		   
		   

	   },
	      error: function(xhr, status, error) {
		   alert(status+error)
		   }
	   
	      });
   });
   
  
   
   
  });