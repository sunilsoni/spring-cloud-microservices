/**
 * 
 */
package com.jci.asn.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author jci
 *
 */
@JsonDeserialize(using = CommentsCollectionDeserializer.class)
public class PoCollectionResource {
	private List<PoResource> taskComments;

	/**
	 * Adds the comment.
	 *
	 * @param comment
	 *            the comment
	 */
	public void addComment(PoResource comment) {
		if (this.taskComments == null) {
			this.taskComments = new ArrayList<>();
		}
		this.taskComments.add(comment);
	}

	/**
	 * @return the taskComments
	 */
	public List<PoResource> getTaskComments() {
		return taskComments;
	}

	/**
	 * @param taskComments
	 *            the taskComments to set
	 */
	public void setTaskComments(List<PoResource> taskComments) {
		this.taskComments = taskComments;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskComments == null) ? 0 : taskComments.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PoCollectionResource other = (PoCollectionResource) obj;
		if (taskComments == null) {
			if (other.taskComments != null)
				return false;
		} else if (!taskComments.equals(other.taskComments))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CommentCollectionResource [taskComments=" + taskComments + "]";
	}
}

/**
 * Inner class to perform the de-serialization of the comments array
 * 
 * @author jci
 *
 */
class CommentsCollectionDeserializer extends JsonDeserializer<PoCollectionResource> {
	@Override
	public PoCollectionResource deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		PoCollectionResource commentArrayResource = new PoCollectionResource();
		PoResource commentResource = null;

		JsonNode jsonNode = jp.readValueAsTree();

		for (JsonNode childNode : jsonNode) {
			if (childNode.has(PoResource.JP_TASKID)) {
				commentResource = new PoResource();
				commentResource.setTaskId(childNode.get(PoResource.JP_TASKID).asText());
				commentResource.setComment(childNode.get(PoResource.JP_COMMENT).asText());
				commentResource.setPosted(new Date(childNode.get(PoResource.JP_POSTED).asLong()));

				commentArrayResource.addComment(commentResource);
			}
		}
		return commentArrayResource;

	}
}