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

    public void menuPrincipale() throws InterruptedException {

        String choix;
        do {
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

            switch (choix) {
                case "1":

                    List<Partie> listePartie = partieService.listerPartieNonDemarrees();
                    System.out.println(" La liste des Parties non démarrées  " + listePartie);
                    for (Partie partie : listePartie) {
                        for (Joueur joueur : partie.getJoueurs()) {
                            System.out.println("Joueur info : " + joueur.getPseudo());
                        }
                    }

                    break;

                case "2":
                    System.out.println(" Entrez le non de la patie ");
                    String nomPartie = scan.nextLine();
                    // partieService.creerNouvellePartie(nomPartie);
                    System.out.println("La partie " + nomPartie + " est de ID =  : " + partieService.creerNouvellePartie(nomPartie).getId());
                    break;

                case "3":
                    System.out.println(" Entrez un Pseudo    *____* ");
                    String pseudo = scan.nextLine();
                     System.out.println(" Entrez   un Avatar   *____* ");
                    String avatar = scan.nextLine();
                     System.out.println("  ID de La partie   *____* ");
                    Long idPartie = Long.parseLong(scan.nextLine());
                    Joueur joueurActif = joueurService.rejoindrePartie(pseudo, idPartie, avatar); /// check it!!!!!!!!
                    partieService.demarrerPartie(idPartie);
                    partieService.ecranJeu(idPartie, joueurActif.getId());
                   
                    
//recup idJoueurActuell enregistrer;
                    // Recherche partie par son id
//                    Partie p = partieDao.rechercherParID(idPartie);
//                    List<Joueur> joueurID = p.getJoueurs();
//                    System.out.println("Joueurs: " + joueurID);
//                    //Récupere  id joueur qui a la main
//                    Joueur idJrALaMain = joueurDao.determineJoueurQuiALaMainDansPArtie(idPartie);
//                    System.out.println(" Le Joueur qui A LA MAIN est  : " + idJrALaMain.getPseudo() + " et la liste de ses cartes est :");
//                    System.out.println(" " + carteDaoJr.listerCartesJoueurs(idJrALaMain.getId()) + "\n");
//                    System.out.println(" La partie : " + idPartie + " est bien démarrée");
//                    //if(getEtatjoueur().A_LA_MAIN){
//                    ecranJeu(idPartie, idJrALaMain.getPseudo());
                    break;

                case "4":
                    // Scan id partie et la démarre
                    System.out.println(" Entrez le ID de La partie   *____* ");
                    idPartie = Long.parseLong(scan.nextLine());
                    partieService.demarrerPartie(idPartie);// chek it

                    break;

                case "5":
                    List<Partie> listePartieDemarrer = partieService.listerPartieDemarrees();
                    System.out.println(" La liste des Parties démarrées  " + listePartieDemarrer);
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

    
    
   

    public static void main(String[] args) throws InterruptedException {
        PointEntree m = new PointEntree();
        m.menuPrincipale();

    }

}
