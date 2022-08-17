package com.sprinthive.pokerhands;

import com.sprinthive.pokerhands.exception.NotEnoughCardsInDeckException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	private List<Card> cards = new ArrayList<Card>(52);

	public Deck() {
		for (Suit suit : Suit.values()) {
			for (CardRank rank : CardRank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
		Collections.shuffle(cards);
	}

	public synchronized int getNumberOfCards() {
		return cards.size();
	}

	public synchronized Card[] pick(int numberOfCards) throws NotEnoughCardsInDeckException {
		if (numberOfCards > 52) {
			throw new IllegalArgumentException("Number of cards to pick from a deck must be 52 or less.");
		}

		Card[] arr = new Card[numberOfCards];		
		arr = cards.toArray(arr);

		if (numberOfCards == 0) {
			return new Card[0];
		}
		for (int i = 0; i < numberOfCards; i++) {
			cards.remove(0);
		}
		
		return arr;
	}
}
