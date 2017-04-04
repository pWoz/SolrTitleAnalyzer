package pl.psnc.synat.SolrAnalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;

/**
 * Harvest titles for all newspapers ( tech_type:czasopismo) from FBC solr
 * Solr url has to be specified
 * <p>
 *
 * Created by pwozniak on 3/14/17.
 */
public class TitlesHarvester {

    private static Logger LOG = LogManager.getLogger(TitlesHarvester.class);

    public static void main(String[] args) throws IOException, SolrServerException {

        String url = "xyz";

        SolrTitlesDownloader analyzer = new SolrTitlesDownloader(url);

        SolrQuery query = analyzer.buildQuery();

        boolean done = false;
        int start = 0;
        int rows = 100;

        while (!done) {
            LOG.info("Reading: " + start + ":" + rows);
            query.setStart(start);
            query.setRows(rows);
            QueryResponse response = analyzer.executeQuery(query);

            SolrDocumentList list = response.getResults();
            analyzer.writeResultsToFile(list);

            if (list.isEmpty()) {
                done = true;
            } else {
                start = start + rows;
            }

        }
        analyzer.closeFile();
    }
}
