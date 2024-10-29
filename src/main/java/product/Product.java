package product;

public interface Product<T> {
    String addProduct(String name, int price, T count);

    Product addProduct(Product product, T count);

    String buyProduct(T count);

    String getName();

    int getPrice();

    T getCount();

    void newPrice(int price);

    void addCount(T count);

    String toString();
}
