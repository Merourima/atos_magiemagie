/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.JoueurService;
import atos.magiemagie.service.PartieService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrateur
 */
public class PartieServiceTest {
    
    
    private PartieService partieservice = new PartieService();
    private JoueurService joueurservice = new JoueurService();
    
     //@Test
    public void creerNouvellePartieTestOK() {
        //Partie p = service.CréerNouvellePartie("hello");
        //Partie p = service.creerNouvellePartie("hello");
        Partie p = partieservice.creerNouvellePartie("hello");
        assertNotNull(p.getId());
        
    }
              
   @Test
    public void demarrerPartieTestOK() {
    //partieservice.demarrerPartie(par.getId());
//
//          Partie p = partieservice.creerNouvellePartie(" GO ");
//    assertNotNull(p.getId());
////              
//         joueurservice.rejoindrePartie("Rima", p.getId(), "rima");
//         joueurservice.rejoindrePartie("Chloé", p.getId(), "Chloé");
//         joueurservice.rejoindrePartie("C", p.getId(), "C");
//         joueurservice.rejoindrePartie("C", p.getId(), "m");
           partieservice.demarrerPartie(1);
    }
    
}
