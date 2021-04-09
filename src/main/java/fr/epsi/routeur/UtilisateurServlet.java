package fr.epsi.routeur;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.epsi.entite.Utilisateur;
import fr.epsi.service.UtilisateurService;

@WebServlet("/signIn")
public class UtilisateurServlet extends HttpServlet {
	
	@EJB
	private UtilisateurService utilisateurService;
	
	/* Message d'erreur affiché */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		HttpSession session = req.getSession();

		if(req.getParameter("action").equals("signUp")) {
			 this.getServletContext().getRequestDispatcher("/WEB-INF/pages/signUp.jsp").forward(req, resp);
		} else if(req.getParameter("action").equals("signOn")) {
		    this.getServletContext().getRequestDispatcher("/WEB-INF/pages/signOn.jsp").forward(req, resp);
		} else if(req.getParameter("action").equals("exit")) {
			session.removeAttribute("isConnected");
		    resp.sendRedirect("signIn?action=signOn");
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
		if(req.getParameter("actionPost").equals("signUp")) {
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setEmail(req.getParameter("email"));
			utilisateur.setNom(req.getParameter("firstName"), req.getParameter("lastName"));
			utilisateur.setPassword(req.getParameter("password"));
			
			if (utilisateurService.checkEmailUtilisateur(utilisateur.getEmail())) {
				utilisateurService.addUtilisateur(utilisateur);
				String status = "<p class='green-text text-lighten-1'>Bravo! Inscription reussie, conectez-vous.</p>";
				req.setAttribute("status", status);
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/signUp.jsp").forward(req, resp);
			} else {
				String status = "<p class='red-text text-lighten-1'>Email déjà existante</p>";
				req.setAttribute("status", status);
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/signUp.jsp").forward(req, resp);
			}
	    }
		// Connexion
		if(req.getParameter("actionPost").equals("signOn")) {
			/* Inscription de l'utilisateur */
			String email 	= req.getParameter("email");
			String password = req.getParameter("password");
			if (!utilisateurService.checkUtilisateur(email, password)) {
				/* creation de la session */
				HttpSession session = req.getSession();
		        session.setAttribute("isConnected", true);
		        session.setAttribute("UtilisateurId", (utilisateurService.getUtilisateur(email)).getId());
				/* Redirection page home */
		        resp.sendRedirect("home");
			} else {
				/* Affichage de la page de connexion */
				String status = "<p class='red-text text-lighten-1'>Mauvais mdp ou email</p>";
				req.setAttribute("status", status);
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/signOn.jsp").forward(req, resp);
			}
	    }
	}
	
}
