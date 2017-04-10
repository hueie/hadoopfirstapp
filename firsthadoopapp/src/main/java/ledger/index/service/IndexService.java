package ledger.index.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ledger.index.repository.Index;
import ledger.index.repository.IndexRepository;

@Service
public class IndexService {
	@Autowired
	IndexRepository indexRepository;

	public void IndexInsert(Index indexVO){
		indexRepository.IndexInsert(indexVO);
	}

}
