package edu.cornell.library.esdemo.service;


import org.elasticsearch.action.search.SearchResponse;
import edu.cornell.library.esdemo.bo.SystemStatus;

public interface ISearchService {

    /**
     * @param term
     * @return
     * @throws Exception
     */
    SearchResponse findCourses(String term) throws Exception;
     
    
    /**
     * @return
     * @throws Exception
     */
    SearchResponse findAllCourses() throws Exception;
    
    /**
     * @param courseName
     * @return
     * @throws Exception
     */
    SearchResponse findCoursesByName(String courseName) throws Exception;
    
    /**
     * @param courseName
     * @return
     * @throws Exception
     */
    SearchResponse findCoursesByNumber(String courseNumber) throws Exception;
    
    /**
     * @return
     * @throws Exception
     */
    public SystemStatus getHealth() throws Exception;
    
}
