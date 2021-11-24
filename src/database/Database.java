package database;

import domain.*;

import java.io.*;
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
                LocalDateTime[] bestTrainingTime = ((CompetitionMember) member).getBestTrainingTime();
                String[] swimmingDisciplines = ((CompetitionMember) member).getSwimmingDisciplines();

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                String resultBestTrainingTimeDate;
                String resultBestTrainingTime;
                String resultSwimmingDiscipline;
                for (int i = 0; i < 4; i++) {
                    resultBestTrainingTimeDate = formatter.format(bestTrainingTimeDate[0]) + ",";

                }
            }

            writer.write(result);
            writer.newLine();
            writer.close();
            System.out.println("Saved");
        }


    public ArrayList<Member> getAllMembers() {
        return members;
    }

    public int nextId() {
        return this.lastId + 1;
    }



    //public Member findMember(String firstName, String lastName);
    //public ArrayList<Division> getAllDivisions();
    //public Division getDivision(String divisionName);


    }