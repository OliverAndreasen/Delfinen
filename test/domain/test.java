package domain;

import database.Database;
import java.io.IOException;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) throws IOException {
        Database db = new Database();
        db.loadMembers();
        ArrayList<CompetitionMember> competitionMembers = db.getAllCompetitionMembers();

        for (int i = 0; i < 2; i++) {
            System.out.println(competitionMembers.get(i).toString());
        }
        /*
        Member member = new Member(5,"Peter Pedal", 99, true, "Motionist");
        db.saveMember(member);
        db.loadMembers();

        for (int i = 0; i < members.size(); i++) {
            System.out.println(members.get(i).toString());
        }
        */

        System.out.println(db.nextIdMember());

    }
}
