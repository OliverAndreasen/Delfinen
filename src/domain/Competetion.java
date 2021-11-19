package domain;
import java.util.HashMap;

public class Competetion {
    private String name;
    private HashMap<Integer, Integer> competetors = new HashMap<>();

    public String getName() {
        return name;
    }

    public HashMap<Integer, Integer> getCompetetors() {
        return competetors;
    }

    // CompetitionMemberID & Time
    public void setCompetetors(HashMap<Integer, Integer> competetors) {
        this.competetors = competetors;
    }
}
