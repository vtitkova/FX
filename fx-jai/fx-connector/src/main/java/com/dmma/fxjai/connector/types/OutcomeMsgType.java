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
	 *  <tr><td>MSG structure:</td><td>msgType|free text</td></tr>
	 *  <tr><td>MSG example:  </td><td>01|Hello client xxxxx, you sent me 'test text sent to server'</td></tr>
	 *  </table>
	 *  Response is {@link IncomeMsgType#isPing}
	 */
	isPong(               1), 
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
