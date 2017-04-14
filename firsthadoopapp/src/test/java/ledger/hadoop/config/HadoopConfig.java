
package ledger.hadoop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HadoopConfig {
	
	@Bean(name="LedgerHdfsClient")
	public LedgerHdfsClient ledgerHdfsClient(){
		System.out.println("LedgerHdfsClient from bean");
	    return new LedgerHdfsClient();
	}
}
