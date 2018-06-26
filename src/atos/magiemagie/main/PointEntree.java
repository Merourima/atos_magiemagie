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
                    System.out.println(" Entrez un Pseudo et un Avatar et le ID de La partie   *____* ");
                    String pseudo = scan.nextLine();
                    String avatar = scan.nextLine();
                    Long idPartie = Long.parseLong(scan.nextLine());
                    Joueur joueurActif = joueurService.rejoindrePartie(pseudo, idPartie, avatar); /// check it!!!!!!!!
                    partieService.demarrerPartie(idPartie);
                    joueurService.infoJoueurDeLaPArtie(idPartie);
                    ecranJeu(idPartie, joueurActif.getId());
                    
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

    public void ecranJeu(long idPartie, long joueurActuel) throws InterruptedException {
        String choix;
        do {// BOUCLE DE JEU
            // Détermine qui a la main
            
            Joueur joueurAct = joueurService.determineJoueurQuiALaMainDansPArtie(idPartie);
            if (joueurAct != null) {//jr a la main
                System.out.println(" Si tu veux  *** Piocher une carte *** Tape [1]  ou Si tu veux *** Lancer un sort *** Tape [2]");
                choix = scan.nextLine();
                do {
                    switch (choix) {
                        case "1":
                            //*********         piocherUneCarte    *********
                            carteservice.distribuerCarte(joueurAct.getId());
                            System.out.println("  \n une carte est rajouter à la liste de tes cartes \n");
                            break;

                        case "2":
                            //*********         LancerUnSort   *********
                            System.out.println(" La liste de tes cartes est : ");
                            System.out.println("  " + carteDaoJr.listerCartesJoueurs(joueurAct.getId()) + " ");
                            System.out.println("");
                            System.out.println(" tu doit choisir un id de deux cartes de votre choix idcarte0 et idcarte1");
                            int idcarte0 = scan.nextInt();
                            int idcarte1 = scan.nextInt();

                            partieService.lancerSort(idcarte0, idcarte1, joueurAct.getId(), idPartie);
                            break;

                        default:
                            System.out.println("  !!!!!!!! Choix inconnu !!!!!! \n");
                            break;
                    }
                } while (!(choix.equals("1") || choix.equals("2")));
                partieService.passeJoueurSuivant(idPartie, joueurAct.getId());
            } else {//na pas la main
                System.out.println("pas ton tour");
                Thread.sleep(1000);
            }
        } while (true);
    }

    public static void main(String[] args) throws InterruptedException {
        PointEntree m = new PointEntree();
        m.menuPrincipale();

    }

}
