package domain;

import java.util.ArrayList;
import java.util.Collections;


public class Team {
    ArrayList<TrainingResult> allButterFlyTimes = new ArrayList<>();
    ArrayList<TrainingResult> allCrawlTimes = new ArrayList<>();
    ArrayList<TrainingResult> allBackStrokeTimes = new ArrayList<>();
    ArrayList<TrainingResult> allBreastStrokeTimes = new ArrayList<>();
    private String teamName;
    private ArrayList<Integer> competitionMemberIds = new ArrayList<>();

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public ArrayList<TrainingResult> getAllButterFlyTimes() {
        return allButterFlyTimes;
    }

    public ArrayList<TrainingResult> getAllCrawlTimes() {
        return allCrawlTimes;
    }

    public ArrayList<TrainingResult> getAllBackStrokeTimes() {
        return allBackStrokeTimes;
    }

    public ArrayList<TrainingResult> getAllBreastStrokeTimes() {
        return allBreastStrokeTimes;
    }

    public void addBestButterFlyTime(Integer competitionMemberId, String bestTime) {
        allButterFlyTimes.add(new TrainingResult(competitionMemberId, bestTime));
    }

    public void addBestCrawlTime(Integer competitionMemberIds, String bestTime) {
        allCrawlTimes.add(new TrainingResult(competitionMemberIds, bestTime));
    }

    public void addBestBackStrokeTime(Integer competitionMemberIds, String bestTime) {
        allBackStrokeTimes.add(new TrainingResult(competitionMemberIds, bestTime));
    }

    public void addBestBreastStrokeTime(Integer competitionMemberIds, String bestTime) {
        allBreastStrokeTimes.add(new TrainingResult(competitionMemberIds, bestTime));
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

    public void sortBestTrainingTimes() {
        Collections.sort(allButterFlyTimes);
        Collections.sort(allCrawlTimes);
        Collections.sort(allBackStrokeTimes);
        Collections.sort(allBreastStrokeTimes);
    }

}

