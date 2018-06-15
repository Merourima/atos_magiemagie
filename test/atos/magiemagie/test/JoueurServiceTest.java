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
import org.junit.Test;

/**
 *
 * @author Administrateur
 */
public class JoueurServiceTest {
    
    private JoueurService service = new JoueurService();
    private PartieService partieservice = new PartieService();
    
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
    
           Partie partie = partieservice.creerNouvellePartie("Partie 1");     
//           Assert.assertNotNull(partie.getId());
//           return partie.getId();
           service.rejoindrePartie("Rima", partie.getId(), "merou");
           
    }
    
}
