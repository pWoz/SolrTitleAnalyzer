import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.psnc.synat.SolrAnalyzer.NewspaperTitle;

import java.util.Arrays;
import java.util.Collection;

/**
 * Tests FileAnalyzer class
 *
 * Created by pwozniak on 3/20/17.
 */
@RunWith(Parameterized.class)
public class TitleAnalyzerTest {

	private String originalTitle;
	private String expectedResult;

	public TitleAnalyzerTest(String originalTitle, String expectedResult) {
		this.originalTitle = originalTitle;
		this.expectedResult = expectedResult;
	}

	// This test will run 4 times since we have 5 parameters defined
	@Test
	public void testTitleAnalyzer() {
		System.out.println("Running analysys for : " + originalTitle);
		NewspaperTitle title = new NewspaperTitle(originalTitle);
		String foundTitle = title.algoryth_2();
		Assert.assertEquals(foundTitle, expectedResult);
	}

	@Parameterized.Parameters
	public static Collection primeNumbers() {
		return Arrays.asList(new Object[][]{
				{"[Express Wieczorny Ilustrowany. 1930-08-02 R. 8 nr 213]", "Express Wieczorny Ilustrowany."},
				{"[Gość Niedzielny, 1999, R. 72, nr 23]", "Gość Niedzielny,"}
		});
	}
}
