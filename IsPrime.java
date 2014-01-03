/**
  * IsPrime.java
  * @author Herb Wolfe, Jr <hwolfe71@gmail.com>
  *
  * Created: 01/03/2014
  *
  * This program displays whether or not a number is prime.
  * The number can either be passed as a command line parameter, or
  * interactively.
  *
  */

//package hwolfe.prime;

import static java.lang.System.*;
import java.util.*;
import java.io.*;
//import prime.*;

public class IsPrime {

	/**
	  * Interactive method to display whether or not a number is prime.
	  */

	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		Prime primes = new Prime();
		long num;
		String tmp;

		if (args.length == 0) {
			out.print("Enter the number to determine its primeness: ");
			tmp = in.next();
		} else {
			tmp = args[0];
		}

		try {
			num = Long.parseLong(tmp);
		} catch (NumberFormatException ex) {
			num = 0;
		}

		// if invalid entry, exit
		if (num == 0) {
			out.println("Invalid entry, goodbye!");
			return;
		}

		if (primes.isPrime(num)) {
			out.println(num + " is a prime number.");
		} else {
			out.println(num + " is not a prime number.");
		}

	} // end main

} // end class ListPrimes
