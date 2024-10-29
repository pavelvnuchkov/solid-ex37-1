package product;

public class Groceries implements Product<Double> {
    private String name;
    private int price;
    private double count;

    @Override
    public String addProduct(String name, int price, Double count) {
        this.name = name;
        this.price = price;
        this.count = count;
        return "Товар " + name + " добавлен!";
    }

    public Product addProduct(Product product, Double count) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.count = count;
        return this;
    }

    @Override
    public String buyProduct(Double count) {
        this.count -= count;
        return "Товар " + name + " куплен!";
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Double getCount() {
        return count;
    }

    public void newPrice(int price) {
        this.price = price;
    }

    public void addCount(Double count) {
        this.count += count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Groceries{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
