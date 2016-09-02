package com.jci.po.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.microsoft.azure.storage.StorageException;

public class AzureUtils {
	//private static final Logger LOG = LoggerFactory.getLogger(AzureUtils.class);
	 
	public static String getPartitionKey(String erpName){
		//String partitionKey = erpName+"_"+"PO"+"_"+Calendar.getInstance().get(Calendar.YEAR);
		String partitionKey = erpName.toUpperCase()+"_"+"PO";	
		return partitionKey;
	}

    /**
     * Print the exception stack trace
     *
     * @param ex Exception to be printed
     */
    public static void printException(Throwable t) { // NO_UCD (unused code)

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        t.printStackTrace(printWriter);
        if (t instanceof StorageException) {
            if (((StorageException) t).getExtendedErrorInformation() != null) {
                System.out.println(String.format("\nError: %s", ((StorageException) t).getExtendedErrorInformation().getErrorMessage()));
            }
        }
        System.out.println(String.format("Exception details:\n%s", stringWriter.toString()));
    }

}
