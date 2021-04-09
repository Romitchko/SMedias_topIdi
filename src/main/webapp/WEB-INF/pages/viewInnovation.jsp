<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${idea.getTitle()}</title>
<%@include file="commons/header.jsp"%>

</head>
<body>

	<%@include file="commons/menu.jsp"%>
	
	<div class="container">
		<h3 class="blue-text center">${idea.getTitle()}</h3>
		<div class="row center" style="display: flex; justify-content: center;">
			<form method="post">
				<input type="hidden" name="idea" value="${idea.getId()}">
				<c:choose>
					<c:when test="${alreadyVoted == true}">
						<c:choose>
							<c:when test="${userVote == 1}">
								<div class="col">
									${likes} 
									<button class="btn-floating btn-large waves-effect waves-light green" name="actionPost" value="deleteVote"> <i class="material-icons">J'aime</i></button>
								</div>
								<div class="col">
									${dislikes} 
									<button class="btn-floating btn-large waves-effect waves-light red grey" disabled="disabled" name="actionPost" value="disabled"> <i class="material-icons">Bof</i></button>
								</div>
							</c:when>
							<c:when test="${userVote == 0}">
								<div class="col">
									${likes} 
									<button class="btn-floating btn-large waves-effect waves-light grey" disabled="disabled" name="actionPost" value="disabled"> <i class="material-icons">J'aime</i></button>
								</div>
								<div class="col">
									${dislikes} 
									<button class="btn-floating btn-large waves-effect waves-light red" name="actionPost" value="deleteVote"> <i class="material-icons">Bof</i></button>
								</div>
							</c:when>
						</c:choose>
					</c:when>
					<c:when test="${alreadyVoted == false }">
						<div class="col">
							${likes} 
							<button class="btn-floating btn-large waves-effect waves-light grey" name="actionPost" value="likeIdea"> <i class="material-icons">J'aime</i></button>
						</div>
						<div class="col">
							${dislikes} 
							<button class="btn-floating btn-large waves-effect waves-light grey" name="actionPost" value="dislikeIdea"> <i class="material-icons">Bof</i></button>
						</div>
					</c:when>
				</c:choose>
			</form>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col l7 s12">
				<div class="row">
					<div class="z-depth-3">
						<img class="materialboxed" width="100%" height="400px" src="${idea.getImage()}">
					</div>
				</div>
				<div class="row">
					<p>${idea.getCreatedAt()}</p>
					<p>${idea.getDescription()}</p>
				</div>
			</div>
			<div class="col l1 s12 hide-on-small-and-down"></div>
			<div class="col l4 s12 card">
				<div class="row center" style=" margin-bottom: 0px;">
					<h5 class="blue-text">Commentaires</h4>
				</div>
				<div class="divider"></div>
				<div class="row" style="height: 500px; overflow: auto; margin-bottom: 0px; padding: 10px;">
					<c:choose>
						<c:when test="${not empty listComments}">
							<c:forEach var="comment" items="${listComments}">
								<div class="row">
									<div class="col s3">${comment.user.getNom()}</div>
									<div class="col s7"><b>${comment.content}</b></div>
									<c:choose>
										<c:when test="${comment.user.getId() == sessionUserId}">
											<form action="" method="post">
												<div class="col right" style="padding-left: 0;">
													<input type="hidden" name="commentId" value="${comment.getId()}">
													<button type="submit" class="btn-floating transparent" name="actionPost" value="deleteComment"><i class="material-icons red-text">Supprimer</i></button>
												</div>
											</form>
										</c:when>
									</c:choose>
								</div>
							</c:forEach>
						</c:when>
						<c:when test="${empty listComments}">
							<div class="col s12 center">Aucun commentaire</div>
						</c:when>
					</c:choose>
				</div>
				<div class="divider"></div>
				<div class="row" style=" margin-bottom: 0px;">
					<form action="" method="post">
						<div class="input-field col s10">
							<input placeholder="votre commentaire" id="comment" name="content" type="text" class="validate">
				        </div>
				        <input type="hidden" name="idea" value="${idea.getId()}">
			        	<button class="input-field btn col s1 waves-effect waves-light blue center" type="submit" name="actionPost" value="addComment">
							<i class="material-icons right">Envoyer</i>
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>