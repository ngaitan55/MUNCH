package munch;

import java.util.List;

public class Reaction {
    String id;
    List<String> substrates;
    List<String> products;

    public Reaction(String id) {
        this.id = id;
    }

    public void addSubstrate(String met){
        substrates.add(met);
    }

    public void addProduct(String met){
        products.add(met);
    }

    public String getId() {
        return id;
    }

    public List<String> getSubstrates() {
        return substrates;
    }

    public List<String> getProducts() {
        return products;
    }
}
