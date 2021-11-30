package domain;

import database.Database;

import java.util.ArrayList;
import java.util.HashMap; // import the HashMap class
import java.util.Map;


public class Coach {
    private String name;
    private String ageGroup;
    private CompetitionMember competitionMember;

    public Coach(String name, String ageGroup) {
        this.name = name;
        this.ageGroup = ageGroup;
    }

    private void addCompetitionMemberBestTime(CompetitionMember competitionMember) {
    }

}
