import java.util.Calendar;
import java.util.GregorianCalendar;

public class age2 {

    public static void main(String[] args) {
        // Code snippet to get the current year
        Calendar cal = GregorianCalendar.getInstance();

        // The "cal" object will provide the current year using the get(Calendar.YEAR) method
        int currentYear = cal.get(Calendar.YEAR);
       
        // Imagine the user was born in 1991
        int birthYear = 1991;
        
        int age = currentYear - birthYear;
        
        System.out.println("You are: " + age);
    }

}