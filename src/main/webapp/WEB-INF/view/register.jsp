<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>ToDo App: Register</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">

</head>

</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">

		<h2>User Register Form</h2>
		<div class="col-md-6 col-md-offset-3">
			<div class="alert alert-success center" role="alert">
				<p>${NOTIFICATION}</p>
			</div>

			<form action="register" method="post">

				<div class="form-group">
					<label for="uname">First Name:</label> <input type="text"
						class="form-control" id="uname" placeholder="First Name"
						name="firstName" required>
				</div>

				<div class="form-group">
					<label for="uname">Last Name:</label> <input type="text"
						class="form-control" id="uname" placeholder="last Name"
						name="lastName" required>
				</div>

				<div class="form-group">
					<label for="uname">User Name:</label> <input type="text"
						class="form-control" id="username" placeholder="User Name"
						name="username" required>
				</div>

				<div class="form-group">
					<label for="uname">Password:</label> <input type="password"
						class="form-control" id="password" placeholder="Password"
						name="password" required>
				</div>

				<button type="submit" class="btn btn-primary">Submit</button>

			</form>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>