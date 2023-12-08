package com.seclore.main;

import java.util.Scanner;

import com.seclore.pojo.CashRegister;
import com.seclore.pojo.DispenserType;

public class CandyMachineMain {
	public static void main(String[] args) {
		int dispenserChoice;
		String continueChoice;
		String buyChoice;
		int amountGiven;
		int changeReturned;
		String selectedItemName;
		DispenserType selectedDispenser;
		Scanner scanner = new Scanner(System.in);

		DispenserType candy = new DispenserType(3, 20);
		DispenserType chips = new DispenserType(2, 50);
		DispenserType gum = new DispenserType(1, 100);
		DispenserType cookie = new DispenserType(5, 50);

		CashRegister cashRegister = new CashRegister();

		do {
			System.out.println("-------------");
			System.out.println("Candy Machine");

			System.out.println("Select a Product:");
			System.out.println("1. Candy");
			System.out.println("2. Chips");
			System.out.println("3. Gum");
			System.out.println("4. Cookie");
			System.out.print("Enter your choice: ");
			dispenserChoice = scanner.nextInt();
			scanner.nextLine();

			switch (dispenserChoice) {
			case 1:
				selectedDispenser = candy;
				selectedItemName = "Candy";
				break;
			case 2:
				selectedDispenser = chips;
				selectedItemName = "Chip";
				break;
			case 3:
				selectedDispenser = gum;
				selectedItemName = "Gum";
				break;
			case 4:
				selectedDispenser = cookie;
				selectedItemName = "Cookie";
				break;
			default:
				System.out.println("Invalid selection. Try again.");
				continueChoice = "y";
				continue;
			}

			System.out.println("You have selected " + selectedItemName + "!");
			if (selectedDispenser.getNumberOfItems() == 0) {
				System.out.println("The dispenser is empty :(");
			} else {
				System.out.println("We have " + selectedDispenser.getNumberOfItems() + " left!");
				System.out.println("Cost is " + selectedDispenser.getCost() + "!");
			}

			if (selectedDispenser.getNumberOfItems() != 0) {
				System.out.print(
						"Do you want to buy " + selectedItemName + " for " + selectedDispenser.getCost() + "? [Y/n] ");
				buyChoice = scanner.nextLine();

				if (buyChoice.equalsIgnoreCase("y") || buyChoice == "") {
					System.out.print("Enter money: ");
					amountGiven = scanner.nextInt();
					scanner.nextLine();

					changeReturned = amountGiven - selectedDispenser.getCost();
					if (changeReturned < 0) {
						System.out.println("The money is not enough.");
						continueChoice = "y";
						continue;
					} else if (changeReturned > 0) {
						System.out.println("You get back " + changeReturned + "!");
					}

					cashRegister.acceptAmount(selectedDispenser.getCost());
					System.out.println("Transaction complete.");
					selectedDispenser.makeSale();

					System.out.println("You got a " + selectedItemName + "!");
				} else {
					System.out.println("No problem ;)");
				}
			}

			System.out.print("Do you want to continue? [Y/n] ");
			continueChoice = scanner.nextLine();
		} while (continueChoice.equalsIgnoreCase("y") || continueChoice == "");
		scanner.close();
	}
}
