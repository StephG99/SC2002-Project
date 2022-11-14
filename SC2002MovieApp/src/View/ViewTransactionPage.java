package View;

import Helper.Helper;
import java.util.*;
import Entity.*;
import Controller.TransactionController;

public class ViewTransactionPage {
    // print single transaction
    /**
     * @param ticket ticket object to be printed
     * print the tickets detail
     */
    public static void printSingleTransaction(Transaction ticket) {
        System.out.println("Transaction ID: " + ticket.getTransactionId());
        System.out.println("User Email: " + ticket.getEmail());
        System.out.println("Phone No.: " + ticket.getPhoneNo());
        System.out.println("Name: " + ticket.getName());
        System.out.println("Movie ID: " + ticket.getMovieId());
        System.out.println("Cineplex ID: " + ticket.getCineplexId());
        System.out.println("Cinema ID: " + ticket.getCinemaID());
        System.out.println("Seat ID: " + ticket.getSeatID());
        System.out.println("Session Time: " + ticket.getTiming());
        System.out.println("Price: $" + ticket.getPrice());
    }

    // print all transactions

    /**
     * @param TicketList an array of ticket to be printed
     * prints all ticket details.
     */
    public static void printAllTransactions(ArrayList<Transaction> TicketList) {
        for (Transaction ticket : TicketList) {
            printSingleTransaction(ticket);
            System.out.println();
        }
        if(TicketList.size() == 0){
            System.out.println("No transaction record found");
        }
    }

    // print all transactions by customer
    

    // simplified view, print only transaction IDs under a single customer
    /* 
    public static void printTicketList(ArrayList<Transaction> TicketList, String email) {
        boolean exists = false;
        for (Transaction ticket : TicketList) {
            if (ticket.getEmail().equals(email)) {
                exists = true;
                System.out.println(ticket.getTransactionId());
            }
        }
        if (!exists) {
            System.out.println("No transactions for this user exist.");
        }
    }
    */
/* 
    // print single transaction by transaction ID
    public void printTransactionByID(ArrayList<Transaction> TicketList, String transactionId) {
        boolean exists = false;
        for (Transaction ticket : TicketList) {
            if (ticket.getTransactionId().equals(transactionId)) {
                exists = true;
                printSingleTransaction(ticket);
                break;
            }
        }
        if (!exists) {
            System.out.println("Transaction ID does not exist.");
        }
    }
    */
/* 
    // get single transaction entry
    public static Transaction getTransaction(ArrayList<Transaction> TicketList, String transactionId) {
        for (Transaction ticket : TicketList) {
            if (ticket.getTransactionId().equals(transactionId)) {
                return ticket;
            }
        }
        return null;
    }
    */

    // page controls
    /**
     * @param loginUser user object will be tested whether is a admin or not
     * print all transaction associated with the user.
     */
    public static void TxnPage(User loginUser) {
        ArrayList<Transaction> TicketList = TransactionController.getTransactions(loginUser);

        if (loginUser.checkAdmin()) {
            Helper.line(80, "=");
            System.out.println("All Users Booking History");
            Helper.line(80, "=");
            
            
        } else {
            Helper.line(80, "=");
            System.out.println("Your Booking History");
            Helper.line(80, "=");
            
        }
        printAllTransactions(TicketList);

    }
}