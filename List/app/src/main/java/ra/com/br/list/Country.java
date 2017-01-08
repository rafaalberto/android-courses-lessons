package ra.com.br.list;

public class Country implements Comparable<Country> {

    private String name;
    private String capital;

    public Country(String name, String capital) {
        this.name = name;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    @Override
    public int compareTo(Country o) {
        return getName().compareTo(o.getName());
    }
}
