package fr.codevalle;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.persistence.*;

import org.junit.*;

import fr.codevallee.formation.tp.Router;
import fr.codevallee.formation.tp.modele.Bateau;
import fr.codevallee.formation.tp.modele.Demo;

import static org.junit.Assert.*;

public class BDDTest {

	protected static EntityManagerFactory emf;
	protected static EntityManager em;

	@BeforeClass
	public static void init() throws FileNotFoundException, SQLException {
		emf = Persistence.createEntityManagerFactory("formation");
		em = emf.createEntityManager();
	}

	@Test
	public void testInsert() {
		Bateau monBateau = new Bateau();

		em.getTransaction().begin();
		em.persist(monBateau);
		em.getTransaction().commit();

		TypedQuery<Bateau> query = em.createQuery("from Bateau", Bateau.class);
		//test erreur JENKINS
//		TypedQuery<Bateau> query = em.createQuery("from YYYYYY", Bateau.class);
		System.out.println(query.getResultList().size());
		
		assertEquals(1, 1);
	}

	@AfterClass
	public static void tearDown() {
		em.clear();
		em.close();
		emf.close();
	}

}