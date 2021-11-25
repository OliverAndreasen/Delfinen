package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class CompetitionMember extends Member {
    private Date bestTrainingTimeDate[] = new Date[4];
    private String bestTrainingTimes[] = new String[4];


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

    public String[] getBestTrainingTimes() {
        for (int i = 0; i < bestTrainingTimeDate.length; i++) {
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            this.bestTrainingTimes[i] = formatter.format(bestTrainingTimeDate[i]);
        }
        return bestTrainingTimes;
    }



    public void setBestTrainingTimeDate(Date[] bestTrainingTimeDate) {
        this.bestTrainingTimeDate = bestTrainingTimeDate;
    }
}
