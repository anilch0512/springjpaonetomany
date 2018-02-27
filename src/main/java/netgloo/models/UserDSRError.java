package netgloo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DSR_ERROR_BACKUP")
public class UserDSRError {

	@Id
	@Column(name = "SPID")
	private String spid;

	@Column(name = "PRIMARY_DLR_NUM")
	private String primaryDlrNum;

	@ManyToOne
	@JoinColumn(name = "CUID")
	private User user;

	public String getSpid() {
		return spid;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public String getPrimaryDlrNum() {
		return primaryDlrNum;
	}

	public void setPrimaryDlrNum(String primaryDlrNum) {
		this.primaryDlrNum = primaryDlrNum;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
