<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Créer un utilisateur</title>
<%@include file="commons/header.jsp"%>
</head>
<body>
 <%@include file="commons/menu.jsp"%>

<form action="create" method="post">
 <label for="pseudoUtilisateur">Pseudo</label> <input type="text" name="pseudoUtilisateur" />
 <label for="roleUtilisateur">Role</label> <input type="text" name="roleUtilisateur" />
 <label for="motdepasseUtilisateur">Mot de passe</label> <input type="text" name="motdepasseUtilisateur" />
 
 <input type="submit" value="Valider">
</form>
 <%@include file="commons/footer.jsp"%>

</body>
</html>