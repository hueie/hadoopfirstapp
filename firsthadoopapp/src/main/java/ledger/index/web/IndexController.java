package ledger.index.web;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.Greeting;
import ledger.index.model.IndexVO;
import ledger.index.service.IndexService;

@RestController
public class IndexController {
	@Autowired
	private IndexService indexService;
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/index")
    public Greeting greeting(@RequestParam(value="name", required=false, defaultValue="World") String name) {

    	IndexVO indexVO = new IndexVO();
    	indexVO.setId(1);
    	
    	indexService.IndexInsert(indexVO);
    	
    	return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
}
