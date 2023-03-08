package realtorManagementApp.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import realtorManagementApp._enum.Statuses;
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
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddRoomServlet extends HttpServlet {
    private RoomService roomService;
    private RoomImageService roomImageService;
    private AddressService addressService;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        roomImageService = RoomImageServiceImpl.getInstance();
        roomService = RoomServiceImpl.getInstance();
        addressService = AddressServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!userService.userIsValidated(request, response)) {
            response.sendRedirect(String.format("%s/login", request.getContextPath()));
            return;
        }
        request.setAttribute("realtors", userService.findAllRealtors());
        request.getRequestDispatcher("/WEB-INF/pages/addNewRoom.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = null, street = null, description = null, type = null;
        int houseNumber = 0, numberOfRooms = 0;
        long square = 0, price = 0, realtorId = 0;
        Map<String, String> possibleErrors = new HashMap<>();

/*        city = addressService.checkParameterString("city", request, possibleErrors, city);
        street = addressService.checkParameterString("street", request, possibleErrors, street);
        description = addressService.checkParameterString("description", request, possibleErrors, description);
        type = addressService.checkParameterString("type", request, possibleErrors, type);

        houseNumber = addressService.checkParameterInteger("houseNumber", request, possibleErrors, houseNumber);
        numberOfRooms = addressService.checkParameterInteger("numberOfRooms", request, possibleErrors, numberOfRooms);

        square = addressService.checkParameterLong("square", request, possibleErrors, square);
        price = addressService.checkParameterLong("price", request, possibleErrors, price);
        realtorId = addressService.checkParameterLong("select", request, possibleErrors, realtorId);*/


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
            upload.setFileSizeMax(1024*1024*3);

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
                        // Создание объекта, содержащего информацию о файле и его содержимом
                        roomImage.setImage(imageBytes);
                        roomImage.setFileName(item.getName());
                        roomImage.setType(item.getContentType());
                        // Сохранение объекта в базу данных
                        roomImageService.save(roomImage);
                    }
                    else{
                        if(item.getFieldName().equals("city")){
                            city = new Scanner(item.openStream(), "UTF-8").useDelimiter("\\A").next();
                        }
                        if(item.getFieldName().equals("street")){
                            street = new Scanner(item.openStream(), "UTF-8").useDelimiter("\\A").next();
                        }

                        if(item.getFieldName().equals("description")){
                            description = new Scanner(item.openStream(), "UTF-8").useDelimiter("\\A").next();
                        }
                        if(item.getFieldName().equals("type")){
                            type = new Scanner(item.openStream(), "UTF-8").useDelimiter("\\A").next();
                        }

                        if(item.getFieldName().equals("houseNumber")){
                            houseNumber = Integer.parseInt(new Scanner(item.openStream(), "UTF-8").useDelimiter("\\A").next());
                        }
                        if(item.getFieldName().equals("numberOfRooms")){
                            numberOfRooms = Integer.parseInt(new Scanner(item.openStream(), "UTF-8").useDelimiter("\\A").next());
                        }
                        if(item.getFieldName().equals("square")){
                            square = Long.parseLong(new Scanner(item.openStream(), "UTF-8").useDelimiter("\\A").next());
                        }
                        if(item.getFieldName().equals("price")){
                            price = Long.parseLong(new Scanner(item.openStream(), "UTF-8").useDelimiter("\\A").next());
                        }
                        if(item.getFieldName().equals("select")){
                            realtorId = Long.parseLong(new Scanner(item.openStream(), "UTF-8").useDelimiter("\\A").next());
                        }

                    }
                }

                // Отправка ответа клиенту
            } catch (FileUploadException e) {
                response.getWriter().println("File upload failed.");
            } catch (org.apache.commons.fileupload.FileUploadException e) {
                e.printStackTrace();
            }
        } else{}



        if (!possibleErrors.isEmpty()) {
            request.setAttribute("possibleErrors", possibleErrors);
            doGet(request, response);
        } else {
            Room roomToSave = new Room(square, numberOfRooms, new Address(city, street, houseNumber),
                    description, price, Statuses.STATUS_POSTED.toString(),
                    Types.valueOf(type).getTitle(), roomImage);
            roomToSave.setUser((User) request.getSession().getAttribute("currentUser"));
            roomToSave.setRealtor(userService.findUserById((int) realtorId));
            roomService.save(roomToSave);
            response.sendRedirect(String.format("%s/user", request.getContextPath()));
        }
    }
}
