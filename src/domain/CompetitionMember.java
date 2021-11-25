package domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CompetitionMember extends Member {

    private Date[] bestTrainingTimeDate = new Date[4];
    private String[] bestTrainingTimes = new String[4];

    public CompetitionMember(int memberId, String name, int age, boolean activeStatus, String teamType, boolean paidThisYear) {
        super(memberId, name, age, activeStatus, teamType, paidThisYear);


        for (int i = 0; i < bestTrainingTimeDate.length; i++) {
            if (bestTrainingTimeDate[i] != null) {
                setBestTrainingTimes(i);
            }

        }
    }

    public String[] getBestTrainingTimes() {
        return bestTrainingTimes;
    }

    public void setBestTrainingTimes(int index) {
        String swimmingDiscipline = bestTrainingTimeDateToString()[index];
        String[] datesplit = swimmingDiscipline.split("/");
        String minutes = datesplit[3];
        String seconds = datesplit[4];
        String result = minutes + ":" + seconds;
        bestTrainingTimes[index] = result;
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

    public String[] bestTrainingTimeDateToString() {
        String[] datesToString = new String[4];
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/mm/ss");
        for (int i = 0; i < bestTrainingTimeDate.length; i++) {
            datesToString[i] = formatter.format(bestTrainingTimeDate[i]);
        }

        return datesToString;
    }

    public String getDateById(int index) {
        String swimmingDiscipline = bestTrainingTimeDateToString()[index];
        String[] datesplit = swimmingDiscipline.split("/");
        String day = datesplit[0];
        String month = datesplit[1];
        String year = datesplit[2];
        String minutes = datesplit[3];
        String seconds = datesplit[4];

        return day + "/" + month + "/" + "/" + year + "\n" + "Best time: " + minutes + ":" + seconds;
    }

    // smid i ui
    public void setDateBySwimmingStyle(String swimmingStyle) {
        String result = "";
        switch (swimmingStyle) {
            case "butterfly":
                /*setDateById(0);*/
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

    public Date convertStringDateToDate(String stringDate) {

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/mm/ss");
        Date date = new Date();
            try {
                //System.out.println("dato " + stringDate);
                date = formatter.parse(stringDate);
                //System.out.println("dato efter format " + date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        return date;
    }

    private void setDateById(int disciplineIndex, String bestTrainingTimeDateString) {
        bestTrainingTimeDate[disciplineIndex] = convertStringDateToDate(bestTrainingTimeDateString);
    }
}
