package domain;

import java.util.ArrayList;
import java.util.Collections;


public class Team {
    private String teamName;
    DisciplineTimes disciplineTimes = new DisciplineTimes();

    private ArrayList<Integer> competitionMemberIds = new ArrayList<>();


    public Team(String teamName) {
        this.teamName = teamName;
    }

    public ArrayList<CompetitionMemberResult> getAllButterflyTimes() {
        return disciplineTimes.getAllButterFlyTimes();
    }

    public ArrayList<CompetitionMemberResult> getALlCrawlTimes() {
        return disciplineTimes.getAllCrawlTimes();
    }

    public ArrayList<CompetitionMemberResult> getALlBackStrokeTimes() {
        return disciplineTimes.getAllBackStrokeTimes();
    }

    public ArrayList<CompetitionMemberResult> getAllBreastStrokeTimes() {
        return disciplineTimes.getAllBreastStrokeTimes();
    }

    public void sortAllTimes()  {
        disciplineTimes.sortBestTimes();
    }

    public void setCompetitionMemberIds(ArrayList<Integer> competitionMemberIds) {
        this.competitionMemberIds = competitionMemberIds;
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

   /* public void setAllTimes() {
        for (Integer competitiveMemberId : competitionMemberIds) {
            //System.out.println(team.getTeamName());
            Member member = db.getMemberById(competitiveMemberId);
            String memberBestTime = ((CompetitionMember) member).getBestTrainingTimeByDiscipline(1);
            //System.out.println(member.getName());
            //System.out.println(memberBestTime);
            if (memberBestTime != null) {
                bestButterFlyTimes.add(memberBestTime);
            }
        }

    }*/

}

