package com.github.beisser.servlet;

import com.github.beisser.controller.UserController;
import com.github.beisser.model.User;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

/**
 * Created by Nico on 13.08.2016.
 */
@WebServlet(name="ImageServlet",urlPatterns = {"/ImageServlet"})
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get userController sessionScoped bean
        UserController userController = (UserController) req.getSession().getAttribute("userController");

        if (userController != null && userController.getUser().getImage() != null) {
            User user = userController.getUser();

            // get output stream
            try(OutputStream out = resp.getOutputStream()) {
                String contentType = user.getImage().getContentType();
                byte[] content = IOUtils.toByteArray(user.getImage().getInputStream());

                // set content type and write output
                resp.setContentType(contentType);
                out.write(content);
            }
        }

    }
}
