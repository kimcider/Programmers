package LocalTime;
import java.time.LocalTime;
public class LocalTimeTester {
	public static void main(String[]args) {
		LocalTime current = LocalTime.of(23, 59);
		LocalTime after = current.plusMinutes(10);
		
		System.out.println(after);
		
		LocalTime end = LocalTime.of(23, 59);
		System.out.println(end.toSecondOfDay()/60);
	}
}
