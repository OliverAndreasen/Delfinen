package domain;

import database.Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) throws IOException {
        Database db = new Database();
        Accounting accounting = new Accounting();
        db.loadMembers();
        ArrayList<Member> members = db.getAllMembers();
        db.setMembersWithDebt();
        /*
        ArrayList<Integer> memberIdsWithDebt = db.getMemberIdsWithDebt();
        for (int i = 0; i < memberIdsWithDebt.size(); i++) {
            db.saveMemberIdWithDebt(memberIdsWithDebt.get(i));
        }
         */

        ArrayList<Integer> memberIdsWithDebt = db.loadMemberIdsWithDebt();

        System.out.println(Arrays.toString(new ArrayList[]{memberIdsWithDebt}));
        ArrayList<Member> getMembersWithDebt = new ArrayList<>();
        for (int i = 0; i < memberIdsWithDebt.size(); i++) {
            getMembersWithDebt.add(db.getMemberById(memberIdsWithDebt.get(i)));
        }
        System.out.println(Arrays.toString(new ArrayList[]{getMembersWithDebt}));


        for (Member member : getMembersWithDebt) {
            accounting.addTotaltDebt(accounting.calculateSubscriptionFee(member.getActiveStatus(), member.getAge()));
        }

        /*for (Member member : getMembersWithDebt) {
            System.out.println(member.getName());
            System.out.println("Resistance: " + accounting.calculateSubscriptionFee(member.getActiveStatus(), member.getAge()));
        }*/

        System.out.println("Total debt: " + accounting.getTotaltDebt());


    }

}



