package ledger.hadoop.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.data.hadoop.fs.SimplerFileSystem;

public class LedgerHdfsClient {
	private SimplerFileSystem simpleFS;

	@Autowired
	public void setSimpleFS() {
		/* etc/hadoop/core-site.xml */
		int port = 9000;
		String ip = "192.168.1.129";
		
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://" + ip + ":" + port); 
		conf.set("dfs.replication", "1");

		try {
			FileSystem filesystem = FileSystem.get(conf);
	    	this.simpleFS =  new SimplerFileSystem(filesystem);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public SimplerFileSystem getSimpleFS() {
		return simpleFS;
	}
	
	
	public String readFile(String filePath) throws IOException {
		FSDataInputStream inputStream = this.simpleFS.open(filePath);
		String output = getStringFromInputStream(inputStream.getWrappedStream());
		System.out.println(output);
		inputStream.close();
		return output;
	}
	
	public String getStringFromInputStream(InputStream is) {
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