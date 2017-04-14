package ledger.hadoop.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.hadoop.fs.SimplerFileSystem;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
public class HadoopClientBasicTest {
	//@Test
	public void HadoopClient() throws Exception {
		System.out.println("Test Start!");
		/* etc/hadoop/core-site.xml */
		int port = 9000;
		String ip = "192.168.1.129";
		HdfsClient hdfsClient = new HdfsClient();
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://" + ip + ":" + port); 
		// conf.set("fs.default.name","hdfs://"+ip+":" + port); OLD VERSION
		conf.set("dfs.replication", "1");
		FileSystem filesystem = FileSystem.get(conf);
		URI uri = URI.create("hdfs://" + ip + ":" + port + "/NOTICE.txt");
		FSDataInputStream in = filesystem.open(new Path(uri));
		String output1 = getStringFromInputStream(in);
		System.out.println("output1 : " + output1);
		hdfsClient.setSimpleFS(new SimplerFileSystem(filesystem));
		String HDFSfilePath = "/NOTICE.txt";
		String output2 = hdfsClient.readFile(HDFSfilePath);
		System.out.println("output2 : " + output2);
		System.out.println("Test End!");
	}
	
	public class HdfsClient {
		private SimplerFileSystem simpleFS;

		public void setSimpleFS(SimplerFileSystem simpleFS) {
			this.simpleFS = simpleFS;
		}

		public String readFile(String filePath) throws IOException {
			FSDataInputStream inputStream = this.simpleFS.open(filePath);
			String output = getStringFromInputStream(inputStream.getWrappedStream());
			System.out.println(output);
			inputStream.close();
			return output;
		}

	}

	public static String getStringFromInputStream(InputStream is) {
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