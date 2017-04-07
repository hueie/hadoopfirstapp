package ledger.index.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ledger.index.model.IndexVO;

@Repository
@Transactional
public class IndexDAOImpl implements IndexDAO{
	@PersistenceContext
	private EntityManager entityManager;
	
	public void IndexInsert( IndexVO indexVO ) {
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
