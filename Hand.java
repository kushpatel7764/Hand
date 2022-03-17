package bsu.comp152;

/** Hand - A class to create Hand objects and get to know them

  Starter code from Computer Science 111, Boston University

  Modified by Laura K. Gross, COMP 152, Bridgewater State University

  Completed by: [student name], [student email]
          date: [date of completion]
 */
public class Hand {
    /* Constants for types of hands
     * The numbers used for the hand types increase
     * with the value of the hand type.
     * For example, four-of-a-kind is the highest-valued
     * hand type that we support, and it has the highest
     * numeric value.
     */
    private static final int HIGH_CARD = 0;
    private static final int PAIR = 1;
    private static final int TWO_PAIRS = 2;
    private static final int THREE_OF_A_KIND = 3;
    private static final int FLUSH = 4;
    private static final int FOUR_OF_A_KIND = 5;

    // The instance fields for a Hand object
    // The Card objects in the Hand object are stored in array called cards.
    // Declare the array of Card objects.
    private Card[] cards;
    // The number of cards in the hand is called numCards.
    // Declare it as an integer.
    private int numCards;

    // Constructor for the class
    public Hand (int size){
        cards = new Card[size];
        numCards = 0;
    }
    //Setters

    // Method to add a cardObject in the cards array.
    public void addCard(Card cardObject){
        /*
            First make sure that a parameter is entered
            If no parameter is entered then throw an IllegalArgumentException.
        */
        if (cardObject == null){
            throw new IllegalArgumentException("Card Object not defined");
        }
        // Create a for-loop to check all the indexes from left to right
        for (int i = 0; i < cards.length; i++){
            // if an index is empty then add the card there, increment cards in hand, and exit the function with return.
            if (cards[i] == null){
                cards[i] = cardObject;
                numCards++;
                return;
            }

        }
        /*
            If all indexes are full and the if-statement is not called then there is no more space to add a card.
            So throw a IllegalStateException to show that the array is full.
        */
        throw new IllegalStateException("Object cards is at max size");
    }

    public Card playCard(int Index){
        if (Index < 0 || Index > (numCards - 1) || cards[Index] == null){
            throw new IllegalArgumentException("Incorrect Index");
        }
        Card playedCard = cards[Index];
        cards[Index] = null;
        for (int i = 0; i < numCards; i++){
            if (cards[i] == null && i != (numCards - 1)){
                cards[i] = cards[i + 1];
                cards[i + 1] = null;
            }
        }
        numCards--;
        return playedCard;
    }




    //Getter

    // getTotalValue computes and returns the sum of the values of the cards in the cards array.
    public int getTotalValue(){
        int value = 0;
        //Calculate the running value by adding the value of each in the hand.
        for (int i = 0; i < numCards; i++){
            value += cards[i].getValue();
        }
        // Once call values are added, return the final total.
        return value;
    }

    // getNumCards get the number of cards in hand
    public int getNumCards (){
        // return the numCards variable since it keep a count of all the cards that are current in hand.
        return numCards;
    }

    /*
        toString method is supposed to return the string representation of that Hand object or all cards objects in hand.
        The card objects returned should be abbreviations.
        The returned string should be formatted so that all the returned card objects are separated with commas
        and the entire hand is covered with brackets.
     */
    public String toString (){
        // Output variable is set up with an opening bracket to start covering the returned string with bracket.
        String output = "[";
        // The for-loop iterates through all the cards in hand
        for (int i = 0; i < numCards; i++) {
            if (i == numCards -1){ // if on the last index then do not add a comma to the string
                output += cards[i].getAbbrev(); // just add the abbreviation at the i index to the bigger output string
            }
            else{
                //if not the last index then add a comma to the string
                output += (cards[i].getAbbrev() + ","); //get the abbreviation of the card at the i index and add a comma after it to the output string
            }
        }
        // lastly, close off the entire string that has all the card abbreviations with a closing bracket
        return output += "]"; // then return the final string

    }
    //Method to get a card from a specified index in the cards array.
    public Card getCard (int cardIndex){
        // Check to make sure the entered index is valid by making sure it is greater than number of cards in hand and is not a negative number,
        if (cardIndex < 0 || cardIndex > (numCards - 1)){
            throw new IllegalArgumentException("Incorrect Index");
        }
        // If the index is valid then return the card object with the specified index.
        else {
            return cards[cardIndex];
        }
    }

    public Card highCard() {
        boolean n = false;
        int oldInt = 0;
        Card largeCard = null;
        for (int i = 0; i < numCards; i++) {
            int currentInt = cards[i].getValue(); // Book page 99
            if (!n) { // Idea help me to simplify n == false to !n
                oldInt = currentInt;
                largeCard = cards[i];
                n = true;
            }
            if (n) { // automatically checks to see if it is true or not.
                if (oldInt < currentInt) {
                    oldInt = currentInt;
                    largeCard = cards[i];
                }

            }
        }
        return largeCard;
    }

    public int numCardsOfRank(int rank){
        int numCardRank = 0;
        for (int i = 0; i < numCards; i++){
            if (cards[i].getRank() == rank){
               numCardRank++;
            }
        }
        return numCardRank;
    }

    public boolean hasFlush(){
        boolean flush = true;
        for (int i = 0; i < numCards; i++){
            if (i == numCards - 1){
                break;
            }
            else if (cards[i].getSuit() == cards[i + 1].getSuit()){
                flush = true;
            }
            else {
                flush = false;
                break;
            }
        }
        return flush;
    }

}
