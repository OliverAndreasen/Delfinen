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

    public ArrayList<TrainingResult> getTop5FromDiscipline(ArrayList<TrainingResult> bestDisciplineTimes){
        ArrayList<TrainingResult> top5FromDisicpline = new ArrayList<>();
        for (int i = 0; i < bestDisciplineTimes.size(); i++) {
            if(top5FromDisicpline.size() < 5){
                top5FromDisicpline.add((bestDisciplineTimes.get(i)));
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
