package com.revature.eval.java.core;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		List<String> words=new ArrayList<String>();
		String toAdd="";
		for(int i=0; i < phrase.length(); i++) {
			Character c=phrase.charAt(i);
			if((65 <= c && c <= 90) || (97 <= c && c <= 122)) { //letter
				toAdd+=c.toString();
			}
			else { //signifies end of a word
				words.add(toAdd);
				toAdd="";
			}
		}
		words.add(toAdd);
		String acronym="";
		for(String s : words) {
			if(s.length() > 0) {
				acronym+=s.substring(0, 1).toUpperCase();
			}
		}
		
		return acronym;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			// TODO Write an implementation for this method declaration
			return this.sideOne==this.sideTwo && this.sideTwo==this.sideThree;
		}

		public boolean isIsosceles() {
			// TODO Write an implementation for this method declaration
			return this.sideOne==this.sideTwo || this.sideOne==this.sideThree || this.sideTwo==this.sideThree;
		}

		public boolean isScalene() {
			// TODO Write an implementation for this method declaration
			return this.sideOne!=this.sideTwo && this.sideOne!=this.sideThree && this.sideTwo!=this.sideThree;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		// TODO Write an implementation for this method declaration
		String[] letters= {"AEIOULNRST", "DG", "BCMP", "FHVWY", "K", "JX", "QZ"};
		int[] scores= {1, 2, 3, 4, 5, 8, 10};
		Map<String, Integer> abc=new HashMap<String, Integer>();
		for(int i=0; i < scores.length; i++)
		{
			for(int j=0; j < letters[i].length(); j++)
			{
				abc.put(letters[i].substring(j, j+1), scores[i]);
			}
		}
		int scrabbleScore=0;
		for(int k=0; k < string.length(); k++)
		{
			scrabbleScore+=abc.get(string.substring(k, k+1).toUpperCase());
		}
		
		return scrabbleScore;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		// TODO Write an implementation for this method declaration
		String cleanNumber="";
		for(int i=0; i < string.length(); i++)
		{
			if( 48 <= string.charAt(i) && string.charAt(i) <= 57) {
				cleanNumber+=string.substring(i,  i+1);
			}
			else { // not a number
				if(string.charAt(i)!=32 && string.charAt(i)!=45 && string.charAt(i)!=46 &&
						string.charAt(i)!=40 && string.charAt(i)!=41) {
					throw new IllegalArgumentException();
				}
			}
		}
		if(cleanNumber.charAt(0)=='1')
		{
			return cleanNumber.substring(1);
		}
		if(cleanNumber.length() > 11) {
			throw new IllegalArgumentException();
		}
		
		return cleanNumber;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		// TODO Write an implementation for this method declaration
		ArrayList<StringBuffer> al=new ArrayList<StringBuffer>();
		StringBuffer sb=new StringBuffer("");
		for(int i=0; i < string.length(); i++) {
			if((97 <= string.charAt(i) && string.charAt(i) <= 122) ||
					(65 <= string.charAt(i) && string.charAt(i) <= 90))
			{
				sb.append(string.charAt(i));
			}
			else {
				if(sb.length() >= 1)
				{
					al.add(sb);
				}
				sb=new StringBuffer("");
			}
		}
		al.add(sb);
		Map<String, Integer> numWords=new HashMap<String, Integer>();
		for(StringBuffer s : al)
		{
			if(s.length() >= 1)
			{
				Integer currentCount=numWords.get(s.toString());
				if(currentCount!=null)
				{
					numWords.replace(s.toString(), currentCount, currentCount+1);
				}
				else
				{
					numWords.put(s.toString(), 1);
				}
			}
		}
		return numWords;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T extends Comparable<T>> { //need compareTo
		private List<T> sortedList;

		public int indexOf(T t) {
			int low=0;
			int high=sortedList.size()-1;
			int mid=(int)((high+low)/2);
			while(low < high) {
				if(sortedList.get(mid).compareTo(t) < 0) {
					low=mid+1;
				}
				else if(sortedList.get(mid).compareTo(t) > 0) {
					high=mid-1;
				}
				else {
					return mid;
				}
				mid=(int)((high+low)/2);
			}
			return mid;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		
		String vowels="aeiou";
		Map<String, Integer> abc=new HashMap<String, Integer>();
		for(int i=0; i < vowels.length(); i++) {
			abc.put(vowels.substring(i, i+1), 0);
		}
		String inPigLatin="";
		String word="";
		for(int j=0; j < string.length(); j++) {
			
			if((97 <= string.charAt(j) && string.charAt(j) <= 122) || 
					(65 <= string.charAt(j) && string.charAt(j) <= 90))
			{
				word+=string.charAt(j);
			}
			else { //end of a word		
				if(word.length() >= 1)
				{
					if(abc.get(word.substring(0, 1).toLowerCase())!=null) { // starts with a vowel
						inPigLatin+=word + "ay ";
					}
					else { //consonant
						int i=0;
						while(abc.get(word.substring(i, i+1).toLowerCase())==null){
							if("q".equals(word.substring(i, i+1)) && "u".equals(word.substring(i+1, i+2)))
							{ //handling qu case
								i+=2;
								break;
							}
							i++;
						}
						inPigLatin+=word.substring(i) + word.substring(0, i) + "ay ";
					}
					
					word="";
				}
			}
			
		}
		if(word.length() >=1)
		{
			if(abc.get(word.substring(0, 1).toLowerCase())!=null) { // starts with a vowel
				inPigLatin+=word + "ay ";
			}
			else { //consonant
				int i=0;
				while(abc.get(word.substring(i, i+1).toLowerCase())==null){
					if("q".equals(word.substring(i, i+1)) && "u".equals(word.substring(i+1, i+2)))
					{ //handling qu case
						i+=2;
						break;
					}
					i++;
				}
				inPigLatin+=word.substring(i) + word.substring(0, i) + "ay ";
				
			}
		}
		
		return inPigLatin.trim();
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		ArrayList<Integer> digits=new ArrayList<Integer>();
		Integer toObject=(Integer)(input);
		int maxPowOfTen=toObject.toString().length()-1;
		while(maxPowOfTen > 0) {
			Integer toAdd=input/(int)(Math.pow(10, maxPowOfTen));
			digits.add(toAdd);
			input-=toAdd*(int)(Math.pow(10, maxPowOfTen));
			maxPowOfTen--;
		}
		digits.add(input);
		int sumOfDigits=0;
		for(Integer i : digits) {
			sumOfDigits+=(int)(Math.pow(i, toObject.toString().length()));
		}
		return sumOfDigits==toObject;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		// get primes up to the floor of the square root of l
		Long floorSqrtL=(long)(Math.sqrt(l));
		List<Long> primes=new ArrayList<Long>();
		for(long i=2l; i <= floorSqrtL; i++) {
			primes.add(i);
		}
		for(int j=0; j < primes.size(); j++) {
			Long multiple=primes.get(j);
			for(int k=j+1; k < primes.size(); k++) {
				if(primes.get(k)%multiple==0) {
					primes.remove(k);
				}
			}
		}
		List<Long> primeFactors=new ArrayList<Long>();
		for(Long prime : primes) {
			if(l%prime==0) {
				Long tempL=l;
				while(tempL%prime==0) {
					primeFactors.add(prime);
					tempL=tempL/prime;
				}
			}
		}
		if(primeFactors.isEmpty()) {
			primeFactors.add(l); // l is prime
		}
		return primeFactors;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			Map<Integer, String> abc=new HashMap<Integer, String>();
			String alphabet="abcdefghijklmnopqrstuvwxyz";
			for(int i=0; i < alphabet.length(); i++) {
				abc.put(i, alphabet.substring(i, i+1));
			}
			String cipheredText="";
			for(int j=0; j < string.length(); j++) {
				char currentChar=string.charAt(j);
				int shifter=0;
				if(97 <= currentChar && currentChar <= 122) { //lowercase letter
					shifter=((currentChar-97)+key)%26;
					cipheredText+=abc.get(shifter);
				}
				else if(65 <= currentChar && currentChar <= 90) { // capital letter
					shifter=((currentChar-65)+key)%26;
					cipheredText+=abc.get(shifter).toUpperCase();
				}
				else {
					cipheredText+=currentChar;
				}
			}
			return cipheredText;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		int checkIfPrime=2;
		if(i > 0) {
			int primeCounter=0;
			while(primeCounter <= i) {
				int j=2;
				while(j <= (int)(Math.sqrt(checkIfPrime))) {
					if(checkIfPrime%j==0) {
						break;
					}
					j++;
				}
				if(j > (int)(Math.sqrt(checkIfPrime)) && primeCounter < i) { // it's prime
					primeCounter++;
				}
				if(j > (int)(Math.sqrt(checkIfPrime)) && primeCounter==i) { 
					break;
				}
				checkIfPrime++;
			}
		}
		else {
			throw new IllegalArgumentException();
		}
		return checkIfPrime;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			Map<String, String> abc=new HashMap<String, String>();
			String alphabet="abcdefghijklmnopqrstuvwxyz";
			for(int i=0; i < alphabet.length(); i++) {
				abc.put(alphabet.substring(i, i+1), alphabet.substring(alphabet.length()-i-1, alphabet.length()-i));
			}
			String encodedString="";
			int numLetters=0;
			for(int i=0; i < string.length(); i++) {
				Character singleChar=string.charAt(i);
				if(65 <= singleChar && singleChar <= 90) { //capital letter
					encodedString+=abc.get(singleChar.toString().toLowerCase());
					numLetters++;
				}
				else if(97 <= singleChar && singleChar <= 122) { //lower case
					encodedString+=abc.get(singleChar.toString());
					numLetters++;
				}
				else if(48 <= singleChar && singleChar <= 57) { //number, include unchanged
					encodedString+=singleChar.toString();
					numLetters++;
				}
				if(numLetters==5) {
					encodedString+=" ";
					numLetters=0;
				}
				
			}
			
			return encodedString.trim();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			Map<String, String> abc=new HashMap<String, String>();
			String alphabet="abcdefghijklmnopqrstuvwxyz";
			for(int i=0; i < alphabet.length(); i++) {
				abc.put(alphabet.substring(alphabet.length()-i-1, alphabet.length()-i), alphabet.substring(i, i+1));
			}
			String decodedString="";
			for(int i=0; i < string.length(); i++) {
				Character singleChar=string.charAt(i);
				if(97 <= singleChar && singleChar <= 122) { // not a space
					decodedString+=abc.get(singleChar.toString());
				}
				else if(48 <= singleChar && singleChar <= 57) { //number
					decodedString+=singleChar.toString();
				}
			}
			
			return decodedString;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		int multiplier=10;
		int isbnScore=0;
		for(int i=0; i < string.length(); i++){
			char singleChar=string.charAt(i);
			if((48 <= singleChar && singleChar <= 57) || singleChar==88){ //character is a number or an X
				if(singleChar!=88){ //not an X
					isbnScore+=multiplier*Integer.parseInt(string.substring(i, i+1));
					multiplier--;
				}
				else{
					isbnScore+=multiplier*10;
					multiplier--;
				}
			}
		}
		if(multiplier > 0){ //didn't even have 10 numbers
			return false;
		}
		return isbnScore%11==0;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		int[] letterCounter=new int[26];
		for(int i=0; i < string.length(); i++) {
			char c=string.charAt(i);
			if(65 <= c && c <= 90) { //capital letter
				letterCounter[c-65]+=1;
			}
			else if(97 <= c && c <= 122) { //lowercase 
				letterCounter[c-97]+=1;
			}
		}
		boolean isAPangram=true;
		for(int j=0; j < letterCounter.length; j++) {
			if(letterCounter[j] < 1) {
				isAPangram=false;
			}
		}
		return isAPangram;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		LocalDateTime ldt;
        if(given instanceof LocalDate) {
            ldt = LocalDateTime.of((LocalDate) given, LocalTime.MIN);
            return ldt.plus(Duration.ofSeconds(1000000000l));
        }
        else {
        	ldt = LocalDateTime.from(given);
        }
        return ldt.plus(Duration.ofSeconds(1000000000l));
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		int sum=0;
		Set<Integer> multiples=new HashSet<Integer>();
		for(int j=0; j < set.length; j++) {
			int counter=1;
			int multiple=counter*set[j];
			while(multiple < i) {
				multiples.add(multiple);
				counter++;
				multiple=counter*set[j];
			}
		}
		for(Integer k : multiples) {
			sum+=k;
		}
		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		// TODO Write an implementation for this method declaration
		int firstSpace=0;
		int lastSpace=0;
		String numStream="";
		String valid="";
		for(int i=0; i < string.length(); i++) {
			Character c=string.charAt(i);
			if(48 <= c && c <=57 || c==32) { //number or space
				if(c==32) {
					if(numStream.length() > 1) { //has to be stream of numbers larger than length 1
						valid+=numStream;
					}
					numStream="";
				}
				else {
					numStream+=c.toString();
				}
			}
			else {
				return false;
			}
		}
		if(numStream.length() > 1) {
			valid+=numStream;
		}
		boolean toDouble=false;
		Integer numsToSum=0;
		for(int j=valid.length(); j > 0; j--) {
			Integer toAdd=Integer.parseInt(valid.substring(j-1, j));
			if(toDouble==true) {
				if(toAdd*2>9) {
					numsToSum+=(toAdd*2-9);
				}
				else {
					numsToSum+=(toAdd*2);
				}
				toDouble=false;
			}
			else {
				toDouble=true;
				numsToSum+=toAdd;
			}
			
		}
		return numsToSum%10==0;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		
		string=string.substring(0, string.length()-1); //strip question mark
		Map<String, Integer> operator=new HashMap<String, Integer>();
		operator.put("plus", 0);
		operator.put("minus", 1);
		operator.put("multiplied", 2);
		operator.put("divided", 3);
		int whatOperator=-1;
		int[] nums=new int[2];
		int totalNumbers=0;
		String[] words=string.split(" ");
		int numNumbers=0;
		for(String word : words) {
			if(operator.get(word)!=null) {
				whatOperator=operator.get(word);
			}
			try {
				nums[totalNumbers]=Integer.parseInt(word);
				totalNumbers++;
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		switch (whatOperator) {
		case 0:
			return nums[0]+nums[1];
		case 1:
			return nums[0]-nums[1];
		case 2:
			return nums[0]*nums[1];
		case 3:
			return nums[0]/nums[1];

		default:
			break;
		}
		
		return 0;
	}

}
