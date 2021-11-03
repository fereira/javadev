package edu.cornell.library.esdemo.service;

import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.index.IndexResponse; 

import edu.cornell.library.esdemo.bo.TestCourse; 

public interface IIndexService {
	
	/**
	 * @param course
	 * @return
	 * @throws Exception
	 */
	IndexResponse indexCourse(TestCourse course) throws Exception ;
	
	boolean createCourseIndex();
	
	public String getElasticsearchIndexSetting(String indexName, String settingName) throws Exception ;
	 
}
