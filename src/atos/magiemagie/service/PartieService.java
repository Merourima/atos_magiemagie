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

    private PartieDAO partiedao = new PartieDAO();
    private JoueurService dao1 = new JoueurService();
    private JoueurDAO joueurDao = new JoueurDAO();
    private CarteDAO daoCarte = new CarteDAO();
    private CarteService carteservice = new CarteService();
    
    
    // *****************          Déterminer Le Joueur Suivant      ***********************
    
    public void passeJoueurSuivant(long idpartie, long idPartie){
    
//        recuperer un joueur qui A_LA_MAIN
        Joueur joueurQuiAlaMain = joueurDao.determineJoueurQuiALaMainDansPArtie(idpartie);
            
//        determine si les autres joueurs ont perdu
//          et Passe le joueur à l'état GAGNé
        if(partiedao.determineSiPlusQueUnJoueurDansPartie(idpartie)){
                 joueurQuiAlaMain.setEtatjoueur(Joueur.EtatJoueur.GAGNEE);
                 joueurDao.modifier(joueurQuiAlaMain);
                 return;
        }
//        La partie n'est pas terminée
//        recupere l'ordre Max des joueurs de la partie
        long ordreMax = partiedao.rechercheOrdreMaxJoueurPourPartieID(idpartie);
        
//        joueurEvalue = joueurQuiALaMain
        Joueur joueurEvalue = joueurQuiAlaMain;
         //boucle qui determine le joueur qui 'attrape' A LA MAIN
         while(true){
             //si joueurEvalue est le dernier joueur alors on évalue le joueur d'ordre 1
             if(joueurEvalue.getOrdre() >= ordreMax){
                 joueurEvalue = joueurDao.rechercheJoueurParPartieidEtORdre(idPartie, 1L);
             }  else {   //             recupere le joueur d'ordre suivant  (ordre+1)
                     joueurEvalue = joueurDao.rechercheJoueurParPartieidEtORdre(idPartie, joueurEvalue.getOrdre()+1);
                }
             
             //Return si tout les joueurs non éliminés était en sommeil profond (et qu'on la a juste réveillés)
             if(joueurEvalue.getId() == joueurQuiAlaMain.getId()){
                return;
             }
             
             // si joueur évaluer en sommeil profond en passe à PAS_LA_MAIN
              if (joueurEvalue.getEtatjoueur()== Joueur.EtatJoueur.SOMMEIL_PROFON){
                  joueurEvalue.setEtatjoueur(Joueur.EtatJoueur.NA_PAS_LA_MAIN);
                  joueurDao.modifier(joueurEvalue);
              } else{
              //si n'est pas en sommeil profond
              
              //si joueurEvalue pas la main alors c'est lui qui prend la main
               if (joueurEvalue.getEtatjoueur() == Joueur.EtatJoueur.NA_PAS_LA_MAIN){
                   joueurQuiAlaMain.setEtatjoueur(Joueur.EtatJoueur.NA_PAS_LA_MAIN);
                   joueurDao.modifier(joueurQuiAlaMain);
                   
                   joueurEvalue.setEtatjoueur(Joueur.EtatJoueur.A_LA_MAIN);
                   joueurDao.modifier(joueurEvalue);
                   return;
               } 
               // sinon : ETAT JOUEUR EVALUER  ==>  passe à PAS LA MAIN
               joueurEvalue.setEtatjoueur(Joueur.EtatJoueur.NA_PAS_LA_MAIN);
               
              }
         }
            
    }

    public List<Partie> listerPartieNonDemarrees() {

        return partiedao.listerPartieNonDemarrees();
    }

    //*****Partie******
    public Partie creerNouvellePartie(String nom) {
        Partie p = new Partie();
        p.setNom(nom);
        partiedao.ajouterPartie(p);

        return p;
    }

    //******Démarrer Partie
    //******Démarrer Partie
    public void demarrerPartie(long idPartie) {

        // Recuperer la partie par id
        Partie p = partiedao.rechercherParID(idPartie);
       // Carte c = daoCarte.

        //Erreur  si pas au moins 2 joueurs dans la partie
        if (p.getJoueurs().size() < 2) {
            throw new RuntimeException("Nombre des joueurs doit étre supérieur à 2");

        }

        //Passe le joueur d'ordre 1 à l'état A_LA_MAIN
        Joueur ord = joueurDao.rechercheLeJoueurOrdre1(idPartie);
        ord.setEtatjoueur(Joueur.EtatJoueur.A_LA_MAIN);
        joueurDao.modifier(ord);

        //distribuer 7 cartes au hasard pour chaque joueur de la partie
        for (Joueur j : p.getJoueurs()) {
            for (int i = 0; i < 7; i++) {
              
                carteservice.distribuerCarte(j.getId());
                
            }
        }
    }

    public List<Partie> listerPartieDemarrees() {
        return partiedao.listerPartieDemarrees();
        
    }
 
}
