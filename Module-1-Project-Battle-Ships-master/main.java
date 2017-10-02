import java.util.*;

public class main {

	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		// STEP 1: Create the ocean map
		char[][] sea = new char[10][10];
		// Fill with space
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				sea[i][j] = ' ';
			}
		}
		System.out.println("**** Welcome to Battle Ships game ****\nRight now, the sea is empty.\n");
		printMap(sea);
		deployPlayersShips(sea);
		deployCompsShips(sea);
		printMap(sea);
		battle(sea);
	}

	static void deployPlayersShips(char[][] sea) {
		// STEP 2: Deploy player's ships
		System.out.println("Deploy your ships");
		for (int i = 0; i < 5; i++) {
			System.out.print("Enter X coordinate for your " + (i + 1) + ". ship: ");
			int x = input.nextInt();
			System.out.print("Enter Y coordinate for your " + (i + 1) + ". ship: ");
			int y = input.nextInt();
			if (x < 10 && y < 10 && sea[x][y] == ' ') {
				sea[x][y] = '@';
			} else {
				i--;
			}
		}
	}

	static void deployCompsShips(char[][] sea) {
		// STEP 3: Deploy computer's ship
		System.out.println("Computer is deploying ships");
		for (int i = 0; i < 5; i++) {
			int x = (int) (Math.random() * 10);
			int y = (int) (Math.random() * 10);
			if (x < 10 && y < 10 && sea[x][y] == ' ') {
				sea[x][y] = '2';
				System.out.println((i + 1) + ". ship DEPLOYED ");
			} else {
				i--;
			}
		}
		System.out.println("-----------------------------------");
	}

	static void battle(char[][] sea) {
		// STEP 4: Battle
		int player = 5;
		int comp = 5;
		int x, y;
		boolean valid = false;
		while (player != 0 && comp != 0) {
			System.out.println("YOUR TURN");
			while (!valid) {
				System.out.print("Enter X coordinate: ");
				x = input.nextInt();
				System.out.print("Enter Y coordinate: ");
				y = input.nextInt();
				if (x < 10 && y < 10 && sea[x][y] == ' ') {
					valid = true;
					sea[x][y] = 'x';
					System.out.println("You missed");
				} else if (x < 10 && y < 10 && sea[x][y] == '2') {
					valid = true;
					sea[x][y] = 'x';
					System.out.println("BOOOM! You sunk the ship!");
					comp--;
				} else if (x < 10 && y < 10 && sea[x][y] == '@') {
					valid = true;
					sea[x][y] = 'x';
					System.out.println("BOOOM! You sunk your ship!");
					player--;
				}
			}
			valid = false;

			System.out.println("COMPUTER'S TURN");
			while (!valid) {
				x = (int) (Math.random() * 10);
				y = (int) (Math.random() * 10);
				if (x < 10 && y < 10 && sea[x][y] == ' ') {
					valid = true;
					sea[x][y] = 'x';
					System.out.println("You missed");
				} else if (x < 10 && y < 10 && sea[x][y] == '@') {
					valid = true;
					sea[x][y] = 'x';
					System.out.println("BOOOM! You sunk the ship!");
					player--;
				} else if (x < 10 && y < 10 && sea[x][y] == '2') {
					valid = true;
					sea[x][y] = 'x';
					System.out.println("BOOOM! You sunk your ship!");
					comp--;
				}
			}
			valid = false;
			
			printMap(sea);
			System.out.println("Your ships: " + player + " | Computer ships: " + comp);
			System.out.println("----------------------------------");
		}

		// STEP 5: Game Over
		if (comp == 0) {
			System.out.println("Hooray! You win the battle :)");
		} else {
			System.out.println("Ouch! You lost the battle :(");
		}
	}

	static void printMap(char[][] sea) {
		System.out.println("   0123456789");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " |");
			for (int j = 0; j < 10; j++) {
				if (sea[i][j] == '2') {
					System.out.print(" ");
				} else {
					System.out.print(sea[i][j]);
				}
			}
			System.out.print("| " + i + "\n");
		}
		System.out.println("   0123456789\n");
	}

}
