package domain;

import java.util.ArrayList;

public class Team {
    private String swimStyle;
    private AgeGroup ageGroup;
    private ArrayList<CompetitionMember> teamMembers = new ArrayList<>();



    public void addMemberToTeam(CompetitionMember member) {
        teamMembers.add(member);
    }

    public void removeMemberFromTeam(CompetitionMember member) {
        teamMembers.remove(member);
    }


}
