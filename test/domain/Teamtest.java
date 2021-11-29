package domain;

import database.Database;
import database.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Teamtest {
    public static void main(String[] args) throws IOException {
        Database db = new Database();
        FileHandler fileHandler = new FileHandler();
        ArrayList<Integer> competitiveMemberIdsJunior = new ArrayList<>();
        ArrayList<Integer> competitiveMemberIdsSenior = new ArrayList<>();

        db.loadMembers();
        Scanner sc = fileHandler.reader("data/Teams.txt");
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            String teamName = sc.next();
            //System.out.println(teamName);
            String[] competitiveIdsString = sc.next().split(",");
            int[] competitiveIds = new int[competitiveIdsString.length];
            for (int i = 0; i < competitiveIdsString.length; i++) {
                competitiveIds[i] = Integer.parseInt(competitiveIdsString[i]);
            }
            for (int competitiveId : competitiveIds) {
                if (teamName.equals("Junior")) {
                    competitiveMemberIdsJunior.add(competitiveId);
                } else {
                    competitiveMemberIdsSenior.add(competitiveId);
                }
            }

            Team team = new Team(teamName);
            if(team.getTeamName().equals("Junior"))       {
                for (Integer competitiveMemberIdJunior : competitiveMemberIdsJunior) {
                    team.addTeamMember(competitiveMemberIdJunior);
                }
            }else {
                for (Integer competitiveMemberIdSenior : competitiveMemberIdsSenior) {
                    team.addTeamMember(competitiveMemberIdSenior);
                }
            }

            ArrayList<Integer> teamIds = team.getTeamMembersIds();
            ArrayList<String> bestButterFlyTimes = new ArrayList<>();
            for (Integer competitiveMemberId : teamIds) {
                //System.out.println(team.getTeamName());
                Member member = db.getMemberById(competitiveMemberId);
                String memberBestTime = ((CompetitionMember) member).getBestTrainingTimeByDiscipline(1);
                //System.out.println(member.getName());
                //System.out.println(memberBestTime);
                if (memberBestTime != null) {
                    bestButterFlyTimes.add(memberBestTime);
                }
            }
            System.out.println("Before sorting");
            System.out.println(bestButterFlyTimes);
            Collections.sort(bestButterFlyTimes);
            System.out.println("After sorting");
            System.out.println(bestButterFlyTimes);
        }

    }
}