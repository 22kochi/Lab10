/** Heading  *****************************************************/
/*	Your name: Andrew Kochi	
	Class block: G Block				Date Started: 2/6/2020
	Lab Number: 10
	Title: Cipher Phase 2
	Purpose: Store the frequency of each letter in an array of Records
*/

import java.util.Comparator;
public class Record implements Comparable<Record>{

	private char letter;
	private int freq;
	private char newLetter;
	
	
	public Record(){
		//default
	}
	
	public Record(int a, char b){
		freq = a;
		letter = b;
	}
	
	public Record(char b, int a) {
		freq = a;
		letter = b;
	}
	public Record(int a, char b, char c) {
		freq = a;
		letter = b;
		newLetter = c;
	}
	
	
	
	//getters
	public int getFreq() {
		return freq;
	}
	public char getLetter(){
		return letter;
	}
	public char getNewLetter() {
		return newLetter;
	}
	
	//setters
	public void setFreq(int newFrequency) {
		freq = newFrequency;
	}
	public void setLetter(char newLetter) {
		letter = newLetter;
	}
	public void setNewLetter(char a) {
		newLetter = a;
	}
	
	public int compareTo(Record newVal) {
		return newVal.getFreq() - this.getFreq();
		
		
	}
	
	public static Comparator<Record> letterComparator = new Comparator<Record>() {

        @Override
        public int compare(Record e1, Record e2) {
            return (int) (e1.getLetter() - e2.getLetter());
        }
	};
     public static Comparator<Record> newLetterComparator = new Comparator<Record>() {

        @Override
        public int compare(Record e1, Record e2) {
             return (int) (e1.getNewLetter() - e2.getNewLetter());
           }
    };

    
	
}
