package database;

import domain.CompetitionMember;
import domain.Member;
import domain.Team;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Database {
    private int lastIdMember;
    private int lastIdCompetitionMember;
    FileHandler fileHandler = new FileHandler();
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<Team> teams = new ArrayList<>();
    private ArrayList<Integer> memberIdsWithDebt = new ArrayList<>();

    public Database() {
        try {
             loadMembers();
             setMembersWithDebt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMembers() throws IOException {
        members.clear();
        Scanner sc = fileHandler.reader("data/Members.txt");
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

                String[] test = dates.split(",");

                for (int i = 0; i < bestTrainingTimeDate.length; i++) {
                    //System.out.println(test[i]);
                    if (!test[i].equals("null")) {
                        try {
                            bestTrainingTimeDate[i] = formatter.parse(test[i]);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                }

                CompetitionMember member = new CompetitionMember(memberId, name, age, activeStatus, teamType, paidThisYear);
                member.setBestTrainingTimeDates(bestTrainingTimeDate);
                members.add(member);
            } else {
                Member member = new Member(memberId, name, age, activeStatus, teamType, paidThisYear);
                members.add(member);
            }

            sc.nextLine();
        }
    }

    public ArrayList<Integer> getMemberIdsWithDebt(){
        return memberIdsWithDebt;
    }

    public ArrayList<Integer> loadMemberIdsWithDebt() throws IOException {
        memberIdsWithDebt.clear();
        Scanner sc = fileHandler.reader("data/Accounting.txt");
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            int memberId = Integer.parseInt(sc.next());
            memberIdsWithDebt.add(memberId);
        }
        return memberIdsWithDebt;
    }

    public void saveMember(Member member) throws IOException {
        BufferedWriter writer = fileHandler.writer("data/Members.txt", true);
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
            Date date = new Date();
            String strDate = formatter.format(date);
            //System.out.println(strDate);

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
        System.out.println("Saved");
    }

    public void saveMemberIdWithDebt(Integer memberId) throws IOException {

        BufferedWriter writer = fileHandler.writer("data/Accounting.txt", false);
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

    //public Member findMember(String firstName, String lastName);
    //public ArrayList<Division> getAllDivisions();
    //public Division getDivision(String divisionName);

}