
package ledger.hadoop.client.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.hadoop.fs.FSDataInputStream;
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
public class HadoopClientFileReadFromHDFSTest {

	@Resource(name = "LedgerHdfsClient")
	private LedgerHdfsClient ledgerHdfsClient;

	@Test
	public void HadoopClient() throws Exception {
		System.out.println("File Transfers (Reads) From HDFS Start!");
		//File Read From HDFS
		String HDFSfilePath = "/test/HadoopClientFileTransferToHDFSTest.txt";
		String fileContents = this.readFile(ledgerHdfsClient.getSimpleFS(), HDFSfilePath);
				
		System.out.println("Output : " + fileContents);
		System.out.println("File Transfers (Reads) From HDFS End!");
	}

	public String readFile(SimplerFileSystem fs, String filePath) throws IOException {
		FSDataInputStream stm = fs.open(filePath);
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {
			br = new BufferedReader(new InputStreamReader(stm));
			while ((line = br.readLine()) != null) { sb.append(line); }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				br.close();
			}
			stm.close();
		}
		return sb.toString();
	}
	
}
