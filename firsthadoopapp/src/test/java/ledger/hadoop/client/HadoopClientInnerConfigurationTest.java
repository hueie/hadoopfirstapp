package ledger.hadoop.client;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ledger.hadoop.config.LedgerHdfsClient;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class HadoopClientInnerConfigurationTest {

	@Resource(name="LedgerHdfsClient")
	private LedgerHdfsClient ledgerHdfsClient;

	@Test
	public void HadoopClient() throws Exception {
		System.out.println("Test3 Start!");
		
		String HDFSfilePath = "/NOTICE.txt";
		String output2 = ledgerHdfsClient.readFile(HDFSfilePath);
		System.out.println("Output : " + output2);
		System.out.println("Test3 End!");
	}
	
	@Configuration
	public static class HadoopConfig {
		@Bean(name="LedgerHdfsClient")
		public LedgerHdfsClient ledgerHdfsClient(){
			System.out.println("LedgerHdfsClient from bean");
		    return new LedgerHdfsClient();
		}
	}
}