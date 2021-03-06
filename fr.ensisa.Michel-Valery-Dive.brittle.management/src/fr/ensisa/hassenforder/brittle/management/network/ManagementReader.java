package fr.ensisa.hassenforder.brittle.management.network;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.ensisa.hassenforder.brittle.management.model.Card;
import fr.ensisa.hassenforder.brittle.network.Protocol;
import fr.ensisa.hassenforder.network.BasicAbstractReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ManagementReader extends BasicAbstractReader {

	private long cardID;
	private Card card;
	private List<Card> cards;

	public List<Card> getCards() {
		return cards;
	}

    public Card getCard() {
		return card;
	}

	public long getcardID() {
		return cardID;
	}

	public ManagementReader(InputStream inputStream) {
        super(inputStream);
    }

    private void eraseFields() {
    }

	private void readCardID()
	{
		cardID = readLong();
	}

	public void receive() {
        type = readInt();
        eraseFields();
        switch (type) {
        case Protocol.REPLY_OK:
        	break;
        case Protocol.REPLY_KO:
        	break;
        case Protocol.REPLY_CARD_ID:
        	readCardID();
        	break;
        case Protocol.REPLY_CARD:
        	readCard();
        	break;
        case Protocol.REPLY_CARDS:
        	cards = readList();
        	break;
        }
    }

	private List<Card> readList() {
		List<Card> cards = new ArrayList<>();
		int size = readInt();
		for (int i=0;i<size;i++){
			cards.add(readCard());
		}
		return cards;
	}


	private Card readCard()
	{
		Card card = new Card();
		card.getNumber().set(readLong());
		card.getCcv().set(readInt());
		card.getOwner().set(readString());
		card.getAmount().set(readLong());
		card.getValid().set(readBoolean());

		return card;
	}

}
