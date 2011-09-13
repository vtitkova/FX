package com.dmma.fxjai.connector;

/**
 * Bean that contain information about 
 * all incoming connections, like
 * amount of accepted(processed) and rejected(failed) connections.
 * 
 * */
public class ConnectorStatus {
	private Integer connectionsProcessedTotal; 
	private Integer connectionsFailed;
	private Integer connectionsRejected;
	private Integer connectionsSucceed; // Succeed 
	private Integer connectionsActive;
	
	public ConnectorStatus() {
		connectionsProcessedTotal = 0;
		connectionsFailed         = 0;
		connectionsRejected       = 0;
		connectionsSucceed        = 0;
		connectionsActive         = 0;
	}

	public void connectionProcessStarted(){
		connectionsActive++;
	}

	public void connectionProcessFailed() {
		connectionsActive--;
		connectionsProcessedTotal++;
		connectionsFailed++;
		
	}
	
	public void connectionProcessRejected() {
		connectionsActive--;
		connectionsProcessedTotal++;
		connectionsRejected++;
	}
	
	
	public void connectionProcessSucceed() {
		connectionsActive--;
		connectionsProcessedTotal++;
		connectionsSucceed++;
	}

	
	
	
	public Integer getConnectionsProcessedTotal() {
		return connectionsProcessedTotal;
	}

	public Integer getConnectionsFailed() {
		return connectionsFailed;
	}

	public Integer getConnectionsRejected() {
		return connectionsRejected;
	}

	public Integer getConnectionsSucceed() {
		return connectionsSucceed;
	}

	public Integer getConnectionsActive() {
		return connectionsActive;
	}
}
