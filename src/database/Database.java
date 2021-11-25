package database;

import domain.*;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Database {
    FileHandler fileHandler = new FileHandler();
    ArrayList<Member> members = new ArrayList<> ();
    ArrayList<Team> teams = new ArrayList<>();
    public int lastIdMember;
    public int lastIdCompetitionMember;

    public void loadMembers() throws IOException {
        members.clear();
        String fileName = "data/Members.txt";
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            int memberId = Integer.parseInt(sc.next());
            if(memberId < 100) {
                this.lastIdMember = memberId;
            }
            else {
                this.lastIdCompetitionMember = memberId;
            }
            String name = sc.next();
            System.out.println("name " + name);
            int age = Integer.parseInt(sc.next());
            boolean activeStatus;
            String activeStatusString = sc.next();
            if (activeStatusString.equals("true")) {
                activeStatus = true;
            } else {
                activeStatus = false;
            }
            String teamType = sc.next();
            String paidThisYearString = sc.next();
            boolean paidThisYear;
            if (paidThisYearString.equals("true")) {
                paidThisYear = true;
            } else {
                paidThisYear = false;
            }
            if(memberId > 100) {
                String dates = sc.next();
                Date bestTrainingTimeDate[] = new Date[4];
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/mm/ss");


                String[] test = dates.split(",");
                for (int i = 0; i < bestTrainingTimeDate.length; i++) {
                    /*bestTrainingTimeDate[i] = dates.split(" ");*/
                    try {
                        bestTrainingTimeDate[i] = formatter.parse(test[i]);
                        System.out.println(bestTrainingTimeDate[i]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

                CompetitionMember member = new CompetitionMember(memberId, name, age, activeStatus, teamType, paidThisYear);
                members.add(member);
            } else {

            }

                sc.nextLine();
            Member member = new Member(memberId, name, age, activeStatus, teamType, paidThisYear);
            members.add(member);
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

        if(member instanceof CompetitionMember) {
            Date[] bestTrainingTimeDate = ((CompetitionMember) member).getBestTrainingTimeDate();

            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/mm/ss");

            Date date = new Date();
            String strDate = formatter.format(date);
            System.out.println(strDate);

            bestTrainingTimeDate[0] = date;
            bestTrainingTimeDate[1] = date;
            bestTrainingTimeDate[2] = date;
            bestTrainingTimeDate[3] = date;

            String resultBestTrainingTimeDate = "";
            for (int i = 0; i < bestTrainingTimeDate.length; i++) {
                if (i == 3) {
                    resultBestTrainingTimeDate += formatter.format(bestTrainingTimeDate[i]);
                } else {
                    resultBestTrainingTimeDate += formatter.format(bestTrainingTimeDate[i]) + ",";
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

    /*
        public void loadTeams() throws FileNotFoundException {
            teams.clear();
            String fileName = "data/Members.txt";
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                int memberId = Integer.parseInt(sc.next());
                this.lastId = memberId;
                String name = sc.next();
                int age = Integer.parseInt(sc.next());
                boolean activeStatus;
                String activeStatusString = sc.next();
                if (activeStatusString.equals("true")) {
                    activeStatus = true;
                } else {
                    activeStatus = false;
                }
                String teamType = sc.next();
                String paidThisYearString = sc.next();
                boolean paidThisYear;
                if (paidThisYearString.equals("true")) {
                    paidThisYear = true;
                } else {
                    paidThisYear = false;
                }
                sc.nextLine();

                Member member = new Member(memberId, name, age, activeStatus, teamType, paidThisYear);
                members.add(member);
            }
        }
 */

    public ArrayList<Member> getAllMembers() {
        return members;
    }

    public int nextIdMember() {
        return this.lastIdMember + 1;
    }

    public int nextIdCompetitionMember(){
        return this.lastIdCompetitionMember + 1;
    }

    public Member getMemberById(int memberId) {
        for (int i = 0; i < members.size(); i++) {
            if(members.get(i).getMemberId() == memberId) {
                return members.get(i);
            }
        }
        return null;
    }

    public ArrayList<CompetitionMember> getAllCompetitionMembers(){
        ArrayList<CompetitionMember> competitionMembers = new ArrayList<>();
        for (int i = 0; i < members.size(); i++) {
            if(members.get(i).getMemberId() > 100) {
                competitionMembers.add((CompetitionMember) members.get(i));
            }
        }
        return competitionMembers;
    }

    /***** Load & Save from accounting *****/
    public void loadMembersFromDebtList() {

    }

    public void saveMemberToDebtList(Member member) {
    }


    //public Member findMember(String firstName, String lastName);
    //public ArrayList<Division> getAllDivisions();
    //public Division getDivision(String divisionName);


    }