package domain;

import database.Database;

import java.util.ArrayList;

public class Coach {
    private String name;
    private String ageGroup;
    private CompetitionMember competitionMember;
    private final Database db = new Database();
    ArrayList<Member> members = db.getAllMembers();
    

   /* public ArrayList top5FromDivision(String Division) throws IOException {
        String [] bestTimes = new String[4];
        for (Member member : members) {
            if (member instanceof CompetitionMember){
                bestTimes = ((CompetitionMember) member).getBestTrainingTimes();
            }
        }
        System.out.println(Arrays.toString(bestTimes));

        return ;
    }*/

    private void addCompetitionMemberBestTime(CompetitionMember competitionMember) {
    }

}
