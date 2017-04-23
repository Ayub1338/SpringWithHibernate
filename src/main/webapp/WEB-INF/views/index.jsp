<meta http-equiv="Cache-control" content="public">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.
%>
<html>
<head>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/jquery-3.2.1"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/index.js"></script>

</head>

<div class="container">
	<div class="col-md-3">
		<form class="navbar-form" role="search">
			<div class="input-group add-on">
				<input class="form-control" placeholder="Search" name="searchKey"
					id="searchKey" type="text">
				<div>
					<button   class="btn btn-primary"
						class="btn-group btn-group-lg" value="Search" id="searchButton"
						type="button">Search</button>
				</div>
			</div>
		</form>



	</div>
</div>
</html>