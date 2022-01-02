package fr.ensisa.hassenforder.brittle.bank.model;

import java.util.Random;

public class Card {

	static Random rnd = new Random();
	private long number;
	private int ccv;
	private String owner;
	private long amount;
	private boolean valid;

	public Card(String owner) {
		super();
		buildCardCredentials ();
		this.owner = owner;
		this.amount = 0;
		this.valid = true;
	}

	private void buildCardCredentials () {
		this.number = 1000+rnd.nextInt(9000);
		this.ccv = 100+rnd.nextInt(900);
	}

	public long getNumber() {
		return number;
	}

	public int getCcv() {
		return ccv;
	}

	public long getAmount() {
		return amount;
	}

	public boolean deposit(long amount) {
		this.amount += amount;
		return true;
	}

	public boolean withdraw(long amount) {
		if (this.amount < amount) return false;
		this.amount -= amount;
		return true;
	}

	public String getOwner() {
		return owner;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
