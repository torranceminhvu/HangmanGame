package hangman;

/*
 *  Program created by Minh Vu
 * 
 * 
 */

/**
 *
 * @author Minh Vu
 */
public class LetterRack {
   // The word the user wants to guess
   private String mystery;
   
   // The mystery word masked
   private String matchedSoFar;
   
   // The letters already guessed
   private String guessedLetters;
   
   // The letters available to guess
   private String availableLetters;
   
   // count for the number of letters matched in the mystery word
   private int matchCount;
   
   // the alphabet to reset
   private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
   
   /* initalize contructor */
   public LetterRack (String m) {
       mystery = m;
       guessedLetters = "";
       availableLetters = ALPHABET;
       
       // mask the mystery word to display
       maskMystery();
   }
   
   /* Function that will help start new game in GUI */
   public void newGame(String m) {
       mystery = m;
       reset();
   }
   
   /* Function that will help reset game with same mystery word in GUI */
   public void reset() {
       guessedLetters = "";
       availableLetters = ALPHABET;
       maskMystery();
   }
   
   /* Function to mask the mystery word with * */
   public void maskMystery() {
       StringBuilder temp = new StringBuilder();
       for (int i = 0; i < getMystery().length(); i++) {
           temp.append("*");
       }
       matchCount = 0;
       matchedSoFar = temp.toString();
   }
   
   /* Funnction that will take a character and check if it matches any of 
   the guessed character
   */
   public int guessChar (char c) {
       StringBuilder guessed = new StringBuilder(getGuessedLetters());
       StringBuilder available = new StringBuilder(getAvailableLetters());
       StringBuilder matched = new StringBuilder(getMatchedSoFar());
       int count = 0;
       
       /* Check if the character has been guessed before */
       for (int i = 0; i < guessed.length(); i++) {
           if (guessed.charAt(i) == c) {
               return -1;
           }
       }
       // add guessed character to the end of guessed string
       guessed.append(c);
       
       // remove guessed char from available string
       for (int i = 0; i < available.length(); i++) {
           if (available.charAt(i) == c) {
               available.deleteCharAt(i);
               break;
           }
       }
       
       // check if guessed char matches mystery word and change mask
       for (int i = 0; i < getMystery().length(); i++) {
           if (c == getMystery().charAt(i)) {
               matched.setCharAt(i, c);
               matchCount++;
               count++;
           }
       }
       
       guessedLetters = guessed.toString();
       availableLetters = available.toString();
       matchedSoFar = matched.toString();
       
       /* Check if any character has been matched correctly, if so return 1 */
       if (count > 0) {
           return 1;
       }
       
       /* Nothing matched, return 0 */
       return 0;
   }
   
   /* Function that only returns true if the guessed word matches the mystery */
   public boolean guessWord (String w) {
       return w.equals(getMystery());
   }
   
   /* Function to check if matchChar has matched the whole word */
   public boolean isSolved () {
       return matchCount == getMystery().length();
   }

    /**
     * @return the mystery
     */
    public String getMystery() {
        return mystery;
    }

    /**
     * @return the matchedSoFar
     */
    public String getMatchedSoFar() {
        return matchedSoFar;
    }

    /**
     * @return the guessedLetters
     */
    public String getGuessedLetters() {
        return guessedLetters;
    }

    /**
     * @return the availableLetters
     */
    public String getAvailableLetters() {
        return availableLetters;
    }
}
