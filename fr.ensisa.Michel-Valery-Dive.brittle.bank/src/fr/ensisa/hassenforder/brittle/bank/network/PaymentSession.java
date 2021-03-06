package fr.ensisa.hassenforder.brittle.bank.network;

import java.io.IOException;
import java.net.Socket;

import fr.ensisa.hassenforder.brittle.bank.model.Model;
import fr.ensisa.hassenforder.brittle.network.Protocol;

public class PaymentSession extends Thread {

	private Socket connection;
	private Model model;

	public PaymentSession(Socket connection, Model model) {
		this.connection = connection;
		this.model = model;
	}

	public void close () {
		this.interrupt();
		try {
			if (connection != null)
				connection.close();
		} catch (IOException e) {
		}
		connection = null;
	}

	private void processXXX(PaymentReader reader, PaymentWriter writer) {
	}

	public boolean operate() {
		try {
			PaymentWriter writer = new PaymentWriter (connection.getOutputStream());
			PaymentReader reader = new PaymentReader (connection.getInputStream());
			reader.receive ();
			switch (reader.getType ()) {
			case 0 : return false; // socket closed
			case Protocol.REQUEST_XXX		: processXXX	(reader, writer); break;
			default: return false; // connection jammed
			}
			writer.send ();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public void run() {
		while (true) {
			if (! operate())
				break;
		}
		try {
			if (connection != null) connection.close();
		} catch (IOException e) {
		}
	}

}
