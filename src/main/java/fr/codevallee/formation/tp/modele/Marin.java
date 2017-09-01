package fr.codevallee.formation.tp.modele;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
@Table(name = "marin")
public  class Marin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
//    @ManyToMany
//    private Collection<Bateau> bateau ;

    // reste de la classe
}
	
				 	
