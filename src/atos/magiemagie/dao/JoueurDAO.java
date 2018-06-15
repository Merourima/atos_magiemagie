/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Joueur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
public class JoueurDAO {
    
    
    /**
     * Renvoi le joueur existe par pseudo
     * @param pseudo
     * @return 
     */
    
    public long rechercheOrdreNouveauJoueurPourPartieID(long partieId){
    
        EntityManager em = Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();
        
           Query query = em.createQuery("select Max(j.ordre)+1 from Joueur j "
                + "                                        join j.partie p where j.id =:idPartie ");
 
           query.setParameter("idPartie", partieId);
           Object res = query.getSingleResult();
           if(res == null){
               return 1;
           }
           return (long) res;
    }
    
    
    public Joueur rechercherParPseudo(String pseudo){
    
        EntityManager em = Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();
        
        Query query = em.createQuery("select j from Joueur j where j.pseudo =:lePseudo");
        query.setParameter("lePseudo", pseudo);
        
        List<Joueur> joueurTrouves = query.getResultList();
        
        if (joueurTrouves .size() == 0)
            return null;
        
        return (joueurTrouves.get(0));
        
    }

    public void ajouter(Joueur joueur) {
       
            EntityManager em = Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();
            em.getTransaction().begin();
             
            em.persist(joueur);
            
            em.getTransaction().commit();
            
    }

    public void modifier(Joueur joueur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
