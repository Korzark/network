package fr.ensisa.hassenforder.brittle.bank.network;

import java.io.OutputStream;

import fr.ensisa.hassenforder.brittle.network.Protocol;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;

public class PaymentWriter extends BasicAbstractWriter {

    public PaymentWriter(OutputStream outputStream) {
        super (outputStream);
    }

}
