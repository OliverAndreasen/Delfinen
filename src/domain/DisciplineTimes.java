package domain;

import java.util.ArrayList;
import java.util.Collections;

public class DisciplineTimes {

    ArrayList<CompetitionMemberResult> allButterFlyTimes = new ArrayList<>();
    ArrayList<CompetitionMemberResult> allCrawlTimes = new ArrayList<>();
    ArrayList<CompetitionMemberResult> allBackStrokeTimes = new ArrayList<>();
    ArrayList<CompetitionMemberResult> allBreastStrokeTimes = new ArrayList<>();

    public void setAllButterFlyTimes(ArrayList<CompetitionMemberResult> allButterFlyTimes) {
        this.allButterFlyTimes = allButterFlyTimes;
    }

    public void setAllCrawlTimes(ArrayList<CompetitionMemberResult> allCrawlTimes) {
        this.allCrawlTimes = allCrawlTimes;
    }

    public void setAllBackStrokeTimes(ArrayList<CompetitionMemberResult> allBackStrokeTimes) {
        this.allBackStrokeTimes = allBackStrokeTimes;
    }

    public void setAllBreastStrokeTimes(ArrayList<CompetitionMemberResult> allBreastStrokeTimes) {
        this.allBreastStrokeTimes = allBreastStrokeTimes;
    }

    public ArrayList<CompetitionMemberResult> getAllButterFlyTimes() {
        return allButterFlyTimes;
    }

    public ArrayList<CompetitionMemberResult> getAllCrawlTimes() {
        return allCrawlTimes;
    }

    public ArrayList<CompetitionMemberResult> getAllBackStrokeTimes() {
        return allBackStrokeTimes;
    }

    public ArrayList<CompetitionMemberResult> getAllBreastStrokeTimes() {
        return allBreastStrokeTimes;
    }



    public void sortBestTimes() {
        Collections.sort(allButterFlyTimes);
        Collections.sort(allCrawlTimes);
        Collections.sort(allBackStrokeTimes);
        Collections.sort(allBreastStrokeTimes);
    }
}



