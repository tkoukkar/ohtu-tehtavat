
package ohtu;

public class Player {
    private String name;
    private String nationality;
    private String team;
    private int assists;
    private int goals;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getTeam() {
        return team;
    }
    
    public int getAssists() {
        return assists;
    }

    public int getGoals() {
        return goals;
    }

    @Override
    public String toString() {
        return name + " " + team + " " + goals + "+" + assists;
    }
      
}
