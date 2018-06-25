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
    Scanner scan = new Scanner(System.in);
    
    public void menuPrincipale(){
        
        
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
                    //recup idJoueurActuell enregistrer;
                    break;
                    
                case "4":
                    // Scan id partie et la démarre
                    System.out.println(" Entrez le ID de La partie   *____* ");
                    idPartie = Long.parseLong(scan.nextLine());
                    partieService.demarrerPartie(idPartie);// chek it
                    // Recherche partie par son id
                    Partie p = partieDao.rechercherParID(idPartie);
                    List<Joueur> joueurID =  p.getJoueurs();
                    System.out.println("Joueurs: " + joueurID);
                   // il faut modifier le code pour afficher seulement les cartes du joueur lui mm et pas des autres
                   for (Joueur joueur : p.getJoueurs()) {
                       System.out.print(" Les cartes de " + joueur.getPseudo()+ " sont : ");
                       System.out.println("" +carteDaoJr.listerCartesJoueurs(joueur.getId()));
                   }
                   System.out.println(" La partie : " +idPartie+ " est bien démarrée");
                   //Récupere  id joueur qui a la main
                   Joueur idJrALaMain = joueurDao.determineJoueurQuiALaMainDansPArtie(idPartie);
                   System.out.println(" Le Joueur qui A LA MAIN est  : " +idJrALaMain);
                   
                   //if(getEtatjoueur().A_LA_MAIN){
                       ecranJeu(idPartie, idJrALaMain.getPseudo());
                       
                  // }
                   
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

    public void ecranJeu(long idPartie, String pseudo){
        Joueur joueurAct = joueurDao.rechercherParPseudo(pseudo);
//        if(joueurAct = alamain)
//    while (true)
        System.out.println(" Si tu veux  *** Piocher une carte *** Tape [1]  ou Si tu veux *** Lancer un sort *** Tape [2]");
        String choix = scan.nextLine();
        switch (choix){
            case "1":
                //partieService.piocherUneCarte(joueurAct);
                carteservice.distribuerCarte(joueurAct.getId());
                System.out.println("  une carte est rajouter à la liste de tes cartes ");
                break;
                
            case "2":
//                 
//                List<Carte> listeCarteJrAct = carteDaoJr.listerCartesJoueurs(joueurAct.getId());
                System.out.println(" La liste de tes cartes est : ");
                System.out.println("  " + carteDaoJr.listerCartesJoueurs(joueurAct.getId()) + " ");
                System.out.println("");
                System.out.println(" tu doit choisir un id de deux cartes de votre choix idcarte0 et idcarte1");
                int idcarte0 = scan.nextInt();
                int idcarte1 = scan.nextInt();

                partieService.lancerSort(idcarte0, idcarte1, joueurAct.getId(), idPartie);
                break;
            
            default:
                    System.out.println(" ");
                    System.out.println(" !!!!!!!! Choix inconnu !!!!!!");    
                    
                    
//        }else
//                    Thread.sleep(1000);
        
        }


//recup id de moi
        
//        long monID = 1L;
//        while(true){
        //recherhce jr qui a la main
        //si == monID ===> a moi jouer
        
        //sysout: lanser un sort ou  passer son tour : piocher une carte
        //case 
//        .
//        .. 
//        . 
//        
//default: 
//systout"option inconnue";
//        }
//    
   }
    public static void main(String[] args) {
        PointEntree m = new PointEntree();
        m.menuPrincipale();
        
    }

}
