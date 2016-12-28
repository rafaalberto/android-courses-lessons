package ra.com.br.justjava;

import android.content.res.Resources;

public class MainService {

    private static final int UNITY_PRICE = 5;
    private static final int WHIPPED_CREAM_PRICE = 1;
    private static final int CHOCOLATE_PRICE = 2;
    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 99;
    private static final String EMPTY = "";

    int quantity = MIN_QUANTITY;
    
    private Resources resources;

    public MainService(Resources resources) {
        this.resources = resources;
    }

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
        if (name.equals(EMPTY)) {
            throw new SystemException(resources.getString(R.string.validation_name));
        }

        String labelName = resources.getString(R.string.name);
        String labelQuantity = resources.getString(R.string.quantity);
        String labelAddWhippedCream = resources.getString(R.string.add_whipped_cream);
        String labelAddChocolate = resources.getString(R.string.add_chocolate);
        String labelThankYou = resources.getString(R.string.thank_you);
        String labelYes = resources.getString(R.string.yes);
        String labelNo = resources.getString(R.string.no);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(labelName.concat(": ").concat(name))
                .append("\n".concat(labelQuantity.concat(": ").concat(String.valueOf(quantity))))
                .append("\n".concat("Total: $ ").concat(String.valueOf(calculatePrice(quantity, hasWhippedCream, hasChocolate))))
                .append("\n".concat(labelAddWhippedCream).concat(" ").concat(hasWhippedCream == true ? labelYes : labelNo))
                .append("\n".concat(labelAddChocolate).concat(" ").concat(hasChocolate == true ? labelYes : labelNo))
                .append("\n".concat(labelThankYou));
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
