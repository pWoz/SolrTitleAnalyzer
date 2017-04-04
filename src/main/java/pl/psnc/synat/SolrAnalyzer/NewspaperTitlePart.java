package pl.psnc.synat.SolrAnalyzer;

/**
 * Created by pwozniak on 3/17/17.
 */
public class NewspaperTitlePart {

    private String value;
    private int numberOfDigits;
    private int numberOfNonWordCharacters;
    private int numberOfWordCharacters;


    public NewspaperTitlePart(String value) {
        this.value = value;
        this.numberOfDigits = value.replaceAll("\\D", "").length();
        this.numberOfNonWordCharacters = value.replaceAll("\\w","").length();
        this.numberOfWordCharacters = value.replaceAll("\\W", "").length();
    }

    public String getValue() {
        return value;
    }

    public boolean isSuspected() {
        if (value.length() == 1 && numberOfNonWordCharacters > 0) {
            return true;
        }
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
