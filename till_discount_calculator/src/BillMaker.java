import java.util.Scanner;

public class BillMaker {
    public Product product;
    public int quantity;
    public double moneyDiscounted;
    public double discountPrice;
    public static double clearanceDiscountPercentage = 20;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getMoneyDiscounted() {
        return Double.parseDouble(String.format("%.2f", moneyDiscounted));
    }

    public void setMoneyDiscounted(double moneyDiscounted) {
        this.moneyDiscounted = Double.parseDouble(String.format("%.2f", moneyDiscounted));
    }

    public double getDiscountPrice() {
        return Double.parseDouble(String.format("%.2f", discountPrice));
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = Double.parseDouble(String.format("%.2f", discountPrice));
    }

    public static void billMaker(Scanner s) {
        boolean isBilled = false;
        boolean isValidNum = false;
        BillMaker bill = null;
        App.productsInKart.clear();
        Product.printProductNames(App.productStringList);
        System.out.println("Enter no. of products you wish to buy.....");
        int count = 0;
        while (!isValidNum) {
            try {
                count = Integer.parseInt(s.nextLine());
                isValidNum = true;
            } catch (Exception e) {
                System.out.println("Please enter valid product count.....");
                isValidNum = false;
            }
        }
        for (int i = 1; i <= count; i++) {
            bill = new BillMaker();
            System.out.println("Enter product " + i + " name");
            String productString = s.nextLine();
            for (int p = 0; p < App.productStringList.size(); p++) {
                Product temp = App.productStringList.get(p);
                if (temp.getProductName().toLowerCase().equals(productString.toLowerCase())) {
                    System.out.println("Product " + i + " added");
                    bill.setProduct(temp);
                    isBilled = true;
                    break;
                } else if (p == App.productStringList.size() - 1) {
                    System.out.println("Product [" + productString + "] not available....");
                    isBilled = false;
                }
            }
            if (isBilled) {
                System.out.println("Enter product quantity");
                isValidNum = false;
                while (!isValidNum) {
                    try {
                        bill.setQuantity(Integer.parseInt(s.nextLine()));
                        isValidNum = true;
                    } catch (Exception e) {
                        System.out.println("Please enter valid quantity.....");
                        isValidNum = false;
                    }
                }
                double sellPrice = bill.getProduct().getRegularPrice() * bill.getQuantity();
                double discountPercentage = bill.getProduct().getProductCategory().getDiscount();
                bill.setMoneyDiscounted(sellPrice * (discountPercentage / 100));
                bill.setDiscountPrice(bill.getProduct().isClearance
                        ? (sellPrice - bill.getMoneyDiscounted())
                                * ((100 - BillMaker.clearanceDiscountPercentage) / 100)
                        : (sellPrice - bill.getMoneyDiscounted()));
                App.productsInKart.add(bill);
            }
        }
        System.out.println("Your bill is");
        double totalDiscount = 0;
        double totalAmount = 0;
        for (BillMaker item : App.productsInKart) {
            System.out.println(
                    item.getQuantity() + " " + item.product.getProductName() + " at " + item.getDiscountPrice());
            totalDiscount += item.getMoneyDiscounted();
            totalAmount += item.getDiscountPrice();
        }
        System.out.println("Total : " + Double.parseDouble(String.format("%.2f", totalAmount)));
        System.out.println("You saved : " + Double.parseDouble(String.format("%.2f", totalDiscount)));
    }
}
