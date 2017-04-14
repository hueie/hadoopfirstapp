package ledger.hadoop.client;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ledger.hadoop.config.HadoopConfig;
import ledger.hadoop.config.LedgerHdfsClient;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=HadoopConfig.class, loader=AnnotationConfigContextLoader.class)
public class HadoopClientOuterConfigurationTest {

	@Resource(name="LedgerHdfsClient")
	private LedgerHdfsClient ledgerHdfsClient;

	@Test
	public void HadoopClient() throws Exception {
		System.out.println("Test2 Start!");
		
		String HDFSfilePath = "/NOTICE.txt";
		String output2 = ledgerHdfsClient.readFile(HDFSfilePath);
		System.out.println("Output : " + output2);
		System.out.println("Test2 End!");
	}
}