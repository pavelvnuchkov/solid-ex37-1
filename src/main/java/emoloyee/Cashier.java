package emoloyee;

import product.Groceries;
import product.PieceGoods;
import product.Product;
import product.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cashier implements Employee {
    private Warehouse warehouse;
    private String name;
    List<Product> listWarehouse = new ArrayList<>();
    List<Product> basket = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Cashier(Warehouse warehouse, String name) {
        this.warehouse = warehouse;
        this.name = name;
    }

    @Override
    public void go() {
        boolean entrance = true;
        System.out.println("Здравствуйте меня зовут " + name);
        System.out.println("Введите своё имя: ");
        String nameClient = scanner.nextLine();
        System.out.println("Рад вас видеть в нашем магазине " + nameClient);
        listWarehouse = warehouse.getProduct();
        System.out.println("Посмотрите что наш магазин может вам предложить!");
        printList(listWarehouse);
        while (entrance) {
            System.out.println("Введите 'name' - если вы хотите осуществить поиск по названию.");
            System.out.println("Введите 'price' - Если хотите осуществить поиск по цене.");
            System.out.println("Введите 'all' - если хотите посмотреть весь ассортимент.");
            System.out.println("Если хотите добавить в корзину товар нажмите ENTER");
            System.out.println("Если хотите посмотреть содержимое корзины введите 'print'");
            System.out.println("Если хотите купить товар который в корзине введите 'end'");
            System.out.println("Если хотите покинуть магазин введите 'exit'");
            String input = scanner.nextLine();
            String commandSearch = "";
            switch (input) {
                case "":
                    addBasket();
                    break;

                case "name":
                    System.out.println("Введите название целиком или начало! ");
                    commandSearch = scanner.nextLine();
                    listWarehouse = warehouse.search(commandSearch);
                    printList(listWarehouse);
                    break;

                case "price":
                    System.out.println("Введите цену: ");
                    int price = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите > если хотите товары дороже " + price);
                    System.out.println("Введите < если хотите товары дешевле " + price);
                    System.out.println("= если хотите товары по такой цене " + price);
                    commandSearch = scanner.nextLine();
                    listWarehouse = warehouse.search(price, commandSearch);
                    printList(listWarehouse);
                    break;

                case "all":
                    listWarehouse = warehouse.getProduct();
                    printList(listWarehouse);
                    break;

                case "print":
                    printBasket(basket);
                    break;

                case "end":
                    printBasket(basket);
                    buyProduct();
                    break;

                case "exit":
                    entrance = false;
                    break;

                default:
                    System.out.println("Введено недопустимое значение!");
            }

        }

    }

    private List<Product> addBasket() {
        System.out.println("Что бы добавить товар в корзину введите номер товара: ");
        int number = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите количество: ");
        String count = scanner.nextLine();
        if (listWarehouse.get(number).getClass().equals(Groceries.class)) {
            basket.add(new Groceries().addProduct(listWarehouse.get(number), Double.parseDouble(count)));
        } else if (listWarehouse.get(number).getClass().equals(PieceGoods.class)) {
            basket.add(new PieceGoods().addProduct(listWarehouse.get(number), Integer.parseInt(count)));
        }
        return basket;
    }

    private void buyProduct() {
        System.out.println("Если хотите купить введите 'y'");
        System.out.println("Если хотите очистить корзину нажмите 'n'");
        String input = scanner.nextLine();
        switch (input) {
            case "y":
                for (Product product : basket) {
                    System.out.println(warehouse.buyProduct(product));
                    System.out.println("Вы приобрели товары из корзины.");
                }
                break;

            case "n":
                basket.clear();
                System.out.println("Корзина пустая.");
        }
    }

    private void printList(List<Product> list) {
        for (int i = 0; i < list.size(); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(i + ". ");
            builder.append("Название: " + list.get(i).getName() + ", ");
            builder.append("Цена - " + list.get(i).getPrice() + ", ");
            builder.append("В наличии - " + list.get(i).getCount());
            if (list.get(i).getClass().equals(PieceGoods.class)) {
                builder.append(" шт.");
            } else {
                builder.append(" кг.");
            }
            builder.append("\n-----------------------");
            System.out.println(builder);
        }
    }

    private void printBasket(List<Product> list) {
        System.out.println("Содержимое вашей корзины: ");
        StringBuilder builder = new StringBuilder();
        int totalAmount = 0;
        for (int i = 0; i < basket.size(); i++) {

            builder.append(i + ". ");
            builder.append("Название: " + basket.get(i).getName() + ", ");
            builder.append("Цена - " + basket.get(i).getPrice() + ", ");
            builder.append("Количество - " + basket.get(i).getCount());
            int amount = 0;
            if (basket.get(i).getClass().equals(PieceGoods.class)) {
                builder.append(" шт.");
                amount = Integer.parseInt(basket.get(i).getCount().toString()) * basket.get(i).getPrice();
            } else {
                builder.append(" кг.");
                amount = (int) Math.round(Double.parseDouble(basket.get(i).getCount().toString()) * basket.get(i).getPrice());
            }
            builder.append("Сумма - " + amount);
            builder.append("\n-------------------\n");

            totalAmount += amount;
        }
        builder.append("\n--------   ");
        builder.append("Итого: " + totalAmount);
        builder.append("   --------\n");
        System.out.println(builder);
    }
}
