package fr.ensisa.hassenforder.brittle.management.network;

import java.io.OutputStream;

import fr.ensisa.hassenforder.brittle.network.Protocol;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;

public class ManagementWriter extends BasicAbstractWriter {

    public ManagementWriter(OutputStream outputStream) {
        super(outputStream);
    }

	public void createPopulateMessage()
	{
		writeInt(Protocol.REQUEST_POPULATE);
	}

	public void createInvalidateMessage(long number)
	{
		writeInt(Protocol.REQUEST_INVALIDATE);
		writeLong(number);
	}

	public void createCreateMessage(String owner)
	{
		writeInt(Protocol.REQUEST_CREATE);
		writeString(owner);
	}

	public void createGetMessage(long number)
	{
		writeInt(Protocol.REQUEST_GET_CARD);
		writeLong(number);

	}

	public void createGetAllMessage() {
		writeInt(Protocol.REQUEST_GET_ALL_CARDS);
	}


}
