package domain;

import database.Database;
import database.FileHandler;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class testCompetition {
    public static void main(String[] args) throws FileNotFoundException {
        Database db = new Database();
        FileHandler fileHandler = new FileHandler();

        Scanner sc = fileHandler.reader("data/Competitions.csv");
        sc.useDelimiter(";");
        int nextDiscipline = -1;
        String teamName = "";
        Competition competition = null;
        while (sc.hasNext()) {
            String competitionName = sc.next();
        if (!competitionName.equals("Senior")) {
            try {
                nextDiscipline = Integer.parseInt(competitionName);
            } catch (NumberFormatException e) {

            }
            if (!competitionName.equals("Junior") || !competitionName.equals("Senior")) {
                if (nextDiscipline == -1) {
                    int competitionId = Integer.parseInt(sc.next());
                    String competitionDateString = sc.next();
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                    Date competitionDate = new Date();
                    try {
                        competitionDate = formatter.parse(competitionDateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(formatter.format(competitionDate));

                    competition = new Competition(competitionId, competitionName, competitionDate);

                    teamName = sc.next();
                }
            }
        } else {
            teamName = "Senior";
        }
            int disciplineIndex;
            if (nextDiscipline != -1) {
                disciplineIndex = nextDiscipline;
                nextDiscipline = -1;
            } else {
                disciplineIndex = Integer.parseInt(sc.next());
            }
            String competitionMemberIdAndTimes = sc.next();
            if (teamName.equals("Junior")) {
                String[] competitionMemberIdAndTime = competitionMemberIdAndTimes.split(",");
                switch (disciplineIndex) {
                    //butterfly
                    case 0:
                        System.out.println(Arrays.toString(competitionMemberIdAndTime));
                        for (int i = 0; i < competitionMemberIdAndTime.length; i++) {
                            int competitionMemberId = Integer.parseInt(competitionMemberIdAndTime[i].substring(0, 3));
                            String competitionMemberTime = competitionMemberIdAndTime[i].substring(4);
                            competition.addBestButterFlyTime(competitionMemberId, competitionMemberTime);
                        }
                        break;
                    //crawl
                    case 1:
                        System.out.println(Arrays.toString(competitionMemberIdAndTime));
                        for (int i = 0; i < competitionMemberIdAndTime.length; i++) {
                            int competitionMemberId = Integer.parseInt(competitionMemberIdAndTime[i].substring(0, 3));
                            String competitionMemberTime = competitionMemberIdAndTime[i].substring(4);
                            competition.addBestCrawlTime(competitionMemberId, competitionMemberTime);
                        }
                        break;
                    //backstroke
                    case 2:
                        System.out.println(Arrays.toString(competitionMemberIdAndTime));
                        for (int i = 0; i < competitionMemberIdAndTime.length; i++) {
                            int competitionMemberId = Integer.parseInt(competitionMemberIdAndTime[i].substring(0, 3));
                            String competitionMemberTime = competitionMemberIdAndTime[i].substring(4);
                            competition.addBestBackStrokeTime(competitionMemberId, competitionMemberTime);
                        }
                        break;
                    //breaststroke
                    case 3:
                        System.out.println(Arrays.toString(competitionMemberIdAndTime));
                        for (int i = 0; i < competitionMemberIdAndTime.length; i++) {
                            int competitionMemberId = Integer.parseInt(competitionMemberIdAndTime[i].substring(0, 3));
                            String competitionMemberTime = competitionMemberIdAndTime[i].substring(4);
                            competition.addBestBreastStrokeTime(competitionMemberId, competitionMemberTime);
                        }
                        break;
                    default:
                        System.out.println("Wrong number!");
                }
                /*
                ArrayList<CompetitionMemberResult> butterfly = competition.getAllButterflyTimes();
                for(CompetitionMemberResult butterFlyMemberResult : butterfly) {
                    System.out.println(butterFlyMemberResult.getResultTime());
                }
                 */
            }

            else if(teamName.equals("Senior") || competitionName.equals("Senior")){
                String[] competitionMemberIdAndTime = competitionMemberIdAndTimes.split(",");
                switch (disciplineIndex) {
                    //butterfly
                    case 0:
                        System.out.println(Arrays.toString(competitionMemberIdAndTime));
                        for (int i = 0; i < competitionMemberIdAndTime.length; i++) {
                            int competitionMemberId = Integer.parseInt(competitionMemberIdAndTime[i].substring(0, 3));
                            String competitionMemberTime = competitionMemberIdAndTime[i].substring(4);
                            competition.addBestButterFlyTime(competitionMemberId, competitionMemberTime);
                        }
                        break;
                    //crawl
                    case 1:
                        System.out.println(Arrays.toString(competitionMemberIdAndTime));
                        for (int i = 0; i < competitionMemberIdAndTime.length; i++) {
                            int competitionMemberId = Integer.parseInt(competitionMemberIdAndTime[i].substring(0, 3));
                            String competitionMemberTime = competitionMemberIdAndTime[i].substring(4);
                            competition.addBestCrawlTime(competitionMemberId, competitionMemberTime);
                        }
                        break;
                    //backstroke
                    case 2:
                        System.out.println(Arrays.toString(competitionMemberIdAndTime));
                        for (int i = 0; i < competitionMemberIdAndTime.length; i++) {
                            int competitionMemberId = Integer.parseInt(competitionMemberIdAndTime[i].substring(0, 3));
                            String competitionMemberTime = competitionMemberIdAndTime[i].substring(4);
                            competition.addBestBackStrokeTime(competitionMemberId, competitionMemberTime);
                        }
                        break;
                    //breaststroke
                    case 3:
                        System.out.println(Arrays.toString(competitionMemberIdAndTime));
                        for (int i = 0; i < competitionMemberIdAndTime.length; i++) {
                            int competitionMemberId = Integer.parseInt(competitionMemberIdAndTime[i].substring(0, 3));
                            String competitionMemberTime = competitionMemberIdAndTime[i].substring(4);
                            competition.addBestBreastStrokeTime(competitionMemberId, competitionMemberTime);
                        }
                        break;
                    default:
                        System.out.println("Wrong number!");
                }
            }
        }
    }
}
