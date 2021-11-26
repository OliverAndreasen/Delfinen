package domain;

import java.util.ArrayList;

public class Accounting {
    private int subscriptionTotal;
    private ArrayList<Integer> memberIdsWithDebt;

    public int getSubscriptionTotal() {
        return subscriptionTotal;
    }

    public void addSubscriptionTotal(int memberFee){
        this.subscriptionTotal = subscriptionTotal+memberFee;
    }

    public void subtractSubscriptionTotal(int memberFee){
        this.subscriptionTotal = subscriptionTotal-memberFee;
    }

    //@Author Oliver
    public int calculateSubscriptionFee(boolean activeStatus, int age) {
        int result;
        if (activeStatus) {
            if (age <= 18) {
                if (age <= 60) {
                    result = 1200;
                } else {
                    result = 1600;
                }
            } else {
                result = 1000;
            }
        } else {
            result = 500;
        }
        return result;
    }

    public ArrayList<Integer> getMemberIdsWithDebt() {
        return memberIdsWithDebt;
    }

    public void setMemberIdsWithDebt(ArrayList<Integer> membersWithDebt) {
        this.memberIdsWithDebt = membersWithDebt;
    }

    /*
    // @Author Oliver
    public String getMembersWithDebtToString() {
        StringBuilder result = new StringBuilder();
        if (!memberIdsWithDebt.isEmpty()) {
            for (Integer memberIds : memberIdsWithDebt) {
                result.append("Navn: ");
                result.append(member.getName());
                result.append("\nResistance: ");
                result.append(calculateSubscriptionFee(member));
                result.append(" kr.\n");
            }
        } else {
            result = new StringBuilder("Ingen medlemmer med resistance");
        }
        return result.toString();
    }
     */
}
