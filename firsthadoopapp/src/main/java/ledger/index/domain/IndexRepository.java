package ledger.index.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class IndexRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void IndexInsert( Index indexVO ) {
		entityManager.persist(indexVO);
	}
	
	
	/*
	public void create(User user) {
	    entityManager.persist(user);
	    return;
	  }
	
	  public void delete(User user) {
	    if (entityManager.contains(user))
	      entityManager.remove(user);
	    else
	      entityManager.remove(entityManager.merge(user));
	    return;
	  }
	  
	  @SuppressWarnings("unchecked")
	  public List getAll() {
	    return entityManager.createQuery("from User").getResultList();
	  }
	  
	  public User getByEmail(String email) {
	    return (User) entityManager.createQuery(
	        "from User where email = :email")
	        .setParameter("email", email)
	        .getSingleResult();
	  }

	  public User getById(long id) {
	    return entityManager.find(User.class, id);
	  }

	  public void update(User user) {
	    entityManager.merge(user);
	    return;
	  }
	  */
}
