/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Carte;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Administrateur
 */
public class CarteDAO {
    
    public void ajouterCarte(Carte carte) {
        
         EntityManager em = Persistence.createEntityManagerFactory("AtelierMagieMagiePU").createEntityManager();
         em.getTransaction().begin();
         em.persist(carte);
         em.flush();
         em.getTransaction().commit();     
    }
    
}
