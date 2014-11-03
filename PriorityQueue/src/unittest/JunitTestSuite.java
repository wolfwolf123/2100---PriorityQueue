package unittest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class) // when called to run use suite class defined below
@Suite.SuiteClasses({
   SortedPQTest.class,
   BinarySerchTreeTest.class
})
public  class JunitTestSuite {   
} 