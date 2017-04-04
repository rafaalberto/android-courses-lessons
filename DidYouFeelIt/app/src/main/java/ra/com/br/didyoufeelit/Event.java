package ra.com.br.didyoufeelit;

public class Event {

    public final String title;
    public final String perceivedStrength;
    public final String numberOfPeople;

    public Event(String eventTitle, String eventPerceivedStrength, String eventNumberOfPeople) {
        title = eventTitle;
        perceivedStrength = eventPerceivedStrength;
        numberOfPeople = eventNumberOfPeople;
    }
}