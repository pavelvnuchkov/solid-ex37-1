package product;

public class PieceGoods implements Product<Integer> {
    private String name;
    private int price;
    private int count;

    @Override
    public String addProduct(String name, int price, Integer count) {
        this.name = name;
        this.price = price;
        this.count = count;
        return "Товар " + name + " добавлен!";
    }

    @Override
    public Product addProduct(Product product, Integer count) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.count = count;
        return this;
    }

    @Override
    public String buyProduct(Integer count) {
        this.count -= count;
        return "Товар " + name + " куплен!";
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Integer getCount() {
        return count;
    }

    public void newPrice(int price) {
        this.price = price;
    }

    public void addCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PieceGoods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
