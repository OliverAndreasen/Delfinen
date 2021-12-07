package domain.controllers;

import domain.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedMap;

public class CoachController extends Controller {

    private Coach coachJunior = new Coach("Søren", "Junior");
    private Coach coachSenior = new Coach("Slette Mette", "Senior");
    private Team junior;
    private Team senior;
    private CompetitionController competitionController = new CompetitionController();
    private DisciplineTimes disciplineTimes = new DisciplineTimes();

    public Coach getCoachJunior() {
        return coachJunior;
    }

    public Coach getCoachSenior() {
        return coachSenior;
    }

    public Team getJunior() {
        return junior;
    }

    public void setJunior(Team junior) {
        this.junior = junior;
    }

    public Team getSenior() {
        return senior;
    }

    public void setSenior(Team senior) {
        this.senior = senior;
    }

    public void startChoach() {
        ui.printCoachMenu();
        createTeams();
        while (programIsRunning) {
            switch (ui.userInputNumber()) {
                case 1 -> {
                    // TODO Tilføj konkurrencesvømmer til hold.
                }
                case 2 -> {
                    // TODO Tilføj konkurrencesvømmer bedste tid.
                }
                case 4 -> {
                    choseAgeGroup();
                }
                case 5 -> {
                    try {
                        db.loadCompetitions();
                    }catch (IOException e){
                        ui.printString("kunne ikke loade nogen konkurencer");
                    }
                    disciplineTimes.sortBestTimes();
                    printCompetitions();

                    String competitionTimeByDiscipline = chooseDiscipline();
                    ui.printString(competitionTimeByDiscipline);
                }
                case 0 -> {
                   start();
                }
                default -> {
                    int min = 0;
                    int max = 5;
                    ui.printInvalidNumber(min, max);
                }
            }
        }
    }

    private String chooseDiscipline() {
        ui.printChooseDisciplines();
        int disciplineId = ui.userInputNumber();

        switch (disciplineId) {
            case 1 -> {return competitionController.CompetitionButterFlyToString();}
            case 2 -> {return competitionController.competitionCrawlToString();}
            case 3 -> {return competitionController.competitionBackStrokeToString();}
            case 4 -> {return competitionController.competitionBreastStrokeToString();}
        }
        return null;
    }

    private void printCompetitions() {
        for (Competition competition: db.getCompetitions()) {
            ui.printString(competition.getName());
        }
    }

    public void choseAgeGroup() {
        ui.printChoseAgeGroup();
        switch (ui.userInputNumber()) {
            case 1 -> {
                ui.printChooseDisciplines();
                Team team = getJunior();
                team.sortAllTimes();
                Coach coach = getCoachJunior();
                printOutTop5(team, coach);
                /*System.out.println();
                ui.printBackToCoachMenu();
                ui.userInputNumber();
                if (ui.userInputNumber() == 0){
                    startChoach();
                }*/
            }
            case 2 -> {
                ui.printChooseDisciplines();
                Team team = getSenior();
                team.sortAllTimes();
                Coach coach = getCoachSenior();
                printOutTop5(team, coach);
                /*System.out.println();
                ui.printBackToCoachMenu();
                ui.userInputNumber();
                if (ui.userInputNumber() == 0){
                    startChoach();
                }*/
            }
            case 0 -> {
                startChoach();
            }
        }
    }

    public void printOutTop5(Team team, Coach coach) {
        ArrayList<CompetitionMemberResult> disciplineBestTimes = chooseBestDisciplineTimes(team);
        ArrayList<CompetitionMemberResult> top5FromDiscipline = coach.getTop5FromDiscipline(disciplineBestTimes);
        for (CompetitionMemberResult CompetitiveMember : top5FromDiscipline) {
            Member member = db.getMemberById(CompetitiveMember.getMemberId());
            ui.printString(coach.getTop5FromDisciplineToString(member.getName(), CompetitiveMember.getResultTime()));
        }
    }

    public ArrayList<CompetitionMemberResult> chooseBestDisciplineTimes(Team team) {
        switch (ui.userInputNumber()) {
            case 1 -> {
                return team.getAllButterflyTimes();
            }
            case 2 -> {
                return team.getALlCrawlTimes();
            }
            case 3 -> {
                return team.getALlBackStrokeTimes();
            }
            case 4 -> {
                return team.getAllBreastStrokeTimes();
            }
        }
        return null;
    }

    public void createTeams() {
        Team junior = new Team("Junior");
        setJunior(junior);
        Team senior = new Team("Senior");
        setSenior(senior);
        junior.setCompetitionMemberIds(db.getCompetitiveMemberIdsJunior());
        senior.setCompetitionMemberIds(db.getCompetitiveMemberIdsSenior());
        setTeamBestTimes(junior);
        setTeamBestTimes(senior);
    }

    public void setTeamBestTimes(Team team) {
        for (int i = 0; i < team.getTeamMembersIds().size(); i++) {
            Integer competitiveId = team.getTeamMembersIds().get(i);
            Member member = db.getMemberById(competitiveId);
            for (int j = 0; j < 4; j++) {
                if (member != null) {
                    String memberBestTime = ((CompetitionMember) member).getBestTrainingTimeByDiscipline(j);
                    setBestTrainingTimesByDiscipline(team, competitiveId, memberBestTime, j);
                }
            }
        }
    }

    public void setBestTrainingTimesByDiscipline(Team team, Integer competitiveId, String memberBestTime, int discipline) {
        switch (discipline) {
            case 0 -> {
                if (memberBestTime != null) {
                    team.addBestButterFlyTime(competitiveId, memberBestTime);
                }
            }
            case 1 -> {
                if (memberBestTime != null) {
                    team.addBestCrawlTime(competitiveId, memberBestTime);
                }
            }
            case 2 -> {
                if (memberBestTime != null) {
                    team.addBestBackStrokeTime(competitiveId, memberBestTime);
                }
            }
            case 3 -> {
                if (memberBestTime != null) {
                    team.addBestBreastStrokeTime(competitiveId, memberBestTime);
                }
            }
        }
    }
}
