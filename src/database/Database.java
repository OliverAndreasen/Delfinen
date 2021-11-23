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
        String fileName = "data/Members.txt";
        Scanner sc = fileHandler.reader(fileName);
        sc.useDelimiter(";");
        while (sc.hasNextLine()) {
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

            Member member = new Member(memberId, name, age, activeStatus, teamType);
            members.add(member);
        }
    }

        public void saveMember(Member member) throws IOException {
            BufferedWriter writer = fileHandler.writer("data/Members.txt");
            for (int i = 0; i < members.size(); i++) {
                String result = "";
                result += members.get(i).getMemberId();
                result += ";";
                result += members.get(i).getName();
                result += ";";
                result += members.get(i).getAge();
                result += ";";
                result += members.get(i).getActiveStatus();
                result += ";";
                result += members.get(i).getTeamType();
                result += ";";
                writer.write(result);
                writer.newLine();
            }
            writer.close();
            System.out.println("Saved");
        }


    //public ArrayList<Member> getAllMembers() {}
    //public Member findMember(String firstName, String lastName);
    //public ArrayList<Division> getAllDivisions();
    //public Division getDivision(String divisionName);


    }