package fr.ensisa.hassenforder.brittle.bank.network;

import java.io.InputStream;

import fr.ensisa.hassenforder.brittle.network.Protocol;
import fr.ensisa.hassenforder.network.BasicAbstractReader;

public class PaymentReader extends BasicAbstractReader {

	public PaymentReader(InputStream inputStream) {
		super (inputStream);
	}

	private void eraseFields() {
	}

	private void readXXX() {
	}

	public void receive() {
		type = readInt ();
		eraseFields ();
		switch (type) {
		case 0 : break;
		case Protocol.REQUEST_XXX		: readXXX	(); break;
		}
	}

}
