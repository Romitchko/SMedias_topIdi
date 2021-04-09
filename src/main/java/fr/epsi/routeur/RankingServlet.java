package fr.epsi.routeur;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.epsi.service.CategorieService;
import fr.epsi.service.InnovationService;
import fr.epsi.service.UtilisateurService;
import fr.epsi.service.CategorieService;
import fr.epsi.service.InnovationService;
import fr.epsi.service.UtilisateurService;

@WebServlet("/ranking")
public class RankingServlet extends HttpServlet{
	@EJB
	private InnovationService innovationService;
	@EJB
	private UtilisateurService utilisateurService;
	@EJB
	private CategorieService categorieService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
		HttpSession session = req.getSession();
		
		req.setAttribute("isConnected", session.getAttribute("isConnected"));
		
		if(req.getParameterMap().containsKey("action")) {
			if(req.getParameter("action").equals("upToDown")) {
				System.out.println("result of filtering " + innovationService.getInnovationsUpToDown());
				req.setAttribute("listIdeas", innovationService.getInnovationsUpToDown());
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/ranking.jsp").forward(req, resp);
			} else if(req.getParameter("action").equals("downToUp")) {
				req.setAttribute("listIdeas", innovationService.getInnovationsDownToUp());
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/ranking.jsp").forward(req, resp);
			} else if(null == session.getAttribute("isConnected")) {
		        resp.sendRedirect("signIn?action=signOn");
			} else if(req.getParameter("action").equals("exit")) {
				session.removeAttribute("isConnected");
			    resp.sendRedirect("signIn?action=signOn");
			}
		} else if(session.getAttribute("isConnected") == null) {
	        resp.sendRedirect("signIn?action=signOn");
		} else {
			req.setAttribute("listInnovations", innovationService.getListInnovations());
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/ranking.jsp").forward(req, resp);
		}
    }
}
