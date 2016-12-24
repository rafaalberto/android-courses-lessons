package ra.com.br.justjava;

public class MainService {

    private static final int MINIMUM_QUANTITY = 0;
    private static final int UNITY_PRICE = 5;
    private static final int ONE = 1;

    int quantity = MINIMUM_QUANTITY;

    public int increment() {
        return quantity = quantity + ONE;
    }

    public int decrement() {
        if (quantity > MINIMUM_QUANTITY) {
            return quantity = quantity - ONE;
        }
        return quantity;
    }

    public void resetOrder() {
        quantity = MINIMUM_QUANTITY;
    }

    public String createOrderSummary(boolean hasWhippedCream) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: Rafael Alberto")
                .append("\nQuantity: " + quantity)
                .append("\nTotal: $ " + calculatePrice(quantity))
                .append("\nAdd Whipped Cream? " + (hasWhippedCream == true ? "Yes" : "No"))
                .append("\nThank you!");
        return stringBuilder.toString();
    }

    private int calculatePrice(int quantity) {
        return quantity * UNITY_PRICE;
    }
}
