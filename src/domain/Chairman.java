package domain;

import java.util.ArrayList;

public class Chairman {

    public ArrayList<Member> newMembers = new ArrayList<>();

    private boolean activeStatus;
    private String memberName;
    private int memberAge;
    private boolean competitive;;

    public Member addMember (Member newMember) {
        Member.add(newMember);
        return newMember;
    }



}

