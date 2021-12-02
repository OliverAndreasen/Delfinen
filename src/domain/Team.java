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

    public void addBestButterFlyTime(Integer competitionMemberId, String bestTime) {
        disciplineTimes.allButterFlyTimes.add(new CompetitionMemberResult(competitionMemberId, bestTime));
    }

    public void addBestCrawlTime(Integer competitionMemberIds, String bestTime) {
        disciplineTimes.allCrawlTimes.add(new CompetitionMemberResult(competitionMemberIds, bestTime));
    }

    public void addBestBackStrokeTime(Integer competitionMemberIds, String bestTime) {
        disciplineTimes.allBackStrokeTimes.add(new CompetitionMemberResult(competitionMemberIds, bestTime));
    }

    public void addBestBreastStrokeTime(Integer competitionMemberIds, String bestTime) {
        disciplineTimes.allBreastStrokeTimes.add(new CompetitionMemberResult(competitionMemberIds, bestTime));
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

