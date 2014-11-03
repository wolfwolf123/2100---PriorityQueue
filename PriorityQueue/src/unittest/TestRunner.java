package unittest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(JunitTestSuite.class); // Calls the JUnitTestSuit's classes, defined as FIFOQTest and LIFOQTest
      for (Failure failure : result.getFailures()) {
         System.out.println("TestRunner:: had failure "+failure.toString());
      }
      System.out.println("TestRunner::  successful = "+result.wasSuccessful());
   }
}  
 
