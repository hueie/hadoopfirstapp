package ledger.sqoop.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SqoopRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void SqoopInsert(Sqoop sqoop) {
		entityManager.persist(sqoop);
	}
	
	@SuppressWarnings("unchecked")
	public List getAll() {
		return entityManager.createQuery("FROM ys_sqoop").getResultList();
	}
	
	public void update(Sqoop sqoop) {
	    entityManager.merge(sqoop);
	}
}
