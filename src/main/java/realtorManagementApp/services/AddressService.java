package realtorManagementApp.services;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AddressService {
    String checkParameterString(String paramName, HttpServletRequest request, Map<String, String> errors, String param);

    int checkParameterInteger(String paramName, HttpServletRequest request, Map<String, String> errors, int param);

    long checkParameterLong(String paramName, HttpServletRequest request, Map<String, String> errors, long param);
}
