package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class CompetitionMember extends Member {
    private Date bestTrainingTimeDate;
    private LocalDateTime bestTrainingTime;
    private String swimmingDiscipline;
    private ArrayList<Competetion> competetions = new ArrayList<>();



    public CompetitionMember(String name, int age, boolean activeStatus, String divisionType) {
        super(name, age, activeStatus, divisionType);
    }

    public String bestTime() {
        // TODO: make best time function
        return "LOL";
    }


}
