package domain.controllers;

import database.Database;
import domain.*;
import ui.UserInterface;
import java.util.ArrayList;


public class CoachController extends Controller {
    private Coach coachJunior = new Coach("Søren", "Junior");
    private Coach coachSenior = new Coach("Slette Mette", "Senior");
    private Team junior;
    private Team senior;

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
                case 3 -> {
                    // TODO Vis top 5.
                    ui.printChoseAgeGroup();
                    choseAgeGroup();
                }
                case 0 -> {
                   start();
                }
                default -> {
                    int min = 0;
                    int max = 3;
                    ui.printInvalidNumber(min, max);
                }
            }
        }
    }

    public void choseAgeGroup() {
        switch (ui.userInputNumber()) {
            case 1 -> {
                ui.printTop5Lists();
                Team team = getJunior();
                team.sortBestTrainingTimes();
                Coach coach = getCoachJunior();
                printOutTop5(team, coach);
            }
            case 2 -> {
                ui.printTop5Lists();
                Team team = getSenior();
                team.sortBestTrainingTimes();
                Coach coach = getCoachSenior();
                printOutTop5(team, coach);
            }
            case 0 ->{
                startChoach();
            }
        }
    }

    public void printOutTop5(Team team, Coach coach) {
        ArrayList<TrainingResult> disciplineBestTimes = chooseBestDisciplineTimes(team);
        ArrayList<TrainingResult> top5FromDiscipline = coach.getTop5FromDiscipline(disciplineBestTimes);
        for (TrainingResult CompetitiveMember : top5FromDiscipline) {
            Member member = db.getMemberById(CompetitiveMember.getMemberId());
            ui.printString(coach.getTop5FromDisciplineToString(member.getName(), CompetitiveMember.getTrainingTime()));
        }
    }

    public ArrayList<TrainingResult> chooseBestDisciplineTimes(Team team) {
        switch (ui.userInputNumber()) {
            case 1 -> {
                return team.getAllButterFlyTimes();
            }
            case 2 -> {
                return team.getAllCrawlTimes();
            }
            case 3 -> {
                return team.getAllBackStrokeTimes();
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
