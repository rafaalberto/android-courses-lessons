package ra.com.br.quakereport;

public class Earthquake {

    private String magnitude;
    private String location;
    private String date;

    public Earthquake(String localization, String level, String date) {
        this.location = localization;
        this.magnitude = level;
        this.date = date;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
