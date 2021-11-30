package domain.controllers;

import database.Database;
import domain.Accounting;
import domain.Member;
import ui.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

public class AccountantController {
    private final boolean programIsRunning;
    private UserInterface ui;
    private Database db;
    private Accounting accountant;

    public AccountantController(UserInterface ui, Database db, Accounting accountant, boolean programIsRunning) {
        this.ui = ui;
        this.db = db;
        this.accountant = accountant;
        this.programIsRunning = programIsRunning;
    }

    public void start() {
        while (programIsRunning) {
            int input = ui.userInputNumber();
            switch (input) {
                case 1 -> {
                    // update this
                    // int total = calculateTotalSubscription();
                    //ui.printTotalSubscription(total);
                    break;
                }

                case 2 -> {
                    // TODO Tjek medlemmer i restance. - mangler load fra accounting.txt
                    ui.printMembersWithDebt(membersWithDebtToString());
                    break;
                }

                case 3 -> {
                    /*
                    ui.addMemberIdToDebtPrint();

                    Member memberToAddToDebt = getMemberToAddToDebt();
                    addMemberToDebtList(memberToAddToDebt);

                    ui.memberAddedToDebtPrint(memberToAddToDebt.getName());

                     */
                }

            }
            ui.printAccountantMenu();

        }
    }

    public String membersWithDebtToString() {
        StringBuilder result = new StringBuilder();
        for (Member member : getMembersWithDebt()) {
            result.append("Medlemsnavn: ").append(member.getName()).append("\n");
            result.append("Resistance: ").append(accountant.calculateSubscriptionFee(member.getActiveStatus(), member.getAge())).append("\n");
        }
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

    public int addSubscriptionTotal(int memberFee) {
        accountant.addSubscriptionTotal(memberFee);
        return accountant.getSubscriptionTotal();
    }

    public Member getMemberToAddToDebt() {
        int memberIdToGet = ui.userInputNumber();
        return db.getMemberById(memberIdToGet);
    }

    /* Update this
    public int calculateTotalSubscription() {
        return accountant.projectedSubscriptionTotal(db.getAllMembers());
    }
    */

    /*  Update this
    public void addMemberToDebtList(Member member) {
        accountant.addMemberWithDebt(member);
        db.saveMemberToDebtList(member);
    }
    */
}

