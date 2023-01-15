import java.io.*;
import java.util.ArrayList;
import java.util.List;

class MetierProduitImpl implements IMetier<Produit, Integer> {
    private List<Produit> produits;
    private String filename;

    public MetierProduitImpl(String filename) {
        this.produits = new ArrayList<>();
        this.filename = filename;
    }

    @Override
    public Produit add(Produit produit) {
        produits.add(produit);
        return produit;
    }

    @Override
    public List<Produit> getAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            produits = (List<Produit>) ois.readObject();
        } catch (FileNotFoundException e) {
            try {
                File file = new File(filename);
                file.createNewFile();
                produits = new ArrayList<>();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return produits;
    }

    @Override
    public Produit findById(Integer id) {
        for (Produit produit : produits) {
            if (produit.getId() == id) {
                return produit;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        Produit produitToRemove = findById(id);
        if (produitToRemove != null) {
            produits.remove(produitToRemove);
        }
    }

    @Override
    public void saveAll() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(produits);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
