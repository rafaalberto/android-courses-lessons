package ra.com.br.trip;

public class City {

    private String name;
    private String country;
    private int idPhoto;

    public City(String name, String country, int resourceIdImage) {
        this.name = name;
        this.country = country;
        this.idPhoto = resourceIdImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }
}
