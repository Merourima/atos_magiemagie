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

    
    public Joueur determineJoueurQuiALaMainDansPArtie(long idPartie){
             EntityManager em = Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();
              Query query = em.createQuery("select j from Joueur join j.partie p where j.etatjoueur=:etat_ALaMain and p.id=:id_Partie");
              query.setParameter("etat_ALaMain", Joueur.EtatJoueur.A_LA_MAIN);
              query.setParameter("id_Partie", idPartie);
              
              return (Joueur) query.getSingleResult();
    }
    
    
    
    public long rechercheOrdreNouveauJoueurPourPartieID(long partieId) {

        EntityManager em = Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();

        Query query = em.createQuery("select Max(j.ordre)+1 from Joueur j "
                + "                                        join j.partie p where p.id =:idPartie ");

        query.setParameter("idPartie", partieId);
        Object res = query.getSingleResult();
        if (res == null) {
            return 1;
        }
        return (long) res;
    }

    public Joueur rechercheLeJoueurOrdre1(long idPartie) {
        EntityManager em = Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();

        Query query = em.createQuery("select j from Joueur j join j.partie p where j.ordre = 1 and p.id =:idpartie ");
        query.setParameter("idpartie", idPartie);
        return  (Joueur) query.getSingleResult();
    }
    /**
     * Renvoi le joueur existe par pseudo
     *
     * @param pseudo
     * @return
     */
    public Joueur rechercherParPseudo(String pseudo) {
        EntityManager em = Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();

        Query query = em.createQuery("select j from Joueur j where j.pseudo =:lePseudo");
        query.setParameter("lePseudo", pseudo);

        List<Joueur> joueurTrouves = query.getResultList();

        if (joueurTrouves.size() == 0) {
            return null;
        }
        return (joueurTrouves.get(0));
    }

    public void ajouter(Joueur joueur) {
        EntityManager em = Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();
        em.getTransaction().begin();
        em.persist(joueur);
        em.getTransaction().commit();

    }

    public void modifier(Joueur joueur) {
         EntityManager em = Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();
         em.getTransaction().begin();
         em.merge(joueur);
         em.flush();
         em.getTransaction().commit();     
    }

    public Joueur rechercheParId(long idJoueur) {
         EntityManager em = Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();
         return em.find(Joueur.class,idJoueur);
    }

    public Joueur rechercheJoueurParPartieidEtORdre(long idParatie, long ordre) {
           EntityManager em = Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();
           Query query = em.createQuery("select j from Joueur j join j.partie p where p.id=:partieID and j.ordre=:ordre");
           query.setParameter("partieID", idParatie);
           query.setParameter("ordre", ordre);
           return (Joueur) query.getSingleResult();


    }
    
    
//    public Joueur ListerJoueursEtLeurNbrDeCartes(long idjoueur, long nbrCarte){
//           EntityManager em = Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();
//           Query query = em.createQuery(" SELECT j FROM Joueur j JOIN j.cartes c"
//                   + "                    WHERE c.joueur_id=:j.id AND  ");
//   
//    
//    }
}
