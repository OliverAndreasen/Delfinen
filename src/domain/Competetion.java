package domain;
import java.util.HashMap;

public class Competetion {
    private String name;
    private HashMap<String, Integer> competetors = new HashMap<>();

    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getCompetetors() {
        return competetors;
    }

    public void setCompetetors(HashMap<String, Integer> competetors) {
        this.competetors = competetors;
    }
}
