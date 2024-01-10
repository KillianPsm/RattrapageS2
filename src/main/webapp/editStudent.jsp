<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="headerT.jsp" %>

<h2>Modifier un étudiant</h2>

<form action="${pageContext.request.contextPath}/student?action=update" method="post">
    <input type="hidden" name="id" value="${student.id}">

    <div class="form-group">
        <label for="lastName">Nom:</label>
        <input type="text" id="lastName" name="lastName" class="form-control" value="${student.lastname}" required>
    </div>

    <div class="form-group">
        <label for="firstName">Prénom:</label>
        <input type="text" id="firstName" name="firstName" class="form-control" value="${student.firstname}" required>
    </div>

    <div class="form-group">
        <label for="birthDate">Date de naissance:</label>
        <input type="date" id="birthDate" name="birthDate" class="form-control" value="${student.birthdate}" required>
    </div>

    <div class="form-group">
        <label for="className">Classe:</label>
        <input type="text" id="className" name="className" class="form-control" value="${student.className}" required>
    </div>

    <button type="submit" class="btn btn-primary">Modifier</button>
</form>

</body>
</html>
