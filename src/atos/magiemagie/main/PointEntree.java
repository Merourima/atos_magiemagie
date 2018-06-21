/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.main;

import atos.magiemagie.dao.CarteDAO;
import atos.magiemagie.dao.JoueurDAO;
import atos.magiemagie.dao.PartieDAO;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import atos.magiemagie.service.CarteService;
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
    private PartieService partieService = new PartieService();
    private JoueurService joueurService = new JoueurService();
    private PartieDAO partieDao = new PartieDAO();
    private JoueurDAO joueurDao = new JoueurDAO();
    private CarteService carteservice = new CarteService();
    private CarteDAO carteDaoJr = new CarteDAO();
    
    
//    public Carte listerCartesDuJoueur(long idjoueur){
//           List<Carte> listeCarteJr = (List<Carte>) carteservice.distribuerCarte(idjoueur);
//           return (Carte) listeCarteJr;
//    }
    
    public void menuPrincipale(){
        
        Scanner scan = new Scanner(System.in);
        String choix ;
     do{
        System.out.println("                                                           ");    
        System.out.println(" ******************** Menu Principale *********************");
        System.out.println("                                                           ");
        System.out.println(" 1. Lister les Parties non démarrées ");
        System.out.println(" 2. Créer une Partie ");
        System.out.println(" 3. Rejoindre une Partie ");
        System.out.println(" 4. Démarrer une Partie ");
        System.out.println(" 5. Lister les Parties démarrées ");
        System.out.println(" Q. Quitter ");
        System.out.print(" votre choix :  ");
        
        choix = scan.nextLine();
        
          switch(choix){
            case "1":
                
                  List<Partie> listePartie = partieService.listerPartieNonDemarrees();
                  System.out.println(" La liste des Parties non démarrées  " +listePartie);
                  for (Partie partie : listePartie) {
                      for (Joueur joueur : partie.getJoueurs()) {
                        System.out.println("Joueur info : " +joueur.getPseudo());   
                      }
                }
                  
                break;
                
            case "2":
                 System.out.println(" Entrez le non de la patie "); 
                 String nomPartie = scan.nextLine();
                // partieService.creerNouvellePartie(nomPartie);
                 System.out.println("La partie " + nomPartie + " est de ID =  : " +partieService.creerNouvellePartie(nomPartie).getId());
                break;
                
                case "3":
                    System.out.println(" Entrez un Pseudo et un Avatar et le ID de La partie   *____* ");
                    String pseudo = scan.nextLine();
                    String avatar = scan.nextLine();
                    Long idPartie = Long.parseLong(scan.nextLine());
                    joueurService.rejoindrePartie(pseudo, idPartie, avatar); /// check it!!!!!!!!
                    System.out.println(" Vous jouez dans la partie : " +partieDao.rechercherParID(idPartie).getNom());
                    break;
                    
                case "4":
                    System.out.println(" Entrez le ID de La partie   *____* ");
                    idPartie = Long.parseLong(scan.nextLine());
                    partieService.demarrerPartie(idPartie);
                    listePartie = partieService.listerPartieDemarrees();
                    for (Partie partie : listePartie) {
                      
                      for (Joueur joueur : partie.getJoueurs()) {
                        //  if(idPartie = joueur.getPartie() ) {
                        System.out.println("Joueur ID : " +joueur.getId());   
                      //}
                      }
                }
                    //System.out.println(" ayouhhhh" +partieDao.rechercherParID(idPartie).getId());
                    
                    
                   /* List<Carte> listeCarteJr = (List<Carte>) carteservice.distribuerCarte(idPartie);
                    for (Carte carte: listeCarteJr) {
                        System.out.print(" Vos cartes :  " +carte.getTYpe +" - ");
                    }*/
                    
                    System.out.println(" La partie : " +idPartie+ " est bien démarrer");

                    break;

                case "5":

                    List<Partie> listePartieDemarrer = partieService.listerPartieDemarrees();
                    System.out.println(" La liste des Parties non démarrées  " + listePartieDemarrer);
                    for (Partie partie : listePartieDemarrer) {
                        for (Joueur joueur : partie.getJoueurs()) {
                            System.out.println("Joueur info : " + joueur.getPseudo());
                        }

                    }

                    break;     
                case "Q":
                    System.out.println(" A La Prochaine fois *____* ");
                    System.out.println("                                     ");
                    break;

                default:
                    System.out.println(" ");
                    System.out.println(" !!!!!!!! Choix inconnu !!!!!!");
            }
        } while (!choix.equals("Q"));
    }
    
    public static void main(String[] args) {
        PointEntree m = new PointEntree();
        m.menuPrincipale();
    }

}
