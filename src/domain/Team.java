package domain;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private ArrayList<Integer> competitionMemberIds = new ArrayList<>();

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public ArrayList<Integer> getTeamMembersIds() {
        return competitionMemberIds;
    }

    public void addTeamMember(int competitionMemberId) {
        competitionMemberIds.add(competitionMemberId);
    }

    public void removeTeamMember(int competitionMemberId) {
        competitionMemberIds.remove(competitionMemberId);
    }

    public String getTeamName() {
        return teamName;
    }
}


