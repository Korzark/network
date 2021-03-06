package fr.ensisa.hassenforder.brittle.management.network;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import fr.ensisa.hassenforder.brittle.management.model.Card;
import fr.ensisa.hassenforder.brittle.network.Protocol;

public class ManagementSession implements ISession {

    private Socket tcp;
    private String host;
    private int port;

    public ManagementSession(String host, int port) {
    	this.host = host;
    	this.port = port;
    }

    @Override
    synchronized public boolean close() {
        try {
            if (tcp != null) {
                tcp.close();
            }
            tcp = null;
        } catch (IOException e) {
        }
        return true;
    }

    @Override
    synchronized public boolean open() {
        this.close();
        try {
            tcp = new Socket(this.host, this.port);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

	@Override
	public Boolean populateCards() {
        try {
        	ManagementWriter w = new  ManagementWriter(tcp.getOutputStream());
        	w.createPopulateMessage();
        	w.send();

        	ManagementReader r = new ManagementReader(tcp.getInputStream());
        	r.receive();

        	if(r.getType()== Protocol.REPLY_OK) return Boolean.TRUE;
        	if(r.getType()== Protocol.REPLY_KO) return Boolean.FALSE;

        	return null;

        } catch (IOException e) {
    		return null;
        }
	}

	@Override
	public Long createCard(String owner) {
        try {
        	ManagementWriter w = new ManagementWriter(tcp.getOutputStream());
        	w.createCreateMessage(owner);
        	w.send();

        	ManagementReader r = new ManagementReader(tcp.getInputStream());
        	r.receive();

        	if(r.getType()== Protocol.REPLY_CARD_ID) return r.getcardID();
        	if(r.getType()== Protocol.REPLY_KO) return 0L;


        	return null;
        } catch (IOException e) {
    		return null;
        }
	}

	@Override
	public Card getCard(long number) {
        try {
        	ManagementWriter w = new ManagementWriter(tcp.getOutputStream());
        	w.createGetMessage(number);
        	w.send();

        	ManagementReader r = new ManagementReader(tcp.getInputStream());
        	r.receive();

        	if(r.getType()== Protocol.REPLY_CARD_ID) r.getCard();
        	if(r.getType()== Protocol.REPLY_KO) return null;
        return null;
        } catch (IOException e) {
    		return null;
        }
	}

	@Override
	public Boolean depositTo(long number, long amount) {
        try {
        	// cette instruction est imp?rativement ? retirer
        	// elle permet de neutraliser les alertes du compilateur suite ? la suppression du code
        	if ("fake".equals("fake")) throw new IOException("fake exception");
    		return null;
        } catch (IOException e) {
    		return null;
        }
	}

	@Override
	public Boolean withdrawFrom(long number, long amount) {
        try {
        	// cette instruction est imp?rativement ? retirer
        	// elle permet de neutraliser les alertes du compilateur suite ? la suppression du code
        	if ("fake".equals("fake")) throw new IOException("fake exception");
    		return null;
        } catch (IOException e) {
    		return null;
        }
	}

	@Override
	public Boolean invalidate(long number) {
        try {
        	ManagementWriter w = new ManagementWriter(tcp.getOutputStream());
        	w.createInvalidateMessage(number);
        	w.send();

        	ManagementReader r = new ManagementReader(tcp.getInputStream());
        	r.receive();

        	if(r.getType()== Protocol.REPLY_OK) return Boolean.TRUE;
        	if(r.getType()== Protocol.REPLY_KO) return Boolean.FALSE;


    		return null;
        } catch (IOException e) {
    		return null;
        }
	}

	@Override
	public List<Card> getAllCards() {
        try {
        	ManagementWriter w = new ManagementWriter(tcp.getOutputStream());
        	w.createGetAllMessage();
        	w.send();
        	ManagementReader r = new ManagementReader(tcp.getInputStream());
            r.receive();
     		if (r.getType() == Protocol.REPLY_CARDS) return r.getCards();
     		if (r.getType() == Protocol.REPLY_KO) return null;
    		return null;
        } catch (IOException e) {
    		return null;
        }
	}

}
