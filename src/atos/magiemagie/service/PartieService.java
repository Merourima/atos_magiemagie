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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class PartieService {

    private PartieDAO partiedao = new PartieDAO();
    private JoueurService dao1 = new JoueurService();
    private JoueurDAO joueurDao = new JoueurDAO();
    private CarteDAO daoCarte = new CarteDAO();
    private CarteService carteservice = new CarteService();
    Scanner scan = new Scanner(System.in);
    
    // *****************          Déroulement des sort      ***********************
    public long choisirUnJoueur(Joueur joueurAct) {
        Partie partie = joueurAct.getPartie();
        
        for (Joueur joueurAdvers : partie.getJoueurs()) {
           if ((!(joueurAdvers.getId().equals(joueurAct.getId()))) || !(joueurAdvers.getEtatjoueur().equals("PERDU"))) {
            System.out.print(" L' Adversaire : " + joueurAdvers.getId()+ " Nombre de carte :  "+joueurAdvers.getCartes().size());
           }
        }
        System.out.print(" choisi un joueur que tu veux récuperer cartes : ");
        Long idJrVict = Long.parseLong(scan.nextLine());
        return idJrVict;
    }
    public long choisiUneDeMesCartes(Joueur joueurAct) {
        Partie partie = joueurAct.getPartie();
//        je veux recuperer la liste de mes cartes:
        for(Joueur joueur: partie.getJoueurs() ){
            System.out.println("La liste de tes cartes est : " + joueur.getCartes()+ " est sont ID est" );
//            System.out.print(" Les cartes de " + joueur.getPseudo()+ " sont : ");
            System.out.println("La liste de tes cartes est : " +daoCarte.listerCartesJoueurs(joueur.getId()));
        
        }
         System.out.println(" choisi une carte (ID de la carte) parmi vos cartes pour faire un échange avec 3 cartes de l'adversaire  ");
         long idcarteJrAct = Long.parseLong(scan.nextLine());
         
         
         //la solution
//         List<Integer> listIdCarte= new ArrayList<Integer>();
//         System.out.println("Voici la liste de tes Carte" );
//         for (Carte carte : joueurAct.getCartes()) {
//            System.out.println(carte.toString() );
//        }
//         
//        System.out.println(" choisi une carte (ID de la carte) parmi vos cartes");
//        long idCarteChoisi = Long.parseLong(scan.nextLine());
//        if(listIdCarte.size()>0 && !listIdCarte.contains(idCarteChoisi) ){
//            return choisiUneDeMesCartes(joueurAct);
//        }
//        return idCarteChoisi;
          return idcarteJrAct;   //c faux 
    }
          

    public void lancerSortLicorneEtCrapaud(Joueur joueurAct) {
        Partie partie = joueurAct.getPartie();
        for (Joueur joueurPartie : partie.getJoueurs()) {
            if (!(joueurPartie.getId().equals(joueurAct.getId()))) {
                carteservice.prendreUneCarteDunJoueur(joueurAct.getId(), joueurPartie.getId());
            }

        }
    }
    
    public void lancerSortLicorneEtMandragore(Joueur joueurAct) {
        long idJoueurVictime = choisirUnJoueur(joueurAct);
        Joueur joueurAdvers = joueurDao.rechercheParId(idJoueurVictime);
        if (joueurAdvers.getCartes().size() > 1) {
            int nbrCarteMoitie = (joueurAdvers.getCartes().size()) % 2;
            for (int i = 0; i <= nbrCarteMoitie; i++) {
                carteservice.prendreUneCarteDunJoueur(joueurAct.getId(), joueurAdvers.getId());
            }
        } else {
           carteservice.prendreUneCarteDunJoueur(joueurAct.getId(), joueurAdvers.getId());
        }
    }
     
     
     public void lancerSortCrapaudLapisLazuli(Joueur joueurAct) {
         long idJoueurAdvers = choisirUnJoueur(joueurAct);
        Joueur joueurAdvers = joueurDao.rechercheParId(idJoueurAdvers);
      
        
        System.out.println(" choisi une carte (ID de la carte) parmi vos cartes pour faire un échange avec 3 cartes de l'adversaire  ");
        long idcarteJrAct = Long.parseLong(scan.nextLine());
        Carte carteJrActChoisi = daoCarte.rechercheParIDCarte(idcarteJrAct);

//         si l'adversaire à une seule carte!!!!!!! ===>  Piocher 2 cartes !!!!!! + la carte de l'advers
        int nbrCartes = joueurAdvers.getCartes().size() > 3 ? 3 :joueurAdvers.getCartes().size();
        for (int i = 0; i < nbrCartes; i++) {
            carteservice.prendreUneCarteDunJoueur(idJoueurAdvers, idcarteJrAct);

        }

        joueurAdvers.getCartes().add(carteJrActChoisi);
        joueurAdvers.setEtatjoueur(Joueur.EtatJoueur.NA_PAS_LA_MAIN);
        joueurDao.modifier(joueurAdvers);
        

    }
     
      /**
       
Ingrédients: lapis-lazuli + aile-de chauve-souris
4) (DIVINATION : le joueur peut voir les cartes de tous les autres joueurs)

       */   
     public void lancerSortChauveSourisLapisLazuli(Joueur joueurAct) {
         
     
     }
     // *****************          Lancer un sort      ***********************
    
    public void lancerSort(long idCarte1, long idCarte2, long idJrAct, long idVictime){
    
        Carte carte1 = daoCarte.rechercheParIDCarte(idCarte1);
        Carte carte2 = daoCarte.rechercheParIDCarte(idCarte2);
        Joueur joueurAct = joueurDao.rechercheParId(idJrAct);
            
        if (carte1.getTypeIngredient() == TypeIngredient.LICORNE && carte2.getTypeIngredient() == TypeIngredient.CRAPAUD) {
            System.out.println(" le sort est INVISIBILITE et tu va gagner une carte de chaque adversaires");
            lancerSortLicorneEtCrapaud(joueurAct);
        } else {
            if (carte1.getTypeIngredient() == TypeIngredient.LICORNE && carte2.getTypeIngredient() == TypeIngredient.MANDRAGORE) {
                System.out.println(" le sort est PHILTRE D’AMOUR");
                lancerSortLicorneEtMandragore(joueurAct);
                
            } else {
                if (carte1.getTypeIngredient() == TypeIngredient.CRAPAUD && carte2.getTypeIngredient() == TypeIngredient.LAPIS_LAZULI) {
                    System.out.println(" le sort est HYPNOSE");
                    lancerSortCrapaudLapisLazuli(joueurAct);
                } else {
                    if (carte1.getTypeIngredient() == TypeIngredient.CHAUVE_SOURIS && carte2.getTypeIngredient() == TypeIngredient.LAPIS_LAZULI) {
                        System.out.println(" le sort est DIVINATION");
                    } else {
                        if (carte1.getTypeIngredient() == TypeIngredient.CHAUVE_SOURIS && carte2.getTypeIngredient() == TypeIngredient.MANDRAGORE) {
                            System.out.println(" le sort est SOMMEIL-PROFOND");
                        }
                    }
                }
            }
        }
        
        
    }
    
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
