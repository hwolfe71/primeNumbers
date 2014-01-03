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
		Prime primes = new Prime();
		int count;
		String tmp;

		if (args.length == 0) {
			out.print("Enter the number of primes to display: ");
			tmp = in.next();
		} else {
			tmp = args[0];
		}
		count = Integer.parseInt(tmp);
		if (count == 0) {
			return;
		}

		for (int i = 1; i <= count; i++) {
			out.println(primes.getPrime(i));
		}

	} // end main

} // end class ListPrimes