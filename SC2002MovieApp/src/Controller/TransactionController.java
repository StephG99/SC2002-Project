package Controller;

import Entity.Transaction;
import Entity.Cinema;
import java.util.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

//Main booking logic is here.
public class TransactionController {

    // generate transaction ID
    public static String newTransactionID(Cinema chosenCinema) {
        String cinemaPrefix = new String();
        int cinemaID = chosenCinema.getCinemaID();
        if (cinemaID >= 1 && cinemaID <= 6) {
            cinemaPrefix = "YIS" + cinemaID + "X";
        } else if (cinemaID >= 7 && cinemaID <= 12) {
            cinemaPrefix = "JUR" + cinemaID + "X";
        } else {
            cinemaPrefix = "SRG" + cinemaID + "X";
        }
        // parse current instance of date & time into a string
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmm");
        String strDate = dateFormat.format(date);

        String transactionID = cinemaPrefix + strDate;
        return transactionID;
    }

    // add transaction
    public static void addTransaction(ArrayList<Transaction> TicketList, String transactionId, String email,
            int phoneNo, String name, int movieId, int cineplexId, int cinemaID, ArrayList<Integer> seatID, Date timing,
            float price) {
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

    // update transaction
    // this will solely be for updating whole transactions
    public static void updateTransaction(Transaction ticket, String transactionId, String email, int phoneNo,
            String name, int movieId, int cineplexId, int cinemaID, ArrayList<Integer> seatID, Date timing,
            float price) {
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
    public static void changeSeat(Transaction ticket, ArrayList<Integer> seatID) {
        ticket.setSeatID(seatID);
    }

    public static ArrayList<Transaction> getAllTransactions() {
        return DatabaseController.getAllTransactions();
    }

}
