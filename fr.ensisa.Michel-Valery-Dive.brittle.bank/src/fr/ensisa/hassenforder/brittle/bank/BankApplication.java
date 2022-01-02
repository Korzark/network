package fr.ensisa.hassenforder.brittle.bank;

import fr.ensisa.hassenforder.brittle.bank.model.Model;

public class BankApplication {

	private Model model = null;
	private BankServer bank = null;
	private PaymentServer payment = null;

	public void start () {
		model = new Model();
		bank = new BankServer(model);
		bank.start();
		payment = new PaymentServer(model);
		payment.start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BankApplication m = new BankApplication ();
		m.start();
	}

}
