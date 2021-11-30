package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap; // import the HashMap class
import java.util.Map;


public class Team {
    private String teamName;
    private ArrayList<Integer> competitionMemberIds = new ArrayList<>();
    HashMap<Integer, String> allButterFlyTimes = new HashMap<Integer, String>();
    HashMap<Integer, String> allCrawlTimes = new HashMap<Integer, String>();
    HashMap<Integer, String> allBackStrokeTimes = new HashMap<Integer, String>();
    HashMap<Integer, String> allBreastStrokeTimes = new HashMap<Integer, String>();

    public void addBestButterFlyTime(Integer competitionMemberIds, String bestTime){
        allButterFlyTimes.put(competitionMemberIds, bestTime);
    }

    public void addBestCrawlTime(Integer competitionMemberIds, String bestTime){
        allCrawlTimes.put(competitionMemberIds, bestTime);
    }

    public void addBestBackStrokeTime(Integer competitionMemberIds, String bestTime){
        allBackStrokeTimes.put(competitionMemberIds, bestTime);
    }

    public void addBestBreastStrokeTime(Integer competitionMemberIds, String bestTime){
        allBreastStrokeTimes.put(competitionMemberIds, bestTime);
    }

    public void setCompetitionMemberIds(ArrayList<Integer> competitionMemberIds) {
        this.competitionMemberIds = competitionMemberIds;
    }

    public HashMap<Integer, String> getAllButterFlyTimes() {
        return allButterFlyTimes;
    }

    public HashMap<Integer, String> getAllCrawlTimes() {
        return allCrawlTimes;
    }

    public HashMap<Integer, String> getAllBackStrokeTimes() {
        return allBackStrokeTimes;
    }

    public HashMap<Integer, String> getAllBreastStrokeTimes() {
        return allBreastStrokeTimes;
    }



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

    public void sortBestTrainingTimes(){
        HashMap<Integer, String> allButterFlyTimes = new HashMap<Integer, String>();
        HashMap<Integer, String> allCrawlTimes = new HashMap<Integer, String>();
        HashMap<Integer, String> allBackStrokeTimes = new HashMap<Integer, String>();
        HashMap<Integer, String> allBreastStrokeTimes = new HashMap<Integer, String>();

    }

}


