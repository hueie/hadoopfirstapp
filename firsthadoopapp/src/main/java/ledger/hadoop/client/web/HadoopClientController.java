package ledger.hadoop.client.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.fs.SimplerFileSystem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ledger.index.service.IndexService;

@RestController
public class HadoopClientController {
	
	private final AtomicLong counter = new AtomicLong();
	
	public class HdfsClient {
        private SimplerFileSystem simpleFS;

        public String readFile(String filePath) throws IOException {
            FSDataInputStream inputStream = this.simpleFS.open(filePath);
            String output =  getStringFromInputStream(inputStream.getWrappedStream());
            inputStream.close();
            return output;
        }
        
        public void setSimpleFS(SimplerFileSystem simpleFS){
        	this.simpleFS = simpleFS;
        }
        
    	private String getStringFromInputStream(InputStream is) {
    		BufferedReader br = null;
    		StringBuilder sb = new StringBuilder();

    		String line;
    		try {

    			br = new BufferedReader(new InputStreamReader(is));
    			while ((line = br.readLine()) != null) {
    				sb.append(line);
    			}

    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally {
    			if (br != null) {
    				try {
    					br.close();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			}
    		}
    		return sb.toString();
    	}
	}
	
	
    @RequestMapping("/hadoop/client")
    public String HadoopClient(@RequestParam(value="name", required=false, defaultValue="World") String name) throws IOException {
    	HdfsClient hdfsClient = new HdfsClient();
        Configuration conf = new Configuration();

        conf.set("fs.defaultFS","hdfs://localhost:32783");
        FileSystem fs = FileSystem.get(conf);
        SimplerFileSystem sFs = new SimplerFileSystem(fs);
        hdfsClient.setSimpleFS(sFs);

        String filePath = "/tmp/tmpTestReadTest.txt";
        String output = hdfsClient.readFile(filePath);
    	
    	return "hadoop";
    }
}
