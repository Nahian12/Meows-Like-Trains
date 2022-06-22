import java.util.Scanner;
import java.util.PriorityQueue;

class TransactionTicket implements Comparable<TransactionTicket> {

    /* Initialize the main components of the transaction. (Time, Ticket ID, and Tier)
     * Use Long object and not primitive type (long) in order to access the compareTo method
     * in Long as it will then be used to compare Tiers and to prioritize which transaction ticket
     * goes first. */

    String txn_id, transactionTier;
    Long startingTime;
    long Time;

    public TransactionTicket(long Time, String txn_id, String transactionTier) {
        this.Time = Time;
        this.txn_id = txn_id;
        this.transactionTier = transactionTier;
        getTierStartingTime();
    }

    public Long getTime() {
        return Time;
    }

    public Long getStartingTime() {
        return startingTime;
    }

    /** To get each transaction ticket time without the starting time which corresponds to
     * the tiers */
    public void getTierStartingTime() {
        if(transactionTier.equals("PLATINUM")) {
            startingTime = Time - 3000;
        }
        else if(transactionTier.equals("GOLD")) {
            startingTime = Time - 2000;
        }
        else if(transactionTier.equals("SILVER")) {
            startingTime = Time - 1000;
        }
        else if(transactionTier.equals("BRONZE")) {
            startingTime = Time;
        }
    }
    /** Used in order to print out the transaction */
    @Override
    public String toString() {
        return txn_id + " ";
    }

    /** compareTo method here is used to compare between 2 transactions,
     the parameters given in the if statement checks whether their starting time (Tier) is the same.
     If it is then it does another comparison with the Time to see which should be prioritized first.
     And if the value equals to 1 it returns the value of whichever transaction ticket is priority.
     */
    @Override
    public int compareTo(TransactionTicket o) {
        if(this.getStartingTime().compareTo(o.getStartingTime()) == 0) {
            return this.getTime().compareTo(o.getTime());
        }
        else {
            return this.getStartingTime().compareTo(o.getStartingTime());
        }
    }

}

public class Payment {
    public static void main(String[] args) {

        PriorityQueue<TransactionTicket> transactionQueue = new PriorityQueue<>();
        String[] transactionDetails;
        long time, time1, time2;
        String txn_id, tier, nextTransaction;
        int d1 = 0, d2, d3 = 0;

        Scanner input = new Scanner(System.in);

        while (true) {
            nextTransaction = input.nextLine();
            if (nextTransaction.equals("EXIT")) {
                break;
            }
            if (nextTransaction.equals("REBOOT")) {
                transactionQueue.clear();
                continue;
            }
            transactionDetails = nextTransaction.split(" ");
            time = Long.parseLong(transactionDetails[0]);
            txn_id = transactionDetails[1];
            tier = transactionDetails[2];
            TransactionTicket foo = new TransactionTicket(time, txn_id, tier);
            if (transactionQueue.peek() != null && d3 < d1) {
                time1 = transactionQueue.peek().getTime();
                d1 = Math.toIntExact(time1 % 10000 / 1000);
            }
            transactionQueue.offer(new TransactionTicket(time, txn_id, tier));
            time2 = foo.getTime();
            if(time2 % 1000 == 0){
                d2 = d1;
            }
            else {
                d2 = Math.toIntExact(time2 % 10000 / 1000);
            }
            while (transactionQueue.size() == 1) {
                time1 = time2;
                d1 = Math.toIntExact(time1 % 10000 / 1000);
                break;
            }
            String answer = "";
            if (d2 != d1) {
                int size = transactionQueue.size();
                if (size < 100){
                    while(!transactionQueue.isEmpty()){
                        TransactionTicket x = transactionQueue.poll();
                        answer = answer + x;
                    }
                }
                else {
                    for (int i = 0; i < 100; i++) {
                        if (!transactionQueue.isEmpty()) {
                            TransactionTicket x = transactionQueue.poll();
                            answer = answer + x;
                        }
                    }
                }
                System.out.println(answer);
            }
            d1 = d3 = d2;
        }
    }
}
