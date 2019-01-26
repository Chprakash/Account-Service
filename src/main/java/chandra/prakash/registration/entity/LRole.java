package chandra.prakash.registration.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "l_role")
public class LRole extends MasterEntity{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer version;
	private String name;
	private List<TUser> users;

	public LRole() {
		super();
	}
	
	public LRole(Long id, Integer version, String name, List<TUser> users) {
		super();
		this.id = id;
		this.version = version;
		this.name = name;
		this.users = users;
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
	@Size(min =3, max = 20)
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "role")
	public List<TUser> getUsers() {
		return users;
	}

	public void setUsers(List<TUser> users) {
		this.users = users;
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
