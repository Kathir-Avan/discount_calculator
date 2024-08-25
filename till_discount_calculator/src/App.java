import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static List<BillMaker> productsInKart = new ArrayList<>();
    public static List<Product> productStringList = new ArrayList<>();
    public static Set<Category> categoryList = new HashSet<>();
    public static int menuNum = 0;
    public static boolean isCompleted = true;
    public static Scanner s;

    public static void main(String[] args) throws Exception {
        setCategoryDiscount();
        setDefaultProducts();
        while (isCompleted) {
            System.out.println("Please enter the menu number.....");
            System.out.println(
                    "1.Add Product\n2.Add Category\n3.Billing Counter\n4.Print Products\n5.Print Categories\n6.Exit");
            s = new Scanner(System.in);
            String input = s.nextLine();
            try {
                menuNum = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Please enter valid menu number.....");
            }
            switch (menuNum) {
                case 1:
                    System.out.println("You are in Add Product menu");
                    Product.addProduct(s, productStringList);
                    break;
                case 2:
                    System.out.println("You are in Add Category menu");
                    Category.addCategory(s, categoryList);
                    break;
                case 3:
                    System.out.println("You are in Billing Counter\nAvailable prducts are ");
                    BillMaker.billMaker(s);
                    break;
                case 4:
                    System.out.println("You are in Printing products");
                    Product.printProducts(productStringList);
                    break;
                case 5:
                    System.out.println("You are in Printing Categories");
                    Category.printCategories(categoryList);
                    break;
                case 6:
                    System.out.println("Exit");
                    isCompleted = false;
                    break;
                default:
                    System.out.println(input + " is not a valid menu number.....");
                    break;
            }
        }
    }

    private static void setDefaultProducts() {
        Product p = new Product("Teak Wood", 22.25, new Category("Hardware", 3), false);
        productStringList.add(p);
        productStringList.add(new Product("The Wings of fire", 220.25, new Category("book", 2), true));
        productStringList.add(new Product("Pink shirt", 599.90, new Category("cloth", 25), false));
        productStringList.add(new Product("Bottle of perfume", 599.90, new Category("other", 30), true));
        productStringList.add(new Product("Chocolate bar", 50.9, new Category("food", 3.5f), false));
        productStringList.add(new Product("Wine", 550.25, new Category("food", 8.5f), true));
    }

    public static void setCategoryDiscount() {
        categoryList.add(new Category("book", 5));
        categoryList.add(new Category("food", 5));
        categoryList.add(new Category("drink", 5));
        categoryList.add(new Category("cloth", 20));
        categoryList.add(new Category("other", 3));
        categoryList.add(new Category("clearance", 5));
    }
}