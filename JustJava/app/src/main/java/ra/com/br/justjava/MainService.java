package ra.com.br.justjava;

import org.apache.commons.lang3.StringUtils;

public class MainService {

    private static final int UNITY_PRICE = 5;
    private static final int WHIPPED_CREAM_PRICE = 1;
    private static final int CHOCOLATE_PRICE = 2;

    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 99;

    private static final String EMPTY = "";

    int quantity = MIN_QUANTITY;

    public int increment() {
        if (quantity < MAX_QUANTITY) {
            return quantity = quantity + MIN_QUANTITY;
        }
        return quantity;
    }

    public int decrement() {
        if (quantity > MIN_QUANTITY) {
            return quantity = quantity - MIN_QUANTITY;
        }
        return quantity;
    }

    public void resetOrder() {
        quantity = MIN_QUANTITY;
    }

    public String createOrderSummary(String name, boolean hasWhippedCream, boolean hasChocolate) {
        name = StringUtils.trimToEmpty(name);
        if (name.equals(EMPTY)) {
            throw new SystemException("You must type your name");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: " + name)
                .append("\nQuantity: " + quantity)
                .append("\nTotal: $ " + calculatePrice(quantity, hasWhippedCream, hasChocolate))
                .append("\nAdd Whipped Cream? " + (hasWhippedCream == true ? "Yes" : "No"))
                .append("\nAdd Chocolate? " + (hasChocolate == true ? "Yes" : "No"))
                .append("\nThank you!");
        return stringBuilder.toString();
    }

    private int calculatePrice(int quantity, boolean hasWhippedCream, boolean hasChcolate) {
        int price = UNITY_PRICE;
        if (hasWhippedCream) {
            price = price + WHIPPED_CREAM_PRICE;
        }
        if (hasChcolate) {
            price = price + CHOCOLATE_PRICE;
        }
        return price * quantity;
    }
}
