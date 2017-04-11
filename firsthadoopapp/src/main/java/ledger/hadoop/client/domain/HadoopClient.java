package ledger.hadoop.client.domain;

public class HadoopClient {
	private int id;
	
	public HadoopClient() {
    }
	
	public HadoopClient(int id) {
        this.id = id;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
