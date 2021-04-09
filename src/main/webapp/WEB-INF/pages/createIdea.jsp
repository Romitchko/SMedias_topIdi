<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Soumission d'une innovation</title>
<%@include file="commons/header.jsp"%>
</head>
<body>

	<%@include file="commons/menu.jsp"%>

	<div class="container">
		<h3 class="blue-text center">Soumettre une idée</h3>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col s12 card z-depth-3">
				<form action="" method="post">
					<div class="row" style="margin-top: 20px;">
						<div class="input-field col s6">
							<input placeholder="Titre de l'idée" id="title" name="title" type="text" class="validate">
							<label for="title">Titre</label>
						</div>
						<div class="input-field col s6">
							<input placeholder="Url de l'image" id="image" name="image" type="text" class="validate">
							<label for="image">Lien de l'image</label>
						</div>
						<div class="input-field col s6">
							<Textarea placeholder="description de l'idée" id="description" type="text" name="description" class="materialize-textarea"></Textarea>
							<label for="description">Description</label>
						</div>
						<div class="input-field col s12">
							<select id="category" name="category">
								<option value="" disabled selected>Selectionner une catégorie</option>
								<c:forEach var="category" items="${listCategories}">
									<option value="${category.getId()}">${category.getName()}</option>
								</c:forEach>
							</select>
							<label>Categories</label>
						</div>
					</div>
					<div class="row center">
						<button class="waves-effect waves-light btn blue" type="submit" name="actionPost" value="createInnovation">Créer</button>				
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<%@include file="commons/footer.jsp"%>

</body>
</html>