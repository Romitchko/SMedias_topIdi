package fr.epsi.entite;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import fr.epsi.entite.Innovation;
import fr.epsi.entite.Utilisateur;

	@Entity
	public class Commentaire {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String content;
		@ManyToOne
	    @JoinColumn(name = "utilisateur_id")
		private Utilisateur utilisateur;
		@ManyToOne
	    @JoinColumn(name = "innovation_id")
		private Innovation innovation;
		private Date createdAt = new Date();
		
		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}
		
		public String getContent() {
			return content;
		}
		
		public void setContent(String content) {
			this.content = content;
		}

		public Utilisateur getUtilisateur() {
			return utilisateur;
		}

		public void setUtilisateur(Utilisateur utilisateur) {
			this.utilisateur = utilisateur;
		}

		public Innovation getInnovation() {
			return innovation;
		}

		public void setInnovation(Innovation innovation) {
			this.innovation = innovation;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
	}