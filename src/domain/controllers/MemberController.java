package domain.controllers;

import domain.Member;

public class MemberController extends Controller{

    public void startMember() {
        ui.printMemberMenu();
        while (programIsRunning) {
            switch (ui.userInputNumber()) {
                case 1 -> {
                    // TODO Betal kontingent.
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
                }
                case 3 -> {
                    // TODO Afslut medlemskab.
                }
                case 4 -> {
                    // TODO Tilmeld konkurrence.
                }
                case 0 -> {
                    start();
                }
                default -> {
                    int min = 0;
                    int max = 4;
                    ui.printInvalidNumber(min, max);
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
