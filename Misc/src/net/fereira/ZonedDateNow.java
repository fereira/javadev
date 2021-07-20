package net.fereira;
import java.time.ZoneId;
import java.time.ZonedDateTime;

//from www. j a  v a 2 s  . co m
public class ZonedDateNow {
  public static void main(String[] args) {
    ZonedDateTime z = ZonedDateTime.now(ZoneId.systemDefault());
    
    System.out.println(z);

  }
}
