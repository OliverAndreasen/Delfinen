package database;

import domain.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    FileHandler fileHandler = new FileHandler();
    ArrayList<Member> members = new ArrayList<> ();
    ArrayList<Team> teams = new ArrayList<>();



    public void loadMembers() throws IOException {
        members.clear();
        String fileName = "data/Members.txt";
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            int memberId = Integer.parseInt(sc.next());
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
            sc.nextLine();

            Member member = new Member(memberId, name, age, activeStatus, teamType);
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

            writer.write(result);
            writer.newLine();
            writer.close();
            System.out.println("Saved");
        }


    public ArrayList<Member> getAllMembers() {
        return members;
    }
    //public Member findMember(String firstName, String lastName);
    //public ArrayList<Division> getAllDivisions();
    //public Division getDivision(String divisionName);


    }