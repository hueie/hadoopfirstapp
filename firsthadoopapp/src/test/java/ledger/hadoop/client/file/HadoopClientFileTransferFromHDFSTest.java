/*
package ledger.hadoop.client.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.hadoop.fs.SimplerFileSystem;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ledger.hadoop.config.HadoopConfig;
import ledger.hadoop.config.LedgerHdfsClient;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HadoopConfig.class, loader = AnnotationConfigContextLoader.class)
public class HadoopClientFileTransferFromHDFSTest {

	@Resource(name = "LedgerHdfsClient")
	private LedgerHdfsClient ledgerHdfsClient;

	@Test
	public void HadoopClient() throws Exception {
		System.out.println("Test Start!");

		String HDFSfilePath = "/NOTICE.txt";
		String output2 = ledgerHdfsClient.readFile(HDFSfilePath);
		System.out.println("Output : " + output2);
		System.out.println("Test End!");
	}

	private void writeFile(SimplerFileSystem fs, String filePath, String filecontents) throws IOException {
		fs.create(filePath);
		
		if (fs.exists(filePath)) {
			fs.delete(filePath, true);
		}

		FSDataOutputStream stm = fs.create(filePath);

		if (filecontents != null) {
				stm.writeBytes(filecontents);
				stm.writeBytes("\n");
		}
		stm.close();
	}
}
*/