package model;

public class Candidate {
    private String id;
    private String name;
    private String party;

    public Candidate(String id, String name, String party) {
        this.id = id;
        this.name = name;
        this.party = party;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public void displayCandidate() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Party: " + party);
    }
}



