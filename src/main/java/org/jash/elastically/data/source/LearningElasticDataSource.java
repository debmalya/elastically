package org.jash.elastically.data.source;

import java.util.Base64;

import org.jash.elastically.repository.LearningElasticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.elasticsearch.support.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class LearningElasticDataSource {
    @Value("${elasticsearch.host}")
    private String host;
	
    @Value("${elasticsearch.username}")
    private String userName;

    @Value("${elasticsearch.password}")
    private String password;
    
    @Value("${elasticsearch.indexName}")
    private String indexName;
    

	private static final Logger logger = LoggerFactory.getLogger(LearningElasticDataSource.class);
	@Autowired
	private LearningElasticRepository learningElasticRepository;
	
	@Autowired
	@Qualifier("restTemplateElasticsearch")
	private RestTemplate restTemplate;
	
	@EventListener(ApplicationReadyEvent.class)
	public void onStartUp() {
		// Set basic authentication
		var httpHeaders = new HttpHeaders();
		var authCredentials = userName+":"+password;
		var authHeader = "Basic " + Base64.getEncoder().encodeToString(authCredentials.getBytes());
		httpHeaders.add(HttpHeaders.AUTHORIZATION, authHeader);
		
		// consume API
		var entity = new HttpEntity<String>(httpHeaders);
		var response = restTemplate.exchange(String.format("https://%s/%s",host, indexName), HttpMethod.GET, entity, String.class);
		logger.info("Elastic Response : {}",response);
	}

}
