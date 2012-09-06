package test.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public interface UserName {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId();

	public void setId(Long id);

	public String getName();

	public void setName(String name);

}
