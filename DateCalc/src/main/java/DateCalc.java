
import java.text.ParseException;
import java.util.*;

/**
 *
 * @author User
 */
public class DateCalc
{
    private static final String[] MONTH_NAMES = new String[] {
        "January", "February", "March", "April",
        "May", "June", "July", "August",
        "September", "October", "November", "December"
    };
    private static final int[] MONTH_KEYS = new int[] {
        1, 4, 4, 0,
        2, 5, 0, 3,
        6, 1, 4, 4
    };
    private static final String[] DAYS_OF_WEEK = new String[] {
        "Saturday",
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday"
    };

    
    public static int[] promptDate(Scanner in) throws ParseException {
        System.out.print( " Please enter the month (e.g. \"January\") : ");
        String month = in.nextLine();
        System.out.print( " Please enter the day of the date : ");
        String day = in.nextLine();
        System.out.print( " Please enter the year of the date : ");
        String year = in.nextLine();

        int[] result = new int[3];
        try {
            int yyyy = result[0] = Integer.parseInt(year);
            int dd   = result[2] = Integer.parseInt(day);
            for (int i = 0; i < MONTH_NAMES.length; i++) {
                if (MONTH_NAMES[i].equalsIgnoreCase(month)) {
                    int mm = result[1] = i;
                    return result;
                }
            }
            throw new ParseException("Invalid month " + month, 0);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    public static String results(int[] ymd) {
        int yyyy = ymd[0];
        int mm = ymd[1];
        int dd = ymd[2];

        int yy = ymd[0] % 100;
        int quarter = yy / 4;
        int monthKey = MONTH_KEYS[mm];
        int sum = yy + quarter + dd + monthKey;

        return String.format(
            "***********************************************************************\n" +
            "***********************    Result    **********************************\n" +
            "***********************************************************************\n" +
            "\n" +
            "\t\t Date entered was :\n" +
            "\t \t   %s %s, %s\n" +
            "\n" +
            "Last two digits of the year were : ........ %s\n" +
            "One quarter of last two digits : ........ %s\n" +
            "The given day of the month : ........ %s\n" +
            "The index key of this month : ........ %s\n" +
            "The sum of all the numbers above is : ........ %s\n" +
            "\n" +
            " The Day Of The Week Was : ........ %s",
            MONTH_NAMES[mm], dd, yyyy,
            yy, quarter, dd, monthKey, sum,
            DAYS_OF_WEEK[sum % 7]
        );
    }

    public static void main(String[] args) {
        int[] ymd = null;
        try (Scanner in = new Scanner(System.in)) {
            do {
                try {
                    ymd = promptDate(in);
                } catch (ParseException badDate) {
                    System.err.println(" Invalid entry, please try again " );
                }
            } while (ymd == null);
        }

        System.out.println("\n");
        System.out.println(results(ymd));
    }

}