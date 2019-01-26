package chandra.prakash.registration.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "t_user")
public class TUser extends MasterEntity{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer version;
	private String firstName;
	private String lastName;
	private String emailID;	
	private String password;
    private LRole role;
    
    private List<TBusinessDetail> businessDetails;
	
	public TUser() {
		// TODO Auto-generated constructor stub
	}
	
	
	public TUser(Long id, Integer version, String firstName, String lastName, String emailID, String password,
			LRole role, List<TBusinessDetail> businessDetails) {
		super();
		this.id = id;
		this.version = version;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.password = password;
		this.role = role;
		this.businessDetails = businessDetails;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@Size(min = 3, max = 20)
	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Size(min = 3, max = 20)
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@NotNull
	@Size(max = 50)
	@Column(name = "email_id")
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
	@NotNull
	@Size(max = 100)
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	public LRole getRole() {
		return role;
	}
	public void setRole(LRole role) {
		this.role = role;
	}
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "user")
	public List<TBusinessDetail> getBusinessDetails() {
		return businessDetails;
	}

	public void setBusinessDetails(List<TBusinessDetail> businessDetails) {
		this.businessDetails = businessDetails;
	}

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
