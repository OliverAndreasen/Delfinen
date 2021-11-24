package domain;

import database.Database;
import ui.UserInterface;

import java.io.IOException;
import java.util.Scanner;

public class Controller {
    private UserInterface ui = new UserInterface();
    private Database db = new Database();
    private boolean programIsRunning = true;

    Scanner scanner = new Scanner(System.in);

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
                    // TODO Tilføj nyt medlem.
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
                    // TODO Tjek forventet kontingent indkomst.
                    calculateTotalSubscription();
                }

                case 2 -> {
                    // TODO Tjek medlemmer i restance.
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

    /***** Chair man methods. ******/
    public void addMember() {
        Member member = createMember();

        try {
            db.saveMember(member);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Member createMember() {
        int nextId = db.nextId();
        String name = userInput();
        int age = userInputNumber();

        String teamType = "";
        ui.chooseTeamType();
        switch (userInputNumber()) {
            case 1 -> {teamType = "Junior";}
            case 2 -> {teamType = "Senior";}
            case 3 -> {teamType = "Motionist";}
        }
        return new Member(nextId, name, age, true, teamType);
    }


    /***** Accountant methods *****/
    public void calculateTotalSubscription() {
        Accounting accounting = new Accounting();
        int total = accounting.projectedSubscriptionTotal(db.getAllMembers());
        ui.printTotalSubscription(total);
    }


    // can change member from competion member to member or the other way.
    public void changeMember(){}

}

