package domain;

import java.util.ArrayList;

public class AgeGroup {
    private String name;
    private ArrayList<Member> ageGroups = new ArrayList<>();


    public void addMemberToAgeGroup(Member member){
        ageGroups.add(member);
    }

    public void removeMemberFromAgeGroup(Member member) {
        ageGroups.remove(member);
    }

}
