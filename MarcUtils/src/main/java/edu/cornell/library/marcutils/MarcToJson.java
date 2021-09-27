package edu.cornell.library.marcutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
 
import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.DataField;
import org.marc4j.marc.Record;

import edu.cornell.library.marcutils.util.MarcUtils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
//import org.apache.commons.configuration.CompositeConfiguration;
//import org.apache.commons.configuration.ConfigurationException;
//import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
import org.json.JSONArray;
import org.json.JSONObject; 

 
public class MarcToJson {
    
    protected final Log logger = LogFactory.getLog(getClass());
    
    private final int INDENT = 3;
    private final int TEN = 10;
    private final int FUNDVAL = 100;
    private final String DEFAULTPONUM = "12345"; 
    private String marcFileName;
    private boolean debug;    

    private String endpoint;
    
    MarcUtils marcUtils = new MarcUtils(); 
    
    public String getMarcFileName() {
        return marcFileName;
    }

    public void setMarcFileName(String marcFileName) {
        this.marcFileName = marcFileName;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
    
    public MarcUtils getMarcUtils() {
        return marcUtils;
    }

    public void setMarcUtils(MarcUtils marcUtils) {
        this.marcUtils = marcUtils;
    }

    
    public MarcToJson() {
        // 
    }
    
    public static void main(String[] args) {
        MarcToJson app = new MarcToJson();
        app.getArgs(args);
        try {
            app.init();
            JSONArray responseMessages  = app.run();
            System.out.println(responseMessages.toString(3));
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public  void getArgs(String[] args) {
        String appName = this.getClass().getSimpleName();
        Options options = new Options();
        options.addOption(new Option("f", "file", true, "marc file (REQUIRED)")); 
        options.addOption(new Option("D","debug", false, "turn on debug output"));
         
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        try {
            CommandLine cmd = parser.parse( options, args); 
            if (cmd.hasOption("file")) {
                this.setMarcFileName(cmd.getOptionValue("file"));
            } else { 
                formatter.printHelp(appName, options );
                System.exit(0);
            }
     
            if (cmd.hasOption("D") || cmd.hasOption("debug")) {
                this.setDebug(true);
            }
            
            
        } catch (ParseException e) {
            formatter.printHelp(appName, options );
            System.exit(0);
        }
    }

    /**
     * @throws Exception - an exception
     */
    public void init() throws Exception { 
         
         
    }  
    
    
    /**
     * @return
     * @throws Exception
     */
    public JSONArray run() throws Exception {          
 
        String materialTypeName = "Book";
        JSONArray responseMessages = new JSONArray();        
        JSONArray errorMessages = new JSONArray();
        // GENERATE UUID for the PO 
       
        //GET THE NEXT PO NUMBER
        // harcode the ponumber just so we have one  
        JSONObject poNumberObj = new JSONObject();
        poNumberObj.put("poNumber", DEFAULTPONUM);
        logger.debug("NEXT PO NUMBER: " + poNumberObj.get("poNumber"));
        
        //responseMessages.put(poNumberObj);
        
        // CREATING THE PURCHASE ORDER
        JSONObject order = new JSONObject();
        UUID orderUUID = UUID.randomUUID();        
        Map<Integer, UUID> orderLineMap = new HashMap<Integer, UUID>(); 
        
        order.put("orderType", "One-Time");
        order.put("reEncumber", false);
        order.put("id", orderUUID.toString());
        order.put("approved", true);
        order.put("workflowStatus", "Open"); 
        order.put("poNumber", poNumberObj.get("poNumber"));
        
        JSONArray poLines = new JSONArray();
        
        // iterator over records in the marc file.
        String filePath =  this.getMarcFileName();
        InputStream in = new FileInputStream(filePath);        
        MarcReader reader = new MarcStreamReader(in);
  
        int numRec = 0;
        
        while (reader.hasNext()) {
            try {
                Record record = reader.next();
                //if (isDebug()) {
                //    System.out.println(record.toString());
                //}
                JSONObject responseMessage = new JSONObject();
                 
                DataField twoFourFive = (DataField) record.getVariableField("245");
                DataField nineEighty = (DataField) record.getVariableField("980");
                DataField nineEightyOne = (DataField) record.getVariableField("981");
                DataField nineSixtyOne = (DataField) record.getVariableField("961");
                
                String title = marcUtils.getTitle(twoFourFive);
                if (isDebug()) {
                    System.out.println("Title: "+ title);
                }
                
                final String fundCode = marcUtils.getFundCode(nineEighty);
                final String vendorCode =  marcUtils.getVendorCode(nineEighty);
                    
                String quantity =  marcUtils.getQuantity(nineEighty);
                Integer quantityNo = 0; //INIT
                if (quantity != null)  { 
                    quantityNo = Integer.valueOf(quantity);
                }
                
                String price = marcUtils.getPrice(nineEightyOne);                
                final String vendorItemId = marcUtils.getVendorItemId(nineSixtyOne);
                
                //String personName = marcUtils.getPersonName(nineEighty);
                
                DataField nineFiveTwo = (DataField) record.getVariableField("952");
                String locationName = marcUtils.getLocation(nineFiveTwo);
                //responseMessage.put("location", locationName + " (" + lookupTable.get(locationName + "-location") + ")");
                             
                 
                
                
                // Create an orderLine for each record
                // FOLIO WILL CREATE THE INSTANCE, HOLDINGS, ITEM (IF PHYSICAL ITEM)
                JSONObject orderLine = new JSONObject();
                JSONObject cost = new JSONObject();
                JSONObject location = new JSONObject();
                JSONArray locations = new JSONArray(); 
  
                JSONObject physical = new JSONObject();
                physical.put("createInventory", "Instance, Holding, Item"); 
                orderLine.put("physical", physical);
                orderLine.put("orderFormat", "Physical Resource");
                cost.put("listUnitPrice", price);
                cost.put("quantityPhysical", 1);
                location.put("quantityPhysical", quantityNo); 
                locations.put(location);
                 
                //VENDOR REFERENCE NUMBER IF INCLUDED IN THE MARC RECORD:                
                if (StringUtils.isNotEmpty(vendorItemId)) {                    
                    JSONArray referenceNumbers = new JSONArray();
                    JSONObject vendorDetail = new JSONObject();
                    JSONObject referenceNumber = new JSONObject();
                    referenceNumber.put("refNumber", vendorItemId);
                    referenceNumber.put("refNumberType", "Vendor internal number");
                    referenceNumbers.put(referenceNumber);
                    vendorDetail.put("referenceNumbers", referenceNumbers);
                    orderLine.put("vendorDetail", vendorDetail);
                }
                
                UUID orderLineUUID = UUID.randomUUID();
                orderLine.put("id", orderLineUUID);
                //responseMessage.put("id", orderLineUUID.toString());
                orderLineMap.put(numRec, orderLineUUID); 
                
                orderLine.put("source", "User");
                cost.put("currency", "USD");
                orderLine.put("cost", cost);
                orderLine.put("locations", locations);
                orderLine.put("titleOrPackage", title);
                orderLine.put("acquisitionMethod", "Purchase");
                
                // get the "internal note", which apparently will be used as a description 
                String internalNotes =  marcUtils.getInternalNotes(nineEighty);
                if (StringUtils.isNotEmpty(internalNotes)) {
                    orderLine.put("description", internalNotes);
                }
                
                JSONObject detailsObject = new JSONObject();
                // get the "receiving note"
                String receivingNote =  marcUtils.getReceivingNote(nineEightyOne);
                if (StringUtils.isNotEmpty(receivingNote)) {
                    detailsObject.put("receivingNote", receivingNote);
                }
                // get ISBN values in a productIds array and add to detailsObject if not empty
                JSONArray productIds = marcUtils.getISBN(record, "ISBN");
                if (productIds.length() > 0) {
                    detailsObject.put("productIds", productIds);
                }
                
                if (! detailsObject.isEmpty()) {
                    orderLine.put("details", detailsObject);   
                }
                
                // get rush value
                String rush = marcUtils.getRush(nineEightyOne);
                // TODO: check if match rush value to Rush:yes before adding to orderLine
                if (StringUtils.isNotEmpty(rush) && StringUtils.contains(rush.toLowerCase(), "rush:yes")) {
                    orderLine.put("rush", true);
                }
                
                // get selector
                String selector = marcUtils.getSelector(nineEighty);
                if (StringUtils.isNotEmpty(selector)) {
                    orderLine.put("selector", selector);
                }
                
                // get requester
                String requester = marcUtils.getRequester(nineEightyOne);
                if (StringUtils.isNotEmpty(requester)) {
                    orderLine.put("requester", requester);
                }
                
                
                 
                JSONObject fundDist = new JSONObject();
                fundDist.put("distributionType", "percentage");
                fundDist.put("value", FUNDVAL); 
                JSONArray funds = new JSONArray();
                funds.put(fundDist);
                orderLine.put("fundDistribution", funds);

                orderLine.put("purchaseOrderId", orderUUID.toString());
                poLines.put(orderLine);
                order.put("compositePoLines", poLines);
                responseMessages.put(order);
                // responseMessages.put(responseMessage);
            } catch (Exception e) {
                logger.error(e.toString());
                JSONObject errorMessage = new JSONObject();
                errorMessage.put("error", e.toString());
                errorMessage.put("PONumber", poNumberObj.get("poNumber"));
                errorMessages.put(errorMessage);
                return errorMessages;                
            }
            
            numRec++;
         
        }  
        
        return responseMessages;
    }
     
    
    /**
     * @param df - datafield
     * @param name - name of datafield
     */
    public void validateDataField(DataField df, String name) {
        if (df == null) {
            logger.error("Required Datafield " + name + " is null");
            System.exit(1);
        }
    }
    
     
}
