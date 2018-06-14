package atos.magiemagie.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrateur
 */
public class MagieMagieTest {
    
    @Test
    public void testMagie(){
    
        Persistence.createEntityManagerFactory("AtelierMagieMagiePU");
    }
}
