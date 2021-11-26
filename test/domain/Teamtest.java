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
            for (int i = 0; i < competitiveIds.length; i++) {
                if (teamName.equals("Junior")) {
                    competitiveMemberIdsJunior.add(competitiveIds[i]);
                } else {
                    competitiveMemberIdsSenior.add(competitiveIds[i]);
                }
            }

            Team team = new Team(teamName);
            if(team.getTeamName().equals("Junior"))       {
                for (int i = 0; i < competitiveMemberIdsJunior.size(); i++) {
                    team.addTeamMember(competitiveMemberIdsJunior.get(i));
                }
            }else {
                for (int i = 0; i < competitiveMemberIdsSenior.size(); i++) {
                    team.addTeamMember(competitiveMemberIdsSenior.get(i));
                }
            }


            ArrayList<Integer> teamIds = team.getTeamMembersIds();
            ArrayList<String> bestButterFlyTimes = new ArrayList<>();
            for (int i = 0; i < teamIds.size(); i++) {
                System.out.println(team.getTeamName());
                Member member = db.getMemberById(teamIds.get(i));
                String memberBestTime = ((CompetitionMember) member).getBestTrainingTimeByDiscipline(0);
                System.out.println(memberBestTime);
                if(memberBestTime != null) {
                    bestButterFlyTimes.add(memberBestTime);
                }
                // System.out.println(member);
            }
            Collections.sort(bestButterFlyTimes);

            System.out.println(bestButterFlyTimes);
        }

    }
}