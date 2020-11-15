
package ohtu;

public class Player implements Comparable<Player> {
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
        return String.format("%-20s", name) + "\t" + team + "\t" + goals + "+" + assists + "=" + (goals + assists);
    }

    @Override
    public int compareTo(Player o) {
        return (o.getGoals() + o.getAssists()) - (goals + assists);
    }
      
}
