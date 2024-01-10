<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="headerT.jsp" %>

<h2>Ajouter un Cours</h2>

<form action="${pageContext.request.contextPath}/teacher" method="post">
    <input type="hidden" name="action" value="addCourse">

    <div class="form-group">
        <label for="inputCode">Code</label>
        <input type="text" class="form-control" id="inputCode" name="inputCode" required>
    </div>

    <div class="form-group">
        <label for="inputLabel">Libelle</label>
        <input type="text" class="form-control" id="inputLabel" name="inputLabel" required>
    </div>

    <button type="submit" class="btn btn-primary">Ajouter</button>
</form>

</body>
</html>
