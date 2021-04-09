<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Se connecter</title>
<%@include file="commons/header.jsp"%>
</head>
<body>

	<div id="slayer" class="" style="height: 100vh;display: flex;justify-content: center;align-items: center;">
        <div class="container" id="content" style="">
            <div class="row">
                <div class="col l4 m2 s12"></div>
                <div class="col l4 m8 s12">
                    <form action="" method="POST">
                        <div class="card-panel z-depth-5" style="border-radius: 12px; border-top: 3px solid #2196F3;">
                        	<div class="circle center" style="height: 100px;">
                                <h3><a href="./" class="blue-text">S Medias</a></h3>
                            </div>
                            <p class="center row">Espace perso</p>
                            <div class="input-field">
                                <label for="email">Email</label>
                                <input type="email" placeholder="saisissez votre email ou nom d'utilisateur" id="email" name="email" focus class="validate" required>
                            </div>
                            <div class="input-field">
                                <label for="password">Password</label>
                                <input type="password" placeholder="saisissez votre mot de passe" id="password" name="password" class="validate" required>
                            </div>
                            <div class="row" id="errors_container">
                                <span class="error" style="display: flex;align-items: end;">${status}</span>
                            </div>
                            <input id="submit" type="submit" name="actionPost" value="signOn" class="btn left col s12 blue">
                            <div class="clearfix"></div>
                        </div>
                        <p class="right"><a href="signIn?action=signUp" class="" style="color: inherit !important;" href="">S'inscrire</a></p>
                    </form>
                </div>
                <div class="col l4 m2 s12"></div>
            </div>
        </div>
    </div>

</body>
</html>