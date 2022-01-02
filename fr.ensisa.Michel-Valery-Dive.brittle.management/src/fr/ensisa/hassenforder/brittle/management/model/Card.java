package fr.ensisa.hassenforder.brittle.management.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Card {

	private LongProperty number;
	private IntegerProperty ccv;
	private StringProperty owner;
	private LongProperty amount;
	private BooleanProperty valid;

	public Card() {
		super();
		this.number = new SimpleLongProperty ();
		this.ccv = new SimpleIntegerProperty();
		this.owner = new SimpleStringProperty ();
		this.amount = new SimpleLongProperty();
		this.valid = new SimpleBooleanProperty();
	}

	public Card(long number, int ccv, String owner, long amount, boolean valid) {
		super();
		this.number = new SimpleLongProperty (number);
		this.ccv = new SimpleIntegerProperty(ccv);
		this.owner = new SimpleStringProperty (owner);
		this.amount = new SimpleLongProperty(amount);
		this.valid = new SimpleBooleanProperty(valid);
	}

	public LongProperty getNumber() {
		return number;
	}

	public IntegerProperty getCcv() {
		return ccv;
	}

	public StringProperty getOwner() {
		return owner;
	}

	public LongProperty getAmount() {
		return amount;
	}

	public BooleanProperty getValid() {
		return valid;
	}

	public void setWith(Card with) {
		if (with == null) {
			this.number.set(0);
			this.ccv.set(0);
			this.owner.set("");
			this.amount.set(0);
			this.valid.set(false);
		} else {
			this.number.set(with.getNumber().get());
			this.ccv.set(with.getCcv().get());
			this.owner.set(with.getOwner().get());
			this.amount.set(with.getAmount().get());
			this.valid.set(with.getValid().get());
		}
	}

}
