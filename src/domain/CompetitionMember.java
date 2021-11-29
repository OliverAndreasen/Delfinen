package domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CompetitionMember extends Member {

    private Date[] bestTrainingTimeDates = new Date[4];
    private final String[] bestTrainingTimes = new String[4];

    public CompetitionMember(int memberId, String name, int age, boolean activeStatus, String teamType, boolean paidThisYear) {
        super(memberId, name, age, activeStatus, teamType, paidThisYear);
    }

    public String[] getBestTrainingTimes() {
        return bestTrainingTimes;
    }


    public void loadBestTraingTimes(){
        for (int i = 0; i < bestTrainingTimeDates.length; i++) {
                setBestTrainingTimes(i);                        
        }                                                       
    }

    public String getBestTrainingTimeByDiscipline(int index){
        switch (index){
            case 0: return getBestTrainingTimes()[0];
            case 1: return getBestTrainingTimes()[1];
            case 2:return getBestTrainingTimes()[2];
            case 3: return getBestTrainingTimes()[3];
        }
        return null;
    }

    public void setBestTrainingTimes(int index) {
        if(bestTrainingTimeDatesToString()[index] != null) {
            String swimmingDiscipline = bestTrainingTimeDatesToString()[index];
            String[] datesplit = swimmingDiscipline.split("/");
            for (int i = 0; i < datesplit.length; i++) {
                    String minutes = datesplit[3];
                    String seconds = datesplit[4];
                    String result = minutes + seconds;
                    bestTrainingTimes[index] = result;
            }
        }
    }

    public String bestTime() {
        // TODO: make best time function
        return "LOL";
    }

    public Date[] getBestTrainingTimeDates() {
        return bestTrainingTimeDates;
    }

    public void setBestTrainingTimeDates(Date[] bestTrainingTimeDates) {
        this.bestTrainingTimeDates = bestTrainingTimeDates;
    }

    public String[] bestTrainingTimeDatesToString() {
        String[] datesToString = new String[4];
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/mm/ss");
        for (int i = 0; i < bestTrainingTimeDates.length; i++) {
            if(bestTrainingTimeDates[i] != null) {
                datesToString[i] = formatter.format(bestTrainingTimeDates[i]);
            }
        }
        return datesToString;
    }

    public String getDateById(int index) {
        String swimmingDiscipline = bestTrainingTimeDatesToString()[index];
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
        bestTrainingTimeDates[disciplineIndex] = convertStringDateToDate(bestTrainingTimeDateString);
    }
}
