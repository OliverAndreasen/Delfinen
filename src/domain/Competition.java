package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Competition {
    private int CompetitionId;
    private String name;
    private Date date;
    private DisciplineTimes disciplineTimes = new DisciplineTimes();


    public Competition(int competitionID, String name, Date date) {
        CompetitionId = competitionID;
        this.name = name;
        this.date = date;
    }

    public ArrayList<CompetitionMemberResult> getAllButterflyTimes() {
        return disciplineTimes.getAllButterFlyTimes();
    }

    public ArrayList<CompetitionMemberResult> getALlCrawlTimes() {
        return disciplineTimes.getAllCrawlTimes();
    }

    public ArrayList<CompetitionMemberResult> getALlBackStrokeTimes() {
        return disciplineTimes.getAllBackStrokeTimes();
    }

    public ArrayList<CompetitionMemberResult> getAllBreastStrokeTimes() {
        return disciplineTimes.getAllBreastStrokeTimes();
    }

    public void addBestButterFlyTime(Integer competitionMemberId, String bestTime) {
        disciplineTimes.allButterFlyTimes.add(new CompetitionMemberResult(competitionMemberId, bestTime));
    }

    public void addBestCrawlTime(Integer competitionMemberIds, String bestTime) {
        disciplineTimes.allCrawlTimes.add(new CompetitionMemberResult(competitionMemberIds, bestTime));
    }

    public void addBestBackStrokeTime(Integer competitionMemberIds, String bestTime) {
        disciplineTimes.allBackStrokeTimes.add(new CompetitionMemberResult(competitionMemberIds, bestTime));
    }

    public void addBestBreastStrokeTime(Integer competitionMemberIds, String bestTime) {
        disciplineTimes.allBreastStrokeTimes.add(new CompetitionMemberResult(competitionMemberIds, bestTime));
    }

    public String getName() {
        return name;
    }
}
