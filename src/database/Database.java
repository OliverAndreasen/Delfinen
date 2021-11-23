package database;

import domain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    FileHandler fileHandler = new FileHandler();
    ArrayList<Member> members = new ArrayList<> ();
    ArrayList<Team> teams = new ArrayList<>();

    public void loadMembers() throws FileNotFoundException {
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
    




    //public ArrayList<Member> getAllMembers() {}
    //public Member findMember(String firstName, String lastName);
    //public ArrayList<Division> getAllDivisions();
    //public Division getDivision(String divisionName);


}
