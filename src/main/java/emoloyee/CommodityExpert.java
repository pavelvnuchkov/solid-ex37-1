package emoloyee;

import product.Groceries;
import product.PieceGoods;
import product.Product;
import product.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommodityExpert implements Employee {

    private Warehouse warehouse;
    private String name;
    //List<Product> listWarehouse = new ArrayList<>();
    List<Product> invoice = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public CommodityExpert(Warehouse warehouse, String name) {
        this.warehouse = warehouse;
        this.name = name;
    }

    @Override
    public void go() {

        while (true) {
            System.out.println("Что бы уйти введите end");
            System.out.println("Если привезли товар то нажмите ENTER");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                while (true) {
                    addInvoice();
                    System.out.println("Если у вас есть ещё товар введите 'y'");
                    System.out.println("Если товаров болше нет введите 'n'");
                    input = scanner.nextLine();
                    if (input.equals("n")) {
                        break;
                    }
                }
                System.out.println("1 Сдать товар на склад.");
                System.out.println("2 Решили не поставлять.");
                if (scanner.nextLine().equals("1")) {
                    invoiceAddWarehouse();
                }

            } else if (input.equals("end")) {
                break;
            }

        }
    }

    private void addInvoice() {
        Product product = null;
        System.out.println("Выберите пункт меню: ");
        System.out.println("1 Если товар бакалея");
        System.out.println("2 Если товар штучный");
        int commandProvider = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите название товара: ");
        String name = scanner.nextLine();
        System.out.println("Введите цену товара: ");
        int price = Integer.parseInt(scanner.nextLine());


        switch (commandProvider) {
            case 1:
                System.out.println("Введите вес товара: ");
                double countGroceries = Double.parseDouble(scanner.nextLine());
                product = new Groceries();
                product.addProduct(name, price, countGroceries);

                break;

            case 2:
                System.out.println("Введите количество товара: ");
                int countPiece = Integer.parseInt(scanner.nextLine());
                product = new PieceGoods();
                product.addProduct(name, price, countPiece);
        }
        invoice.add(product);
        System.out.println(warehouse.addProduct(product));
    }

    private void invoiceAddWarehouse() {
        for (Product product : invoice) {
            System.out.println(warehouse.addProduct(product));
        }
    }
}
