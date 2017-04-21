package ledger.sqoop.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ledger.sqoop.domain.Sqoop;
import ledger.sqoop.service.SqoopService;

@RestController
public class SqoopController {

	@Autowired
	private SqoopService sqoopService;

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/sqoopinsert")
	public String sqoop(@RequestParam(value = "dbtype", required = false, defaultValue = "") String dbtype,
			@RequestParam(value = "ip", required = false, defaultValue = "") String ip,
			@RequestParam(value = "port", required = false, defaultValue = "") String port,
			@RequestParam(value = "tablespace", required = false, defaultValue = "") String tablespace,
			@RequestParam(value = "user", required = false, defaultValue = "") String user,
			@RequestParam(value = "pwd", required = false, defaultValue = "") String pwd,
			@RequestParam(value = "tablename", required = false, defaultValue = "") String tablename
			) {

		Sqoop sqoop = new Sqoop();
		sqoop.setSqoop_id((int) counter.incrementAndGet());
		sqoop.setSqoop_dbtype(dbtype);
		sqoop.setSqoop_ip(ip);
		sqoop.setSqoop_port(port);
		sqoop.setSqoop_tablespace(tablespace);
		sqoop.setSqoop_tablename(tablename);
		sqoop.setSqoop_user(user);
		sqoop.setSqoop_pwd(pwd);
		sqoop.setSqoop_pid("0");

		sqoopService.SqoopInsert(sqoop);

		try {
			new Thread(new Runnable() {
				InputStream is = null;

				public void run() {
					try {
						System.out.println("Step 1...");
						List<Sqoop> list = sqoopService.getAll();
						String dbtype = list.get(0).getSqoop_dbtype();
						String ip = list.get(0).getSqoop_ip();
						String port = list.get(0).getSqoop_port();
						String tablespace = list.get(0).getSqoop_tablespace();
						String user = list.get(0).getSqoop_user();
						String pwd = list.get(0).getSqoop_pwd();
						String tablename = list.get(0).getSqoop_tablename();
						String url = "";
						if (dbtype.equals("mysql")) {
							url = "jdbc:mysql://" + ip + ":" + port + "/" + tablespace;
						}

						ProcessBuilder pb = new ProcessBuilder("sqoop", "import", "--connect", url, "--username", user,
								"--password", pwd, "--table", tablename); //, "--hive-import"
						Process p = pb.start();

						System.out.println("Step 2...");
						is = p.getInputStream();
						BufferedReader reader = new BufferedReader(new InputStreamReader(is));
						String line;
						while ((line = reader.readLine()) != null) {
							System.out.println(line);
						}

						System.out.println("Step 3...");
						String pid = "1";
						list.get(0).setSqoop_pid(pid);
						sqoopService.update(list.get(0));
						
					} catch (Exception err) {
						System.out.println("First Exception : " + err.toString());
					} finally {
						System.out.println("Step 4...");
						try {
							is.close();
						} catch (IOException err) {
							System.out.println("IO Exception : " + err.toString());
						}
					}
				}
			}).start();
			System.out.println("Step 5...");
		} catch (RuntimeException err) {
			System.out.println("Runtime Exception : " + err.toString());
		} catch (Exception err) {
			System.out.println("Second Exception : " + err.toString());
		} finally {
			System.out.println("Step 6...");
		}

		return "Success";
	}

}
