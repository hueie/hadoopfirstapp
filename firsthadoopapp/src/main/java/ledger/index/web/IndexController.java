package ledger.index.web;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ledger.index.model.IndexVO;
import ledger.index.service.IndexService;

@RestController
public class IndexController {
	@Autowired
	private IndexService indexService;
	
	private final AtomicLong counter = new AtomicLong();
	
    @RequestMapping("/index")
    public IndexVO index(@RequestParam(value="name", required=false, defaultValue="World") String name) {
    	System.out.println("index : param : " + name);
    	IndexVO indexVO = new IndexVO();
    	indexVO.setId( (int)counter.incrementAndGet() );
    	indexService.IndexInsert(indexVO);
    	
    	return indexVO;
    }
}
