package domain;

import database.Database;
import database.FileHandler;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountingTest {

    @Test
    public void testCalculateSubscription(){
        //Arrange
        Database db = new Database();
        Accounting accounting = new Accounting();
        ArrayList<Member> members = db.getAllMembers();

        try {
            db.loadMemberIdsWithDebt();
        } catch (IOException e){

        }

        //Act
        for (Member member : members){
            accounting.addSubscriptionTotal(accounting.calculateSubscriptionFee(member.getActiveStatus(),member.getAge()));
        }

        Integer expected = 31100;

        //Assert
        assertEquals(expected, accounting.getSubscriptionTotal());
    }
}