/**
  * factors.java
  * @author Herb Wolfe, Jr <hwolfe71@gmail.com>
  *
  * Created: 01/03/2014
  *
  * This program displays a list of the prime factors of a number.
  * The number can either be passed as a command line parameter, or
  * interactively.
  *
  */

import static java.lang.System.*;
import java.util.*;
import java.io.*;

public class factors {

	/**
	  * Interactive method to display whether or not a number is prime.
	  */

	public static void main(String [] args) {
		Scanner in = new Scanner(System.in);
		Prime primes = new Prime();
		long num;
		String tmp;

		if (args.length == 0) {
			out.print("Enter the number to list its factors: ");
			tmp = in.next();
		} else {
			tmp = args[0];
		}

		try {
			num = Long.parseLong(tmp);
			primes.printFactors(num);
		} catch (NumberFormatException ex) {
			out.println("Invalid entry, goodbye!");
		}

		return;
	} 

} 
