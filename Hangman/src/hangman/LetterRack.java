package hangman;

/*
 *  Program created by Minh Vu
 * 
 * 
 */

/**
 *
 * @author Minh
 */
public class LetterRack {
   private String mystery;
   private String matchedSoFar;
   private String guessedLetters;
   private String availableLetters;
   private int matchCount;
   private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   
   public LetterRack (String m) {
       mystery = m;
       guessedLetters = "";
       availableLetters = ALPHABET;
       matchCount = 0;
       
       StringBuilder temp = new StringBuilder();
       for (int i = 0; i < mystery.length(); i++) {
           temp.append("*");
       }
       matchedSoFar = temp.toString();
   }
   
   public void newGame(String m) {
       mystery = m;
       reset();
   }
   
   public void reset() {
       guessedLetters = "";
       availableLetters = ALPHABET;
       maskMystery();
   }
   
   public void maskMystery() {
       StringBuilder temp = new StringBuilder();
       for (int i = 0; i < getMystery().length(); i++) {
           temp.append("*");
       }
       matchCount = 0;
       matchedSoFar = temp.toString();
   }
   
   public int guessChar (char c) {
       StringBuilder guessed = new StringBuilder(getGuessedLetters());
       StringBuilder available = new StringBuilder(getAvailableLetters());
       StringBuilder matched = new StringBuilder(getMatchedSoFar());
       int count = 0;
       
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
       
       if (count > 0) {
           return 1;
       }
       return 0;
   }
   
   public boolean guessWord (String w) {
       return w.equals(getMystery());
   }
   
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
