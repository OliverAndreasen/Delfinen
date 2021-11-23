package domain;

import java.util.ArrayList;

public class Accounting {
    private int subscriptionTotal;
    private ArrayList<Member> membersWithDebt = new ArrayList<>();

    //@Author Oliver
    public int calculateSubscriptionFee(Member member) {
        int result;
        if(member.getActiveStatus()){
            if(member.getAge() <= 18) {
                if(member.getAge() <= 60) {
                    result = 1200;
                }
                else {
                    result = 1600;
                }
            }
            else {
                result = 1000;
            }
        }
        else {
            result = 500;
        }
        return result;
    }

    public ArrayList getMembersWithDebt() {
        return membersWithDebt;
    }

    // @Author Oliver
    public String getMembersWithDebtToString(){
        StringBuilder result = new StringBuilder();
        if(!membersWithDebt.isEmpty()){
            for (Member member : membersWithDebt) {
                result.append("Navn: ");
                result.append(member.getName());
                result.append("\nResistance: ");
                result.append(calculateSubscriptionFee(member));
                result.append(" kr.\n");
            }
        }
        else {
            result = new StringBuilder("Ingen medlemmer med resistance");
        }
        return result.toString();
    }

    public int projectedSubscriptionTotal() {
        return -1;
    }

    public void addMemberWithDebt(Member member) {
        membersWithDebt.add(member);
    }

    public void removeMemberWithDebt(Member member) {
        membersWithDebt.remove(member);
    }
}
