package com.mum.pm.report_module.controller;

import com.mum.pm.report_module.service.GradeReportService;
import com.mum.pm.report_module.service.SubResultService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

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
    public ModelAndView gradeReportPage() {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //modelAndView.addObject("user", new User());
        modelAndView.setViewName("grade-report");

        return modelAndView;
    }

    @RequestMapping(value = "/grade-report", method = RequestMethod.GET)
    public ModelAndView questionDetailPdf() {

        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //modelAndView.addObject("user", new User());
        modelAndView.setViewName("grade-report");

        return modelAndView;
    }


    public String questionDetailPdfGenerate(int id){

        try {
            Map<String, Object> params = new HashMap<String, Object>();
            InputStream input = this.getClass().getResourceAsStream("/reports/question_detail.jrxml" );
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            // JasperReport jasperReport = JasperCompileManager.compileReport("resources/reports/question_detail.jrxml");
            Connection connection = DriverManager.getConnection("jdbc:mysql://34.203.200.194:3306/onlinetestsystem"
                    + "?"  + "user="+"govinda" + "&password=" + "root");
            ResultSet rs  = connection.createStatement().executeQuery("SELECT * FROM question_view WHERE report_id="+id);
            JRResultSetDataSource resultSetDataSource=new JRResultSetDataSource(rs);


            params.put("logo", this.getClass().getResourceAsStream("/static/images/mumbanner.jpg"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,resultSetDataSource );

            InputStream ouput = this.getClass().getResourceAsStream("/download/pdf/" );

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH mm ");
            Date date = new Date();
            System.out.println(dateFormat.format(date));

            String relativePath = new File("src/main/resources/download/pdf/").getAbsolutePath();
            String filename = relativePath+"/Exam Paper " + id + ".pdf";

            JasperExportManager.exportReportToPdfFile(jasperPrint, filename) ;

            return  filename;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return null;

    }

    public String gradeReportGenerate(int id){


        try {
            Map<String, Object> params = new HashMap<String, Object>();

            InputStream input = this.getClass().getResourceAsStream("/reports/grade_report.jrxml" );
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            //   JasperReport jasperReport = JasperCompileManager.compileReport("D://MUM Courses/PM/ireports/report8.jrxml");
            Connection connection= DriverManager.getConnection("jdbc:mysql://34.203.200.194:3306/onlinetestsystem"
                    + "?"  + "user="+"govinda" + "&password=" + "root");
            ResultSet rs  = connection.createStatement().executeQuery("SELECT * FROM report_view WHERE report_id="+id);
            //List<SubResult> subResults=subResultService.getAllSubResults();


            JRResultSetDataSource resultSetDataSource=new JRResultSetDataSource(rs);
            //  JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);

            params.put("logo", this.getClass().getResourceAsStream("/static/images/mumbanner.jpg"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,resultSetDataSource );
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH mm ");
            Date date = new Date();

            String relativePath = new File("src/main/resources/download/pdf/").getAbsolutePath();
            String filename = relativePath+"/Grade Report " + id + ".pdf";

            System.out.println("Current folder: " + (new File(".")).getCanonicalPath()+"/target/downloaded/Grade Report "+ id+".pdf");
            String tare= new File(".").getCanonicalPath()+"/target/downloaded/Grade Report "+ id+".pdf";
            System.out.println(tare);
            JasperExportManager.exportReportToPdfFile(jasperPrint, filename) ;

            return filename;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return null;

    }



}
