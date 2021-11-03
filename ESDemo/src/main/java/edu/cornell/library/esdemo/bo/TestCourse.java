package edu.cornell.library.esdemo.bo;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


// @Document(indexName = "testcourses", shards = 1, replicas = 0, refreshInterval = "5s", createIndex = true)
public class TestCourse extends BaseObject implements Serializable {

   private static final long serialVersionUID = -3146082196126823410L;

   //@Field(type = FieldType.Keyword)
   private String courseNumber;
   
   //@Field(type = FieldType.Text)
   private String courseName;
   
   public TestCourse() {
      super();
   }

   /**
    * @return the courseNumber
    */
   public String getCourseNumber() {
      return courseNumber;
   }

   /**
    * @param courseNumber
    *           the courseNumber to set
    */
   public void setCourseNumber(String courseNumber) {
      this.courseNumber = courseNumber;
   }

    

    
   /**
    * @return the courseName
    */
   public String getCourseName() {
      return courseName;
   }

   /**
    * @param courseName
    *           the courseName to set
    */
   public void setCourseName(String courseName) {
      this.courseName = courseName;
   } 


}
