package ledger.index.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ledger.index.dao.IndexDAO;
import ledger.index.model.IndexVO;

@Service
@Transactional
public class IndexServiceImpl implements IndexService {
	@Autowired
	IndexDAO indexDAO;

	@Override
	@Transactional
	public void IndexInsert(IndexVO indexVO){
		indexDAO.IndexInsert(indexVO);
	}

}
