package pl.psnc.synat.SolrAnalyzer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Describes single title instance (taken from Solr)
 *
 * Algoryth of extracting proper title from 'originalTitle':
 * 1. Original title is split on spaces and every item is marked as suspicious (or not). What suspicious means will be later.
 * 2. We are scanning original title from the last word and searching for pair of non suspicious words. This value is stored in 'firstNonSuspiciousPairPosition'
 * 3. The output title is the concatenation of all words from original title from beggining to 'firstNonSuspiciousPairPosition'.
 *
 * <p>
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
		firstSuspiciousPosition = findFirstSuspiciousPosition();
		firstNonSuspiciousPairPosition = findFirstNonSuspiciousPairPosition();
	}

	public List<NewspaperTitlePart> getParts() {
		return splitTitle;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	/**
	 * Extracts title (without dates) from original title
	 *
	 * @return title without dates and issue numbers;
	 */
	public String findTitle() {
		StringBuilder result = new StringBuilder("");
		if (firstSuspiciousPosition < 1) {
			result.append(getOriginalTitle());
			return result.toString();
		}

		if(firstNonSuspiciousPairPosition == -1){
			if (firstSuspiciousPosition == 1) {
				result.append(getParts().get(0).getValue());
			}else{
				result.append(getOriginalTitle());
			}
		}

		for (int i = 0; i <= firstNonSuspiciousPairPosition; i++) {
			if (i < firstNonSuspiciousPairPosition) {
				result.append(getParts().get(i).getValue());
				result.append(" ");
			} else {
				if (!isBannedWord(getParts().get(i).getValue())) {
					result.append(getParts().get(i).getValue());
					result.append(" ");
				}
				result = new StringBuilder(result.substring(0, result.length() - 1));
			}

		}
		return result.toString();
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

	private int findFirstSuspiciousPosition() {
		int counter = -1;
		for (NewspaperTitlePart part : splitTitle) {
			counter++;
			if (part.isSuspected()) {
				return counter;
			}
		}
		return -1;
	}

	private int findFirstNonSuspiciousPairPosition() {
		boolean nonSuspiciousWordFound = false;
		for (int i = splitTitle.size() - 1; i >= 0; i--) {
			if (!splitTitle.get(i).isSuspected()) {
				if (nonSuspiciousWordFound) {
					if (i == splitTitle.size() - 2) {
						nonSuspiciousWordFound = false;
					} else {
						return i + 1;
					}
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

	private boolean isBannedWord(String word) {
		if ("R.".equalsIgnoreCase(word) ||
				"[R.".equalsIgnoreCase(word) ||
				"No.".equalsIgnoreCase(word) ||
				"Nr.".equalsIgnoreCase(word) ||
				"nr".equalsIgnoreCase(word) ||
				"Vol.".equalsIgnoreCase(word) ||
				"Jg.".equalsIgnoreCase(word) ||
				"T.".equalsIgnoreCase(word)
				)
			return true;
		else
			return false;
	}
}
