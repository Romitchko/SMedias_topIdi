package fr.epsi.entite;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

	@Entity
	public class Innovation {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String title;
		
		@ManyToOne
	    @JoinColumn(name = "utilisateur_id")
	    private Utilisateur utilisateur;
		private String description;
		private String image;
		private Date createdAt = new Date();
		
		@OneToOne
		@JoinColumn(name = "categorie_id")
	    private Categorie categorie;
		private Long numberOfVotes;
		
		public Long getId() {
			return this.id;
		}

		public void setId(Long id) {
			this.id = id;
		}
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Utilisateur getUser() {
			return utilisateur;
		}
		
		public void setUtilisateur(Utilisateur utilisateur) {
			this.utilisateur = utilisateur;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
		public String getLittleDescription() {
			return this.getDescription().substring(0,110) + "...";
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public Categorie getCategorie() {
			return categorie;
		}

		public void setCategorie(Categorie categorie) {
			this.categorie = categorie;
		}

		public Long getNumberOfVotes() {
			return numberOfVotes;
		}

		public void setNumberOfVotes(Long numberOfVotes) {
			this.numberOfVotes = numberOfVotes;
		}
		
	}

