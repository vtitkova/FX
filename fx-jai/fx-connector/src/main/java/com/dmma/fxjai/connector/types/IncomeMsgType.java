package com.dmma.fxjai.connector.types;

/** 
 * <b>Income Messages</b> types what server can accept and process.<br>
 * For response messages, see {@link OutcomeMsgType}
 *
 *@see  
 *OutcomeMsgType
 */
public enum IncomeMsgType {
	
	/** <b>Ping</b> message<br>
	 * No authentication required. 
	 * Use this message to test connection to server and response time
	 *  <table>
	 *  <tr><td><b>structure:</b></td><td>IncomeMsgType|pingText      </td></tr>
	 *  <tr><td><b>example:  </b></td><td>           01|some free text</td></tr>
	 *  </table>
	 *  Response is {@link OutcomeMsgType#isPong}
	 */
	isPing (1), 
	
	/** <b>Registration</b> message<br>
	 *  Client trying to register him self at server.<br>
	 *  If client already exist, client information will be updated
	 *  <table>                                       
	 *  <tr><td><b>structure:</b></td><td>IncomeMsgType|account|AccountType|Name               |Server </td></tr>
	 *  <tr><td><b>example:  </b></td><td>           02|  12456|       DEMO|Dmitrijs Marcenkovs|FXOpen </td></tr>
	 *  </table>
	 *  Response is {@link OutcomeMsgType#isRegistrationStatus}
	 * @see  
	 * Account
	 */
	isRegistration (2),
	
	/** <table>
	 *  <tr><td><b>structure:</b></td><td>IncomeMsgType|account|accountId|SymbolType</td></tr>
	 *  <tr><td><b>example:  </b></td><td>           03| 123456|       12|    USDCHF</td></tr>
	 *  </table>
	 */
	isLastBarRequest(3),
	
	/** <table>
	 *  <tr><td><b>structure:</b></td><td>IncomeMsgType|account|accountId|SymbolType|PeriodType|         time|O|H|L|C|V</td></tr>
	 *  <tr><td><b>example:  </b></td><td>           04| 123456|       12|    USDCHF|      1440|1316087684225|x|x|x|x|x</td></tr>
	 *  </table>
	 */
	isBarUpdate(4),
	
	/** <b>Actual</b> message<br>
	 *  The most frequent message from server, contain actual information about currency ASK, BID .<br>
	 *  If client already exist, client information will be updated
	 *  <table>                                       //TODO 
	 *  <tr><td><b>structure:</b></td><td>IncomeMsgType|account|accountId|SymbolType|        time |bid   </td></tr>
	 *  <tr><td><b>example:  </b></td><td>           05| 123456|       12|    USDCHF|1316087684225|1.5773</td></tr>
	 *  </table>
	 * @see  
	 * Account
	 */
	isActual(5);
	
	
	
	private Integer id;
		
	private IncomeMsgType(Integer id) {
		this.id   = id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public static IncomeMsgType findById(Integer id){
		for(IncomeMsgType type: IncomeMsgType.values()){
			if(type.getId()==id)
				return type;
		}
		return null;
	}
	public static IncomeMsgType findById(String id){
		return findById(Integer.valueOf(id));
	}
	
	public boolean isEequals(Integer id){
		return this.id.equals(id);
	}
	
	public boolean isEequals(String stringId){
		try{
			return isEequals(Integer.parseInt(stringId));
		}catch (Exception e) {
			return false;
		}
	}
	
	
}
