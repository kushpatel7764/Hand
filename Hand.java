package bsu.comp152;

/** Hand - A class to create Hand objects and get to know them

  Starter code from Computer Science 111, Boston University

  Modified by Laura K. Gross, COMP 152, Bridgewater State University

  Completed by: Kush Patel, k6patel@student.bridgew.edu
          date: 3/06/22
 */
public class Hand {

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
            // Check if the hand is full or not by comparing the cards in the hand by the space in the cards array.
        if (numCards >= cards.length){
            /*
               If all indexes are full  then there is no more space to add a card.
               So throw a IllegalStateException to show that the array is full.
            */
            throw new IllegalStateException("Object cards is at max size");
        }
            // adds a card at the numCards' index
            cards[numCards] = cardObject;
            // numCards is incremented by one since now there is one more card in the hand.
            numCards++;
    }

    //Plays a card which means to remove it from the list and to move the rest of the cards to the right.
    public Card playCard(int Index){
        if (Index < 0 || Index > (numCards - 1) || cards[Index] == null){
            // if the entered index is incorrect or if there is no card at the index then throw an IllegalArgumentException.
            throw new IllegalArgumentException("Incorrect Index");
        }
        // Save the card at the index
        Card playedCard = cards[Index];
        //once card is saved then remove the card from that index in the hand
        cards[Index] = null;
        // iterate through the hand
        for (int i = 0; i < numCards; i++){
            // check for a null spot in the array, and I do not need to check the last index because if it is null then it can stay null.
            // There is no card after it, so I will not need to move any cards if the last index is null.
            if (cards[i] == null && i != (numCards - 1)){
                //give the current null index the value of the presiding index.
                cards[i] = cards[i + 1];
                // and make the presiding index null so the loop can work.
                cards[i + 1] = null;
            }
        }
        // Decrease the numCards value by 1 since one card was played and removed from the cards list.
        numCards--;
        //return the saved card that was played
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
        return output + "]"; // then return the final string

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
    // highCard is used to find the card with the highest value in the hand.
    public Card highCard() {
        // declare local variables
        boolean n = false;
        int oldInt = 0;
        Card largeCard = null;
        // iterate through the cards in hand.
        for (int i = 0; i < numCards; i++) {
            // save the value of the current cards value
            int currentInt = cards[i].getValue();
            // if this is the first iteration then saved the current value and the card with that value.
            if (!n) { // Idea help me to simplify n == false to !n
                oldInt = currentInt;
                largeCard = cards[i];
                n = true;
            }
            // On the second iteration...
            else {
                // check to see if the saved oldInt or card value is less than the current card value
                /*
                    if oldInt is the largest value then the below if statement will not run and card saved in the first iteration will be returned.
                    if OldInt is not the largest value then the current largest value will be saved in the oldInt and the card at the current
                    index value will also be saved. This loop will check and save the card with the largest value in the entire hand. Then largeCard
                    will return the card with the largest value.
                */
                if (oldInt < currentInt) {
                    oldInt = currentInt;
                    largeCard = cards[i];
                }

            }
        }
        // The card with the largest value is returned here.
        return largeCard;
    }
    //numCardsOfRank returns the number of cards that have the rank that was entered in the parameter.
    public int numCardsOfRank(int rank){
        // declare local variable
        int numCardRank = 0;
        // iterate through the hand array
        for (int i = 0; i < numCards; i++){
            // if the rank of the card at i is equal to the rank entered in index then increment numCardRank by 1.
            if (cards[i].getRank() == rank){
                numCardRank++;
            }
        }
        // lastly return the total number of cards with the same rank as the rank entered in index.
        return numCardRank;
    }
    // hasFlush returns true if all the cards in the Hand have the same suit, and false if they do not.
    public boolean hasFlush(){
        // Declare flush with initial true value.
        boolean flush = true;
        // iterate through the hand array
        for (int i = 0; i < numCards; i++){
            // break the loop on the last index since the last index is not needed alone. plus it will have been checked
            // by the index before it.
            if (i == numCards - 1){
                break;
            }
            // compare the suit of the current index and the index right after it. If all the cards have the same suit then flush will remain true
            else if (cards[i].getSuit() != cards[i + 1].getSuit()){
                flush = false;
                break;
            }
        }
        // The final value of flush is returned here
        return flush;
    }
