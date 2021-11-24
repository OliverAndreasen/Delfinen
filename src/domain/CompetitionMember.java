package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class CompetitionMember extends Member {
    private Date bestTrainingTimeDate;
    private LocalDateTime bestTrainingTime;
    private String swimmingDiscipline;
    private ArrayList<Competetion> competetions = new ArrayList<>();

    public CompetitionMember(int memberId, String name, int age, boolean activeStatus, String teamType, boolean paidThisYear) {
        super(memberId, name, age, activeStatus, teamType, paidThisYear);
    }

    public String bestTime() {
        // TODO: make best time function
        return "LOL";
    }

    public Date getBestTrainingTimeDate() {
        return bestTrainingTimeDate;
    }

    public LocalDateTime getBestTrainingTime() {
        return bestTrainingTime;
    }

    public String getSwimmingDiscipline() {
        return swimmingDiscipline;
    }

    public ArrayList<Competetion> getCompetetions() {
        return competetions;
    }

    public void setBestTrainingTimeDate(Date bestTrainingTimeDate) {
        this.bestTrainingTimeDate = bestTrainingTimeDate;
    }

    public void setBestTrainingTime(LocalDateTime bestTrainingTime) {
        this.bestTrainingTime = bestTrainingTime;
    }

    public void setSwimmingDiscipline(String swimmingDiscipline) {
        this.swimmingDiscipline = swimmingDiscipline;
    }

    public void addCompetetion(Competetion competetion) {
        competetions.add(competetion);
    }
}
