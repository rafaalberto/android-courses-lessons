package br.com.waitlist.data;

public class Waitlist {

    private String personName;
    private Integer partyCount;

    public Waitlist(String personName, Integer partyCount) {
        this.personName = personName;
        this.partyCount = partyCount;
    }

    public String getPersonName() {
        return personName;
    }

    public int getPartyCount() {
        return partyCount;
    }
}
