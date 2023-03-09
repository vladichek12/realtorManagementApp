package realtorManagementApp.services;


import org.apache.commons.fileupload.FileItemStream;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AddressService {
    String checkParameterString(String paramName, HttpServletRequest request, Map<String, String> errors, String param);

    int checkParameterInteger(String paramName, HttpServletRequest request, Map<String, String> errors, int param);

    long checkParameterLong(String paramName, HttpServletRequest request, Map<String, String> errors, long param);

    String checkString(String paramName, Map<String, String> errors, Map<String, String> params, String param, FileItemStream item);

    int checkInteger(String paramName, Map<String, String> errors, Map<String, String> params, int param, FileItemStream item);

    long checkLong(String paramName, Map<String, String> errors, Map<String, String> params, long param, FileItemStream item);

}
