package ra.com.br.pets;

public enum GenderEnum {

    NO_SELECTED(0), UNKNOWN(1), MALE(2), FEMALE(3);

    private int code;

    private GenderEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
