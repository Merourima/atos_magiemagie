/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.JoueurService;
import atos.magiemagie.service.PartieService;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Administrateur
 */
public class JoueurServiceTest {
    
    private JoueurService joueurservice = new JoueurService();
    private PartieService partieservice = new PartieService();
    
    //@Test
    public void ordreJoueursOK(){
    
          Partie nouvellePartie = partieservice.creerNouvellePartie("ordreJoueursOK");
          joueurservice.rejoindrePartie("A",nouvellePartie.getId(), "A");
          joueurservice.rejoindrePartie("B",nouvellePartie.getId(), "B");
          Joueur j = joueurservice.rejoindrePartie("C",nouvellePartie.getId(), "C");
          
          assertEquals(2L,(long) j.getOrdre());
    }
    
    
//    @Test
//    public void creerNouvellePartieTestOK() {
//        //Partie p = service.CréerNouvellePartie("hello");
//        //Partie p = service.creerNouvellePartie("hello");
//        Joueur j = service.creerNouvellePartie("hello");
//        assertNotNull(p.getId());
//        
    //}
    
    @Test
    public void rejoindrePArtieOK(){
    
           joueurservice.rejoindrePartie("Rima", 1, "merou");
           joueurservice.rejoindrePartie("bbbb", 1, "bbbb");
           joueurservice.rejoindrePartie("ccc", 1, "cccc");
           
    }
    
}
