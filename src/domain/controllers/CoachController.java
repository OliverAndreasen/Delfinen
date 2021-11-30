package domain.controllers;

import database.Database;
import ui.UserInterface;

public class CoachController {
    private UserInterface ui;
    private Database db;
    private boolean programIsRunning;

    public CoachController(UserInterface ui, Database db, boolean programIsRunning) {
        this.ui = ui;
        this.db = db;
        this.programIsRunning = programIsRunning;
    }

    public void start() {
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
}
