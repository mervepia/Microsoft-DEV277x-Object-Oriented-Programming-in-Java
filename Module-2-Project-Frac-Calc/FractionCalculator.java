import java.util.*;

public class FractionCalculator {

	public static void main(String[] args) {
		init();
		Scanner input = new Scanner(System.in);
		String operation = " ";
		Fraction frac1, frac2;

		while (true) {
			System.out.println("---------------------------------------------------------------------------");
			operation = getOperation(input);
			if (operation.compareTo("q") == 0 || operation.compareTo("Q") == 0) {
				break;
			}
			frac1 = getFraction(input);
			frac2 = getFraction(input);

			calculate(frac1, frac2, operation);
		}
		input.close();
		System.exit(0);
	}

	private static void init() {
		System.out.println("This program is a fraction calculator");
		System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit");
		System.out.println("Please enter your fractions in the form a/b, where a and b are integers.");
	}

	private static void calculate(Fraction frac1, Fraction frac2, String op) {
		if (op.compareTo("=") == 0) {
			System.out.println(frac1.toString() + " " + op + " " + frac2.toString() + " is " + frac1.equals(frac2));
		} else {
			Fraction result = null;

			if (op.compareTo("+") == 0) {
				result = frac1.add(frac2);
			} else if (op.compareTo("-") == 0) {
				result = frac1.subtract(frac2);
			} else if (op.compareTo("/") == 0) {
				result = frac1.divide(frac2);
			} else if (op.compareTo("*") == 0) {
				result = frac1.multiply(frac2);
			}
			System.out.println(frac1.toString() + " " + op + " " + frac2.toString() + " = " + result.toString());
		}
	}

	private static String getOperation(Scanner input) {
		System.out.println("Please enter an operation (+, -, /, *, = or Q to quit): ");
		String op = input.nextLine();
		while (op.equalsIgnoreCase("+") == false && op.equalsIgnoreCase("-") == false
				&& op.equalsIgnoreCase("/") == false && op.equalsIgnoreCase("*") == false
				&& op.equalsIgnoreCase("=") == false && op.equalsIgnoreCase("q") == false) {
			System.out.println("Invalid input (+, -, /, *, = or Q to quit): ");
			op = input.nextLine();
		}
		return op;
	}

	private static boolean validFraction(String input) {
		// negative sign control
		for (int i = 1; i < input.length(); i++) {
			if (input.charAt(i) == '-') {
				return false;
			}
		}
		// find '/' location
		int div = input.indexOf("/");
		// if input is a integer
		if (div == -1) {
			return isNumber(input);
		} else {
			if (isNumber(input.substring(0, div)) == false || isNumber(input.substring(div + 1)) == false) {
				return false;
			} else if (Integer.parseInt(input.substring(div + 1)) == 0) {
				return false;
			}
		}
		return true;
	}

	private static boolean isNumber(String input) {
		if (input.length() == 0) {
			return false;
		}
		// Control every character
		for (int i = 0; i < input.length(); i++) {

			if (input.charAt(i) != '0' && input.charAt(i) != '1' && input.charAt(i) != '2' && input.charAt(i) != '3'
					&& input.charAt(i) != '4' && input.charAt(i) != '5' && input.charAt(i) != '6'
					&& input.charAt(i) != '7' && input.charAt(i) != '8' && input.charAt(i) != '9') {
				// for negative number
				if (i == 0 && input.charAt(i) == '-') {
					continue;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	private static Fraction getFraction(Scanner input) {
		System.out.println("Please enter a fraction (a/b) or integer (a): ");
		String inFrac = input.nextLine();
		while (!validFraction(inFrac)) {
			System.out.println(
					"Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
			inFrac = input.nextLine();
		}
		// Create new Fraction object
		int div = inFrac.indexOf("/");
		// if input is a integer
		if (div == -1) {
			Fraction newFrac = new Fraction(Integer.parseInt(inFrac));
			return newFrac;
		} else {
			Fraction newFrac = new Fraction(Integer.parseInt(inFrac.substring(0, div)),
					Integer.parseInt(inFrac.substring(div + 1)));
			return newFrac;
		}

	}
}
