/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.service;

import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Carte.TypeIngredient;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Administrateur
 */
public class PartieService {

    private PartieDAO dao = new PartieDAO();
    private JoueurService dao1 = new JoueurService();
    private JoueurDAO daoJr = new JoueurDAO();

    public List<Partie> listerPartieNonDemarrees() {

        return dao.listerPartieNonDemarrees();
    }

    //*****Partie******
    public Partie creerNouvellePartie(String nom) {
        Partie p = new Partie();
        p.setNom(nom);
        dao.ajouterPartie(p);

        return p;
    }

    //******Démarrer Partie
    public void demarrerPartie(long idPartie) {

        // Recuperer la partie par id
        Partie p = dao.rechercherParID(idPartie);

        //Erreur  si pas au moins 2 joueurs dans la partie
        if (p.getJoueurs().size() < 2) {
            throw new RuntimeException("Nombre des joueurs doit étre supérieur à 2");

        }

        //Passe le joueur d'ordre 1 à l'état A_LA_MAIN
        for (Joueur j : p.getJoueurs()) {
            Joueur ord = daoJr.rechercheLeJoueurOrdre1(idPartie);
            if (ord.equals(1)) {
                j.setEtatjoueur(Joueur.EtatJoueur.A_LA_MAIN);
            }
        }

        //distribuer 7 cartes au hasard pour chaque joueur de la partie
        for (Joueur j : p.getJoueurs()) {

            for (int i = 0; i < 7; i++) {

                  distribuerCarte( j.getId() );
            }
        }
    }
    // cartes au hasard

    public Carte distribuerCarte(long joueurId) {

        // 1. Générer nouvelle carte
        TypeIngredient[] tabCarteIng = TypeIngredient.values();
        Random r = new Random();
        int n = r.nextInt(tabCarteIng.length);
        Carte c = new Carte();
        c.setTypeIngredient(tabCarteIng[n]);
        
        // 2. Associe la carte au joueur et vice-versa
        daoJr.
        
        // 3. Persiste la carte
        
        
        return c;
    }

}
