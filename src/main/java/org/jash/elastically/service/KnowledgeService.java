package org.jash.elastically.service;

import java.util.List;

import org.jash.elastically.entity.Learning;

public interface KnowledgeService {
	Learning learnNewThing(Learning learning);
	Learning getLearning(String id);
	List<Learning> findLearningByTopic(String topic);

}
