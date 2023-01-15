import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        IMetier<Produit, Integer> metierProduit = new MetierProduitImpl("produits.dat");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Afficher la liste des produits.");
            System.out.println("2. Rechercher un produit par son id.");
            System.out.println("3. Ajouter un nouveau produit dans la liste.");
            System.out.println("4. Supprimer un produit par id.");
            System.out.println("5. Sauvegarder les produits.");
            System.out.println("6. Quitter ce programme.");
            System.out.print("Choisissez une option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    List<Produit> produits = metierProduit.getAll();
                    for (Produit produit : produits) {
                        System.out.println(produit);
                    }
                    break;
                case 2:
                    System.out.print("Entrez l'id du produit à rechercher: ");
                    int id = scanner.nextInt();
                    Produit produit = metierProduit.findById(id);
                    if (produit != null) {
                        System.out.println(produit);
                    } else {
                        System.out.println("Aucun produit trouvé avec cet id.");
                    }
                    break;
                case 3:
                    System.out.print("Entrez l'id du produit: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Entrez le nom du produit: ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez la marque du produit: ");
                    String marque = scanner.nextLine();
                    System.out.print("Entrez le prix du produit: ");
                    double prix = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Entrez la description du produit: ");
                    String description = scanner.nextLine();
                    System.out.print("Entrez le nombre en stock du produit: ");
                    int nombreEnStock = scanner.nextInt();
                    Produit newProduit = new Produit(id, nom, marque, prix, description, nombreEnStock);
                    metierProduit.add(newProduit);
                    System.out.println("Produit ajouté avec succès.");
                    break;
                case 4:
                    System.out.print("Entrez l'id du produit à supprimer: ");
                    id = scanner.nextInt();
                    metierProduit.delete(id);
                    System.out.println("Produit supprimé avec succès.");
                    break;
                case 5:
                    metierProduit.saveAll();
                    System.out.println("Produits sauvegardés avec succès.");
                    break;
                case 6:
                    System.out.println("Au revoir!");
                    System.exit(0);
                default:
                    System.out.println("Option non valide. Veuillez choisir une option valide.");
                    break;
            }
        }
    }
}