/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.main;

import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.JoueurService;
import atos.magiemagie.service.PartieService;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Administrateur
 */
public class PointEntree {

    /**
     * @param args the command line arguments
     */
    private PartieService partie = new PartieService();
    private JoueurService joueur = new JoueurService();
    
    public void menuPrincipale(){
        
        Scanner scan = new Scanner(System.in);
        String choix ;
     do{
        System.out.println("                                                           ");    
        System.out.println(" ******************** Menu Principale *********************");
        System.out.println("  ");
        System.out.println(" 1. Lister les Parties non démarrées ");
        System.out.println(" 2. Créer une Partie ");
        System.out.println(" 3. Rejoindre une Partie ");
        System.out.println(" 4. Démarrer une Partie ");
        System.out.println(" Q. Quitter ");
        System.out.print(" votre choix :  ");
        
        choix = scan.nextLine();
        
          switch(choix){
            case "1":
                
                  List<Partie> listePartie = partie.listerPartieNonDemarrees();
                  System.out.println(" La liste des Parties non démarrées  " +listePartie);
                  
                break;
                
            case "2":
                 System.out.println(" Entrez le non de la patie "); 
                 String nomPartie = scan.nextLine();
                 partie.creerNouvellePartie(nomPartie);
                break;
                
            case "3":
                System.out.println(" Entrez un Pseudo et un Avatar  *____* ");
                String pseudo = scan.nextLine();
                String avatar = scan.nextLine();
                joueur.rejoindrePartie(pseudo, 1, avatar); /// check it!!!!!!!!
                break;
            case "4":
                break;
            case "Q":
                break;
                
            default:
                System.out.println(" ");
                System.out.println(" !!!!!!!! Choix inconnu !!!!!!");
        }
        }while(! choix.equals("Q"));
    }
    
    public static void main (String[] args) {
        
        PointEntree m = new PointEntree();
        m.menuPrincipale();
        
//        System.out.print("Voulez vous  :  ");
//        
//        Scanner s = new Scanner(System.in);
//        String nom = s.nextLine();
//        
//        System.out.println("Salut " +nom);
        
    }
    
}
