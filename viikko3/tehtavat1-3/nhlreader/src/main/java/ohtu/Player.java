
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String team;
    private String nationality;
    private int goals;
    private int assists;
    private int penalties;

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }
    
    // Getters
    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getNationality() {
        return nationality;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getPenalties() {
        return penalties;
    }
     
    @Override
    public String toString() {
        return this.name;
    }
    
    @Override
    public int compareTo(Player otherPlayer) {
        return (otherPlayer.getGoals() + otherPlayer.getAssists()) - (this.goals + this.assists);
    } 
    
}
