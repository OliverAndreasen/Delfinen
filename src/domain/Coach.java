package domain;

import database.Database;

import java.util.ArrayList;
import java.util.HashMap; // import the HashMap class
import java.util.Map;


public class Coach {
    private String name;
    private String ageGroup;
    private CompetitionMember competitionMember;

    public Coach(String name, String ageGroup) {
        this.name = name;
        this.ageGroup = ageGroup;
    }

    private void addCompetitionMemberBestTime(CompetitionMember competitionMember) {
    }

    public HashMap<Integer, String > getTop5FromDiscipline(HashMap<Integer, String> bestDisciplineTimes){
        HashMap<Integer, String> top5FromDisicpline = new HashMap<>();
        for (Map.Entry bestTime : bestDisciplineTimes.entrySet()) {
            if(top5FromDisicpline.size() < 5){
                top5FromDisicpline.put((Integer) bestTime.getKey(), (String)bestTime.getValue());
            }
            else {
                return top5FromDisicpline;
            }
        }
        return top5FromDisicpline;
    }

    public String getTop5FromDisciplineToString(String memberName, String bestTime){
        return "Konkurrencesvømmer navn: " + memberName + " bedste tid: " + bestTime;
    }

}
