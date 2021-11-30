package domain;

import java.util.ArrayList;

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

    public ArrayList<TrainingResult> getTop5FromDiscipline(ArrayList<TrainingResult> bestDisciplineTimes) {
        ArrayList<TrainingResult> top5FromDisicpline = new ArrayList<>();
        for (TrainingResult bestDisciplineTime : bestDisciplineTimes) {
            if (top5FromDisicpline.size() < 5) {
                top5FromDisicpline.add(bestDisciplineTime);
            } else {
                return top5FromDisicpline;
            }
        }
        return top5FromDisicpline;
    }

    public String getTop5FromDisciplineToString(String memberName, String bestTime) {
        return "Konkurrencesvømmer " + memberName + " bedste tid: " + bestTime + "\n";
    }

}
