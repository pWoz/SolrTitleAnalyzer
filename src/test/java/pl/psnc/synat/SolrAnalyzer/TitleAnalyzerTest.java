package pl.psnc.synat.SolrAnalyzer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Tests title extraction
 * <p>
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

    @Test
    public void testTitleAnalyzer() {
        System.out.println("Running analysis for : " + originalTitle);
        NewspaperTitle title = new NewspaperTitle(originalTitle);
        String foundTitle = title.findTitle();
        System.out.println("Result: " + foundTitle);
        Assert.assertEquals(expectedResult, foundTitle);
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {
                        "[Danziger Intelligenz Blatt für den Königlichen Regierungs-Bezirk Danzig, 1867.11.11 nr]",
                        "Danziger Intelligenz Blatt für den Königlichen Regierungs-Bezirk Danzig,"
                },
                {
                        "[Przegląd Wieczorny. 1919, no 253]",
                        "Przegląd Wieczorny."
                },
                {
                        "[Triterpenoids. Part XII. [1]. Oxidation. Products of Methyl 11-Oxo-18Hᵅ-oleanolate by Sodium Dichromate]",
                        "Triterpenoids. Part XII. [1]. Oxidation. Products of Methyl 11-Oxo-18Hᵅ-oleanolate by Sodium Dichromate"
                },
                {
                        "[Sprawozdanie Komisji Fizjograficznej T. 40 (1905)]",
                        "Sprawozdanie Komisji Fizjograficznej"
                },
                {
                        "[Dziennik Radiowy]",
                        "Dziennik Radiowy"
                },
                {
                        "[Dziennik Radiowy - Ostatnie Wiadomości. R. 5, 1944 nr 44/224, dodatek nadzwyczajny]",
                        "Dziennik Radiowy - Ostatnie Wiadomości."
                },

                {
                        "[Tygodnik Handlowy: czasopismo poświęcone sprawom gospodarczym: organ Stow. Kupców Polskich w m. Warszawie. R. 6, 1924, nr 38]",
                        "Tygodnik Handlowy: czasopismo poświęcone sprawom gospodarczym: organ Stow. Kupców Polskich w m. Warszawie."
                },
                {
                        "[Express Wieczorny Ilustrowany. 1930-08-02 R. 8 nr 213]",
                        "Express Wieczorny Ilustrowany."
                },
                {
                        "[Gość Niedzielny, 1999, R. 72, nr 23]",
                        "Gość Niedzielny,"
                },
                {
                        "[Grünberger Wochenblatt: Zeitung für Stadt und Land, No. 114. (25. September 1880)]",
                        "Grünberger Wochenblatt: Zeitung für Stadt und Land,"},
                {
                        "[Zaranie : pismo tygodniowe, ogólno-kształcące, społeczne, rolnicze i przemysłowe. 1910, nr 35]",
                        "Zaranie : pismo tygodniowe, ogólno-kształcące, społeczne, rolnicze i przemysłowe."},
                {
                        "[Express Wieczorny Ilustrowany. 1930-09-15 R. 8 nr 257]",
                        "Express Wieczorny Ilustrowany."},
                {
                        "[Telegraf Brukowy. 1862, no 19]",
                        "Telegraf Brukowy."},
                {
                        "[Tygodnik Handlowy: czasopismo poświęcone sprawom gospodarczym: organ Stow. Kupców Polskich w m. Warszawie. R. 5, 1923, nr 11]",
                        "Tygodnik Handlowy: czasopismo poświęcone sprawom gospodarczym: organ Stow. Kupców Polskich w m. Warszawie."},
                {
                        "[Przegląd Gospodarczy : organ Centralnego Związku Polskiego Przemysłu, Górnictwa, Handlu i Finansów. 1935, z. 9]",
                        "Przegląd Gospodarczy : organ Centralnego Związku Polskiego Przemysłu, Górnictwa, Handlu i Finansów."},
                {
                        "[Litzmannstaedter Zeitung 3 listopad 1940 nr 305]",
                        "Litzmannstaedter Zeitung"},
                {
                        "[Sprawozdanie Lekarskie ze Szpitala dla Psychicznie i Nerwowo Chorych \"Kochanówka\" 22 : 1924]",
                        "Sprawozdanie Lekarskie ze Szpitala dla Psychicznie i Nerwowo Chorych \"Kochanówka\""},
                {
                        "[Zbawiciel Świata R. 1 : 1933]",
                        "Zbawiciel Świata"},
                {
                        "[Lodzer Illustriertes Sonntagsblatt: Beilage zur ,,Neuen Lodzer Zeitung\" 11 października 1925 nr 42]",
                        "Lodzer Illustriertes Sonntagsblatt: Beilage zur ,,Neuen Lodzer Zeitung\""},
                {
                        "[Elbinger Anzeigen, Nr. 31. Sonnabend, 23. April 1825]",
                        "Elbinger Anzeigen,"},
                {
                        "[Polish Maritime Research, Vol. 16, No. 2 (60) 2009]",
                        "Polish Maritime Research,"},
                {
                        "[Staatsanzeiger für die Freie Stadt Danzig. Teil 2, Oeffentlicher Anzeiger, 1939.05.31 nr 38]",
                        "Staatsanzeiger für die Freie Stadt Danzig. Teil 2, Oeffentlicher Anzeiger,"},
                {
                        "[Dziennik Warszawski. 1872, nr 154 + dod.]",
                        "Dziennik Warszawski."},
                {
                        "[Danziger Intelligenz Blatt für den Königlichen Regierungs-Bezirk Danzig, 1867.11.11 nr]",
                        "Danziger Intelligenz Blatt für den Königlichen Regierungs-Bezirk Danzig,"},
                {
                        "[Przegląd Wieczorny. 1919, no 253]",
                        "Przegląd Wieczorny."},
                {
                        "[Telegraf Brukowy. 1862, no 19]",
                        "Telegraf Brukowy."},
                {
                        "[Dziennik Radiowy - Ostatnie Wiadomości. 1944 nr 4]",
                        "Dziennik Radiowy - Ostatnie Wiadomości."},
                {
                        "[Komunikat. 1944.08.13]",
                        "Komunikat."},
        });
    }
}





















