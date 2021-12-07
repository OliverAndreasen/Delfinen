package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CompetitionMember extends Member {

    private final String[] bestTrainingTimes = new String[4];
    private Date[] bestTrainingTimeDates = new Date[4];

    public CompetitionMember(int memberId, String name, int age, boolean activeStatus, String teamType, boolean paidThisYear) {
        super(memberId, name, age, activeStatus, teamType, paidThisYear);
    }

    public String[] getBestTrainingTimes() {
        return bestTrainingTimes;
    }

    public void setBestTrainingTimes(int index) {
        if (bestTrainingTimeDatesToString()[index] != null) {
            String swimmingDiscipline = bestTrainingTimeDatesToString()[index];
            String[] dateSplit = swimmingDiscipline.split("/");
            for (int i = 0; i < dateSplit.length; i++) {
                String minutes = dateSplit[3];
                String seconds = dateSplit[4];
                String result = minutes + seconds;
                bestTrainingTimes[index] = result;
            }
        }
    }

    public void loadBestTrainingTimes() {
        for (int i = 0; i < bestTrainingTimeDates.length; i++) {
            setBestTrainingTimes(i);
        }
    }

    public String getBestTrainingTimeByDiscipline(int index) {
        return switch (index) {
            case 0 -> getBestTrainingTimes()[0];
            case 1 -> getBestTrainingTimes()[1];
            case 2 -> getBestTrainingTimes()[2];
            case 3 -> getBestTrainingTimes()[3];
            default -> null;
        };
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
            if (bestTrainingTimeDates[i] != null) {
                datesToString[i] = formatter.format(bestTrainingTimeDates[i]);
            }
        }
        return datesToString;
    }

    public String getBestTrainingDateById(int index) {
        String swimmingDiscipline = bestTrainingTimeDatesToString()[index];
        String[] dateSplit = swimmingDiscipline.split("/");
        String day = dateSplit[0];
        String month = dateSplit[1];
        String year = dateSplit[2];
        String minutes = dateSplit[3];
        String seconds = dateSplit[4];
        return day + "/" + month + "/" + "/" + year + "\n" + "Best time: " + minutes + ":" + seconds;
    }
}
