/**
  * ListPrimes.java
  * @author Herb Wolfe, Jr <hwolfe71@gmail.com>
  *
  * This program displays a list of prime numbers
  *
  */

//package hwolfe.prime;

import static java.lang.System.*;
import java.util.*;
import java.io.*;
//import prime.*;

public class ListPrimes {

	/**
	  * Interactive method to display prime numbers to console.
	  */

	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		Prime primes;
		int count;
		String tmp;

		if (args.length == 0) {
			out.print("Enter the number of primes to display: ");
			tmp = in.next();
		} else {
			tmp = args[0];
		}

		try {
			count = Integer.parseInt(tmp);
			primes = new Prime(count);
			primes.printAllPrimes();
		} catch (NumberFormatException ex) {
			// if invalid entry, exit
			out.println("Invalid entry, goodbye!");
		}

		return;
	} // end main

} // end class ListPrimes
