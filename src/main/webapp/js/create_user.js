 $(document).ready(function(){
	
	   $("#create_user").click(function(event) {
		   var userName = $("#userName").val();
		   var password = $("#password").val();
		   var emailId = $("#emailId").val();
		   var name = $("#name").val();
	 $.ajax({
	        url: '/SpringMavenMvc/createUser',
	        	//?userName='+userName+'&password='+password+'&emailId='+emailId,
	        dataType: "json",
	        type: "POST",
	        data : JSON.stringify({ 
	        "userName": userName,
	        "password": password,
	        "email": emailId,
	    }),
	        contentType: 'application/json',
	   success: function(data){
	        		alert(JSON.stringify(data))
		 	alert("user created successfully");
	 },
	      error: function(xhr, status, error) {
		   alert(status+error);
		   var test = $.parseJSON(jqXHR.responseText);
		    var test2 = $.parseJSON(test.d);
		    alert(test2[0].Name);
		   }
	   
	      });  });
 });
 
 