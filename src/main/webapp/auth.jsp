<!DOCTYPE html>
<html lang="en">
<% String ctxPath = request.getContextPath(); %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Signin Template for Bootstrap</title>

    <link href="<%= ctxPath %>/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%= ctxPath %>/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="<%= ctxPath %>/assets/css/signin.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <!-- TODO : fix form method -->
    <form class="form-signin" method="POST" action="auth">

        <!-- TODO : check for error message and display this div -->
        <% if (request.getAttribute("errorMessage") != null) {%>
        <div class="alert alert-danger" role="alert"><%=request.getAttribute("errorMessage")%>
        </div>
        <%}%>

        <h2 class="form-signin-heading">Identifiez-vous</h2>
        <label for="inputLog" class="sr-only">Login</label>
        <input id="inputLog" name="inputLog" class="form-control" placeholder="Login" required=""
               autofocus="" type="text">
        <label for="inputPwd" class="sr-only">Mot de passe</label>
        <input id="inputPwd" name="inputPwd" class="form-control" placeholder="Mot de passe" required=""
               type="password">
        <div class="checkbox">
            <label>
                <input value="remember-me" type="checkbox">Se souvenir de moi
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Me connecter</button>
    </form>

</div>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<%= ctxPath %>/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>