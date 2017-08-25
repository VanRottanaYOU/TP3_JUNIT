package fr.codevallee.formation.tp;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.codevallee.formation.tp.modele.Commune;
import fr.codevallee.formation.tp.modele.Demo;
import fr.codevallee.formation.tp.modele.Elu;
import fr.codevallee.formation.tp.modele.Maire;
import fr.codevallee.formation.tp.modele.Projet;
import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.ModelAndView;
import spark.servlet.SparkApplication;
import spark.template.freemarker.FreeMarkerEngine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Router implements SparkApplication {

	public void init() {

		final Logger logger = LoggerFactory.getLogger(Router.class);

		
		get("/exemple1", (request, response) -> {

			logger.debug("start");

			Map<String, Object> attributes = new HashMap<>();

			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("formation");
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			
			Maire monMaire = new Maire();
			Commune maCommune = new Commune();
			Set<Elu> monSetelu = new HashSet<Elu>();
			Elu premElu = new Elu();
			Elu deuxElu = new Elu();
			Set<Projet> monSetProjet = new HashSet<Projet>();
			Projet premProjet = new Projet();
			Projet deuxProjet = new Projet();
			
			{
				entityManager.getTransaction().begin();
				String requeteCommune = "SELECT c FROM Commune c WHERE c.nom = 'LOOS'";
	//			TypedQuery<Commune> query = entityManager.createQuery(requeteCommune,Commune.class);
				Query query = entityManager.createQuery(requeteCommune.toString());
				maCommune = (Commune) query.getResultList().get(0);
				entityManager.remove(maCommune);
				entityManager.getTransaction().commit();
			}
			
//			{
//				//oneToone sans cascade
//				entityManager.getTransaction().begin();
//				
//				monMaire.setNom("Van");
//				maCommune.setNom("LOOS");
//				maCommune.setMaire(monMaire);
//				entityManager.persist(maCommune);	
//				entityManager.persist(monMaire);
//
//				premElu.setNom("Manu");
//				deuxElu.setNom("Michel");
//				entityManager.persist(premElu);
//				entityManager.persist(deuxElu);
//				monSetelu.add(premElu);
//				monSetelu.add(deuxElu);
//				premProjet.setNom("CDN");
//				deuxProjet.setNom("Auchan");
//				entityManager.persist(premProjet);
//				entityManager.persist(deuxProjet);
//				monSetProjet.add(premProjet);
//				monSetProjet.add(deuxProjet);
//				premElu.setProjets(monSetProjet);
//				
//				monMaire.setElu(monSetelu);
//				
//				
//				entityManager.flush();	
//				entityManager.getTransaction().commit();
//			}	


//			TypedQuery<Maire> query = entityManager.createQuery("from Maire", Maire.class);
//			attributes.put("objets",query.getResultList());
//			for (Maire m : query.getResultList()) {
//				System.out.println("1" +m.getNom()+ " ; "+m.getCommune());
//			}
//			entityManager.getTransaction().commit();
//			entityManager.close();
//			Query query_1 = entityManager.createQuery("SELECT maire from );
//			Maire monMaire = new Maire();
//			Commune maCommune = new Commune();
			
			return new ModelAndView(attributes, "home.ftl");
		}, getFreeMarkerEngine());

	}

	private FreeMarkerEngine getFreeMarkerEngine() {
		FreeMarkerEngine engine = new FreeMarkerEngine();
		Configuration configuration = new Configuration(new Version(2, 3, 23));
		configuration.setTemplateUpdateDelayMilliseconds(Long.MAX_VALUE);
		configuration.setClassForTemplateLoading(FreeMarkerEngine.class, "");
		engine.setConfiguration(configuration);
		return engine;
	}

}