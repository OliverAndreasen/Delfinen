package domain.controllers;

import domain.Member;
import java.io.IOException;
import java.util.ArrayList;

public class AccountantController extends Controller{

    public void startAccounting()  {
        ui.printAccountantMenu();

        try {
        db.loadMemberIdsWithDebt();
        }
        catch (IOException e){

        }
        while (programIsRunning) {
            int input = ui.userInputNumber();
            switch (input) {
                case 1 -> {
                    calculateSubscriptionTotal();
                    ui.printTotalSubscription(accountant.getSubscriptionTotal());
                    break;
                }
                case 2 -> {
                    ui.printMembersWithDebt(membersWithDebtToString());
                    break;
                }
                case 3 -> {
                    ui.addMemberIdToDebtPrint();
                    Member memberToAddToDebt = getMemberToAddToDebt();
                    addMemberToDebtList(memberToAddToDebt);
                    ui.memberAddedToDebtPrint(memberToAddToDebt.getName());
                }
                case 0 ->{
                    start();
                }
                default -> {
                    int min = 0;
                    int max = 3;
                    ui.printInvalidNumber(min, max);
                }
            }
            ui.printAccountantMenu();
        }
    }

    private void addMemberToDebtList(Member memberToAddToDebt) {
        int memberId = memberToAddToDebt.getMemberId();
        db.addMemberIdToDebt(memberId);

        try {
            db.saveMemberIdWithDebt(memberId);
        } catch (IOException e) {

        }
    }

    public String membersWithDebtToString() {
        StringBuilder result = new StringBuilder();
        int totalResistance = 0;
        for (Member member : getMembersWithDebt()) {
            result.append("Medlemsnavn: ").append(member.getName()).append("\n");
            result.append("Resistance: ").append(accountant.calculateSubscriptionFee(member.getActiveStatus(), member.getAge())).append(" kr.\n");
            totalResistance += accountant.calculateSubscriptionFee(member.getActiveStatus(), member.getAge());
        }
        result.append("Total resistance: " + totalResistance + "\n");
        return result.toString();
    }

    public ArrayList<Member> getMembersWithDebt() {
        ArrayList<Integer> memberIdsWithDebt = db.getMemberIdsWithDebt();
        ArrayList<Member> membersWithDebt = new ArrayList<>();
        for (Integer memberId : memberIdsWithDebt) {
            membersWithDebt.add(getMemberById(memberId));
        }
        return membersWithDebt;
    }

    public Member getMemberById(int memberId) {
        return db.getMemberById(memberId);
    }

    public void setMembersWithDebt() throws IOException {
        accountant.setMemberIdsWithDebt(db.loadMemberIdsWithDebt());
    }

    public int calculateSubscriptionFee(boolean activeStatus, int age) {
        return accountant.calculateSubscriptionFee(activeStatus, age);
    }

    public void addSubscriptionTotal(int memberFee) {
        accountant.addSubscriptionTotal(memberFee);
    }

    public Member getMemberToAddToDebt() {
        int memberIdToGet = ui.userInputNumber();
        return db.getMemberById(memberIdToGet);
    }

    public void calculateSubscriptionTotal() {
        ArrayList<Member> allMembers = db.getAllMembers();
        for (Member member : allMembers) {
            addSubscriptionTotal(calculateSubscriptionFee(member.getActiveStatus(), member.getAge()));
        }
    }
}

