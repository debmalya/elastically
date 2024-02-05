package org.jash.elastically.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class MyElasticConfiguration extends ElasticsearchConfiguration {
    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.username}")
    private String userName;

    @Value("${elasticsearch.password}")
    private String password;
    
    @Value("${elasticsearch.token}")
    private String token;

	@Override
	public org.springframework.data.elasticsearch.client.ClientConfiguration clientConfiguration() {
		
		return ClientConfiguration.builder().connectedTo(host)
				.usingSsl(token)
				.withBasicAuth(userName, password).build();
	}

}
