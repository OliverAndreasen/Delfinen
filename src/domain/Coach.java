package domain;

import database.Database;

import java.util.ArrayList;

public class Coach {
    private String name;
    private String ageGroup;
    private CompetitionMember competitionMember;
    private Database db;

    public ArrayList top5FromDivision(Team team, String swimmingDiscipline) {

        // TODO: change to Array instead
        ArrayList<CompetitionMember> top5 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {

        }

        return top5;
    }

    private void addCompetitionMemberBestTime(CompetitionMember competitionMember){}

    }
