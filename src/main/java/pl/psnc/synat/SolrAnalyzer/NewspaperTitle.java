package pl.psnc.synat.SolrAnalyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Describes single title instance (taken from Solr)
 *
 * Created by pwozniak on 3/17/17.
 */
public class NewspaperTitle {

	private String originalTitle;
	private List<NewspaperTitlePart> splitTitle;
	private int firstSuspiciousPosition;
	private int firstNonSuspiciousPairPosition;

	public NewspaperTitle(String value) {
		originalTitle = removeFirstSign(removeLastSign(value));
		splitTitle = splitTitle(originalTitle);
		firstSuspiciousPosition = findFirstSuspitiousPosition();
		firstNonSuspiciousPairPosition = findFirstNonSuspiciousPairPosition();
	}

	private List<NewspaperTitlePart> getParts() {
		return splitTitle;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public String algoryth_1() {
		String result = "";
		for (int i = 0; i <= firstSuspiciousPosition; i++) {
			result += splitTitle.get(i).getValue();
			if (i != findFirstSuspitiousPosition())
				result += " ";
		}
		return result;
	}

	public String algoryth_2() {
		String result = "";
		for (int i = 0; i <= firstNonSuspiciousPairPosition; i++) {
			result += getParts().get(i).getValue();
			if (i < firstNonSuspiciousPairPosition)
				result += " ";
		}
		return result;
	}

	private List<NewspaperTitlePart> splitTitle(String title) {
		List<NewspaperTitlePart> result = new ArrayList<>();
		List<String> parts = Arrays.asList(title.split(" "));
		for (String part : parts) {
			NewspaperTitlePart n = new NewspaperTitlePart(part);
			result.add(n);
		}
		return result;
	}

	private int findFirstSuspitiousPosition() {
		int counter = -1;
		for (NewspaperTitlePart part : splitTitle) {
			counter++;
			if (part.isSuspected()) {
				return counter;
			}
		}
		return counter;
	}

	private int findFirstNonSuspiciousPairPosition() {
		boolean nonSuspiciousWordFound = false;
		for (int i = splitTitle.size() - 1; i >= 0; i--) {
			if (!splitTitle.get(i).isSuspected()) {
				if (nonSuspiciousWordFound) {
					return i + 1;
				} else {
					nonSuspiciousWordFound = true;
				}
			} else {
				nonSuspiciousWordFound = false;
			}
		}
		return -1;
	}

	private String removeFirstSign(String title) {
		return title.substring(1);

	}

	private String removeLastSign(String title) {
		if (title == null) {
			System.out.println("Null");
		}
		return title.substring(0, title.length() - 1);
	}
}
