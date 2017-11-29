// Source: http://www.springminutes.com/2011/09/uuid-and-spring-data-jpa.html
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

/**
 * Abstract base class for entities. Provides a String ID that will contain a
 * UUID, leverages Hibernate's auto-generation for UUIDs, and
 * implements {@link #equals(Object)} and {@link #hashCode()} based on that id.
 * 
 * Inspired by Spring Data JPA's AbstractPersistable implementation.
 * 
 * @author Steve Finch
 */
@MappedSuperclass
public abstract class AbstractPersistable implements Persistable<String> {

	private static final long serialVersionUID = 2535090450811888936L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	public String getId() {
		return id;
	}

	protected void setId(final String id) {
		this.id = id;
	}

	public boolean isNew() {
		return this.id == null;
	}


	@Override
	public String toString() {
		return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
	}


	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (!getClass().equals(obj.getClass())) {
			return false;
		}

		AbstractPersistable rhs = (AbstractPersistable) obj;
		return this.id == null ? false : this.id.equals(rhs.id);
	}


	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += (this.id == null) ? 0 : this.id.hashCode() * 31;
		return hashCode;
	}
}
