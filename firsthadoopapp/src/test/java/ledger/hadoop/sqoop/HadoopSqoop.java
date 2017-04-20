package ledger.hadoop.sqoop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.catalina.Globals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import ledger.hadoop.sqoop.exe.HadoopSqoopExecutor;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = HadoopConfig.class, loader = AnnotationConfigContextLoader.class)
public class HadoopSqoop {
	
	
	@Test
	public void test_sqoop() throws Exception {
		String OS_TYPE = "UNIX";
		
		String dcn = "jdbc:mysql://";
		String url = "192.168.1.129:3306"; 
		String tablespace = "testdb";
		String user = "sqoop"; 
		String pwd = "1234"; 
		String table = "sqoop_test";
		try {
		    if ("WINDOWS".equals(OS_TYPE)) {
		    	/* Sub Example
		    	//Runtime.getRuntime().exec("jar cf SystAtchmgExecutor.jar SystAtchmgExecutor SystAtchmgExecutor.class");
		    	Process proc = Runtime.getRuntime().exec("cmd /c SysAtchmgExecutor.bat");
		    	proc.waitFor();
		    	System.out.println("bye");
		    	Runtime.getRuntime().exec("cmd /c java -jar SystAtchmgExecutor.jar");
		    	System.getProperty("java.home")+"\\bin\\java"
		    	*/
		    	
		    	//Thread Way
		    	new Thread(new Runnable() {
		    	    public void run() {
		    	    	String abspath = HadoopSqoopExecutor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
				    	int i = abspath.lastIndexOf("/");
				        if(i > -1) abspath = abspath.substring(1, i);
				    	System.out.println("abspath : " + abspath);
				    	
						System.out.println(dcn + url + user +pwd);
						
		    	    	InputStream is = null;
		    	        try {
		    	        	ProcessBuilder pb = new ProcessBuilder("java.exe", "-jar", abspath+"/SystAtchmgExecutor.jar",dcn,url,user,pwd);
		    		    	Process p = pb.start();
		    		    	is = p.getInputStream();
		    	            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		    	            String line;
		    	            while ((line = reader.readLine()) != null) { 
		    	            	//진행상황 확인 가능 서버 반영시 아래 라인은 주석을 취해준다.
		    	            	System.out.println(line); 
		    	            }
		    	        } catch (IOException e) {
		    	            e.printStackTrace();
		    	        } catch (Exception e) {
		    	        	e.printStackTrace();
		    	        } finally {
		    	        	System.out.println("Successfully Finishing Check Atch List");
		    	            try {
								is.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
		    	        }
		    	    }
		    	}).start();
		    	
		    	
		    	//Non-Thread Way
		    	/*
		    	String abspath = SystAtchmgModController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		    	int i = abspath.lastIndexOf("/");
		        if(i > -1) abspath = abspath.substring(1, i);
		    	System.out.println("abspath : " + abspath);
		    	
		    	String dcn = "DriverClassName");
				String url = "Url"); 
				String user = "UserName"); 
				String pwd = "Password"); 
				
		    	System.out.println(dcn + url + user +pwd);
		    	*/
		    	//1) Runtime Way
		    	//Runtime.getRuntime().exec("cmd /c java.exe -jar "+abspath+"/SystAtchmgExecutor.jar" + " "+ dcn + " "+ url + " "+ user + " "+ pwd);
		    	
		    	//2) ProcessBuilder Way
		    	//ProcessBuilder pb = new ProcessBuilder("java.exe", "-jar", abspath+"/SystAtchmgExecutor.jar",dcn,url,user,pwd);
		    	//Process p = pb.start();
		    	
		    	/*	Print Communication with Children Process , by receiving System.print in Children Process, SystAtchmgExecutor,
		    	BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    	String s = "";
		    	while((s = in.readLine()) != null){
		    	    System.out.println(s);
		    	}
		    	int exitValue =  p.waitFor();
		    	System.out.println("\n\nExit Value is " + exitValue);
		    	*/
		    	
			}else if ("UNIX".equals(OS_TYPE)) {
				new Thread(new Runnable() {
		    	    public void run() {
		    	    	String abspath = HadoopSqoopExecutor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
				    	int i = abspath.lastIndexOf("/");
				        if(i > -1) abspath = abspath.substring(1, i);
				    	System.out.println("abspath : " + abspath);
						System.out.println(dcn + url + user +pwd);
						
		    	    	InputStream is = null;
		    	        try {
		    	        	//sqoop import --connect jdbc:mysql://192.168.1.129:3306/testdb --username sqoop -P --table sqoop_test
		    	        	
		    	        	
		    	        	ProcessBuilder pb = new ProcessBuilder("sqoop", "import","--connect", dcn+url+"/"+tablespace,"--username" ,user,"--password" ,pwd,"--table", table);
		    		    	Process p = pb.start();
		    		    	is = p.getInputStream();
		    	            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		    	            String line;
		    	            while ((line = reader.readLine()) != null) { 
		    	            	//진행상황 확인 가능 서버 반영시 아래 라인은 주석을 취해준다.
		    	            	System.out.println(line); 
		    	            }
		    	        } catch (IOException e) {
		    	            e.printStackTrace();
		    	        } catch (Exception e) {
		    	        	e.printStackTrace();
		    	        } finally {
		    	            try {
								is.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
		    	        }
		    	    }
		    	}).start();
			}
		} catch (RuntimeException err) {
			err.printStackTrace();
		} catch (Exception err){
			err.printStackTrace();
		}
	}
	
	 
	
}
