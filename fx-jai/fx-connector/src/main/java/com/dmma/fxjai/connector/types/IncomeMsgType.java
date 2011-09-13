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
	 *  <tr><td><b>structure:</b></td><td>account|msgType|free text               </td></tr>
	 *  <tr><td><b>example:  </b></td><td>0123456|01     |test text sent to server</td></tr>
	 *  </table>
	 *  Response is {@link OutcomeMsgType#isPong}
	 */
	isPing (1), 
	/** <b>Registration</b> message<br>
	 *  Client trying to register him self at server.<br>
	 *  If client already exist, client information will be updated
	 *  <table>                                       //TODO 
	 *  <tr><td><b>structure:</b></td><td>account|msgType|accountType|Name    |Surname   |ServerUrl</td></tr>
	 *  <tr><td><b>example:  </b></td><td>0123456|     02|       DEMO|Dmitrijs|Marcenkovs|ver</td></tr>
	 *  </table>
	 *  Response is {@link OutcomeMsgType#isRegistrationStatus}
	 * @see  
	 * Account
	 */
	isRegistration (2),
	/** <b>Actual</b> message<br>
	 *  The most frequent message from server, contain actual information about currency ASK, BID .<br>
	 *  If client already exist, client information will be updated
	 *  <table>                                       //TODO 
	 *  <tr><td><b>structure:</b></td><td>account|msgType|SYMBOL|BID   |DATE         </td></tr>
	 *  <tr><td><b>example:  </b></td><td>0123456|     03|USDCHF|1.5773|21.05.02 9:52</td></tr>
	 *  </table>
	 * @see  
	 * Account
	 */
	isActual(3);
	
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
