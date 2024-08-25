import java.util.Scanner;
import java.util.Set;

public class Category {
    public String categoryName = "";
    public float discount = 0;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Category() {
    }

    public Category(String categoryName, float discount) {
        this.categoryName = categoryName.toLowerCase();
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Category [categoryName=" + categoryName + ", discount=" + discount + "]";
    }

    public static Category addCategory(Scanner s, Set<Category> categoryList) {
        boolean isValidDiscount = false;
        Category c = new Category();
        System.out.println("Enter Product Category Name:");
        c.categoryName = s.nextLine();
        System.out.println("Enter Discount :");
        while (!isValidDiscount) {
            try {
                // set regular price
                c.setDiscount(Integer.parseInt(s.nextLine()));
                isValidDiscount = true;
            } catch (Exception e) {
                System.out.println("Please enter valid discount value.....");
                isValidDiscount = false;
            }
        }
        App.categoryList.add(c);
        return c;
    }

    public static void printCategories(Set<Category> lc) {
        for (Category category : lc) {
            System.out.println(category.toString());
        }
    }
}