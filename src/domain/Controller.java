package domain;

import database.Database;
import ui.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    private UserInterface ui = new UserInterface();
    private Database db = new Database();
    private boolean programIsRunning = true;
    private Accounting accountant = new Accounting();


    public void start() {
        ui.start();
        // Fetch members from database
        while (programIsRunning) {
            switch (ui.userInputNumber()) {
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
                    ui.printCoachMenu();
                    coachController();
                }

            }
        }
    }

    public void chairManController() {

        while (programIsRunning) {
            switch (ui.userInputNumber()) {
                case 1 -> {
                    ui.printChairManAddMember();
                    try {
                        addMember();
                        ui.printString("Medlem oprettet");
                    } catch (Exception e) {
                        ui.printErrorMessage();
                    }
                }

                case 2 -> {
                    ui.printChairManDeleteMember();
                    try {
                        String nameToBeDeleted = findAndDeleteMember();
                        ui.printMemberDeleted(nameToBeDeleted);
                    } catch (Exception e) {
                        ui.printErrorMessage();
                    }
                }
            }
        }
    }

    private String findAndDeleteMember() {
        int memberId = ui.userInputNumber();
        Member memberExists = checkIfMemberExists(memberId);

        if (memberExists != null) {
            deleteMember(memberExists);
            return memberExists.getName();
        }
        return null;
    }

    public void memberController() {
        while (programIsRunning) {
            switch (ui.userInputNumber()) {
                case 1 -> {
                    // TODO Betal kontingent.
                    /*test();*/
                }

                case 2 -> {
                    // TODO Skift medlemsstatus.
                    ui.printChangeActiveStatus();
                    ui.printString("MedlemsID: ");
                    try {
                        changeActiveStatus();
                        ui.printString("Status ændret.");
                    } catch (Exception e) {
                        ui.printErrorMessage();
                    }



                    /*CompetitionMember member = new CompetitionMember(110, "Kristian", 69, true, "Konkurrencesvømmer", true);
                    member.convertStringDateToDate(ui.userInput());*/

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
            switch (ui.userInputNumber()) {
                case 1 -> {
                    // update this
                    // int total = calculateTotalSubscription();
                    //ui.printTotalSubscription(total);
                }

                case 2 -> {
                    // TODO Tjek medlemmer i restance. - mangler load fra accounting.txt
                    ui.printMembersWithDebt(membersWithDebtToString());
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
            switch (ui.userInputNumber()) {
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

    /*********** Chairman methods. **********/
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

        ui.printString("Name: ");
        String name = ui.userInput();

        ui.printString("Age: ");
        int age = ui.userInputNumber();

        String teamType = "";
        ui.chooseTeamType();
        switch (ui.userInputNumber()) {
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

    public void deleteMember(Member member) {
        db.deleteMember(member);
    }

    public Member checkIfMemberExists(int memberId) {
        for (Member member : db.getAllMembers()) {
            if (memberId == member.getMemberId()) {
                return member;
            }
        }
        return null;
    }
    /***** Accountant methods *****/
    /* Update this
    public int calculateTotalSubscription() {
        return accountant.projectedSubscriptionTotal(db.getAllMembers());
    }

     */

    public Member getMemberToAddToDebt() {
        int memberIdToGet = ui.userInputNumber();
        return db.getMemberById(memberIdToGet);
    }
/* // Update this
    public void addMemberToDebtList(Member member) {
        accountant.addMemberWithDebt(member);
        db.saveMemberToDebtList(member);
    }
 */

    private void changeActiveStatus() {
        int memberId = ui.userInputNumber();
        Member member = db.getMemberById(memberId);

        toggleMemberActiveStatus(member);
        db.overWriteAndSaveFile();
    }

    private void toggleMemberActiveStatus(Member member) {
        if (member.getActiveStatus()) {
            member.setActiveStatus(false);
        } else {
            member.setActiveStatus(true);
        }

    }

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
        for (Member member : getMembersWithDebt()) {
            result +=  "Medlemsnavn: " + member.getName() + "\n";
            result += "Resistance: " + accountant.calculateSubscriptionFee(member.getActiveStatus(), member.getAge()) + "\n";
        }
        return result;
    }



    /// can change member from competion member to member or the other way.
    public void changeMember() {
    }

}

