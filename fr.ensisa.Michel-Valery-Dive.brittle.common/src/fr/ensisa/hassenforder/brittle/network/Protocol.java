package fr.ensisa.hassenforder.brittle.network;

public class Protocol {

    public static final int BANK_TCP_PORT				= 1666;
    public static final int MERCHANT_TCP_PORT			= 2666;
    public static final int PAYMENT_TCP_PORT			= 3666;
    public static final int STEALER_TCP_PORT			= 6666;
    public static final int HARVEST_TCP_PORT			= 6667;

    private static final int REPLY_COMMON_START			= 0000;

    private static final int REQUEST_COMMON_START				= 1000;

    public static final int REPLY_OK					= REPLY_COMMON_START+1;
	public static final int REPLY_KO					= REPLY_COMMON_START+2;

	public static final int REPLY_CARD_ID = REPLY_COMMON_START + 3;
	public static final int REPLY_CARD = REPLY_COMMON_START + 4;
	public static final int REPLY_CARDS = REPLY_COMMON_START + 5;


	public  static final int REQUEST_POPULATE = REQUEST_COMMON_START +1;
	public static final int REQUEST_INVALIDATE = REQUEST_COMMON_START +2;
	public static final int REQUEST_CREATE = REQUEST_COMMON_START +3;
	public static final int REQUEST_GET_CARD = REQUEST_COMMON_START +4;
	public static final int REQUEST_GET_ALL_CARDS = REQUEST_COMMON_START +5;


	// constantes inutiles cr��es pour �viter des erreurs de compilation suite � la suppression de codes
	public static final int REQUEST_XXX = -1;
	public static final int REPLY_XXX = -1;








}
