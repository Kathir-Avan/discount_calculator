import java.util.List;
import java.util.Scanner;

public class Product {
    public String productName = "";
    public double regularPrice = 0;
    public Category productCategory = null;
    public boolean isClearance = false;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getRegularPrice() {
        return Double.parseDouble(String.format("%.2f", regularPrice));
    }

    public void setRegularPrice(double regularPrice) {
        this.regularPrice = Double.parseDouble(String.format("%.2f", regularPrice));
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public boolean isClearance() {
        return isClearance;
    }

    public void setClearance(boolean isClearance) {
        this.isClearance = isClearance;
    }

    public Product() {
    }

    public Product(String productName, double regularPrice, Category productCategory, boolean isClearance) {
        this.productName = productName.toString();
        this.regularPrice = regularPrice;
        this.productCategory = productCategory;
        this.isClearance = isClearance;
    }

    @Override
    public String toString() {
        return "Product [ProductName=" + productName + ", RegularPrice=" + regularPrice + ", ProductCategoryName="
                + productCategory.categoryName + ", ProductCategoryDiscount=" + productCategory.discount
                + ", Clearance Discount Available=" + isClearance + "]";
    }

    public static void addProduct(Scanner s, List<Product> productList) {
        boolean isValidPrice = false;
        boolean isValidCategory = false;
        boolean isValidClearance = false;
        Product p = new Product();
        Category c = new Category();
        System.out.println("Enter Product Name :");
        // set product name
        p.setProductName(s.nextLine());
        System.out.println("Enter regular price :");
        while (!isValidPrice) {
            try {
                // set regular price
                p.setRegularPrice(Integer.parseInt(s.nextLine()));
                isValidPrice = true;
            } catch (Exception e) {
                System.out.println("Please enter valid regular price.....");
                isValidPrice = false;
            }
        }
        while (!isValidCategory) {
            System.out.println("Enter any available Product Category :");
            boolean isCategoryExists = false;
            for (Category cat : App.categoryList) {
                System.out.println(cat.getCategoryName());
            }
            try {
                c.setCategoryName(s.nextLine());
                for (Category cat : App.categoryList) {
                    if (cat.getCategoryName().equals(c.getCategoryName())) {
                        c.setDiscount(cat.getDiscount());
                        isCategoryExists = true;
                    }
                    // to add logic for add category if not available.
                }
                if (!isCategoryExists) {
                    System.out.println("Lets add this new category and proceed with adding product");
                    c = Category.addCategory(s, App.categoryList);
                    isValidCategory = false;
                }
                isValidCategory = true;
            } catch (Exception e) {
                System.out.println("Please enter valid Product Category.....");
                isValidCategory = false;
            }
        }
        // set category
        p.setProductCategory(c);
        // set clearance
        System.out.println("Enter clearance discount is applicable for the Product? (Y/N)");
        while (!isValidClearance) {
            switch (s.nextLine().toLowerCase()) {
                case "y":
                    p.setClearance(true);
                    isValidClearance = true;
                    break;
                case "n":
                    p.setClearance(false);
                    isValidClearance = true;
                    break;
                default:
                    System.out.println("Please enter valid response.....");
                    isValidClearance = false;
                    break;
            }
        }
        productList.add(p);
        System.out.println("Product added sucessfully - " + p.toString());
    }

    public static void printProducts(List<Product> lp) {
        for (Product product : lp) {
            System.out.println(product.toString());
        }
    }

    public static void printProductNames(List<Product> lp) {
        for (Product product : lp) {
            System.out.print(product.getProductName() + " - ");
        }
        System.out.println();
    }
}