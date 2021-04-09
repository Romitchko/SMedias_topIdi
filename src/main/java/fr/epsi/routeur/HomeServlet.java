package fr.epsi.routeur;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.epsi.service.InnovationService;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
	@EJB
	private InnovationService innovationService;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(req.getParameterMap().containsKey("action")) {
			if(req.getParameter("action").equals("exit")) {
				session.removeAttribute("isConnected");
			    resp.sendRedirect("signIn?action=signOn");
			}
		} else {
			req.setAttribute("isConnected", session.getAttribute("isConnected"));
			req.setAttribute("listInnovations", innovationService.getListInnovations());
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
		}
	}

}
