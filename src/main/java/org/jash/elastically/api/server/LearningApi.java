package org.jash.elastically.api.server;

import java.util.UUID;

import org.apache.hc.core5.http.HttpStatus;
import org.jash.elastically.entity.Learning;
import org.jash.elastically.service.KnowledgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/learning/v1")
@Tag(name = "Learning API", description = "Documentation for Learning API")
@RestController
public class LearningApi {
	private static final Logger LOG = LoggerFactory.getLogger(LearningApi.class);

	private KnowledgeService knowledgeService;

	@Autowired
	public LearningApi(KnowledgeService knowledgeService) {
		this.knowledgeService = knowledgeService;
	}


	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Record new learning", description = "Echo newly created one")
	public ResponseEntity<String> newLearning(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody Learning learning) {
		try {
			
			learning.setId(UUID.randomUUID().toString());
			LOG.debug("Passed : {}",learning);
			return new ResponseEntity(knowledgeService.learnNewThing(learning),HttpStatusCode.valueOf(HttpStatus.SC_CREATED));
		} catch (Exception exception) {
           LOG.error("ERR : {}",exception.getMessage(),exception);
		}
		return new ResponseEntity("ERROR",HttpStatusCode.valueOf(HttpStatus.SC_SERVER_ERROR));
	}
	
	@GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Retrieve existing learning", description = "Get existing one or not found")
	public ResponseEntity<String> getLearning(@PathVariable String id){
		var retrievedLearning = knowledgeService.getLearning(id);
		return retrievedLearning != null ? new ResponseEntity(retrievedLearning,HttpStatusCode.valueOf(HttpStatus.SC_OK)) :
			new ResponseEntity("NOT FOUND",HttpStatusCode.valueOf(HttpStatus.SC_NOT_FOUND));
	}
	
	@GetMapping(value="/topic/{topic}",produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Retrieve by existing topic", description = "Get existing topics or not found")
	public ResponseEntity<String> getTopic(@PathVariable String topic){
		var retrievedLearning = knowledgeService.findLearningByTopic(topic);
		return retrievedLearning != null ? new ResponseEntity(retrievedLearning,HttpStatusCode.valueOf(HttpStatus.SC_OK)) :
			new ResponseEntity("NOT FOUND",HttpStatusCode.valueOf(HttpStatus.SC_NOT_FOUND));
	}
	
}
