package fr.ensisa.hassenforder.brittle.bank.model;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Model {

	private Map<Long, Card> cards;

	public Model () {
		super();
	}

	private Map<Long, Card> getCards() {
		if (cards == null) cards = new TreeMap<>();
		return cards;
	}

	public void add(Card card) {
		getCards().put(card.getNumber(), card);
	}

	public boolean exists (long number) {
		return getCards().containsKey(number);
	}

	public Card get(long number) {
		if (! getCards().containsKey(number)) return null;
		return getCards().get(number);
	}

	public Collection<Card> getAll() {
		return getCards().values();
	}

	public void populate() {
		add (new Card ("Pierre"));
		add (new Card ("Paul"));
		add (new Card ("Jean"));
		add (new Card ("Jacques"));
		add (new Card ("Michel"));
		add (new Card ("Thomas"));
	}

	public boolean pay(int price, long number, int ccv, String owner) {
		if (price <= 0) return false;
		if (! exists(number)) return false;
		Card card = get(number);
		if (! card.isValid()) return false;
		if (card.getAmount() < price) return false;
		if (card.getCcv() != ccv) return false;
		if (! card.getOwner().equals(owner)) return false;
		card.withdraw(price);
		return true;
	}

}
