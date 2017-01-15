package ra.com.br.list;

public class Country implements Comparable<Country> {

    private String name;
    private String capital;
    private int idImageFlag;

    public Country(String name, String capital, int idImageFlag) {
        this.name = name;
        this.capital = capital;
        this.idImageFlag = idImageFlag;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public int getIdImageFlag() {
        return idImageFlag;
    }

    @Override
    public int compareTo(Country o) {
        return getName().compareTo(o.getName());
    }
}
