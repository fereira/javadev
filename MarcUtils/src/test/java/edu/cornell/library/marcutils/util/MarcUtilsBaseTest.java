package edu.cornell.library.marcutils.util;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader; 
import org.marc4j.marc.Record;
 
/**
 * MarcUtilsBaseTest.
 * 
 * @author jaf30
 *
 */ 
public class MarcUtilsBaseTest {

    private String buildDir;

    protected String harrass;
    protected String casalini;
    protected String physical;
    protected String amazonFO;
    protected String coutts;
    protected String requestors;
    protected String singleharrass;
    protected String harrassowitz;
    protected String bksFO;
    
    List<String> fnames = new ArrayList<String>();

    MarcUtils marcUtils = new MarcUtils();

    public MarcUtilsBaseTest() {
        init();
        this.harrass = "/cul/data/marc-test-files/harrass.mrc";
        this.casalini = "/cul/data/marc-test-files/Casalini.1.mrc";
        this.physical = "/cul/data/marc-test-files/physical.mrc";
        this.amazonFO = "/cul/data/marc-test-files/AmazonFO.1.mrc";
        this.coutts = "/cul/data/marc-test-files/CouttsUKFO.1.mrc";
        this.requestors = "/cul/data/marc-test-files/requesters_5-records_2021-03-11.mrc";
        this.singleharrass = "/cul/data/marc-test-files/singleharrass.mrc";
        this.harrassowitz = "/cul/data/marc-test-files/harrassowitz_9-records_2021-03-10.mrc";
        this.bksFO = "/cul/data/marc-test-files/w.j.bksFO.1.mrc";
        
        fnames.add(this.harrass);
        fnames.add(this.casalini);
        fnames.add(this.physical);
        fnames.add(this.amazonFO);
        fnames.add(this.coutts);
        fnames.add(this.requestors);
        fnames.add(this.singleharrass);
        fnames.add(this.harrassowitz);
        fnames.add(this.bksFO);
        
    }

    public List<Record> getRecords(String fname) throws Exception {
        List<Record> records = new ArrayList<Record>();
        FileInputStream in = new FileInputStream(fname);
        MarcReader reader = new MarcStreamReader(in);
        Record record = null;
        while (reader.hasNext()) {
            record = reader.next();
            records.add(record);
        }
        return records;
    }

    public void init() {
        CompositeConfiguration config = new CompositeConfiguration();
        PropertiesConfiguration props = new PropertiesConfiguration();

        String use_env = System.getenv("USE_SYSTEM_ENV");
        if (StringUtils.isNotEmpty(use_env) && StringUtils.equals(use_env, "true")) {
            config.setProperty("buildDir", System.getenv("buildDir"));

        } else {
            try {
                props.load(ClassLoader.getSystemResourceAsStream("application.properties"));
                this.buildDir = (String) props.getProperty("buildDir");
            } catch (ConfigurationException e) {
                throw new RuntimeException("Could not load application.properties file");
            }
            config.addConfiguration(props);
        }
        
        
    }
}
