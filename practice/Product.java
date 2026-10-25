import java.util.Objects;
import java.util.function.Predicate;

public class Product {

    String name;
    double price;
    boolean inStock;

    Product(String name, double price, boolean inStock){
        this.name= name;
        this.price =price;
        this.inStock=inStock;
    }

    String getName(){
        return name;
    }
    double getPrice(){
        return price;
    }
    boolean isInStock(){
        return inStock;
    }

    void check(){
        Predicate<Product> nonNull= Objects::nonNull;
        Predicate<Product> inStock = Product::isInStock;
    }
}



