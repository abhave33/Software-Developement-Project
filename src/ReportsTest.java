public class ReportsTest {

    public static void main(String[] args) {
        System.out.println("Test 1: Pay by Job Title");
        Reports.PayByJobTitle();

        System.out.println("Test 2: Pay by Division");
        Reports.PayByDivision();

        System.out.println("Test 3: Pay History");
        Reports.PayHistory(101);
    }
}
