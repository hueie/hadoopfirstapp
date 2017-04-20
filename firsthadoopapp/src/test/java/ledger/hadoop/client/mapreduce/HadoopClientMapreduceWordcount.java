
/*
 * !!!!!!!!!!!!Not Finish!!!!!!!!!!!!!!!!
 * !!! It is NOT POSSIBLE TO EXECUTE MAPREDUCE REMOTELY WITHOUT JAR FILE !!!!
 * 
 *  We should add mapred-site.xml & yarn-site.xml
 *  and Start ResourceManager daemon and NodeManager daemon
 *  sbin/start-yarn.sh
 *  
 *  YARN on a Single Node
 *  http://hadoop.apache.org/docs/r2.7.3/hadoop-project-dist/hadoop-common/SingleCluster.html
 *  
 *  Examples
 *  http://www.programcreek.com/java-api-examples/index.php?api=org.apache.hadoop.mapreduce.Job
 */

/*
package ledger.hadoop.client.mapreduce;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.hadoop.fs.SimplerFileSystem;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.IOException;
import javax.annotation.Resource;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import ledger.hadoop.config.HadoopConfig;
import ledger.hadoop.config.LedgerHdfsClient;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HadoopConfig.class, loader = AnnotationConfigContextLoader.class)
public class HadoopClientMapreduceWordcount {
	
	@Resource(name = "LedgerHdfsClient")
	private LedgerHdfsClient ledgerHdfsClient;
	
	@Test
	public void test1() throws Exception {
		
		Configuration conf = ledgerHdfsClient.getConf();
		System.err.println("HDFS CONF : " + conf.toString());
		conf.set("mapreduce.framework.name", "yarn");
		conf.set("yarn.nodemanager.aux-services", "mapreduce_shuffle");
		
		
		Job job = Job.getInstance(conf);
		
		job.setJarByClass(HadoopClientMapreduceWordcount.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//URI inUri = URI.create("hdfs://192.168.1.129:9000/NOTICE.txt");
		//URI outUri = URI.create("hdfs://192.168.1.129:9000/OUTPUT.txt");
		
		String inUri = "/NOTICE.txt";
		String outUri = "/OUTPUT.txt";
		
		FileInputFormat.addInputPath(job, new Path(inUri));
		FileOutputFormat.setOutputPath(job, new Path(outUri));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
	
	
	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			StringTokenizer itr = new StringTokenizer(value.toString());
			while (itr.hasMoreTokens()) {
				word.set(itr.nextToken());
				context.write(word, one);
			}
		}
	}

	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}
	}
	 
	
}
*/