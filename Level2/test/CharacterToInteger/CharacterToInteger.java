package CharacterToInteger;

public class CharacterToInteger {
	public static void main(String[]args) {
		String str= "X12X";
		for(int i = 0; i < str.length(); i++) {
			System.out.println((int)str.charAt(i) - '0');
		}
	}
}
