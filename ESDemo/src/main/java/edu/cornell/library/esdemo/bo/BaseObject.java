package edu.cornell.library.esdemo.bo;

import org.springframework.data.annotation.Id;

public class BaseObject {
   /**
    * Simple JavaBean domain object with an id property.
    * Used as a base class for objects needing this property.
    *
    * @author Ken Krebs
    * @author Juergen Hoeller
    */
   @Id
    private Integer id;

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getId() {
      return id;
   } 
}
