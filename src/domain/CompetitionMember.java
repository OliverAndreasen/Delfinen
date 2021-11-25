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

    public String[] bestTrainingTimeDateToString() {
        String[] datesToString = new String[4];
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/mm/ss");
        for (int i = 0; i < bestTrainingTimeDate.length; i++) {
            datesToString[i] = formatter.format(bestTrainingTimeDate[i]);
        }

        return datesToString;
    }

    public String getDateById(int index){
        String swimmingDiscipline = bestTrainingTimeDateToString()[index];
        String[] datesplit = swimmingDiscipline.split("/");
        String day = datesplit[0];
        String month = datesplit[1];
        String year = datesplit[2];
        String mintues = datesplit[3];
        String secounds = datesplit[4];

        String result = day + "/" + month + "/" + "/" + year + "\n" + "Best time: " + mintues + ":" + secounds;
        return result;
    }

    // smid i ui
    public void getDateBySwimmingStyle(String swimmingStyle){
        String result = "";
        switch(swimmingStyle) {
            case "butterfly":
                getDateById(0);
                break;
            case "crawl":
                getDateById(1);
                break;
            case "rygcrawl":
                getDateById(2);
                break;
            case "brystsvømning":
                getDateById(3);
                break;
            default:
                // code block
        }
    }

    public void setBestTrainingTimeDate(Date[] bestTrainingTimeDate) {
        this.bestTrainingTimeDate = bestTrainingTimeDate;
    }
}
