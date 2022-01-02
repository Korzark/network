package fr.ensisa.hassenforder.brittle.bank.network;

import java.io.OutputStream;
import java.util.Collection;

import fr.ensisa.hassenforder.brittle.bank.model.Card;
import fr.ensisa.hassenforder.brittle.network.Protocol;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;

public class BankWriter extends BasicAbstractWriter {

    public BankWriter(OutputStream outputStream) {
        super (outputStream);
    }

	public void createOK()
	{
		writeInt(Protocol.REPLY_OK);
	}

	public void createKO()
	{
		writeInt(Protocol.REPLY_KO);
	}

	public void createCardID(Card card)
	{
		writeInt(Protocol.REPLY_CARD_ID);
		writeLong(card.getNumber());
	}
	public void createCard(Card card)
	{
		writeInt(Protocol.REPLY_CARD);
		writeLong(card.getNumber());
		writeInt(card.getCcv());
		writeString(card.getOwner());
		writeLong(card.getAmount());
		writeBoolean(card.isValid());
	}

	public void createList(Collection<Card> cards) {
		writeInt(Protocol.REPLY_CARDS);
        writeInt(cards.size());
        for (Card card : cards) {
        	writeLong(card.getNumber());
    		writeInt(card.getCcv());
    		writeString(card.getOwner());
    		writeLong(card.getAmount());
    		writeBoolean(card.isValid());
        }

	}

}
