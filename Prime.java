/**
  * Prime.java
  * @author Herb Wolfe, Jr <hwolfe71@gmail.com>
  *
  * This program displays a list of prime numbers
  * and provides other functions for determining working with primes.
  *
  * TODO: 
  *		Create gui?
  *		create various classes/programs to test the features.
  *			PrimeList/ListPrimes #
  *			IsPrime #
  */

import static java.lang.System.*;
import java.util.*;
import java.io.*;

class Prime {
	private List <Long> primes;
	private static Long firstPrime = new Long(2);	  // First Prime number

	/**
	  * Interactive method to display prime numbers to console.
	  */

	public static void main(String [] args) {
		int count = 1;
		boolean goAgain = true;
		Prime aPrimeList = new Prime();
		Scanner in = new Scanner(System.in);
		String answer;

		while (goAgain) {
			out.println("Prime # " + count + ": "
					+ aPrimeList.getPrime(count));
			count++;
			out.println("Would you like to see prime # " + count 
					+ "? (Y/N): ");
			answer = in.next();
			answer = answer.toUpperCase().substring(0,1);
			goAgain = (answer.equals("Y"));
			if (goAgain) {
				aPrimeList.addNextPrime();
			}
		} // end while goAgain

		aPrimeList.printAllPrimesToFile();

		return;
	} 

	public Prime() {
		this.primes = new ArrayList<Long>();

		this.primes.add(firstPrime);
	}

	/**
	  * Calculate the next prime number, adding it to the List
	  * Uses the sieve of Eratosthenes to find the next prime
	  */

	private void addNextPrime() {
		long nextPrime = getLastPrime();

		if (nextPrime == 2) {
			nextPrime = 3;
		} else {
			nextPrime += 2;
			while (hasFactors(nextPrime)) {
				// All primes > 2 are odd
				nextPrime += 2;
			}
		}
		this.primes.add(nextPrime);
	}

	/**
	  * Grow the list to at least size primes
	  * @param size - the minimum number of primes.
	  */

	public void growToSize(int size) {
		while (size > primes.size()) {
			addNextPrime();
		}
	}

	/**
	  * Grow the list until the last number is larger than value.
	  * @param value - the minimum number of primes.
	  */

	public void growToValue(long value) {
		while (value > this.getLastPrime()) {
			addNextPrime();
		}
	}

	/**
	  * Print the current List of primes to stdout
	  */

	public void printAllPrimes() {
		printPrimes(primes.size());
	}

	/**
	  * Print the first num primes to stdout
	  * @param num - how many primes to print
	  */

	public void printPrimes(int num) {
		this.growToSize(num);

		for (Long p : this.primes.subList(0, num) ) {
			System.out.println(p);
		}
	}

	/**
	  * Print the current List of primes to a file, primes.txt
	  */

	private void printAllPrimesToFile() {
		printPrimesToFile(primes.size());
	}

	/**
	  * Print the first num primes to a file, primes.txt
	  * @param num - how many primes to print
	  */

	private void printPrimesToFile(int num) {
		try {
			PrintWriter pwout = new PrintWriter("primes.txt");

			this.growToSize(num);

			for (Long p : this.primes.subList(0, num) ) {
				pwout.println(p);
			}
			pwout.close();
		} catch (FileNotFoundException ex) {
			out.println(ex.getMessage());
			out.println("in " + System.getProperty("user.dir"));
			System.exit(1);
		}
	}

	/**
	  * Get the nth prime number
	  * @param nth - the index of the prime number
	  * @return - the prime number at the given index
	  */

	public long getPrime(int nth) {
		this.growToSize(nth);
		return this.primes.get(nth - 1);
	}

	/**
	  * Return the index of the prime number, if it exists
	  * @param num - the number to search for
	  * @return - the index of the number or -1 if it is not in the list
	  */

	private int indexOf(int num) {
		return indexOf((long)num);
	}

	/**
	  * Return the index of the prime number, if it exists
	  * @param num - the number to search for
	  * @return - the index of the number or -1 if it is not in the list
	  */

	private int indexOf(long num) {
		return this.primes.indexOf(num);
	}

	/**
	  * Get the last prime number in the List
	  * @return - the last prime number currently in the list
	  */

	public long getLastPrime() {
		return getPrime(this.getNumPrimes());
	}

	/**
	  * How many primes are in the list
	  * @return - the number of primes in the list
	  */

	public int getNumPrimes() {
		return this.primes.size();
	}

	/**
	  * Determine whether the number is prime
	  * @param num - the number to be tested
	  * @return whether or not the number is prime
	  */

	public boolean isPrime(long num) {
		boolean numIsPrime = false;

		this.growToValue(num);

		if ((num > 1) && (this.indexOf(num) > -1))
			numIsPrime = true;

		return numIsPrime;
	}

	/**
	  * int version of above
	  * @param num - the integer to test
	  * @return whether or not the number is prime
	  */

	public boolean isPrime(int num) {
		return isPrime((long)num);
	}

	/**
	  * Determine whether the number has any (prime) factors
	  * @param num - the number to check
	  * @return whether or not the number has any prime factors
	  */

	private boolean hasFactors(long num) {
		boolean factors = false;

		// The largest factor of a number is it's square root.
		int max = (int)Math.ceil(Math.sqrt(num));
		this.growToValue(max);

		for (Long p : this.primes) {
			if (num % p == 0) {
				factors = true;
			}
			if (factors || p > max) {
				break;
			}
		} 
		return factors;
	} 

	/**
	  * Determine whether the int has any prime factors
	  * @param num - the integer to check
	  * @return whether or not the number has any prime factors
	  */

	private boolean hasFactors(int num) {
		return hasFactors((long)num);
	}

	/**
	  * Print the prime factors of a number
	  * @param num - the number to print the factors 
	  */

	public void printFactors(long num) {
		String factorList = new String();

		this.growToValue(num);
		
		if (num < 2) {
			out.println(num + " is less than 2 and therefore has no prime factors!");
		} else if (this.indexOf(num) > -1) {
			out.println(num + " is a prime, therefore it has only one prime factor, itself!");
		} else {
			while (num > 1) {
				for (Long p : primes) {
					if (num % p == 0) {
						factorList = factorList + Long.toString(p) + " ";
						while (num % p == 0) {
							num /= p;
						} // end while
					} // endif
				} //end for
			} //end while

			out.println(factorList);
		} 
	} 

} // end Prime
