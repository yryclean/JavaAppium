package tests.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ArticleTests;
import tests.GetStartedTests;
import tests.SearchTests;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                ArticleTests.class,
                GetStartedTests.class,
                SearchTests.class
        }
)
public class TestSuite {

}
