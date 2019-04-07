package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Jeu.Bateau;
import Jeu.BateauException;
import Jeu.Grille;
import Jeu.GrilleException;

public class TestGrille {

	Grille g, g2, g3;
	Bateau porteAvion, porteAvionMalPlace, torpilleur, croiseur;

	@Before
	public void avant() throws BateauException, GrilleException {
		g = new Grille(10, 10);
		g2= new Grille(6,10);
		g3 = new Grille(10, 6);
		porteAvion = new Bateau(0, 0, "porte avion", "bas");
		porteAvionMalPlace = new Bateau(7, 0, "porte avion", "droite");
		torpilleur = new Bateau(1, 0, "torpilleur", "bas");
		croiseur = new Bateau(2, 0, "croiseur", "droite");
	}

	public void testIsPlacable() throws BateauException {
		boolean res = g.isPlacable(porteAvion);
		assertEquals("Devrait pouvroir être placé", true, res);
		res = g.isPlacable(porteAvionMalPlace);
		assertEquals("Ne devrait pas pouvoir être placé", false, res);

	}

	@Test
	public void testPlacerBateau() throws BateauException {
		g.placerBateau(porteAvion);
		boolean res = g.getPlateau()[0][0].getBateau() == porteAvion;
		assertEquals("Bateau mal placé", true, res);
		res = g.getPlateau()[0][1].getBateau() == porteAvion;
		assertEquals("Bateau mal placé", true, res);
		res = g.getPlateau()[0][2].getBateau() == porteAvion;
		assertEquals("Bateau mal placé", true, res);
		res = g.getPlateau()[0][3].getBateau() == porteAvion;
		assertEquals("Bateau mal placé", true, res);
		res = g.getPlateau()[0][4].getBateau() == porteAvion;
		assertEquals("Bateau mal placé", true, res);

		res = false;
		try {
			g.placerBateau(porteAvionMalPlace);
		} catch (BateauException e) {
			res = true;
		}

		assertEquals("Bateau mal placé", true, res);
	}

	@Test
	public void testPlacerTerre() throws GrilleException{
		boolean res = g.getPlateau()[0][0].isLibre();
		assertEquals("Case libre normalement", true, res);
		g.placerTerre(0, 0);
		res = g.getPlateau()[0][0].isLibre();
		assertEquals("Case occupée normalement", false, res);
	}

	@Test
	public void testTirNormal() throws BateauException, GrilleException {
		g.placerBateau(porteAvion);
		boolean res = g.tirer(1, 1);
		assertEquals("Ne devrait pas toucher", false, res);
		res = g.getPlateau()[1][1].isLibre();
		assertEquals("Case occupée normalement", false, res);
		res = g.tirer(0, 0);
		assertEquals("Devrait toucher", true, res);
		res = g.getPlateau()[0][0].isLibre();
		assertEquals("Case occupée normalement", false, res);
		res = g.getPlateau()[0][0].getBateau() == null;
		assertEquals("Case bateau censée être détruite", true, res);
	}

	@Test
	public void testTirHorsPlateau() {
		boolean res = false;
		try {
			g.tirer(10, 10);
		} catch (GrilleException e) {
			res = true;
		}
		assertEquals("Tir en dehors de la carte", true, res);

		try {
			g.tirer(-1, -2);
		} catch (GrilleException e) {
			res = true;
		}
		assertEquals("Tir en dehors de la carte", true, res);

	}

	@Test
	public void testTirCaseOccupee() {
		boolean res = false;
		try {
			g.getPlateau()[1][1].setLibre(false);
			g.tirer(1, 1);
		} catch (GrilleException e) {
			res = true;
		}
		assertEquals("Tir  impossible", true, res);
	}

	@Test
	public void testFloteDetruite() throws BateauException, GrilleException {
		g.placerBateau(porteAvion);
		g.placerBateau(torpilleur);
		g.tirer(0, 0);
		g.tirer(0, 1);
		g.tirer(0, 2);
		g.tirer(0, 3);
		g.tirer(0, 4);
		g.tirer(1, 0);
		g.tirer(1, 1);
		assertEquals("Flote devrait etre detruite", true, g.floteDetruite());
	}

	@Test
	public void testTriVie() throws BateauException, GrilleException {
		g.placerBateau(croiseur);
		g.placerBateau(porteAvion);

		g.tirer(0, 0);
		g.trierVie();
		String res = g.getFlote().get(0).getNom() + " " + g.getFlote().get(1).getNom();
		assertEquals("Mauvais Tri", "porte avion croiseur", res);

		g.tirer(2, 0);
		g.tirer(2, 1);
		g.trierVie();
		res = g.getFlote().get(0).getNom() + " " + g.getFlote().get(1).getNom();
		assertEquals("Mauvais Tri", "croiseur porte avion", res);
	}

	@Test
	public void testTriNom() throws BateauException {
		g.placerBateau(croiseur);
		g.placerBateau(torpilleur);
		g.trierTaille();
		String res = g.getFlote().get(0).getNom() + " " + g.getFlote().get(1).getNom();
		assertEquals("Mauvais Tri", "torpilleur croiseur", res);

		g.placerBateau(porteAvion);
		g.trierTaille();
		res = g.getFlote().get(0).getNom() + " " + g.getFlote().get(1).getNom() + " " + g.getFlote().get(2).getNom();
		assertEquals("Mauvais Tri", "torpilleur croiseur porte avion", res);
	}
}
