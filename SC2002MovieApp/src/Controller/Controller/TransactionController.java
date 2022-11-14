package Controller;

import Entity.Transaction;
import Entity.User;

import java.util.*;





//Main booking logic is here.
public class TransactionController {

    /**
     * @return All Transaction from database.
     */
    public static ArrayList<Transaction> getAllTransactions() {
        return DatabaseController.getAllTransactions();
    }

    /**
     * @param user User object to check whether if user is admin
     * @return a array of Transaction that the user is allow to view.
     */
    public static ArrayList<Transaction> getTransactions(User user){
        
        ArrayList<Transaction> ticketList = getAllTransactions();
        ArrayList<Transaction> resultList = new ArrayList<Transaction>();
        if(!user.checkAdmin()){
        for (Transaction ticket : ticketList) {
            if (ticket.getEmail().equals((user.getEmail()))) {
                resultList.add(ticket);
                
            }
        }
    }
    else{
        resultList = ticketList;
    }
    return resultList;
        
    }

}
