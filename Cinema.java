package cinema;

import java.util.Scanner;

public class Cinema {

    private static int rowsNumber;
    private static int seatsNumberInEachRow;
    private static int totalNumberOfSeats;
    private static Scanner sc;
    private static String[][] array;
    private static int numberOfRow;
    private static int numberOfSeat;
    private static int numberOfPurchasedTickets;
    private static double occupiedPlacesPercentage;
    private static double currentIncomeVariable;
    private static double totalIncomeVariable = 0;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        initializeRowsAndSeats();
    }

    private static void initializeRowsAndSeats() {
        System.out.println("Enter the number of rows:");
        rowsNumber = sc.nextInt();
        sc.nextLine();
        if (rowsNumber <= 0) {
            System.out.println("Invalid number of rows");
            return;
        }
        System.out.println("Enter the number of seats in each row:");
        seatsNumberInEachRow = sc.nextInt();
        sc.nextLine();
        if (seatsNumberInEachRow <= 0) {
            System.out.println("Invalid number of seats");
            return;
        }
        array = new String[rowsNumber][seatsNumberInEachRow];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = "S ";
            }
        }
        totalNumberOfSeats = rowsNumber * seatsNumberInEachRow;
        printMenu();
    }

    private static void showTheSeats() {
        //print the seats
        System.out.println();
        System.out.println("Cinema:");
        for (int i = 0; i <= seatsNumberInEachRow; i++) {
            if (i < 1) {
                System.out.print(" ");
            } else {
                System.out.print(" " + i);
            }
        }
        System.out.println();
        //print all the seats in the screen rooms
        for (int i = 0; i < array.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }

    private static void getTicketPrice() {
        // print the seats number in each row
        System.out.println();
        System.out.println("Enter a row number:");
        if (!sc.hasNextInt()) {
            System.out.println("No input available for row number. Exiting");
            return;
        }
        numberOfRow = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter a seat number in that row:");
        if (!sc.hasNextInt()) {
            System.out.println("No input available for seat number. Exiting");
            return;
        }
        numberOfSeat = sc.nextInt();
        sc.nextLine();

        if (numberOfRow < 1 || numberOfRow > rowsNumber ||
                numberOfSeat < 1 || numberOfSeat > seatsNumberInEachRow) {
            System.out.println("Wrong input!");
            getTicketPrice();
            return;
        }
        int rowIndex = numberOfRow - 1;
        int seatIndex = numberOfSeat - 1;

        if (array[rowIndex][seatIndex].equals("S ")) {
            array[rowIndex][seatIndex] = "B ";
            numberOfPurchasedTickets++;// each call starts the counter
        } else {
            System.out.println("That ticket has already been purchased!");
            //printMenu();
            getTicketPrice();
            return;
        }

        int ticketPrice;
        if (totalNumberOfSeats <= 60) {
            ticketPrice = 10;
        } else {
            int firstRows = -1;
            if (rowsNumber % 2 != 0) {
                firstRows = (rowsNumber - 1) / 2;
            } else {
                firstRows = rowsNumber / 2;
            }

            if (numberOfRow <= firstRows) ticketPrice = 10;
            else ticketPrice = 8;
        }
        currentIncomeVariable += ticketPrice;
        System.out.printf("Ticket price: $%d\n", ticketPrice);
    }

    public static void statistics() {
        numberOfPurchasedTickets();
        calculateOccupiedSeatsPercentage();
        currentIncome();
        totalIncome();
    }

    public static void numberOfPurchasedTickets() {
        System.out.println("Number Of Purchased Tickets: " +
                numberOfPurchasedTickets);
    }

    public static void calculateOccupiedSeatsPercentage() {
        if (totalNumberOfSeats == 0) {
            throw new IllegalArgumentException("Total number of seats cannot be zero!");

        }
        occupiedPlacesPercentage =
                (double) (numberOfPurchasedTickets * 100) / totalNumberOfSeats;
        System.out.print("Percentage: ");
        System.out.printf("%.2f", occupiedPlacesPercentage);
        System.out.println("%");
    }

    public static void currentIncome() {
        int ticketPrice;
        if (totalNumberOfSeats <= 60) {
            ticketPrice = 10;
        } else {
            int firstRows = -1;
            if (rowsNumber % 2 != 0) {
                firstRows = (rowsNumber - 1) / 2;
            } else {
                firstRows = rowsNumber / 2;
            }
            ticketPrice = numberOfRow <= firstRows ? 10 : 8;
        }
        System.out.println("Current income: $" + (int) currentIncomeVariable);
    }

    public static void totalIncome() {
        boolean switchTotalIncome = true;
        int ticketPrice = 0;
        while (switchTotalIncome) {
            if (totalNumberOfSeats <= 60) {
                ticketPrice = 10;
                totalIncomeVariable = totalNumberOfSeats * ticketPrice;
            } else {
                int firstRows;
                if (rowsNumber % 2 != 0) {
                    firstRows = (rowsNumber - 1) / 2;
                } else {
                    firstRows = rowsNumber / 2;
                }
                int ticketPriceForFirstPlaces = firstRows * seatsNumberInEachRow * 10;
                int ticketPriceForSecondPlaces = (rowsNumber - firstRows) * seatsNumberInEachRow * 8;
                totalIncomeVariable = ticketPriceForFirstPlaces + ticketPriceForSecondPlaces;
            }
            switchTotalIncome = false;
        }
        System.out.println("Total income: $" + (int) totalIncomeVariable);
    }

    private static void printMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("""

                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit""");
            System.out.println();
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Exciting.");
                break;
            }
            int menuOption = sc.nextInt();

            switch (menuOption) {
                case 1 -> showTheSeats();
                case 2 -> getTicketPrice();
                case 3 -> statistics();
                case 0 -> {
                    isRunning = false;
                    sc.close();
                }
                default -> System.out.println("Invalid option. PLease try again!");
            }
        }
    }
}
