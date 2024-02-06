package org.jash.elastically.service.impl;

import java.util.List;

import org.jash.elastically.entity.Learning;
import org.jash.elastically.repository.LearningElasticRepository;
import org.jash.elastically.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
	
	private LearningElasticRepository learningElasticRepository;
	
	@Autowired
	public KnowledgeServiceImpl(LearningElasticRepository learningElasticRepository) {
		this.learningElasticRepository = learningElasticRepository;
	}

	@Override
	public Learning learnNewThing(Learning learning) {
		return learningElasticRepository.save(learning);
	}

	@Override
	public Learning getLearning(String id) {
		return learningElasticRepository.findById(id).orElse(null);
	}

	@Override
	public List<Learning> findLearningByTopic(String topic) {
		return learningElasticRepository.findByTopicName(topic);
	}

}
