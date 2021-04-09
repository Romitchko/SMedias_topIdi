package fr.epsi.routeur;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.epsi.entite.Commentaire;
import fr.epsi.entite.Innovation;
import fr.epsi.entite.Vote;
import fr.epsi.service.CategorieService;
import fr.epsi.service.CommentaireService;
import fr.epsi.service.InnovationService;
import fr.epsi.service.UtilisateurService;
import fr.epsi.service.VoteService;

@WebServlet("/innovation")
public class InnovationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private InnovationService innovationService;
	@EJB
	private UtilisateurService utilisateurService;
	@EJB
	private CategorieService categorieService;
	@EJB
	private CommentaireService commentaireService;
	@EJB
	private VoteService voteService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
		HttpSession session = req.getSession();
		
		req.setAttribute("isConnected", session.getAttribute("isConnected"));

		if(null == session.getAttribute("isConnected")) {
	        resp.sendRedirect("signIn?action=signOn");
		} else  {
			Long utilisateurId = (Long) session.getAttribute("utilisateurId");
			if(req.getParameter("action").equals("create")) {
				req.setAttribute("listCategories", categorieService.getListCategories());
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/createInnovation.jsp").forward(req, resp);
			} else if(req.getParameter("action").equals("view")) {
				String innovationid = req.getParameter("id");
				System.out.println("L'id de l'innovation est " + Long.parseLong(innovationid));
				
				Long innovationId = Long.parseLong(req.getParameter("id"));
				req.setAttribute("innovation", innovationService.getInnovation(innovationId));
				req.setAttribute("likes", voteService.getLikes(innovationService.getInnovation(innovationId)));
				req.setAttribute("dislikes", voteService.getDislikes(innovationService.getInnovation(innovationId)));
				req.setAttribute("listCommentaires", commentaireService.getListCommentaires(innovationService.getInnovation(innovationId)));
				req.setAttribute("alreadyVoted", voteService.checkIfAlreadyVoted(utilisateurService.getUtilisateur(utilisateurId), innovationService.getInnovation(innovationId)));
				req.setAttribute("sessionUtilisateurId", session.getAttribute("utilisateurId"));
				
				Vote userVote = voteService.getUtilisateurVote(innovationService.getInnovation(innovationId), utilisateurService.getUtilisateur(utilisateurId));
				if(userVote != null)
					req.setAttribute("utilisateurVote", voteService.getUtilisateurVote(innovationService.getInnovation(innovationId), utilisateurService.getUtilisateur(utilisateurId)).getVote());
				
				System.out.println("Nombre de mentions j'aime : " + voteService.getLikes(innovationService.getInnovation(innovationId)));
				
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/viewInnovation.jsp").forward(req, resp);
			} else if(req.getParameter("action").equals("exit")) {
				session.removeAttribute("isConnected");
			    resp.sendRedirect("signIn?action=signOn");
			}
		}
    }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
		HttpSession session = req.getSession();
		Long utilisateurId = (Long) session.getAttribute("utilisateurId");
		
		if(req.getParameter("actionPost").equals("createInnovation")) {
			Innovation innovation = new Innovation();
			innovation.setTitle(req.getParameter("title"));
			innovation.setDescription(req.getParameter("description"));
			innovation.setImage(req.getParameter("image"));
			innovation.setUtilisateur(utilisateurService.getUtilisateur(utilisateurId));
			innovation.setCategorie(categorieService.getCategorie(Long.parseLong(req.getParameter("categorie"))));
			
			innovationService.addInnovation(innovation);
			
			resp.sendRedirect("home");
		} else if(req.getParameter("actionPost").equals("addCommentaire")) {
			Commentaire commentaire = new Commentaire();
			commentaire.setContent(req.getParameter("content"));
			commentaire.setInnovation(innovationService.getInnovation(Long.parseLong(req.getParameter("innovation"))));
			commentaire.setUtilisateur(utilisateurService.getUtilisateur(utilisateurId));
			
			commentaireService.addCommentaire(commentaire);
			
			resp.sendRedirect("innovation?action=view&id=" + Long.parseLong(req.getParameter("innovation")));
		} else if(req.getParameter("actionPost").equals("deleteCommentaire")) {
			Long commentaireId = Long.parseLong(req.getParameter("commentaireId"));
			
			commentaireService.deleteCommentaire(commentaireService.getCommentaire(commentaireId));
			
			resp.sendRedirect("innovation?action=view&id=" + Long.parseLong(req.getParameter("innovation")));
		} else if(req.getParameter("actionPost").equals("likeIdea")) {
			Vote vote = new Vote();
			vote.setInnovation(innovationService.getInnovation(Long.parseLong(req.getParameter("innovation"))));
			vote.setUtilisateur(utilisateurService.getUtilisateur(utilisateurId)); 
			vote.setVote(1);
			
			voteService.like(vote, innovationService.getInnovation(Long.parseLong(req.getParameter("innovation"))));
			
			resp.sendRedirect("innovation?action=view&id=" + Long.parseLong(req.getParameter("innovation")));
		} else if(req.getParameter("actionPost").equals("dislikeInnovation")) {
			Vote vote = new Vote();
			vote.setInnovation(innovationService.getInnovation(Long.parseLong(req.getParameter("innovation"))));
			vote.setUtilisateur(utilisateurService.getUtilisateur(utilisateurId)); 
			vote.setVote(0);
			
			voteService.dislike(vote, innovationService.getInnovation(Long.parseLong(req.getParameter("innovation"))));
			
			resp.sendRedirect("innovation?action=view&id=" + Long.parseLong(req.getParameter("innovation")));
		} else if(req.getParameter("actionPost").equals("deleteVote")) {	
			Long innovationId = Long.parseLong(req.getParameter("id"));
			Vote vote = voteService.getUtilisateurVote(innovationService.getInnovation(innovationId), utilisateurService.getUtilisateur(utilisateurId));
			voteService.deleteVote(vote, innovationService.getInnovation(Long.parseLong(req.getParameter("innovation"))));
			
			System.out.println("Le vote supprimé est " + voteService.getUtilisateurVote(innovationService.getInnovation(innovationId), utilisateurService.getUtilisateur(utilisateurId)));
			
			resp.sendRedirect("innovation?action=view&id=" + Long.parseLong(req.getParameter("innovation")));
		}
    }
	
}
