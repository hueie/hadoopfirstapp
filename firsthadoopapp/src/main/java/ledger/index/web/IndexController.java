package ledger.index.web;

import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ledger.index.repository.Index;
import ledger.index.service.IndexService;

@RestController
public class IndexController {
	
	@Autowired
	private IndexService indexService;
	
	private final AtomicLong counter = new AtomicLong();
	
    @RequestMapping("/ysindex")
    public Index index(@RequestParam(value="name", required=false, defaultValue="World") String name) {
    	System.out.println("index : param : " + name);
    	Index index = new Index();
    	index.setId( (int)counter.incrementAndGet() );
    	indexService.IndexInsert(index);
    	
    	return index;
    }
}
