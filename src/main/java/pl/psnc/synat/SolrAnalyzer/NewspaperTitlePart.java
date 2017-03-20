package pl.psnc.synat.SolrAnalyzer;

/**
 * Created by pwozniak on 3/17/17.
 */
public class NewspaperTitlePart {

	private String value;
	private int numberOfDigits;


	public NewspaperTitlePart(String value) {
		this.value = value;
		this.numberOfDigits = value.replaceAll("\\D", "").length();
	}

	public String getValue() {
		return value;
	}

	public boolean isSuspected() {
		if (value.length() < 2) {
			return numberOfDigits > value.length() / 2;
		} else {
			return numberOfDigits >= value.length() / 2;
		}
	}

	@Override
	public String toString() {
		return value + ":" + numberOfDigits;
	}
}
