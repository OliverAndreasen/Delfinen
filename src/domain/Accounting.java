package domain;

import java.util.ArrayList;

public class Accounting {
    private int subscriptionTotal;
    private int totaltDebt;
    private ArrayList<Integer> memberIdsWithDebt;

    public Accounting() {
        this.subscriptionTotal = 0;
        this.totaltDebt = 0;
    }

    public int getTotaltDebt() {
        return totaltDebt;
    }

    public void addTotaltDebt(int addDebt) {
        this.totaltDebt = getTotaltDebt() + addDebt;
    }

    public int getSubscriptionTotal() {
        return subscriptionTotal;
    }

    public void addSubscriptionTotal(int memberFee) {
        this.subscriptionTotal = subscriptionTotal + memberFee;
    }

    public void subtractSubscriptionTotal(int memberFee) {
        this.subscriptionTotal = subscriptionTotal - memberFee;
    }

    public int calculateSubscriptionFee(boolean activeStatus, int age) {
        int result;
        if (activeStatus) {
            if (age >= 18) {
                if (age >= 60) {
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
}
