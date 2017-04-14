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
public class HadoopClientFileTransferToHDFSTest {

	@Resource(name = "LedgerHdfsClient")
	private LedgerHdfsClient ledgerHdfsClient;

	@Test
	public void HadoopClient() throws Exception {
		System.out.println("File Transfers (Writes) To HDFS Start!");
		/*
		 * You can see These Errors
		 * Error 1)  
		 * Permission denied: user=WindowsUser, access=WRITE, inode="/HadoopClientFileTransferToHDFSTest.txt":alpha:supergroup:drwxr-xr-x
		 * 
		 * You Should Change Your Permission
		 * $ bin/hdfs dfs -mkdir /test
		 * $ bin/hdfs dfs -chmod 1777 /test
		 * $ bin/hdfs dfs -ls /   
		 * 
		 * After Processing
		 * $ bin/hdfs dfs -cat /test/HadoopClientFileTransferToHDFSTest.txt  
		 * 
		 * Error 2)
		 * ERROR org.apache.hadoop.hdfs.DFSClient - Failed to close inode 16395
		 * org.apache.hadoop.ipc.RemoteException: No lease on /test/HadoopClientFileTransferToHDFSTest.txt (inode 16395)
		 * 	: File does not exist. Holder DFSClient_NONMAPREDUCE_299703813_11 does not have any open files.
		 * 
		 * I has not solved Yet!!
		 * 
		 */
		
		//File Write To HDFS
		String HDFSfilePath = "/test/HadoopClientFileTransferToHDFSTest.txt";
		String fileContents = "Hello, I'm Youngseok Joung.";
		this.writeFile(ledgerHdfsClient.getSimpleFS(), HDFSfilePath, fileContents);
		
		//File Read From HDFS
		String output2 = ledgerHdfsClient.readFile(HDFSfilePath);
		System.out.println("Output : " + output2);
		System.out.println("File Transfers (Writes) To HDFS End!");
	}

	private void writeFile(SimplerFileSystem fs, String filePath, String fileContents) {
		try {
			if (fs.exists(filePath)) {
				System.out.println("File Delete!!!");
				fs.delete(filePath, true);
			} else {
				System.out.println("File Create!!!");
				fs.create(filePath);
				
				FSDataOutputStream stm = fs.create(filePath);
				if (fileContents != null) {
						stm.writeBytes(fileContents);
						stm.writeBytes("\n");
				}
				stm.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}