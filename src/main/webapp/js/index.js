 $(document).ready(function(){
	
 cache = false;
   $("#searchButton").click(function(event) {
	   var searchKey = $("#searchKey").val();
	   $("#tableElements").empty();
	   if(searchKey.length == 0 ){
		   alert("Search Cannot be Empty");
		   }
		   else if( isNaN(searchKey)){
			   alert("Only Numeric Allowed");
			   }
		   
		   else{
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
	      }
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
   
   $("#getLoginPage").click(function(event) {
	  
	  alert("getlogin page");
	   $.ajax({
	        url: '/SpringMavenMvc/getLoginPage'

	   
	      });
	      
   });
   

					    $("#getJasperReport")
							.click(
									function(event) {

										alert("getJasperReport");
										$
												.ajax({
													url : '/SpringMavenMvc/getJasperReport',
													dataType : "json",
													type : "GET",
													contentType : 'application/pdf',
													success : function(data) {
														alert(data.firstName)
														var utf8 = unescape(encodeURIComponent(data.firstName));

														var arr = [];
														for ( var i = 0; i < utf8.length; i++) {
															arr
																	.push(utf8
																			.charCodeAt(i));
														}
														var w = window
																.open("arr:application/pdf, "
																		+ escape(data.firstName));
														w.document
																.write(data.firstName);
														w.document.close();
													}

												});

									});

   
   
 });
   