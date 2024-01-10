<%@ page import="java.util.List" %>
<%@ page import="fr.vannes.rattrapages2.model.Course" %>
<%@ page import="fr.vannes.rattrapages2.controller.TeacherServlet" %>

<%
    List<Course> courses = (List<Course>) request.getAttribute("courses");
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%-- Si l'utilisateur connecte est un professeur --%>
<% if (session.getAttribute("role") != null && (int) session.getAttribute("role") == 1) { %>
<%@ include file="headerT.jsp" %>
<% } %>

<%-- Si l'utilisateur connecte est un eleve --%>
<% if (session.getAttribute("role") != null && (int) session.getAttribute("role") == 2) { %>
<%@ include file="headerS.jsp" %>
<% } %>

<h2>Liste des Cours</h2>

<% if (courses != null) { %>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Code</th>
        <th scope="col">Label</th>
        <% if (session.getAttribute("role") != null && (int) session.getAttribute("role") == 1) { %>
        <th scope="col">Actions</th>
        <% } %>
    </tr>
    </thead>
    <tbody>
    <% for (Course course : courses) { %>
    <tr>
        <th scope="row"><%= course.getId() %>
        </th>
        <td><%= course.getCode() %>
        </td>
        <td><%= course.getLabel() %>
        </td>
        <% if (session.getAttribute("role") != null && (int) session.getAttribute("role") == 1) { %>
        <td>
            <div class="btn-group" role="group">
                <a href="#" class="btn btn-outline-secondary">Modifier</a>
                <a href="delete" class="btn btn-outline-danger">Supprimer</a>
            </div>
        </td>
        <% } %>
    </tr>
    <% } %>
    </tbody>
</table>
<% } else { %>
<p>Aucun cours disponible pour le moment.</p>
<% } %>

</body>
</html>
