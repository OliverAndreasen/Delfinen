package domain.controllers;

import database.Database;
import domain.Member;
import ui.UserInterface;

public class MemberController {
    private UserInterface ui;
    private Database db;
    private boolean programIsRunning;

    public MemberController(UserInterface ui, Database db, boolean programIsRunning) {
        this.ui = ui;
        this.db = db;
        this.programIsRunning = programIsRunning;
    }

    public void start() {
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

    private void changeActiveStatus() {
        int memberId = ui.userInputNumber();
        Member member = db.getMemberById(memberId);
        toggleMemberActiveStatus(member);
        db.overWriteAndSaveFile();
    }

    private void toggleMemberActiveStatus(Member member) {
        member.setActiveStatus(!member.getActiveStatus());
    }
}
