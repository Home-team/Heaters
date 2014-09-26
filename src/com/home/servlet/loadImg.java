package com.home.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.home.dao.JdbcImagesDao;
import com.home.entity.Images;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.DeferredFileOutputStream;

public class loadImg extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isMultipartImg = ServletFileUpload.isMultipartContent(req);
        if(isMultipartImg){
            FileItemFactory factoryImg = new DiskFileItemFactory();
            ServletFileUpload uploadImg = new ServletFileUpload(factoryImg); //Создаём сам загрузчик файла
            List items_img = null; // Парсим запрос
            try {
                items_img = uploadImg.parseRequest(req);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            Iterator iterImg = items_img.iterator(); // Обрабатываем результат
            DiskFileItem item = null;
            Images image = new Images();
            while (iterImg.hasNext()) {
                item = (DiskFileItem) iterImg.next();
                if(!item.isFormField()) {
                    image.setName(item.getName());

                    File newFile = null;
                    for (int i = 0; true; i++) {
                        if(i>0) {
                            newFile = new File("/home/dassader/"+"("+i+")"+item.getName());
                        } else {
                            newFile = new File("/home/dassader/"+item.getName());
                        }

                        if(!newFile.exists()) {
                            break;
                        }
                    }

                    image.setUrl(newFile.getAbsolutePath());

                    FileOutputStream fos = new FileOutputStream(newFile);
                    fos.write(item.get());
                    fos.close();
                } else {
                    image.setHeater_id(item.getString());
                }
            }

            new JdbcImagesDao().create(image);
        }
    }
}
