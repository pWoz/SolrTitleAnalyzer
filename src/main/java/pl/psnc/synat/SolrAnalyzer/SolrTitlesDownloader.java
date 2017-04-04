package pl.psnc.synat.SolrAnalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Dowloads title from Solr instance and pushes them to file
 *
 * Created by pwozniak on 3/14/17.
 */
public class SolrTitlesDownloader {

    private static Logger LOG = LogManager.getLogger(SolrTitlesDownloader.class);

    private String url;
    private SolrClient solrClient;
    private FileWriter fileWriter;

    SolrTitlesDownloader(String url) throws IOException {
        this.url = url;
        solrClient = new HttpSolrClient.Builder(this.url).build();
        fileWriter = new FileWriter("results_1.csv");
    }

    public SolrQuery buildQuery() {
        LOG.debug("Building query");
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setFields("id", "dc_title");
        query.addFilterQuery("tech_type:czasopismo");
        LOG.debug("Query created: " + query);
        return query;
    }

    public QueryResponse executeQuery(SolrQuery query) throws IOException, SolrServerException {
        LOG.debug("Executing query");
        return solrClient.query(query);
    }

    public void writeResultsToFile(SolrDocumentList results) throws IOException {
        LOG.debug("Writing results to file");
        for (SolrDocument doc : results) {
            fileWriter.write(doc.get("id").toString());
            fileWriter.write(",");
            if (doc.get("dc_title") != null) {
                fileWriter.write(doc.get("dc_title").toString());
            } else {
                fileWriter.write("NULL");
            }
            fileWriter.write(System.lineSeparator());
        }
    }

    public void closeFile() throws IOException {
        fileWriter.close();
    }
}
