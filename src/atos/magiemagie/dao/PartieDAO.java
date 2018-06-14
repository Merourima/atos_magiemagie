/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
public class PartieDAO {
    
     public List<Partie> listerPartieNonDemarrees(){
     
         EntityManager em =  Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();
//         Query query = em.createQuery("select p "
//                 + "                   from Partie p "
//                 + "                   where p.id NOT IN ("
//                 + "                   select p2.id"
//                 + "                   from Partie p2"
//                 + "                   join p2.joueurs j"
//                 + "                   where j.etatjoueur =: etatjoueur1"
//                 + "                         or j.etatjoueur =: etatjoueur2 ");

         Query query = em.createQuery("select p "
                 + "                         from Partie p "
                 + "                         exept"
                 + "                           select p1"
                 + "                           from Partie p1"
                 + "                      join p.joueurs j"
                 + "                           where j.etatjoueurs in (:etat_gagne, : etat_alamain) ");
         query.setParameter("etat_gagne", Joueur.EtatJoueur.GAGNEE);
         query.setParameter("etat_alamain", Joueur.EtatJoueur.A_LA_MAIN);
                 
         return query.getResultList();
     }
    
}
