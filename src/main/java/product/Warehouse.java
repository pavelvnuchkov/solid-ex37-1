package product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Warehouse {
    List<Product> listProduct;

    public Warehouse() {
        listProduct = new ArrayList<>();
    }

    public String buyProduct(Product product) {
        for (Product oneProduct : listProduct) {
            if (oneProduct.getName().equals(product.getName())) {
                if (Double.parseDouble(oneProduct.getCount().toString()) < Double.parseDouble(product.getCount().toString())) {
                    return "Такого количества нет!";
                }
                return oneProduct.buyProduct(product.getCount());
            }
        }
        return "Такого товара нет в наличии!";
    }

    public String addProduct(Product product) {
        StringBuilder builder = new StringBuilder();
        for (Product oneProduct : listProduct) {
            if (oneProduct.getName().equals(product.getName())) {
                oneProduct.addCount(product.getCount());
                builder.append("Товар " + product.getName() + " добавлен!");
                if (oneProduct.getPrice() != product.getPrice()) {
                    oneProduct.newPrice(product.getPrice());
                    builder.append("Теперь у товара " + product.getName() + " новая цена " + product.getPrice());
                }
                return builder.toString();
            }
        }
        listProduct.add(product);
        return "Новый товар " + product.getName() + " добавлен!";
    }

    public List<Product> search(String name) {
        List<Product> list = new ArrayList<>();
        for (Product product : listProduct) {
            if (product.getName().contains(name)) {
                list.add(product);
            }
        }
        return list;
    }

    public List<Product> search(int price, String ch) {
        List<Product> list = new ArrayList<>();
        switch (ch) {
            case ">":
                list = listProduct.stream().filter(p -> p.getPrice() > price).collect(Collectors.toList());
                break;
            case "<":
                list = listProduct.stream().filter(p -> p.getPrice() < price).collect(Collectors.toList());
                break;
            case "=":
                list = listProduct.stream().filter(p -> p.getPrice() == price).collect(Collectors.toList());
                break;
            default:
                System.out.println("Вы ввели неизвестную команду!");
                break;
        }

        return list;
    }

    public List<Product> getProduct() {
        return listProduct;
    }
}
