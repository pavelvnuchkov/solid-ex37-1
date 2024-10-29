import emoloyee.Post;
import product.Groceries;
import product.PieceGoods;
import product.Warehouse;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        PieceGoods pieceGoods = new PieceGoods();
        pieceGoods.addProduct("Хлеб", 100, 10);
        warehouse.addProduct(pieceGoods);

        Groceries groceries = new Groceries();
        groceries.addProduct("Сахар", 150, 200.);
        warehouse.addProduct(groceries);

        Groceries groceries3 = new Groceries();
        groceries3.addProduct("Сахароза", 222, 8.);
        warehouse.addProduct(groceries3);

        Shop shop = new Shop(warehouse).addEmployee("Павел", Post.Кассир);
        shop.addEmployee("Катя", Post.Товаровед);
        shop.open();

    }
}
