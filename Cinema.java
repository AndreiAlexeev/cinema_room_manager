import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:\n> ");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:\n> ");
        int numberOfSeatsInRow = scanner.nextInt();
        int numberOfRowsInFront;
        int numberOfRowsInBack;
        int totalNumberOfSeats;
        int pricePerSeat = 10;
        int totalIncome;

        if (numberOfRows * numberOfSeatsInRow < 60) {
            totalNumberOfSeats = numberOfRows * numberOfSeatsInRow;
            totalIncome = totalNumberOfSeats * pricePerSeat;
            System.out.println("Total income:\n$" + totalIncome);
        } else {
            if (numberOfRows % 2 != 0) {
                numberOfRowsInFront = numberOfRows / 2 - 1;
            } else {
                numberOfRowsInFront = numberOfRows/2;
            }
            numberOfRowsInBack = numberOfRows - numberOfRowsInFront;
            totalIncome = (numberOfRowsInFront * pricePerSeat) + (numberOfRowsInBack * (pricePerSeat - 2));
            System.out.println("Total income:\n$" + totalIncome);
        }
        scanner.close();
        System.exit(0);
    }
}
