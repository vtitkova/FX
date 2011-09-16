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
	 *  Response is {@link IncomeMsgType#isPing}
	 */
	isPong(               1), 
	
	/** 
	 *  <table>
	 *  <tr><td>MSG structure:</td><td>msgType|accountId</td></tr>
	 *  <tr><td>MSG example:  </td><td>02     |      112</td></tr>
	 *  </table>
	 *  Response is {@link IncomeMsgType#isRegistration}
	 */
	isRegistrationStatus( 2),
	
	isAuth(               3);
	
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
