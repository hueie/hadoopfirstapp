package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ledger.index.model.IndexVO;
import ledger.index.service.IndexService;

@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
	private IndexService indexService;
	
    
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", required=false, defaultValue="World") String name) {
    	System.out.println("hi2");
    	IndexVO indexVO = new IndexVO();
    	indexVO.setId( (int)counter.incrementAndGet() );
    	indexService.IndexInsert(indexVO);
    	
    	
    	return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
    
}
