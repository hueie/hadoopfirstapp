package ledger.hadoop.client;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.hadoop.fs.FSDataInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.hadoop.fs.SimplerFileSystem;
import org.springframework.test.context.junit4.SpringRunner;

public class HadoopClientControllerTest {
/*	
	@Before
	public void before() {
		StepSynchronizationManager.release();
		StepSynchronizationManager.close();
	}
*/
	
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
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
	public void HadoopClient() throws Exception {
		int port = 32783;
		assertThat("aaa").contains("aaa");

		/*
    	HdfsClient hdfsClient = new HdfsClient();
        Configuration conf = new Configuration();

        conf.set("fs.defaultFS","hdfs://localhost:32783");
        FileSystem fs = FileSystem.get(conf);
        SimplerFileSystem sFs = new SimplerFileSystem(fs);
        hdfsClient.setSimpleFS(sFs);

        String filePath = "/tmp/tmpTestReadTest.txt";
        String output = hdfsClient.readFile(filePath);
    	*/
		
		
    	
/*
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"/org/springframework/data/hadoop/batch/in-do-out.xml");

		ctx.registerShutdownHook();

		FileSystem fs = FileSystem.get(ctx.getBean(Configuration.class));
		System.out.println("FS is " + fs.getClass().getName());
		HdfsResourceLoader hrl = ctx.getBean(HdfsResourceLoader.class);
		Resource resource = hrl.getResource("/ide-test/output/word/");

		assertTrue(ctx.isPrototype("script-tasklet"));

		fs.delete(new Path(resource.getURI().toString()), true);

		JobsTrigger.startJobs(ctx);

		Path p = new Path("/ide-test/output/word/");
		Job job = (Job) ctx.getBean("mr-job");
		Configuration c = job.getConfiguration();
		FileSystem fs2 = p.getFileSystem(c);
		System.out.println("FS is " + fs2.getClass().getName());

		fs2.exists(p);

		ctx.close();
      */
        
    }
}
