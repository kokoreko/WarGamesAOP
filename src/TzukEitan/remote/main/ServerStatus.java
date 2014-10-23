package TzukEitan.remote.main;

import java.io.Serializable;


public class ServerStatus implements Serializable {

	private static final long serialVersionUID = 1665144814040426543L;

	public enum STATUS {Accepted,Deniend};
	private STATUS stats;
	private String details;
	public ServerStatus(STATUS s) {
			this.stats =s;
		}
	
	public String toString(){
		return stats.toString();
	}
}
