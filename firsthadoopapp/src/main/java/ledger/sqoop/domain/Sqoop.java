package ledger.sqoop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ys_sqoop")
public class Sqoop {
	@Id
	@Column(name="sqoop_id")
	private int sqoop_id;

	@Column(name="sqoop_dbtype")
	private String sqoop_dbtype;
	@Column(name="sqoop_ip")
	private String sqoop_ip;
	@Column(name="sqoop_port")
	private String sqoop_port;
	@Column(name="sqoop_tablespace")
	private String sqoop_tablespace;
	@Column(name="sqoop_user")
	private String sqoop_user;
	@Column(name="sqoop_pwd")
	private String sqoop_pwd;
	@Column(name="sqoop_tablename")
	private String sqoop_tablename;
	@Column(name="sqoop_pid")
	private String sqoop_pid;
	
	
	public Sqoop() {
    }


	public int getSqoop_id() {
		return sqoop_id;
	}


	public void setSqoop_id(int sqoop_id) {
		this.sqoop_id = sqoop_id;
	}


	public String getSqoop_dbtype() {
		return sqoop_dbtype;
	}


	public void setSqoop_dbtype(String sqoop_dbtype) {
		this.sqoop_dbtype = sqoop_dbtype;
	}


	public String getSqoop_ip() {
		return sqoop_ip;
	}


	public void setSqoop_ip(String sqoop_ip) {
		this.sqoop_ip = sqoop_ip;
	}


	public String getSqoop_port() {
		return sqoop_port;
	}


	public void setSqoop_port(String sqoop_port) {
		this.sqoop_port = sqoop_port;
	}


	public String getSqoop_tablespace() {
		return sqoop_tablespace;
	}


	public void setSqoop_tablespace(String sqoop_tablespace) {
		this.sqoop_tablespace = sqoop_tablespace;
	}


	public String getSqoop_user() {
		return sqoop_user;
	}


	public void setSqoop_user(String sqoop_user) {
		this.sqoop_user = sqoop_user;
	}


	public String getSqoop_pwd() {
		return sqoop_pwd;
	}


	public void setSqoop_pwd(String sqoop_pwd) {
		this.sqoop_pwd = sqoop_pwd;
	}


	public String getSqoop_tablename() {
		return sqoop_tablename;
	}


	public void setSqoop_tablename(String sqoop_tablename) {
		this.sqoop_tablename = sqoop_tablename;
	}


	public String getSqoop_pid() {
		return sqoop_pid;
	}


	public void setSqoop_pid(String sqoop_pid) {
		this.sqoop_pid = sqoop_pid;
	}




}
