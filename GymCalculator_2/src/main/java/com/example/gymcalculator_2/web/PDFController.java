package com.example.gymcalculator_2.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.gymcalculator_2.model.LoggedLifts;
import com.example.gymcalculator_2.model.PDFExporter;
import com.example.gymcalculator_2.model.User;
import com.example.gymcalculator_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.lowagie.text.DocumentException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/export/pdf")
public class PDFController {

    private final UserService userService;

    public PDFController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public void exportToPDF(HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {
        String user = request.getRemoteUser();
        User currentUser = (User) userService.loadUserByUsername(user);
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + currentUser.getUsername() + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        PDFExporter exporter = new PDFExporter(currentUser);
        exporter.export(response);
    }
}
