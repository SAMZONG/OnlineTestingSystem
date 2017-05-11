package com.mum.pm.report_module.controller;

import com.mum.pm.question_module.model.Question;
import com.mum.pm.question_module.service.QuestionService;
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
 * Created by tareman on 5/5/2017.
 */
@Controller
public class QuestionReportController {

    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/question-report", method = RequestMethod.POST)
    public ModelAndView questionReportGener() {

        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //modelAndView.addObject("user", new User());
        modelAndView.setViewName("question-report");
        return modelAndView;
    }


    @RequestMapping(value = "/question-report", method = RequestMethod.GET)
    public ModelAndView gradereporgenPdf() {

        ModelAndView modelAndView = new ModelAndView();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            JasperReport jasperReport = JasperCompileManager.compileReport("D://MUM Courses/PM/ireports/report7.jrxml");

            List<Question> questions = (List<Question>) questionService.getAllQuestions();

            System.out.println("Tare 1");
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(questions);
            // JRDataSource dataSource = new JRJpaDataSource(users);
            System.out.println("Tare 2");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            System.out.println("Tare 3");
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH mm ");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            String filename = "D://MUM Courses/PM/ireports/jasperoutput/Question List " + dateFormat.format(date).toString() + ".pdf";
            String filename2 = "D://MUM Courses/PM/ireports/jasperoutput/Question List " + dateFormat.format(date).toString() + ".html";

            JasperExportManager.exportReportToPdfFile(jasperPrint, filename);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, filename2);
            System.out.println(filename);


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //  modelAndView.addObject("user", new User());
        modelAndView.setViewName("question-report");

        return modelAndView;
    }
}
