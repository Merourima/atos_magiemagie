/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.service;

import atos.magiemagie.dao.CarteDAO;
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
    private CarteDAO daoCarte = new CarteDAO();
    private CarteService carteservice = new CarteService();

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
    //******Démarrer Partie
    public void demarrerPartie(long idPartie) {

        // Recuperer la partie par id
        Partie p = dao.rechercherParID(idPartie);
       // Carte c = daoCarte.

        //Erreur  si pas au moins 2 joueurs dans la partie
        if (p.getJoueurs().size() < 2) {
            throw new RuntimeException("Nombre des joueurs doit étre supérieur à 2");

        }

        //Passe le joueur d'ordre 1 à l'état A_LA_MAIN
        Joueur ord = daoJr.rechercheLeJoueurOrdre1(idPartie);
        ord.setEtatjoueur(Joueur.EtatJoueur.A_LA_MAIN);
        daoJr.modifier(ord);

        //distribuer 7 cartes au hasard pour chaque joueur de la partie
        for (Joueur j : p.getJoueurs()) {
            for (int i = 0; i < 7; i++) {
                //System.out.println("hhhhh"+i);
                carteservice.distribuerCarte(j.getId());
                
            }
        }
//        daoCarte.modifierCarteBDD();
    }
    
    
    // cartes au hasard
//    public Carte distribuerCarte(long idJoueur) {
//
//        //0. Récup joueur
//        Joueur j = daoJr.rechercheParId(idJoueur);
//        
//        // 1. Générer nouvelle carte
//        TypeIngredient[] tabCarteIng = TypeIngredient.values();
//        Random r = new Random();
//        int n = r.nextInt(tabCarteIng.length);
//        Carte c = new Carte();
//        c.setTypeIngredient(tabCarteIng[n]);
//
//        // 2. Associe la carte au joueur et vice-versa
//        List<Carte> list = j.getCartes();
//        list.add(c);
//        j.setCartes(list);                       //         j.getCartes().add(c);
//
//
//        // 3. Persiste la carte
//        daoJr.modifier(j);
//        daoCarte.modifierCarteBDD(c);
//        return c;
//    }
}
