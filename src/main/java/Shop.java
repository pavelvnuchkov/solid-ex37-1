import emoloyee.Cashier;
import emoloyee.CommodityExpert;
import emoloyee.Employee;
import emoloyee.Post;
import product.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
    private Warehouse warehouse;
    private List<Employee> employee;

    public Shop(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.employee = new ArrayList<>();
    }

    public void open() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Здравствуйте!");
            System.out.println("Если вы покупатель нажмите 1 Enter");
            System.out.println("Если вы поставщик нажмите 2 Enter");
            System.out.println("Если хотите уйти введите 'exit'");
            String inputUser = scanner.nextLine();
            if (inputUser.equals("exit")) {
                break;
            }
            employee.get(Integer.parseInt(inputUser) - 1).go();
        }
    }

    public Shop addEmployee(String name, Post post) {
        if (Post.Кассир.equals(post)) {
            employee.add(new Cashier(warehouse, name));
        } else if (Post.Товаровед.equals(post)) {
            employee.add(new CommodityExpert(warehouse, name));
        }
        return this;
    }

}
