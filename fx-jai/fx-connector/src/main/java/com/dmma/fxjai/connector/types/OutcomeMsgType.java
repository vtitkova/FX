package com.dmma.fxjai.connector.types;

/** 
 * <b>Outcome Messages</b> types what server send as reply to client.<br>
 * For request(income) messages, see {@link IncomeMsgType}
 *
 *@see  
 *IncomeMsgType
 */
public enum OutcomeMsgType {
	
	/** <b>Pong</b> message<br>
	 *  <table>
	 *  <tr><td>MSG structure:</td><td>OutcomeMsgType|pongText</td></tr>
	 *  <tr><td>MSG example:  </td><td>            01|Hello, you sent me <PING_FREE_TEXT></td></tr>
	 *  </table>
	 *  Request is {@link IncomeMsgType#isPing}
	 */
	isPong(               1), 
	
	/** 
	 *  <table>
	 *  <tr><td>MSG structure:</td><td>OutcomeMsgType|accountId</td></tr>
	 *  <tr><td>MSG example:  </td><td>            02|      112</td></tr>
	 *  </table>
	 *  Request is {@link IncomeMsgType#isRegistration}
	 */
	isRegistrationStatus( 2),
	

	/** last known bar for this symbol
	 *  <table>
	 *  <tr><td>structure:</td><td>OutcomeMsgType|SymbolType| time_MN| time_W1| time_D1|...</td></tr>
	 *  <tr><td>example:  </td><td>            03|    EURUSD|87684225|87684225|87684225|...</td></tr>
	 *  </table>
	 *  This is response to {@link IncomeMsgType#isLastBarRequest} request.
	 */
	isLastBarResponce(3),
	
	/** inform client that bar was updated 
	 *  <table>
	 *  <tr><td>structure:</td><td>OutcomeMsgType</td></tr>
	 *  <tr><td>example:  </td><td>            04</td></tr>
	 *  </table>
	 *  This is response to {@link IncomeMsgType#isBarUpdate} request.
	 */
	isBarUpdateResponse(4);
	
	private Integer id;
		
	private OutcomeMsgType(Integer id) {
		this.id   = id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

}
