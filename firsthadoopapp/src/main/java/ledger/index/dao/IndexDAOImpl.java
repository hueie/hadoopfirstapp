package ledger.index.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ledger.index.model.IndexVO;

@Repository
public class IndexDAOImpl implements IndexDAO{
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public void IndexInsert( IndexVO indexVO ) {
		((IndexDAOImpl) getSession()).IndexInsert(indexVO);
	}
}
