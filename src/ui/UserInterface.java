package ui;

import java.util.Scanner;

public class UserInterface {

    public void start() {
        System.out.println("""
                Login som:
                    1. Formand
                    2. Kasserer
                    3. Medlemmer
                    4. Konkurrencesvømmer
                    5. Træner
                """);
    }

    public void printCoachMenu() {
        System.out.println("""
                1. Tilføj konkurrencesvømmer til hold.
                2. Tilføj konkurrencesvømmer bedste tid.
                3. Vis top 5.
                """);
    }

    public void printTop5Lists(){
        System.out.println("""
                Vælg en disciplin:
                    1. Butterfly
                    2. Crawl, 
                    3. Rygcrawl og 
                    4. Brystsvømning
                """);
    }

    public void printMemberMenu() {
        System.out.println("""
                1. Betal kontingent.
                2. Skift medlemsstatus.
                3. Afslut medlemsskab.
                4. Tilmeld konkurrence.
                """);
    }

    public void printAccountantMenu(){
        System.out.println("""
                1. Tjek forventet kontingent indkomst.
                2. Tjek medlemmer i restance.
                """);
    }

    public void printTotalSubscription(int total) {
        System.out.printf("Den forventede indkomst af alle kontigenter er: %d.-" , total);
    }



    public void printChairmanMenu(){
            System.out.println("""
                1. Tilføj nyt medlem.
                2. Skift medlems hold.
                3. Skift medlemstype.
                4. Slet medlem.
                """);
        }


    public void printChairManAddMember() {
        // noget test
/*      String[] arr = {"Indtast navn:", "Indtast alder:", "Vælg status:", "Vælg hold:"};

        for (String str : arr) {
            System.out.println(str);
        }*/

        System.out.println("""
                Tilføj nyt medlem.
                    Indtast navn:
                    Indtast alder:
                    Vælg status:
                    Vælg hold:
                """);
    }

    public void chooseTeamType() {
        System.out.println("""
                \tVælg hold:
                    1. Junior
                    2. Senior
                    3. Motionist
                """);
    }

}




