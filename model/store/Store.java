package source.model.store;

import java.util.*;

import source.model.Poor;
import source.model.product.Product;
import source.repository.Repository;

public abstract class Store<T extends Product> {
    protected Repository<T> repo;

    public abstract boolean sellProduct(int productIndex, Poor poor);
    public abstract void addProducts(List<T> product);
    public abstract ArrayList<T> getProducts();
}