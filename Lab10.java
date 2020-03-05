import java.io.File;
/** Heading  *****************************************************/
/*	Your name: Andrew Kochi	
	Class block: G Block				Date Started: 2/6/2020
	Lab Number: 10
	Title: Cipher Phase 2
	Purpose: Store the frequency of each letter in an array of Records
 */
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class Lab10 {
	private static Scanner input;
	private static Scanner input2;
	private static Scanner freqChart;
	private static Scanner user;
	

	public static void main(String[] args) 
	{
		Record[]  array = new Record[26];  	
		//set each letter any freq to 0
		int j = 0;
		for(char i = 'a'; i<='z'; i++) {
			array[j] = new Record(0, i);

			j++;
		}

		String pathname = "Cipher.txt";
		File file = new File(pathname);
		File file2 = new File("Replace.txt");
		input = null;
		try
		{
			input = new Scanner(file);
			input2 = new Scanner(file);
			freqChart  = new Scanner(file2);
			//declare array


			getFre(array);
			//sort by frequency
			array = setLet(array);
			//print 

			for(int i = 0; i< 26; i++) {
				System.out.println(array[i].getNewLetter() + " " + array[i].getFreq());
			}
			
			decode(array);


		}
		catch (FileNotFoundException ex)
		{
			System.out.println("*** Cannot open " + pathname
					+ " ***");
			System.exit(1);  // quit the program
		} 


	}
	public static void getFre(Record[] array) {
		//go through each word
		while(input.hasNext()) {
			String w = input.next();
			//counter for each letter in the word
			for(int i = 0; i < w.length(); i++) {

				char letter = w.charAt(i);

				//compare that letter to each letter in Record[]

				if(letter >= 97 && letter <= 123) {
					array[letter-97].setFreq(array[letter-97].getFreq() + 1);
				}
			}
		}
		for(int i = 0; i< 26; i++) {
			System.out.println(array[i].getLetter() + " " + array[i].getFreq() + " " + array[i].getNewLetter());
		}
	}


	public static void decode(Record[] array) {
		String pathname = "Cipher.txt";
		File file = new File(pathname);
		user = new Scanner(System.in);

		try {
			input = new Scanner(file);
			input2 = new Scanner(file);

			//keep track of letters with the same freq
			int[]  Repeats = new int[26];  	
			for(int i = 0; i< 25; i++) {
				int freq1 = array[i].getFreq();
				int freq2 = array[i+1].getFreq();

				if(freq1 == freq2) {
					Repeats[i] = 1;
					Repeats[i+1] = 1;
				}
			}

			
			//go through each word
			while(input.hasNextLine()) {
				String w = input.nextLine();
				//counter for each letter in the word
				for(int i = 0; i < w.length(); i++) {

					char letter = w.charAt(i);
					//compare that letter to each letter in Record[]
					
					if(letter >= 97 && letter <= 123 && Repeats[letter - 97] != 1) {
						System.out.print(array[letter-97].getNewLetter());
					}
					
					
					//letters with the same frequency
					else if(letter >= 97 && letter <= 123 && Repeats[letter-97] == 1){
						/*
						if(Repeats[letter-96] == 1) {
							System.out.print(letter);
							//System.out.println(" Select between " + array[letter-97].getNewLetter() + " and " + array[letter-96].getNewLetter() );
						}
						else if(Repeats[letter-98] == 1) {
						System.out.println(" Select between " + array[letter-97].getNewLetter() + " and " + array[letter-98].getNewLetter() );
						
						}
						
						String userInput = user.next();
						System.out.print(userInput);
						*/
						System.out.print(array[letter-97].getNewLetter());
					}
					
					//punctuation/spaces
					else{
						System.out.print(letter);
					}
						
				}
				System.out.println();
			}
			
			for(int i = 0; i< 26; i++) {
				//System.out.print(array[i].getNewLetter());
				if(Repeats[i] == 1) {
					System.out.println("Would you like to switch " + array[i].getNewLetter() + " and " + array[i+1].getNewLetter() + "? (Y or N)");
					
					String YorN = user.next();
					//System.out.println(YorN);
					if(YorN.compareTo("Y") == 0) {
						char temp = array[i].getNewLetter();
						array[i].setNewLetter(array[i+1].getNewLetter());
						array[i+1].setNewLetter(temp);
						//System.out.println("If statement");
					}
					i++;
					}
			}

			//go through each word
			while(input2.hasNextLine()) {
				String w = input2.nextLine();
				//counter for each letter in the word
				for(int i = 0; i < w.length(); i++) {

					char letter = w.charAt(i);
					//compare that letter to each letter in Record[]
					
					if(letter >= 97 && letter <= 123 && Repeats[letter - 97] != 1) {
						System.out.print(array[letter-97].getNewLetter());
					}
					//letters with the same frequency
					else if(letter >= 97 && letter <= 123 && Repeats[letter-97] == 1){
						
						System.out.print(array[letter-97].getNewLetter());
					}
					
					//punctuation/spaces
					else{
						System.out.print(letter);
					}
						
				}
				System.out.println();
			}
			
		}

		catch (FileNotFoundException ex)
		{
			System.out.println("*** Cannot open " + pathname
					+ " ***");
			System.exit(1);  // quit the program
		}
	}



	//copy and pasted selection sort
	public static Record[] setLet(Record[] array) {
		String pathname = "file2";

		try {
			File file2 = new File("Replace.txt");
			freqChart  = new Scanner(file2);
			Arrays.sort(array);
			
			
			/*
			for (int n = array.length; n > 1; n--)
			{
				// Find the index iMax of the largest element
				//   among list[0], ..., list[n-1]:

				int iMax = 0;
				for (int i = 1; i < n; i++)
				{
					if (array[i].getFreq() < array[iMax].getFreq())
						iMax = i;
				}

				char a = 'a';
				if(a == 'c') {
					System.out.println();
				}
				//written out swap function
				int iMaxF = array[iMax].getFreq();
				char iMaxL = array[iMax].getLetter();
				char iMaxN = array[iMax].getNewLetter();

				array[iMax].setFreq(array[n-1].getFreq());
				array[iMax].setLetter(array[n-1].getLetter());
				array[iMax].setNewLetter(array[n-1].getNewLetter());

				array[n-1].setFreq(iMaxF);
				array[n-1].setLetter(iMaxL);
				array[n-1].setNewLetter(iMaxN);
				//swap(array, iMax, n-1);

			}

*/
			//sets each Record to the corresponding value in the frequency chart
			for(int i = 0; i < 26; i++) {
				String temp = freqChart.next();
				char letter = temp.charAt(0);

				array[i].setNewLetter(letter);
			}
		}

		catch (FileNotFoundException ex)
		{
			System.out.println("*** Cannot open " + pathname
					+ " ***");
			System.exit(1);  // quit the program
		}
		return array;
	}


}
/*
//make an int array of each frequency
int[] freqs = new int[26];
for(int i = 0; i < 26; i++)
	freqs[i] = array[i].getFreq();

//sort the frequencies
Arrays.sort(freqs);

//empty sorted array
Record[] sorted = new Record[26];

//counter for char to set NewLetter in sorted[]
int z = 0;

//set each Record in sorted in order of frequency
for(int i = 0; i< 26; i++) {


	sorted[i].setFreq(freqs[i]);
	char let = 'Z';
	//set each Record's letter to the letter that corresponds with that frequency
	for(int j = 0; j < 26; j++) {
		if(array[j].getFreq() == sorted[i].getFreq())
			let = array[j].getLetter();
	}
	sorted[i].setLetter(let);

	//set NewLetter to the corresponding letter on the frequency chart
	sorted[i].setNewLetter(freqChart.next().charAt(z));
	z++;
}
 */
