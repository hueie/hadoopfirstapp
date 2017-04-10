package ledger.index.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ysindex")
public class Index {
	@Id
	@Column(name="id")
	private int id;
	
	public Index() {
    }
	
	public Index(int id) {
        this.id = id;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
