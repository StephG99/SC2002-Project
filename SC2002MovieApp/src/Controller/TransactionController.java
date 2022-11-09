package Controller;

import Entity.Transaction;
import java.util.*;
//Main booking logic is here.
public class TransactionController {
    public static void bookShow(int movieId,int cinemaID,Date bookingDate,double price,ArrayList<Integer> seatIDs){

    }
    // add transaction
    public static void addTransaction(ArrayList<Transaction> TicketList, String transactionId, String email,
            int phoneNo, String name, int movieId, int cineplexId, int cinemaID, ArrayList<Integer> seatID, Date timing, float price) {
        Transaction ticket = new Transaction(transactionId, email, phoneNo, name, movieId, cineplexId, cinemaID, seatID,
                timing, price);
        TicketList.add(ticket);
    }

    // remove transaction (optional, may not be necessary because all transactions
    // are already locked in)
    public static void removeTransaction(ArrayList<Transaction> TicketList, String transactionId) {
        boolean exists = false;
        for (Transaction ticket : TicketList) {
            if (ticket.getTransactionId().equals(transactionId)) {
                exists = true;
                TicketList.remove(ticket);
                System.out.println("Ticket cancelled. Transaction removed from database.");
                break;
            }
        }
        if (!exists) {
            System.out.println("Transaction not found.");
        }
    }

    // get single transaction entry
    public static Transaction getTransaction(ArrayList<Transaction> TicketList, String transactionId) {
        for (Transaction ticket : TicketList) {
            if (ticket.getTransactionId().equals(transactionId)) {
                return ticket;
            }
        }
        return null;
    }

    // update transaction
    // this will solely be for updating whole transactions
    public static void updateTransaction(Transaction ticket, String transactionId, String email, int phoneNo,
            String name, int movieId, int cineplexId, int cinemaID, int seatID, Date timing, float price) {
        ticket.setTransactionId(transactionId);
        ticket.setEmail(email);
        ticket.setPhoneNo(phoneNo);
        ticket.setName(name);
        ticket.setMovieId(movieId);
        ticket.setCineplexId(cineplexId);
        ticket.setCinemaID(cinemaID);
        ticket.setSeatID(seatID);
        ticket.setTiming(timing);
        ticket.setPrice(price);
    }

    // change seat for single ticket
    // realistically, only this situation will be required, but above function kept
    // for reference
    public static void changeSeat(Transaction ticket, int seatID) {
        ticket.setSeatID(seatID);
    }

    // print single transaction
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
        System.out.println("Price: " + ticket.getPrice());
    }

    // print all transactions
    public void printAllTransactions(ArrayList<Transaction> TicketList) {
        for (Transaction ticket : TicketList) {
            printSingleTransaction(ticket);
            System.out.println();
        }
    }

    // print all transactions by customer
    public void printTransactionsByCustomer(ArrayList<Transaction> TicketList, String email) {
        boolean exists = false;
        for (Transaction ticket : TicketList) {
            if (ticket.getEmail().equals(email)) {
                exists = true;
                printSingleTransaction(ticket);
                System.out.println();
            }
        }
        if (!exists) {
            System.out.println("No transactions for this user exist.");
        }
    }

    // simplified view, print only transaction IDs under a single customer
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

}
