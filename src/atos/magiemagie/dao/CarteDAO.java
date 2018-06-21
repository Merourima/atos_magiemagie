/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Carte;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
public class CarteDAO {
    
    
         public List<Carte> listerCartesJoueurs(long idJoueur){
            EntityManager em = Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();
//            Query query = em.createQuery(" SELECT j FROM Joueur j JOIN j.cartes c"
//                   + "                    WHERE c.joueur_id=j.id ");
             Query query = em.createQuery(" SELECT c FROM Carte c "
                   + "                    WHERE c.joueur=:idjoueur ");
             query.setParameter("idjoueur", idJoueur);
            return   query.getResultList();
    }
    
    public void ajouterCarte(Carte carte) {
        
         EntityManager em = Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();
         em.getTransaction().begin();
         em.persist(carte);
         em.flush();
         em.getTransaction().commit();     
    }
    
}
