
    public static void main(String[] args) {

        // Test the Card class.
        Hand hand = new Hand(10);
        Card cardObj = new Card(2, Card.DIAMONDS);
        Card cardObj2 = new Card (6, Card.SPADES);
        Card cardObj3 = new Card (10, Card.CLUBS);
        Card cardObj4 = new Card (10, Card.CLUBS);
        Card cardObj5 = new Card (11, Card.HEARTS);
        Card cardObj6 = new Card (9, Card.SPADES);
        Card cardObj7 = new Card (8, Card.DIAMONDS);
        Card cardObj8 = new Card (11, Card.SPADES);


        hand.addCard(cardObj); // add card, using the instance
        hand.addCard(cardObj2);
        hand.addCard(cardObj3);
        hand.addCard(cardObj4);
        hand.addCard(cardObj5);
        hand.addCard(cardObj6);
        hand.addCard(cardObj7);
        hand.addCard(cardObj8);
        hand.addCard(cardObj2);
        System.out.println(hand.toString());
        System.out.println(hand.getCard(2));
        System.out.println(hand.playCard(1));
        System.out.println(hand.toString());
        System.out.println(hand.highCard());
        System.out.println(hand.numCardsOfRank(11));
        System.out.println(hand.hasFlush());
        System.out.println("tested");
        // Create a Card object with reference variable c1.
        Card c1 = new Card(2, Card.DIAMONDS);
        // Create a Card object with reference variable c2.
        Card c2 = new Card(Card.ACE, Card.DIAMONDS);
        Card c3 = new Card(Card.KING, Card.SPADES);
        Card c4 = new Card(10, Card.CLUBS);
        Card c5 = new Card(10, Card.HEARTS);

        System.out.println(Card.ACE);
        System.out.println(c1.getRank());
        System.out.println(c1.getSuit());
        System.out.println(c1.getValue());
        System.out.println();
        System.out.println(c1);
        System.out.println(c1.getAbbrev());
        System.out.println(c2);
        System.out.println(c2.getAbbrev());
        System.out.println(c3);
        System.out.println(c3.getAbbrev());
    }
