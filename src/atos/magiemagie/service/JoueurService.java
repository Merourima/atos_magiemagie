/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.service;

import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public class JoueurService {
    
    private JoueurDAO dao = new JoueurDAO();
    private PartieDAO partiedao = new PartieDAO();
    
    public Joueur rejoindrePartie( String pseudo,long idPartie,String avatar){
    
      //recherche si le joueur existe déjà
      Joueur joueur = dao.rechercherParPseudo(pseudo);
      if (joueur == null){
           // le joueur n'existe pas encore
          joueur = new Joueur();
          joueur.setPseudo(pseudo);
      }
      
      joueur.setAvatar(avatar);
      joueur.setEtatjoueur(Joueur.EtatJoueur.NA_PAS_LA_MAIN);
      joueur.setOrdre(dao.rechercheOrdreNouveauJoueurPourPartieID(idPartie));
      
      //Assicie le joueur a la partie et vice_versa (JPA relations bidirec...)
      Partie partie = partiedao.rechercherParID(idPartie);
      joueur.setPartie(partie);
      
      //partie.getJoueurs().add(joueur);  equi ==
      List<Joueur> listJoueurs = partie.getJoueurs();
      listJoueurs.add(joueur);
      
      if(joueur.getId() == null){
      
         dao.ajouter(joueur);
      }
      else{
           dao.modifier(joueur);
      }
      return joueur;
    }
}
