package fr.ensisa.hassenforder.brittle.bank.network;

import java.io.InputStream;

import fr.ensisa.hassenforder.brittle.network.Protocol;
import fr.ensisa.hassenforder.network.BasicAbstractReader;

public class BankReader extends BasicAbstractReader {


	private long number;
	private String owner;

	public String getOwner() {
		return owner;
	}

	public long getNumber()
	{
		return number;
	}

	public BankReader(InputStream inputStream) {
		super (inputStream);
	}

	private void eraseFields()
	{
		number = 0;
	}

	public void receive()
	{
		type = readInt ();
		eraseFields ();
		switch (type) {
		case 0 : break;
		case Protocol.REQUEST_POPULATE:
			readPopulate();
			break;
		case Protocol.REQUEST_INVALIDATE:
			readInvalidate();
			break;
		case Protocol.REQUEST_CREATE:
			readCreate();
			break;
		case Protocol.REQUEST_GET_CARD:
			readGetCard();
			break;
		}
	}
	private void readGetCard()
	{
		number = readLong();
	}

	private void readCreate()
	{
		owner = readString();
	}

	private void readInvalidate()
	{
		number = readLong();
	}


	private void readPopulate()
	{
	}

}
