package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class CompetitionMember extends Member {
    private Date bestTrainingTimeDate[] = new Date[4];

    public CompetitionMember(int memberId, String name, int age, boolean activeStatus, String teamType, boolean paidThisYear) {
        super(memberId, name, age, activeStatus, teamType, paidThisYear);
    }

    public String bestTime() {
        // TODO: make best time function
        return "LOL";
    }

    public Date[] getBestTrainingTimeDate() {
        return bestTrainingTimeDate;
    }


    public void setBestTrainingTimeDate(Date[] bestTrainingTimeDate) {
        this.bestTrainingTimeDate = bestTrainingTimeDate;
    }
}
