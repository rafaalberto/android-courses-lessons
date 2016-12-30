package ra.com.br.vehicle;

import android.content.res.Resources;

import java.text.NumberFormat;

public class MainService {

    private static final String EMPTY = "";
    private static final int ZERO = 0;

    public static String getMessage(Resources resources, Vehicle vehicle) {
        validation(resources, vehicle);
        return composeMessage(resources, vehicle);
    }

    private static void validation(Resources resources, Vehicle vehicle) {
        if (vehicle.getDescription().equals(EMPTY)) {
            throw new SystemException(resources.getString(R.string.description).concat(" ")
                    .concat(resources.getString(R.string.type_validation)));
        } else if (vehicle.getYear().equals(EMPTY)) {
            throw new SystemException(resources.getString(R.string.year).concat(" ")
                    .concat(resources.getString(R.string.type_validation)));
        } else if (vehicle.getOwner().equals(EMPTY)) {
            throw new SystemException(resources.getString(R.string.owner).concat(" ")
                    .concat(resources.getString(R.string.type_validation)));
        } else if (vehicle.getEmailOwner().equals(EMPTY)) {
            throw new SystemException(resources.getString(R.string.email_owner).concat(" ")
                    .concat(resources.getString(R.string.type_validation)));
        } else if (vehicle.getPrice() == ZERO) {
            throw new SystemException(resources.getString(R.string.price).concat(" ")
                    .concat(resources.getString(R.string.type_validation)));
        }
    }

    private static String composeMessage(Resources resources, Vehicle vehicle) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(resources.getString(R.string.description).concat(": ").concat(vehicle.getDescription()));
        stringBuilder.append("\n".concat(resources.getString(R.string.year)).concat(": ").concat(vehicle.getYear()));
        stringBuilder.append("\n".concat(resources.getString(R.string.owner)).concat(": ").concat(vehicle.getOwner()));
        stringBuilder.append("\n".concat(resources.getString(R.string.email_owner)).concat(": ").concat(vehicle.getEmailOwner()));
        stringBuilder.append("\n".concat(resources.getString(R.string.price)).concat(": ")
                .concat(NumberFormat.getCurrencyInstance().format(vehicle.getPrice())));
        stringBuilder.append("\n".concat(resources.getString(R.string.alarm)).concat(": ")
                .concat(vehicle.isAlarm() == true ? resources.getString(R.string.yes) : resources.getString(R.string.no)));
        stringBuilder.append("\n".concat(resources.getString(R.string.powerWindows)).concat(": ")
                .concat(vehicle.isPowerWindows() == true ? resources.getString(R.string.yes) : resources.getString(R.string.no)));
        stringBuilder.append("\n".concat(resources.getString(R.string.airConditioning)).concat(": ")
                .concat(vehicle.isAirConditioning() == true ? resources.getString(R.string.yes) : resources.getString(R.string.no)));
        return stringBuilder.toString();
    }
}
