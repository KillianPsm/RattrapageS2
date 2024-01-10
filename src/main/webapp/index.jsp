<%@ page import="fr.vannes.rattrapages2.model.User" %>

<% if (session.getAttribute("role") != null && (int) session.getAttribute("role") == 1) { %>
<%@ include file="headerT.jsp" %>
<% } %>

<%-- Si l'utilisateur connecté est un élève --%>
<% if (session.getAttribute("role") != null && (int) session.getAttribute("role") == 2) { %>
<%@ include file="headerS.jsp" %>
<% } %>

</body>
</html>
