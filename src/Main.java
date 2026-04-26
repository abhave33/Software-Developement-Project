import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner UserInput = new Scanner(System.in);

        int searchChoice = 0;

        while (searchChoice != 3){
            System.out.println("Search By");
            System.out.println("1. Employee");
            System.out.println("2. Reports");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            searchChoice = UserInput.nextInt();

            switch (searchChoice) {
            case 1:
                SearchEmployee employeeSearch = new SearchEmployee();
                employeeSearch.main(args);
                searchChoice = 3;
                break;
            case 2:
                Reports reports = new Reports();
                reports.main(args);
                searchChoice = 3;
                break;
            case 3:
                System.out.println("Exiting program.");
                break;
            default:
                 System.out.println("Invalid choice. Please try again.");
            }
        } 
    }
}
