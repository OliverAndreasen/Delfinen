package domain;

import database.Database;
import java.io.IOException;
import java.util.ArrayList;

public class Coach {
    private String name;
    private String ageGroup;
    private CompetitionMember competitionMember;
    private Database db;

    public ArrayList top5FromDivision() throws IOException {
        db.loadMembers();
        ArrayList<CompetitionMember> competitionMembers = db.getAllCompetitionMembers();
        //ArrayList<CompetitionMember> top5 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            competitionMembers.get(i).getBestTrainingTimes();
        }

        return competitionMembers;
    }

    private void addCompetitionMemberBestTime(CompetitionMember competitionMember){}

    }
