package domain;

import database.Database;
import ui.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Controller {
    private UserInterface ui = new UserInterface();
    private Database db = new Database();
    private boolean programIsRunning = true;
    private Accounting accountant = new Accounting();
    private Scanner scanner = new Scanner(System.in);

    public String userInput() {
        return scanner.nextLine();
    }

    public int userInputNumber() {
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public void start() {
        ui.start();
        // Fetch members from database
        while (programIsRunning) {
            switch (userInputNumber()) {
                case 1 -> {
                    ui.printChairmanMenu();
                    chairManController();
                }

                case 2 -> {
                    ui.printAccountantMenu();
                    accountantController();
                }

                case 3 -> {
                    ui.printMemberMenu();
                    memberController();
                }

                case 4 -> {
                    // TODO
                    // competition member?
                }

                case 5 -> {
                    ui.printCoachMenu();
                    coachController();
                }

            }
        }
    }

    public void chairManController() {

        while (programIsRunning) {
            switch (userInputNumber()) {
                case 1 -> {
                    // TODO optimer ux (print)
                    ui.printChairManAddMember();
                    addMember();

                }

                case 2 -> {
                    // TODO Slet medlem.
                }
            }
        }
    }

    public void memberController() {
        while (programIsRunning) {
            switch (userInputNumber()) {
                case 1 -> {
                    // TODO Betal kontingent.
                    test();
                }

                case 2 -> {
                    // TODO Skift medlemsstatus.
                    CompetitionMember member = new CompetitionMember(110, "Kristian", 69, true, "Konkurrencesvømmer", true);
                    member.convertStringDateToDate(userInput());

                }

                case 3 -> {
                    // TODO Afslut medlemskab.
                }

                case 4 -> {
                    // TODO Tilmeld konkurrence.
                }

            }
        }
    }

    public void accountantController() {
        while (programIsRunning) {
            switch (userInputNumber()) {
                case 1 -> {
                    // update this
                    // int total = calculateTotalSubscription();
                    //ui.printTotalSubscription(total);
                }

                case 2 -> {
                    // TODO Tjek medlemmer i restance. - mangler load fra accounting.txt
                    //ui.printDebtList(accountant.getMembersWithDebt());
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
        }
    }

    public void coachController() {

        while (programIsRunning) {
            switch (userInputNumber()) {
                case 1 -> {
                    // TODO Tilføj konkurrencesvømmer til hold.
                }

                case 2 -> {
                    // TODO Tilføj konkurrencesvømmer bedste tid.
                }

                case 3 -> {
                    // TODO Vis top 5.
                    db.getAllCompetitionMembers();
                }
            }
        }

    }

    /***** Chairman methods. ******/
    public void addMember() {
        Member member = createMember();

        try {
            db.saveMember(member);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Member createMember() {
        int nextId;
        String name = userInput();
        int age = userInputNumber();

        String teamType = "";
        ui.chooseTeamType();
        switch (userInputNumber()) {
            case 1 -> teamType = "Junior";
            case 2 -> teamType = "Senior";
            case 3 -> teamType = "Motionist";
            case 4 -> teamType = "Konkurrencesvømmer";
        }
        // create regular or competition member
        if (teamType.equals("Konkurrencesvømmer")) {
            nextId = db.nextIdCompetitionMember();
            return new CompetitionMember(nextId, name, age, true, teamType, true);
        } else {
            nextId = db.nextIdMember();
            return new Member(nextId, name, age, true, teamType, true);
        }
    }

    /***** Accountant methods *****/
    /* Update this
    public int calculateTotalSubscription() {
        return accountant.projectedSubscriptionTotal(db.getAllMembers());
    }

     */

    public Member getMemberToAddToDebt() {
        int memberIdToGet = userInputNumber();
        return db.getMemberById(memberIdToGet);
    }
/* // Update this
    public void addMemberToDebtList(Member member) {
        accountant.addMemberWithDebt(member);

        db.saveMemberToDebtList(member);
    }

 */

    public void test() {
        for (Member member : db.getAllMembers()) {
            if (member instanceof CompetitionMember) {
                if (((CompetitionMember) member).getBestTrainingTimeDates() != null) {
                    System.out.println(Arrays.toString(((CompetitionMember) member).bestTrainingTimeDatesToString()));
                    for (int i = 0; i < ((CompetitionMember) member).bestTrainingTimeDatesToString().length; i++) {
                        System.out.println(((CompetitionMember) member).getDateById(i));

                    }
                }
            } else {
                System.out.println(member);
            }
        }
    }

    public void setMembersWithDebt() throws IOException {
        accountant.setMemberIdsWithDebt(db.loadMemberIdsWithDebt());
    }

    public Member getMemberById(int memberId) {
        return db.getMemberById(memberId);
    }

    public ArrayList<Member> getMembersWithDebt() {
        ArrayList<Integer> memberIdsWithDebt = db.getMemberIdsWithDebt();
        ArrayList<Member> membersWithDebt = new ArrayList<>();
        for (int i = 0; i < memberIdsWithDebt.size(); i++) {
            membersWithDebt.add(getMemberById(memberIdsWithDebt.get(i)));
        }
        return membersWithDebt;
    }

    public int calculateSubscriptionFee(boolean activeStatus, int age){
        return accountant.calculateSubscriptionFee(activeStatus, age);
    }

    public int addSubscriptionTotal(int memberFee) {
        accountant.addSubscriptionTotal(memberFee);
        return accountant.getSubscriptionTotal();
    }

    public String membersWithDebtToString(){
        String result = "";
        for (Member member : db.getMembersWithDebt()) {
            result +=  "Medlemsnavn: " + member.getName() + "\n";
            result += "Resistance: " + accountant.calculateSubscriptionFee(member.getActiveStatus(), member.getAge()) + "\n";
        }
        return result;
    }





    // can change member from competion member to member or the other way.
    public void changeMember() {
    }

}

