package ledger.hadoop.sqoop.exe;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class HadoopSqoopExecutor {
	/* 
	 * 사용 방법
	 * 1) 새로운 JAVA 어플리케이션 프로젝트(프로젝트명 : SystAtchmgExecutor)를 생성하고 해당 프로젝트에 이 자바파일(SystAtchmgExecutor.java) 를 옴긴다.
	 * 2) OJDBC 드라이버를 해당 프로젝트 라이브러리에 추가를 한다.
	 * 3) 해당 프로젝트를 Export -> Runnable jar file 을 선택, 백그라운드로 실행할 jar 파일을 추출한다. 
	 * 3) Export 옵션 : Library Handling (Package required libraries into generated JAR)
	 * 3) 추출 파일명 : (SystAtchmgExecutor.jar)
	 * 4) 추출된 jar 파일을 kams.syst.atchmg.web 에 복사한다. 
	 * *) 클린, 재빌드, 서버 실행 시 리소스로 인식되서 지워지지않고 자동으로 복사되서 새로 Export 할 필요는 없다. 
	 * 
	 * 커맨드 프로그램 실행방법 : java -jar SystAtchmgExecutor.jar oracle.jdbc.OracleDriver jdbc:log4jdbc:oracle:thin:@127.0.0.1:1521:orcl id pw
	 * DriverClassName=oracle.jdbc.OracleDriver 변수 넣으면 된다. 순으로 넣으면 된다. 
	 * Url=jdbc:log4jdbc:oracle:thin:@127.0.0.1:1521:orcl
	 * User=id
	 * Password=pw
	 */
	
	
	public static void main(String[] args) {
		/* For Asynchronous process Test Using Sleep
		try {
			System.out.println("Send To Parent Process");
			TimeUnit.MINUTES.sleep(1);
		    File file = new File("newfile.txt");
		    if (file.createNewFile()){
		    	System.out.println("File is created!");
		    }else{
		        System.out.println("File already exists.");
		    }
	    } catch (IOException e) {
		      e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 */
		File file = null;
		try {
		    file = new File("TB_TRRECORDFILE_log.txt");
		    if (file.createNewFile()){
		    	System.out.println("File is created!");
		    }else{
		        System.out.println("File already exists.");
		    }
	    } catch (IOException e) {
		      e.printStackTrace();
		}
		
		/* Logic Start */
		String dcn = "";
		String url = "";
		String user = ""; 
		String pwd = "";
		
		for(int i=0; i < args.length ; i++){
			switch (i) {
				case 0: 
					dcn = args[i];
					break;
				case 1: 
					url = args[i];
					break;
				case 2: 
					user = args[i];
					break;
				case 3: 
					pwd = args[i];
					break;
			}
			System.out.println(args[i]);
		}
		
		Connection connection = null;
		Statement deletestatement = null;
		Statement selectstatement = null;
		Statement insertstatement = null; 
		
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write("FileWriter Success\n");
			System.out.println("FileWriter Success\n");

			dcn = "oracle.jdbc.OracleDriver";
			if(url == null || url.equals("") || url == "" || url.isEmpty()){
				System.out.println("URL is not in Args\n");
				throw new Exception();
			} else {
				String ip = url.split("@")[1];
				System.out.println("IP : " + ip);
				fw.write("IP : " + ip + "\n");
				url = "jdbc:oracle:thin:@" + ip;
			}
			if(user == null || user.equals("") || user == "" || user.isEmpty()){
				System.out.println("USER is not in Args\n");
				throw new Exception();
			} else { }
			if(pwd == null || pwd.equals("") || pwd == "" || pwd.isEmpty()){
				System.out.println("PASSWD is not in Args\n");
				throw new Exception();
			} else { }
			
			
			fw.write("dcn : " + dcn + "\t" + "url : " + url +"\n");
			System.out.println("dcn : " + dcn + "\t" + "url : " + url +"\n");
			fw.write("user : " + user + "\t" + "pwd : " + pwd +"\n");
			System.out.println("user : " + user + "\t" + "pwd : " + pwd +"\n");
			
			Class.forName(dcn);
			fw.write("Class.forName Success\n");
			System.out.println("Class.forName Success\n");
			connection = DriverManager.getConnection(url, user, pwd);
			
			fw.write("Connection Success\n");
			System.out.println("Connection Success\n");
			
			
			deletestatement = connection.createStatement();
			selectstatement = connection.createStatement();
			insertstatement = connection.createStatement();
			
			
			String deleteTableSQL = "DELETE FROM TB_TRRECORDFILE_ERROR"; 
			deletestatement.executeUpdate(deleteTableSQL);
			
			String selectTableSQL = "SELECT ";
			selectTableSQL += "I_ID ,I_FILENO ,I_FILENAME ,I_FILE_SAVE_PATH ,I_FILE_TYPE_CD ,I_ORIGN_FILE_NM";
			selectTableSQL += ",I_FILE_EXT ,I_FILE_SIZE ,REG_ID ,REG_NAME ,REG_DT";
			selectTableSQL += ",SYS_ID ,SYS_NAME ,SYS_DT ,PDF_TRANS_FLAG ,PDF_TRANS_FILE_PATH ,EXTERNAL_PDF_TRANS_FLAG, I_RANK";
			selectTableSQL += ",I_THUMB_FILE_PATH ,I_THUMB_FILE_NAME ,I_THUMB_FILE_SIZE ,I_THUMB_FLAG ,I_SVC_TYPE_CD ,FILEPATH";
			selectTableSQL += " FROM TB_TRRECORDFILE ";
			
			ResultSet rs = selectstatement.executeQuery(selectTableSQL);
			while (rs.next()) {
				
				String i_id = rs.getString("I_ID");
				String i_fileno = rs.getString("I_FILENO");
				String i_filename = rs.getString("I_FILENAME") == null ? "" : rs.getString("I_FILENAME");
				String i_file_save_path = rs.getString("I_FILE_SAVE_PATH") == null ? "" : rs.getString("I_FILE_SAVE_PATH");
				String i_file_type_cd = rs.getString("I_FILE_TYPE_CD");
				String i_orign_file_nm = rs.getString("I_ORIGN_FILE_NM") == null ? "" : rs.getString("I_ORIGN_FILE_NM");
				String i_file_ext = rs.getString("I_FILE_EXT");
				String i_file_size = rs.getString("I_FILE_SIZE");
				String reg_id = rs.getString("REG_ID");
				String reg_name = rs.getString("REG_NAME");
				String reg_dt = rs.getString("REG_DT");
				String sys_id = rs.getString("SYS_ID");
				String sys_name = rs.getString("SYS_NAME");
				String sys_dt = rs.getString("SYS_DT");
				String pdf_trans_flag = rs.getString("PDF_TRANS_FLAG");
				String pdf_trans_file_path = rs.getString("PDF_TRANS_FILE_PATH");
				String external_pdf_trans_flag = rs.getString("EXTERNAL_PDF_TRANS_FLAG");
				String i_rank = rs.getString("I_RANK");
				String i_thumb_file_path = rs.getString("I_THUMB_FILE_PATH");
				String i_thumb_file_name = rs.getString("I_THUMB_FILE_NAME");
				String i_thumb_file_size = rs.getString("I_THUMB_FILE_SIZE");
				String i_thumb_flag = rs.getString("I_THUMB_FLAG");
				String i_svc_type_cd = rs.getString("I_SVC_TYPE_CD");
				String filepath = rs.getString("FILEPATH") == null ? "" : rs.getString("FILEPATH");				
				
				
				File tmpfile = new File(i_file_save_path);
				if(!tmpfile.exists() ){
					//System.out.println("File Does Not Exist\n");
					System.out.println("I_ID : " + i_id + "\t" + "I_FILE_SAVE_PATH : " + i_file_save_path +"");
					//fw.write("I_ID : " + i_id + "\t" + "I_FILE_SAVE_PATH : " + i_file_save_path +"\n");
					String insertTableSQL = "INSERT INTO TB_TRRECORDFILE_ERROR ( ";
						insertTableSQL += "I_ID ,I_FILENO ,I_FILENAME ,I_FILE_SAVE_PATH ,I_FILE_TYPE_CD ,I_ORIGN_FILE_NM";
						insertTableSQL += ",I_FILE_EXT ,I_FILE_SIZE ,REG_ID ,REG_NAME ,REG_DT";
						insertTableSQL += ",SYS_ID ,SYS_NAME ,SYS_DT ,PDF_TRANS_FLAG ,PDF_TRANS_FILE_PATH ,EXTERNAL_PDF_TRANS_FLAG, I_RANK";
						insertTableSQL += ",I_THUMB_FILE_PATH ,I_THUMB_FILE_NAME ,I_THUMB_FILE_SIZE ,I_THUMB_FLAG ,I_SVC_TYPE_CD ,FILEPATH";
						insertTableSQL += " ) VALUES ( ";
						insertTableSQL += ""+i_id+","+i_fileno +",'"+i_filename.replace("'", "''") +"','"+i_file_save_path.replace("'", "''") +"','"+i_file_type_cd +"','"+i_orign_file_nm.replace("'", "''")  +"'";
						insertTableSQL += ",'"+i_file_ext +"',"+i_file_size +",'"+reg_id  +"','"+ reg_name +"','"+ reg_dt +"'";
						insertTableSQL += ",'"+sys_id +"','"+sys_name +"','"+ sys_dt +"','"+ pdf_trans_flag +"','"+ pdf_trans_file_path +"','"+ external_pdf_trans_flag +"',"+ i_rank +"";
						insertTableSQL += ",'"+i_thumb_file_path +"','"+i_thumb_file_name +"',"+ i_thumb_file_size +",'"+ i_thumb_flag +"','"+ i_svc_type_cd +"','"+ filepath.replace("'", "''")  +"'";
						insertTableSQL += " ) ";
					insertstatement.executeUpdate(insertTableSQL);
					
				} else{
					//System.out.println("File Exists\n");
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				if(fw != null){
					fw.close();
				}
				if (deletestatement != null) {
					deletestatement.close();
				}
				if (selectstatement != null) {
					selectstatement.close();
				}
				if (insertstatement != null) {
					insertstatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch(Exception e){
				e.printStackTrace();
			}
		}

	}

}
