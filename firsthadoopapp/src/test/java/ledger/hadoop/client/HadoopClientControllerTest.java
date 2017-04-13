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
import org.springframework.data.hadoop.fs.SimplerFileSystem;

public class HadoopClientControllerTest {
	
	public class HdfsClient {
        private SimplerFileSystem simpleFS;

        public void setSimpleFS(SimplerFileSystem simpleFS){
        	this.simpleFS = simpleFS;
        }
        
        public String readFile(String filePath) throws IOException {
            FSDataInputStream inputStream = this.simpleFS.open(filePath);
            String output =  getStringFromInputStream(inputStream.getWrappedStream());
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
	
	@Test
	public void HadoopClient() throws Exception {
		System.out.println("Test Start!");
		
		/* etc/hadoop/core-site.xml */
		int port = 9000;
		String ip = "192.168.1.129";
    	HdfsClient hdfsClient = new HdfsClient();
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://"+ip+":" + port); //conf.set("fs.default.name","hdfs://"+ip+":" + port); OLD VERSION
       	conf.set("dfs.replication", "1");
        FileSystem filesystem = FileSystem.get(conf);
       	
        
        URI uri = URI.create ("hdfs://"+ip+":" + port + "/NOTICE.txt");
       	FSDataInputStream in = filesystem.open(new Path(uri));
       	String output1 = getStringFromInputStream(in);
       	System.out.println("output1 : " + output1);
       	
       	
       	
        hdfsClient.setSimpleFS(new SimplerFileSystem(filesystem));
        String HDFSfilePath = "/NOTICE.txt";
        String output2 = hdfsClient.readFile(HDFSfilePath);
       	System.out.println("output2 : " + output2);
		
		System.out.println("Test End!");
      	
        /*
        FileSystem fs = FileSystem.get(conf);
        SimplerFileSystem sFs = new SimplerFileSystem(fs);
        
        Path p = new Path("/");
		RemoteIterator<LocatedFileStatus> a = sFs.listFiles(p, false);
		System.out.println(a.toString());
		*/
		
        
		
       	/*
       	 byte[] btbuffer = new byte[5];
        in.seek(5); // sent to 5th position
        //Assert.assertEquals(5, in.getPos());
        in.read(btbuffer, 0, 5);//read 5 byte in byte array from offset 0
        System.out.println(new String(btbuffer));// &amp;amp;amp;quot; print 5 character from 5th position
        in.read(10,btbuffer, 0, 5);
       	  
       	 * */
        
    }
}
