package com.mum.pm.report_module.controller;

import com.mum.pm.report_module.service.GradeReportService;
import com.mum.pm.report_module.service.SubResultService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tareman on 5/4/2017.
 */
@Controller
public class GradeReportController {

    @Autowired
    GradeReportService gradeReportService;

    @Autowired
    SubResultService subResultService;

    @RequestMapping(value = "/grade-report2", method = RequestMethod.GET)
    public ModelAndView gradereporgeneratePdf() {

        ModelAndView modelAndView = new ModelAndView();
        try {
            Map<String, Object> params = new HashMap<String, Object>();

            InputStream input = this.getClass().getResourceAsStream("/reports/grade_report.jrxml" );
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
         //   JasperReport jasperReport = JasperCompileManager.compileReport("D://MUM Courses/PM/ireports/report8.jrxml");
                    Connection connection= DriverManager.getConnection("jdbc:mysql://34.203.200.194:3306/onlinetestsystem"
                    + "?"  + "user="+"govinda" + "&password=" + "root");
            ResultSet rs  = connection.createStatement().executeQuery("SELECT * FROM report_view WHERE report_id=1");
            //List<SubResult> subResults=subResultService.getAllSubResults();


            JRResultSetDataSource resultSetDataSource=new JRResultSetDataSource(rs);
          //  JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,resultSetDataSource );
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH mm ");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            String filename= "D://MUM Courses/PM/ireports/jasperoutput/Grade Report List "+ dateFormat.format(date).toString()+".pdf";

            JasperExportManager.exportReportToPdfFile(jasperPrint, filename) ;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //modelAndView.addObject("user", new User());
        modelAndView.setViewName("grade-report");

        return modelAndView;
    }

    @RequestMapping(value = "/grade-report", method = RequestMethod.GET)
    public ModelAndView questionDetailPdf() {

        ModelAndView modelAndView = new ModelAndView();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            InputStream input = this.getClass().getResourceAsStream("/reports/question_detail.jrxml" );
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
           // JasperReport jasperReport = JasperCompileManager.compileReport("resources/reports/question_detail.jrxml");
            Connection connection = DriverManager.getConnection("jdbc:mysql://34.203.200.194:3306/onlinetestsystem"
                    + "?"  + "user="+"govinda" + "&password=" + "root");
            ResultSet rs  = connection.createStatement().executeQuery("SELECT * FROM question_view WHERE report_id=1");
            JRResultSetDataSource resultSetDataSource=new JRResultSetDataSource(rs);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,resultSetDataSource );
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH mm ");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            String filename= "D://MUM Courses/PM/ireports/jasperoutput/Question Detail report "+ dateFormat.format(date).toString()+".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, filename) ;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //modelAndView.addObject("user", new User());
        modelAndView.setViewName("grade-report");

        return modelAndView;
    }


}
