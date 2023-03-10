package Hotel;
public class Main {
	public static void main(String[] args) {
		FirstSolution_GreedyAlgorithm_ActivitySelectionApproach sol = new FirstSolution_GreedyAlgorithm_ActivitySelectionApproach();
		String[][] str1 = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
		String[][] str2 = {{"09:10", "10:10"}, {"10:20", "12:20"}};
		String[][] str3 = {{"10:20", "12:30"}, {"10:20", "12:30"}, {"10:20", "12:30"}};
		String[][] str4 = {{"00:00", "23:59"},{"00:00", "23:59"},{"00:00", "23:59"},{"00:00", "23:59"}};
		String[][] str5 = {{"00:00", "12:00"}, {"12:00", "23:59"}};
		String[][] str6 = {{"00:00", "12:00"}, {"12:10", "23:48"}, {"23:58", "23:59"}};
		
		System.out.println("answer of case 1's answer: 3 == " + sol.solution(str1));
		System.out.println("answer of case 2's answer: 1 == " + sol.solution(str2));
		System.out.println("answer of case 3's answer: 3 == " + sol.solution(str3));
		System.out.println("answer of case 4's answer: 4 == " + sol.solution(str4));
		System.out.println("answer of case 5's answer: 2 == " + sol.solution(str5));
		System.out.println("answer of case 6's answer: 1 == " + sol.solution(str6));
	}
}
