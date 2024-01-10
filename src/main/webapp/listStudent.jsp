<%@ page import="java.util.List" %>
<%@ page import="fr.vannes.rattrapages2.model.User" %>
<%@ page import="fr.vannes.rattrapages2.controller.TeacherServlet" %>

<%
    List<User> students = (List<User>) request.getAttribute("students");
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="headerT.jsp" %>

<h2>Liste des Étudiants</h2>

<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Nom</th>
        <th scope="col">Prénom</th>
        <th scope="col">Date de naissance</th>
        <th scope="col">Classe</th>
        <th scope="col">Actions</th>
    </tr>
    </thead>
    <tbody>
    <% for (User student : students) { %>
    <tr>
        <th scope="row"><%= student.getId() %>
        </th>
        <td><%= student.getLastname() %>
        </td>
        <td><%= student.getFirstname() %>
        </td>
        <td><%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(student.getBirthdate()) %>
        </td>
        <td><%= student.getClassName() %>
        </td>
        <td>
            <div class="btn-group" role="group">
                <a href="#" class="btn btn-outline-secondary">Modifier</a>
                <a href="#" class="btn btn-outline-danger"
                   onclick="confirmDelete(<%= student.getId() %>);">Supprimer</a>
            </div>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>

<script>
    function confirmDelete(studentId) {
        var confirmation = confirm("Voulez-vous vraiment supprimer cet étudiant ?");
        if (confirmation) {
            window.location.href = '<%= request.getContextPath() %>/teacher?action=deleteStudent&id=' + studentId;
        }
    }
</script>

</body>
</html>
