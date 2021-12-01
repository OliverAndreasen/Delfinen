package domain;

import java.util.ArrayList;

public class Coach {
    private String name;
    private String ageGroup;

    public Coach(String name, String ageGroup) {
        this.name = name;
        this.ageGroup = ageGroup;
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
