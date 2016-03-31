package adgaonkar.shrirang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Main {
	public static void scheduler(int n) throws IOException {
		if (n <= 1)
			System.out.print("\ncannot create a schedule");
		else if (n == 2)
			System.out.println("1:2:1");
		else {
			File file = new File("output.txt");
			if (!file.exists() && file.isDirectory())
				file.createNewFile();
			
			FileWriter writer = new FileWriter(file);
				int days;
				int[] list;
				// check whether # of players is even or odd to SET # OF DAYS
				// REQD
				if (n % 2 == 0) {
					days = n - 1;
					list = new int[n];
					for (int i = 0; i < n; i++)
						list[i] = i + 1;
				} else {
					days = n;
					list = new int[n + 1];
					for (int i = 0; i < n; i++)
						list[i] = i + 1;
					list[n] = n + 1;
				}

				int size = list.length;

				// System.out.println("size is :"+size);
				int[] listA = new int[size / 2];
				int[] listB = new int[size / 2];

				// now split array into 2 halves. and assign values
				for (int i = 0; i < size / 2; i++)
					listA[i] = list[i];
				for (int i = 0; i < size / 2; i++)
					listB[i] = list[size - i - 1];

				int[] listP = new int[size];
				// print
				int k = 0;
				while (days > 0) {
					days--;
					k++;
					for (int i = 0; i < size / 2; i++) {
						listP[(listA[i] - 1)] = listB[i];
						listP[(listB[i] - 1)] = listA[i];
					}
					if (n < 10) {
						System.out.print("\n" + k);
						if (n % 2 == 0) {
							for (int i = 0; i < size; i++)
								System.out.print(":" + listP[i]);
						} else {
							for (int i = 0; i < size - 1; i++) {
								if (listP[i] == n + 1)
									System.out.print(":-");
								else if (listP[i] == (i + 1))
									System.out.print(":-");
								else
									System.out.print(":" + listP[i]);
							}
						}
					} else {
						writer.write("\n" + k);
						// writer.flush();
						// System.out.print("\n" + k);
						if (n % 2 == 0) {
							for (int i = 0; i < size; i++)
								writer.write(":" + listP[i]);
							// writer.flush();
							// System.out.print(":" + listP[i]);
						} else {
							for (int i = 0; i < size - 1; i++) {
								if (listP[i] == n + 1) {
									writer.write(":-");
									// writer.flush();
									// System.out.print(":-");
								} else if (listP[i] == (i + 1)) {
									writer.write(":-");
									// System.out.print(":-");
									// writer.flush();
								} else {
									writer.write(":" + listP[i]);
									// writer.flush();
									// System.out.print(":" + listP[i]);
								}
							}
						}
						// writer.flush();
					}

					int tempA = listA[size / 2 - 1];
					int tempB = listB[0];
					for (int i = size / 2; i > 1; i--) {
						listA[i - 1] = listA[i - 2];
						listB[size / 2 - i] = listB[size / 2 - i + 1];
					}
					listA[1] = tempB;
					listB[size / 2 - 1] = tempA;
				}
				writer.write("\n");
				for (int i = 0; i < n; i++)
					writer.write("##");
				writer.flush();
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		Scanner sc = new Scanner(System.in);

		System.out.println("enter 1 to use console input");
		System.out.println("enter 2 to use file input");
		int choice = sc.nextInt();
		if (choice == 1) {
			int c;
			do {
				System.out.println("enter no of players: ");
				int n = sc.nextInt();
				scheduler(n);
				System.out.println("\nEnter 0 to exit or a positive number to restart scheduler");
				c = sc.nextInt();
			} while (c != 0);
		} else if (choice == 2) {
			int np;
			Scanner read = new Scanner(new File(System.getProperty("user.dir") + "/input.txt"));
			while (read.hasNextInt()) {
				np = read.nextInt();
				scheduler(np);
			}
		}
	}
}