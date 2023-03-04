package realtorManagementApp.services.impl;

import realtorManagementApp.dao.RoomDao;
import realtorManagementApp.dao.impl.RoomDaoImpl;
import realtorManagementApp.services.AddressService;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class AddressServiceImpl implements AddressService {
    private static volatile AddressServiceImpl instance;


    private final String WRONG_ARGUMENT_MESSAGE = "Введенное Вами значение неверное!";
    private final String WRONG_INT_ARGUMENT_MESSAGE = "Требуется ввести целое число";
    private final String WRONG_LONG_ARGUMENT_MESSAGE = "Требуется ввести действительное положительное число";

    private RoomDao roomDao;

    public static AddressServiceImpl getInstance() {
        AddressServiceImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (AddressServiceImpl.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new AddressServiceImpl();
            }
        }
        return localInstance;
    }

    private AddressServiceImpl() {
        roomDao = RoomDaoImpl.getInstance();
    }

    @Override
    public String checkParameterString(String paramName, HttpServletRequest request, Map<String, String> errors, String param) {
        try {
            param = request.getParameter(paramName);
            if (param == null || param.isEmpty()) {
                throw new IllegalArgumentException(paramName);
            }
        } catch (IllegalArgumentException iae) {
            errors.put(iae.getMessage(), new String(WRONG_ARGUMENT_MESSAGE.getBytes(), StandardCharsets.UTF_8));
        }
        return param;
    }

    @Override
    public int checkParameterInteger(String paramName, HttpServletRequest request, Map<String, String> errors, int param) {
        try {
            param = Integer.parseInt(request.getParameter(paramName));
            if (param < 1) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException nfe) {
            errors.put(paramName, new String((WRONG_ARGUMENT_MESSAGE + " " + WRONG_INT_ARGUMENT_MESSAGE).getBytes(), StandardCharsets.UTF_8));
        }
        return param;
    }

    @Override
    public long checkParameterLong(String paramName, HttpServletRequest request, Map<String, String> errors, long param) {
        try {
            param = Long.parseLong(request.getParameter(paramName));
            if (param < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException nfe) {
            errors.put(paramName, new String((WRONG_ARGUMENT_MESSAGE + " " + WRONG_LONG_ARGUMENT_MESSAGE).getBytes(), StandardCharsets.UTF_8));
        }
        return param;
    }
}
