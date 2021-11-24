package database;

import domain.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Database {
    FileHandler fileHandler = new FileHandler();
    ArrayList<Member> members = new ArrayList<> ();
    ArrayList<Team> teams = new ArrayList<>();
    public int lastId;

    public void loadMembers() throws IOException {
        members.clear();
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
                String[] swimmingDisciplines = ((CompetitionMember) member).getSwimmingDisciplines();

                DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

                Date date = new Date();

                String strDate = formatter.format(date);
                System.out.println(strDate);

                bestTrainingTimeDate[0] = date;
                bestTrainingTimeDate[1] = date;
                bestTrainingTimeDate[2] = date;
                bestTrainingTimeDate[3] = date;

                String resultBestTrainingTimeDate = "";
                String resultSwimmingDiscipline = "";
                for (int i = 0; i < 4; i++) {
                    swimmingDisciplines[i] = "crawl";
                    if (i == 3) {
                        resultBestTrainingTimeDate += formatter.format(bestTrainingTimeDate[i]);
                        resultSwimmingDiscipline += swimmingDisciplines[i];
                    } else {
                        resultBestTrainingTimeDate += formatter.format(bestTrainingTimeDate[i]) + ",";
                        resultSwimmingDiscipline += swimmingDisciplines[i] + ",";
                    }
                }
                result += resultBestTrainingTimeDate;
                result += resultSwimmingDiscipline;
            }

            writer.write(result);
            writer.newLine();
            writer.close();
            System.out.println("Saved");
        }

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


    public ArrayList<Member> getAllMembers() {
        return members;
    }

    public int nextId() {
        return this.lastId + 1;
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



    //public Member findMember(String firstName, String lastName);
    //public ArrayList<Division> getAllDivisions();
    //public Division getDivision(String divisionName);


    }