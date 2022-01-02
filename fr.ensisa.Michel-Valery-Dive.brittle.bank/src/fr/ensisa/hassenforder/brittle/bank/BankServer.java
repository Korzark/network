package fr.ensisa.hassenforder.brittle.bank;

import java.net.ServerSocket;
import java.net.Socket;

import fr.ensisa.hassenforder.brittle.bank.model.Model;
import fr.ensisa.hassenforder.brittle.bank.network.BankSession;
import fr.ensisa.hassenforder.brittle.network.Protocol;

public class BankServer extends Thread {

	private ServerSocket server = null;
	private Model model = null;

	public BankServer(Model model) {
		super();
		this.model = model;
	}

	public void run () {
		try {
			server = new ServerSocket (Protocol.BANK_TCP_PORT);
			while (true) {
				Socket connection = server.accept();
				BankSession session = new BankSession (connection, model);
				session.start ();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
