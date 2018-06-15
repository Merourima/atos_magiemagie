/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.test;

import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.PartieService;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrateur
 */
public class PartieServiceTest {
    
    
    private PartieService service = new PartieService();
    
    @Test
    public void creerNouvellePartieTestOK() {
        //Partie p = service.CréerNouvellePartie("hello");
        //Partie p = service.creerNouvellePartie("hello");
        Partie p = service.creerNouvellePartie("hello");
        assertNotNull(p.getId());
        
    }
              
   
    
}
