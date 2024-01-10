<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="headerT.jsp" %>

<h2>Ajouter un Étudiant</h2>

<form action="${pageContext.request.contextPath}/teacher" method="post">
    <input type="hidden" name="action" value="addStudent">

    <div class="form-group">
        <label for="inputLastName">Nom</label>
        <input type="text" class="form-control" id="inputLastName" name="inputLastName" required>
    </div>

    <div class="form-group">
        <label for="inputFirstName">Prénom</label>
        <input type="text" class="form-control" id="inputFirstName" name="inputFirstName" required>
    </div>

    <div class="form-group">
        <label for="inputBirthdate">Date de naissance</label>
        <input type="date" class="form-control" id="inputBirthdate" name="inputBirthdate" required>
    </div>

    <div class="form-group">
        <label for="inputClassName">Classe</label>
        <input type="text" class="form-control" id="inputClassName" name="inputClassName" required>
    </div>

    <div class="form-group">
        <label for="inputPwd">Mot de passe</label>
        <input type="text" class="form-control" id="inputPwd" name="inputPwd" required>
    </div>

    <button type="submit" class="btn btn-primary">Ajouter</button>
</form>

</body>
</html>
