package domain;

import java.util.ArrayList;

public class Coach {
    private String name;
    private String ageGroup;

    public Coach(String name, String ageGroup) {
        this.name = name;
        this.ageGroup = ageGroup;
    }

    public ArrayList<CompetitionMemberResult> getTop5FromDiscipline(ArrayList<CompetitionMemberResult> bestDisciplineTimes) {
        ArrayList<CompetitionMemberResult> top5FromDiscipline = new ArrayList<>();
        for (CompetitionMemberResult bestDisciplineTime : bestDisciplineTimes) {
            if (top5FromDiscipline.size() < 5) {
                top5FromDiscipline.add(bestDisciplineTime);
            } else {
                return top5FromDiscipline;
            }
        }
        return top5FromDiscipline;
    }

    public String getTop5FromDisciplineToString(String memberName, String bestTime) {
        return "Konkurrencesvømmer " + memberName + " bedste tid: " + bestTime + "\n";
    }

}
