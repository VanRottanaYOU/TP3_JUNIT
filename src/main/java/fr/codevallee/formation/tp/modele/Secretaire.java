package fr.codevallee.formation.tp.modele;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "secretaire")

public class Secretaire {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(length=40)
	private String nom;
	
	@ManyToMany
	private Set<Projet> projets ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
