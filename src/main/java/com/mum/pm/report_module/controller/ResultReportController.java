package com.mum.pm.report_module.controller;

import com.mum.pm.report_module.model.GradeReport;
import com.mum.pm.report_module.service.GradeReportService;
import com.mum.pm.user_module.model.User;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tareman on 5/2/2017.
 */
@Controller
public class ResultReportController {

    @Autowired
    GradeReportService reportService;

    @RequestMapping(value = "/grade-report", method = RequestMethod.POST)
    public void gradereportBuildPdf() {

        ModelAndView modelAndView = new ModelAndView();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            JasperReport jasperReport = JasperCompileManager.compileReport("D://MUM Courses/PM/ireports/report5.jrxml");

            List<GradeReport> reports = reportService.getAllReports();

            // reports.remove(1);
            // reports.remove(2);

            for (int i = 0; i < reports.size(); i++) {
                //if(!u)
                System.out.println(reports.get(i).toString());
            }

            System.out.println("Tare 1");
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);
            // JRDataSource dataSource = new JRJpaDataSource(users);
            System.out.println("Tare 2");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH mm ");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            String filename = "D://MUM Courses/PM/ireports/jasperoutput/Result " + dateFormat.format(date).toString() + ".pdf";


            JasperExportManager.exportReportToPdfFile(jasperPrint, filename);
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("grade-report");
    }
}
