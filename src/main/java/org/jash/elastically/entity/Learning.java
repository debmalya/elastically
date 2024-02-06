package org.jash.elastically.entity;

import java.time.LocalDate;
import java.util.List;

import org.jash.elastically.api.server.LearningApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(indexName = "learning")
public class Learning {
	private static final Logger LOG = LoggerFactory.getLogger(Learning.class);
	@Id
	private String id;

	@Field
	private String topicName;
	@Field
	private List<String> url;
	@Field
	private String description;
	
	@Field(type = FieldType.Date, format = DateFormat.date)
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Kolkata")
	private LocalDate addedOn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public LocalDate getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(LocalDate addedOn) {
		this.addedOn = addedOn;
	}

	public List<String> getUrl() {
		return url;
	}

	public void setUrl(List<String> url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Learning(String id, String topicName, List<String> url, String description, LocalDate addedOn) {
		super();
		this.id = id;
		this.topicName = topicName;
		this.url = url;
		this.description = description;
		this.addedOn = addedOn;
		LOG.info("~~~~ All property constructor called ~~~~");
	}
	
	public Learning() {
	}

	@Override
	public String toString() {
		return "Learning [id=" + id + ", topicName=" + topicName + ", url=" + url + ", description=" + description
				+ ", addedOn=" + addedOn + "]";
	}
	
	

}
