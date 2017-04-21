package ledger.sqoop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ledger.sqoop.domain.Sqoop;
import ledger.sqoop.domain.SqoopRepository;

@Service
public class SqoopService {
	@Autowired
	SqoopRepository sqoopRepository;

	public void SqoopInsert(Sqoop sqoop){
		sqoopRepository.SqoopInsert(sqoop);
	}
	
	public List getAll(){
		return sqoopRepository.getAll();
	}
	
	public void update(Sqoop sqoop) {
	    sqoopRepository.update(sqoop);
	}
}
