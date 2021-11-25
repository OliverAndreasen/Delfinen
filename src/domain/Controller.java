package domain;

import database.Database;
import ui.UserInterface;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Controller {
    private UserInterface ui = new UserInterface();
    private Database db = new Database();
    private boolean programIsRunning = true;
    private Accounting accountant = new Accounting();
    private Scanner scanner = new Scanner(System.in);

    public String userInput(){
        return scanner.nextLine();
    }

    public int userInputNumber(){
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public void start(){
        ui.start();

        // Fetch members from database
        try {
            db.loadMembers();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    // competetition member?
                }

                case 5 -> {
                    ui.printCoachMenu();
                    coachController();
                }

            }
        }
    }

    public void chairManController() {

        while(programIsRunning) {
            switch (userInputNumber()) {
                case 1 -> {
                    // TODO optimer ux (print)
                    ui.printChairManAddMember();
                    addMember();

                }

                case 2 -> {
                    // TODO Skift medlems hold.
                }

                case 3 -> {
                    // TODO Skift medlemstype.
                }

                case 4 -> {
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
                }

                case 3 -> {
                    // TODO Afslut medlemsskab.
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
                    int total = calculateTotalSubscription();
                    ui.printTotalSubscription(total);
                }

                case 2 -> {
                    // TODO Tjek medlemmer i restance. - mangler load fra accounting.txt
                    ui.printDebtList(accountant.getMembersWithDebt());
                }

                case 3 -> {
                    ui.addMemberIdToDebtPrint();

                    Member memberToAddToDebt = getMemberToAddToDebt();
                    addMemberToDebtList(memberToAddToDebt);

                    ui.memberAddedToDebtPrint(memberToAddToDebt.getName());
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
        int nextId = db.nextIdMember();
        String name = userInput();
        int age = userInputNumber();

        String teamType = "";
        ui.chooseTeamType();
        switch (userInputNumber()) {
            case 1 -> {teamType = "Junior";}
            case 2 -> {teamType = "Senior";}
            case 3 -> {teamType = "Motionist";}
        }

        // choose between competition member & regular member.
        String chooseMemberType = userInput();
        switch (chooseMemberType) {
            case "y" -> {
                return new CompetitionMember(nextId, name, age, true, teamType, true);
            }
            case "n" -> {
                return new Member(nextId, name, age, true, teamType, true);
            }
        }
        return null;
    }


    /***** Accountant methods *****/
    public int calculateTotalSubscription() {
        return accountant.projectedSubscriptionTotal(db.getAllMembers());
    }

    public Member getMemberToAddToDebt() {
        int memberIdToGet = userInputNumber();
        return db.getMemberById(memberIdToGet);
    }

    public void addMemberToDebtList(Member member) {
        accountant.addMemberWithDebt(member);

        db.saveMemberToDebtList(member);
    }

    public void test() {

        for (Member member : db.getAllMembers()) {
            if (member instanceof CompetitionMember) {

                System.out.println(Arrays.toString(((CompetitionMember) member).getBestTrainingTimeDate()));

            }
            else {
                System.out.println(member);
            }
        }
    }


    // can change member from competion member to member or the other way.
    public void changeMember(){}

}

