package database;

import domain.CompetitionMember;
import domain.Member;
import domain.Team;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Database {
    public int lastIdMember;
    public int lastIdCompetitionMember;
    FileHandler fileHandler = new FileHandler();
    ArrayList<Member> members = new ArrayList<>();
    ArrayList<Team> teams = new ArrayList<>();
    private ArrayList<Member> membersWithDebt = new ArrayList<>();

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
        String fileName = "data/Members.txt";
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
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
                member.setBestTrainingTimeDate(bestTrainingTimeDate);
                members.add(member);
            } else {
                Member member = new Member(memberId, name, age, activeStatus, teamType, paidThisYear);
                members.add(member);
            }

            sc.nextLine();
        }
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
            Date[] bestTrainingTimeDate = ((CompetitionMember) member).getBestTrainingTimeDate();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/mm/ss");
            Date date = new Date();
            String strDate = formatter.format(date);
            //System.out.println(strDate);

            StringBuilder resultBestTrainingTimeDate = new StringBuilder();
            for (int i = 0; i < bestTrainingTimeDate.length; i++) {
                if (i == 3) {
                    if(bestTrainingTimeDate[i] == null)  {
                        resultBestTrainingTimeDate.append(bestTrainingTimeDate[i]);
                    }else{
                        resultBestTrainingTimeDate.append(formatter.format(bestTrainingTimeDate[i]));
                    }
                } else {
                    if (bestTrainingTimeDate[i] == null){
                        resultBestTrainingTimeDate.append(bestTrainingTimeDate[i]).append(",");
                    } else {
                        resultBestTrainingTimeDate.append(formatter.format(bestTrainingTimeDate[i])).append(",");
                    }
                }
            }
            result += resultBestTrainingTimeDate;
            result += ";";
        }


        writer.write(result);
        writer.newLine();
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

    public void saveMemberToDebtList(Member member) {
    }

    public ArrayList<Member> setMembersWithDebt() {
        for (Member member : members) {
            if (!member.getPaidThisYear()) {
                membersWithDebt.add(member);
            }
        }
        return membersWithDebt;
    }

    //public Member findMember(String firstName, String lastName);
    //public ArrayList<Division> getAllDivisions();
    //public Division getDivision(String divisionName);

}