package fr.ensisa.hassenforder.brittle.bank;

import java.net.ServerSocket;
import java.net.Socket;

import fr.ensisa.hassenforder.brittle.bank.model.Model;
import fr.ensisa.hassenforder.brittle.bank.network.PaymentSession;
import fr.ensisa.hassenforder.brittle.network.Protocol;

public class PaymentServer extends Thread {

	private ServerSocket server = null;
	private Model model = null;

	public PaymentServer(Model model) {
		super();
		this.model = model;
	}

	public void run () {
		try {
			server = new ServerSocket (Protocol.PAYMENT_TCP_PORT);
			while (true) {
				Socket connection = server.accept();
				PaymentSession session = new PaymentSession (connection, model);
				session.start ();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
