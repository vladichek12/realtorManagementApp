package realtorManagementApp.web;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import realtorManagementApp._enum.Types;
import realtorManagementApp.entities.Address;
import realtorManagementApp.entities.Room;
import realtorManagementApp.entities.RoomImage;
import realtorManagementApp.entities.User;
import realtorManagementApp.services.AddressService;
import realtorManagementApp.services.RoomImageService;
import realtorManagementApp.services.RoomService;
import realtorManagementApp.services.UserService;
import realtorManagementApp.services.impl.AddressServiceImpl;
import realtorManagementApp.services.impl.RoomImageServiceImpl;
import realtorManagementApp.services.impl.RoomServiceImpl;
import realtorManagementApp.services.impl.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class UpdateRoomServlet extends HttpServlet {
    private RoomService roomService;
    private AddressService addressService;
    private UserService userService;
    private RoomImageService roomImageService;

    private final String NO_IMAGE = "Пожалуйста, выберите фото!";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        roomService = RoomServiceImpl.getInstance();
        addressService = AddressServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
        roomImageService = RoomImageServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!userService.userIsValidated(request, response)) {
            response.sendRedirect(String.format("%s/login", request.getContextPath()));
            return;
        }
        request.setAttribute("room", roomService.findById(Integer.valueOf(request.getPathInfo().substring(1))));
        request.setAttribute("realtors", userService.findAllRealtors());
        request.getRequestDispatcher("/WEB-INF/pages/updateRoom.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = null, street = null, description = null, type = null;
        int houseNumber = 0, numberOfRooms = 0;
        long square = 0, id = 0, price = 0;
        Map<String, String> possibleErrors = new HashMap<>();
        Map<String, String> parameters = new HashMap<>();

        RoomImage roomImage = new RoomImage();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            // Создание фабрики загрузчиков
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Установка максимального размера, после которого файлы будут записываться на диск
            factory.setSizeThreshold(100000000);

            // Установка временного каталога, где будут сохраняться файлы до тех пор, пока не будут записаны на диск
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            // Создание парсера многочастных запросов
            ServletFileUpload upload = new ServletFileUpload();

            // Установка максимального размера загружаемого файла
            upload.setFileSizeMax(1024 * 1024 * 3);

            // Установка максимального размера всего запроса
            upload.setSizeMax(10000000);

            try {
                // Парсинг запроса и получение списка частей
                FileItemIterator items = upload.getItemIterator(request);

                // Обработка частей, например, сохранение файлов в базу данных или на диск
                while (items.hasNext()) {
                    FileItemStream item = items.next();
                    if (!item.isFormField()) {
                        // Получение содержимого файла
                        byte[] imageBytes = item.openStream().readAllBytes();
                        if (imageBytes.length == 0) {
                            possibleErrors.put("imageError", new String(NO_IMAGE.getBytes(), StandardCharsets.UTF_8));
                        }
                        // Создание объекта, содержащего информацию о файле и его содержимом
                        roomImage.setImage(imageBytes);
                        roomImage.setFileName(item.getName());
                        roomImage.setType(item.getContentType());
                        // Сохранение объекта в базу данных
                        roomImageService.save(roomImage);
                    } else {
                        if (item.getFieldName().equals("city")) {
                            city = addressService.checkString("city", possibleErrors, parameters, city, item);
                        }
                        if (item.getFieldName().equals("street")) {
                            street = addressService.checkString("street", possibleErrors, parameters, street, item);
                        }

                        if (item.getFieldName().equals("description")) {
                            description = addressService.checkString("description", possibleErrors, parameters, description, item);
                        }
                        if (item.getFieldName().equals("type")) {
                            type = addressService.checkString("select", possibleErrors, parameters, type, item);
                        }

                        if (item.getFieldName().equals("houseNumber")) {
                            houseNumber = addressService.checkInteger("houseNumber", possibleErrors, parameters, houseNumber, item);
                        }
                        if (item.getFieldName().equals("numberOfRooms")) {
                            numberOfRooms = addressService.checkInteger("numberOfRooms", possibleErrors, parameters, numberOfRooms, item);
                        }
                        if (item.getFieldName().equals("square")) {
                            square = addressService.checkLong("square", possibleErrors, parameters, square, item);
                        }
                        if (item.getFieldName().equals("price")) {
                            price = addressService.checkLong("price", possibleErrors, parameters, price, item);
                        }
                        if (item.getFieldName().equals("id")) {
                            id = addressService.checkLong("id", possibleErrors, parameters, id, item);
                        }
                    }
                }
            } catch (FileUploadException e) {
                response.getWriter().println("File upload failed!");
            }
        }
        if (!possibleErrors.isEmpty()) {
            request.setAttribute("possibleErrors", possibleErrors);
            request.setAttribute("parameters", parameters);
            doGet(request, response);
        } else {
            Room roomToUpdate = roomService.findById((int) id);
            roomToUpdate.setNumberOfRooms(numberOfRooms);
            roomToUpdate.setSquare(square);
            roomToUpdate.setAddress(new Address(city, street, houseNumber));
            roomToUpdate.setPrice(price);
            roomToUpdate.setDescription(description);
            roomToUpdate.setType(Types.valueOf(type).getTitle());
            roomToUpdate.setRoomImage(roomImage);
            roomService.update(roomToUpdate);
            if (((User) (request.getSession().getAttribute("currentUser"))).getUserRole().equals("ROLE_ADMIN")) {
                response.sendRedirect(String.format("%s/admin/orders", request.getContextPath()));
            } else {
                response.sendRedirect(String.format("%s/user", request.getContextPath()));
            }
        }
    }
}