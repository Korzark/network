package fr.ensisa.hassenforder.brittle.management.network;

import java.util.List;

import fr.ensisa.hassenforder.brittle.management.model.Card;

/**
 *
 * @author hassenforder
 */
public interface ISession {

    boolean open ();
    boolean close ();

    Long createCard (String owner);
    Card getCard (long number);
    Boolean depositTo (long number, long amount);
    Boolean withdrawFrom (long number, long amount);
    Boolean invalidate (long number);

    Boolean populateCards ();
    List<Card> getAllCards ();
}
