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
		   alert(JSON.stringify(data))
	   },
	      error: function(xhr, status, error) {
		   alert(status+error)
		   }
	   
	      });
   });
   
  
   
   
  });