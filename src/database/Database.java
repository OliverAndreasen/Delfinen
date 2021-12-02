package database;

import domain.*;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Database {

    private FileHandler fileHandler = new FileHandler();
    private int lastIdMember;
    private int lastIdCompetitionMember;
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Team> teams = new ArrayList<>();
    private ArrayList<Integer> memberIdsWithDebt = new ArrayList<>();
    private ArrayList<Integer> competitiveMemberIdsJunior = new ArrayList<>();
    private ArrayList<Integer> competitiveMemberIdsSenior = new ArrayList<>();
    private ArrayList<Competition> competitions = new ArrayList<>();

    public Database() {
        try {
            loadMembers();
            loadTeamMembers();
            setMembersWithDebt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Competition> getCompetitions() {
        return competitions;
    }


    public ArrayList<Integer> getCompetitiveMemberIdsJunior() {
        return competitiveMemberIdsJunior;
    }

    public ArrayList<Integer> getCompetitiveMemberIdsSenior() {
        return competitiveMemberIdsSenior;
    }

    public void loadTeamMembers() throws FileNotFoundException {
        competitiveMemberIdsJunior.clear();
        competitiveMemberIdsSenior.clear();
        Scanner sc = fileHandler.reader("data/Teams.csv");
        sc.useDelimiter(";");
        while (sc.hasNext()) {

            String teamName = sc.next();
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
            if (team.getTeamName().equals("Junior")) {
                for (Integer competitiveMemberIdJunior : competitiveMemberIdsJunior) {
                    team.addTeamMember(competitiveMemberIdJunior);
                }
            } else {
                for (Integer competitiveMemberIdSenior : competitiveMemberIdsSenior) {
                    team.addTeamMember(competitiveMemberIdSenior);
                }
            }
        }
    }

    public void loadMembers() throws IOException {
        members.clear();
        Scanner sc = fileHandler.reader("data/Members.csv");
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            int memberId = Integer.parseInt(sc.next());
            if (memberId < 100) {
                this.lastIdMember = memberId;
            } else {
                this.lastIdCompetitionMember = memberId;
            }
            String name = sc.next();
            int age = Integer.parseInt(sc.next());
            boolean activeStatus;
            String activeStatusString = sc.next();
            activeStatus = activeStatusString.equals("true");
            String teamType = sc.next();
            String paidThisYearString = sc.next();
            boolean paidThisYear;
            paidThisYear = paidThisYearString.equals("true");
            if (memberId > 100) {
                String dates = sc.next();
                Date[] bestTrainingTimeDate = new Date[4];
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/mm/ss");
                String[] rawDates = dates.split(",");

                for (int i = 0; i < bestTrainingTimeDate.length; i++) {
                    if (!rawDates[i].equals("null")) {
                        try {
                            bestTrainingTimeDate[i] = formatter.parse(rawDates[i]);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
                CompetitionMember member = new CompetitionMember(memberId, name, age, activeStatus, teamType, paidThisYear);
                member.setBestTrainingTimeDates(bestTrainingTimeDate);
                member.loadBestTraingTimes();
                members.add(member);
            } else {
                Member member = new Member(memberId, name, age, activeStatus, teamType, paidThisYear);
                members.add(member);
            }
            sc.nextLine();
        }
    }

    public void loadCompetitions() throws FileNotFoundException {
        Scanner sc = fileHandler.reader("data/Competitions.csv");
        sc.useDelimiter(";");
        int nextDiscipline = -1;
        String teamName = "";
        Competition competition = null;
        while (sc.hasNext()) {
            String competitionName = sc.next();
            if (!competitionName.equals("Senior")) {
                try {
                    nextDiscipline = Integer.parseInt(competitionName);
                } catch (NumberFormatException e) {

                }
                if (!competitionName.equals("Junior") || !competitionName.equals("Senior")) {
                    if (nextDiscipline == -1) {
                        int competitionId = Integer.parseInt(sc.next());
                        String competitionDateString = sc.next();
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                        Date competitionDate = new Date();
                        try {
                            competitionDate = formatter.parse(competitionDateString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        competition = new Competition(competitionId, competitionName, competitionDate);
                        competitions.add(competition);
                        teamName = sc.next();
                    }
                }
            }else {
                teamName = "Senior";
            }
            int disciplineIndex;
            if(nextDiscipline != -1){
                disciplineIndex = nextDiscipline;
                nextDiscipline = -1;
            }else {
                disciplineIndex = Integer.parseInt(sc.next());
            }
            String competitionMemberIdAndTimes = sc.next();

            if (teamName.equals("Junior")) {
                loadDisicplineTime(competition, competitionMemberIdAndTimes, disciplineIndex);
            } else if (teamName.equals("Senior")) {
                loadDisicplineTime(competition, competitionMemberIdAndTimes, disciplineIndex);
            }

            /*
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
            if (team.getTeamName().equals("Junior")) {
                for (Integer competitiveMemberIdJunior : competitiveMemberIdsJunior) {
                    team.addTeamMember(competitiveMemberIdJunior);
                }
            } else {
                for (Integer competitiveMemberIdSenior : competitiveMemberIdsSenior) {
                    team.addTeamMember(competitiveMemberIdSenior);
                }
            }
             */
        }
    }


    public ArrayList<Integer> getMemberIdsWithDebt() {
        return memberIdsWithDebt;
    }

    public ArrayList<Integer> loadMemberIdsWithDebt() throws IOException {
        memberIdsWithDebt.clear();
        Scanner sc = fileHandler.reader("data/Accounting.csv");
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            int memberId = Integer.parseInt(sc.next());
            memberIdsWithDebt.add(memberId);
        }
        return memberIdsWithDebt;
    }

    public void saveMember(Member member) throws IOException {
        BufferedWriter writer = fileHandler.writer("data/Members.csv", true);
        String result = "";
        result += member.getMemberId();
        result += ";";
        result += member.getName();
        result += ";";
        result += member.getAge();
        result += ";";
        result += member.getActiveStatus();
        result += ";";
        result += member.getTeamType();
        result += ";";
        result += member.getPaidThisYear();
        result += ";";

        if (member instanceof CompetitionMember) {
            Date[] bestTrainingTimeDates = ((CompetitionMember) member).getBestTrainingTimeDates();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/mm/ss");
            StringBuilder resultBestTrainingTimeDates = new StringBuilder();
            for (int i = 0; i < bestTrainingTimeDates.length; i++) {
                if (i == 3) {
                    if (bestTrainingTimeDates[i] == null) {
                        resultBestTrainingTimeDates.append(bestTrainingTimeDates[i]);
                    } else {
                        resultBestTrainingTimeDates.append(formatter.format(bestTrainingTimeDates[i]));
                    }
                } else {
                    if (bestTrainingTimeDates[i] == null) {
                        resultBestTrainingTimeDates.append(bestTrainingTimeDates[i]).append(",");
                    } else {
                        resultBestTrainingTimeDates.append(formatter.format(bestTrainingTimeDates[i])).append(",");
                    }
                }
            }
            result += resultBestTrainingTimeDates;
            result += ";";
        }

        writer.write(result);
        writer.newLine();
        writer.close();
    }

    public void saveMemberIdWithDebt(Integer memberId) throws IOException {
        BufferedWriter writer = fileHandler.writer("data/Accounting.csv", false);
        String result = memberId + ";";
        writer.write(result);
        writer.close();
        System.out.println("Saved");
    }

    public ArrayList<Member> getAllMembers() {
        return members;
    }

    public int nextIdMember() {
        return this.lastIdMember + 1;
    }

    public int nextIdCompetitionMember() {
        return this.lastIdCompetitionMember + 1;
    }

    public Member getMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public ArrayList<CompetitionMember> getAllCompetitionMembers() {
        ArrayList<CompetitionMember> competitionMembers = new ArrayList<>();
        for (Member member : members) {
            if (member.getMemberId() > 100) {
                competitionMembers.add((CompetitionMember) member);
            }
        }
        return competitionMembers;
    }

    /***** Load & Save from accounting *****/
    public void loadMembersFromDebtList() {

    }

    public void setMembersWithDebt() {
        for (Member member : members) {
            if (!member.getPaidThisYear()) {
                memberIdsWithDebt.add(member.getMemberId());
            }
        }
    }

    // TODO - skal måske lige rettes/optimeres
    public void deleteMember(Member member) {
        members.remove(member);
        overWriteAndSaveFile();

    }

    public void overWriteAndSaveFile() {
        fileHandler.overwriteFile();

        for (Member m : members) {
            try {
                saveMember(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void loadDisicplineTime(Competition competition, String competitionMemberIdAndTimes, int disciplineIndex) {
        String[] competitionMemberIdAndTime = competitionMemberIdAndTimes.split(",");
        switch (disciplineIndex) {
            //butterfly
            case 0:
                for (int i = 0; i < competitionMemberIdAndTime.length; i++) {
                    int competitionMemberId = Integer.parseInt(competitionMemberIdAndTime[i].substring(0, 3));
                    String competitionMemberTime = competitionMemberIdAndTime[i].substring(4);
                    competition.addBestButterFlyTime(competitionMemberId, competitionMemberTime);
                }
                break;
            //crawl
            case 1:
                for (int i = 0; i < competitionMemberIdAndTime.length; i++) {
                    int competitionMemberId = Integer.parseInt(competitionMemberIdAndTime[i].substring(0, 3));
                    String competitionMemberTime = competitionMemberIdAndTime[i].substring(4);
                    competition.addBestCrawlTime(competitionMemberId, competitionMemberTime);
                }
                break;
            //backstroke
            case 2:
                for (int i = 0; i < competitionMemberIdAndTime.length; i++) {
                    int competitionMemberId = Integer.parseInt(competitionMemberIdAndTime[i].substring(0, 3));
                    String competitionMemberTime = competitionMemberIdAndTime[i].substring(4);
                    competition.addBestBackStrokeTime(competitionMemberId, competitionMemberTime);
                }
                break;
            //breaststroke
            case 3:
                for (int i = 0; i < competitionMemberIdAndTime.length; i++) {
                    int competitionMemberId = Integer.parseInt(competitionMemberIdAndTime[i].substring(0, 3));
                    String competitionMemberTime = competitionMemberIdAndTime[i].substring(4);
                    competition.addBestBreastStrokeTime(competitionMemberId, competitionMemberTime);
                }
                break;
            default:
                System.out.println("Wrong number!");
        }
    }
    /*
    public void loopCompetitionMemberAndTime(String[] competitionMemberIdAndTime, Competition competetion) {
        for (int i = 0; i < competitionMemberIdAndTime.length; i++) {
            int competitionMemberId = Integer.parseInt(competitionMemberIdAndTime[i].substring(0, 3));
            String competitionMemberTime = competitionMemberIdAndTime[i].substring(4);
            switch ()
            competetion.addBestButterFlyTime(competitionMemberId, competitionMemberTime);
        }


    }*/

}