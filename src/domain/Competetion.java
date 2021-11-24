package domain;

import java.time.LocalDateTime;

public class Competetion {
    private int CompetitionID;
    private String name;
    private LocalDateTime date;
    private boolean paidThisYear;

    public Competetion(int competitionID, String name, LocalDateTime date) {
        CompetitionID = competitionID;
        this.name = name;
        this.date = date;
    }


    public int getCompetitionID() {
        return CompetitionID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}
