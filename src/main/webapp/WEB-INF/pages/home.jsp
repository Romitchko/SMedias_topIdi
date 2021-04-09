<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<%@include file="commons/header.jsp"%>

</head>
<body>

	<%@include file="commons/menu.jsp"%>
	
	<div class="container">
		<h3 class="blue-text center">Liste des innovations</h3>
	</div>

	<div class="container">
		<div class="row">
			<c:forEach var="idea" items="${listIdeas}">
				<div class="col s12 l4">
					<div class="card">
						<div class="card-image">
							<a href="idea?action=view&id=${idea.getId()}">
								<img src="${idea.getImage()}">
								<span class="card-title blue-text"><b>${idea.getTitle()}</b></span>
							</a>
						</div>
						<div class="card-content">
							<div class="row">
								<p>${idea.getCreatedAt()}</p>
								<p>${idea.getLittleDescription()}
									<a href="idea?action=view&id=${idea.getId()}">En savoir plus</a>
								</p>
							</div>
							<c:choose>
		      					<c:when test="${isConnected == true}">
									<div class="row" style="margin-bottom: 0px;">
										<div class="col s6">
											<p>${idea.getNumberOfVotes()} <i class="material-icons blue-text">thumbs_up_down</i></p>
										</div>
									</div>
								</c:when>
							</c:choose>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<%@include file="commons/footer.jsp"%>
</body>
</html>