package com.mum.pm.report_module.controller;


import com.mum.pm.user_module.model.User;
import com.mum.pm.user_module.service.UserService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by tareman on 4/26/2017.
 */

@MultipartConfig
@Controller
public class UserReportController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/web/viewer", method = RequestMethod.GET)
    public ModelAndView displaypdfview(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("web/viewer");

        return modelAndView;
    }

    @RequestMapping(value = "/table", method = RequestMethod.GET)
    public ModelAndView diplaytable(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("table");

        return modelAndView;
    }


    @RequestMapping(value = "/pdfreport", method = RequestMethod.POST)
    public ModelAndView display(Model model) {
        // model.addAttribute("userReport", new Question());

        ModelAndView modelAndView = new ModelAndView();
        try

        {
            System.out.println("tare 1");
            //  InputStream jasperStream = this.getClass().getResourceAsStream("D://MUM Courses/PM/ireports/report1.jrxml");
            //   JasperDesign jasperDesign= JRXmlLoader.load(jasperStream);
            // Compile jrxml file.
            JasperReport jasperReport = JasperCompileManager.compileReport("D://MUM Courses/PM/ireports/userlist.jrxml");
            // JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            System.out.println("tare 2");
            // Parameters for report
            Map<String, Object> parameters = new HashMap<String, Object>();

            // DataSource
            // This is simple example, no database.
            // then using empty datasource.

            List<User> users = userService.getAllUsers();


            // JRDataSource dataSource = new JRJpaDataSource(users);

            JRDataSource dataSource = new JREmptyDataSource();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            System.out.println("tare 3");

            // Make sure the output directory exists.
            File outDir = new File("D://MUM Courses/PM/ireports/jasperoutput");
            outDir.mkdirs();

            // PDF Exportor.
            JRPdfExporter exporter = new JRPdfExporter();

            ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
            // ExporterInput
            exporter.setExporterInput(exporterInput);

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH mm ");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            String filename = "D://MUM Courses/PM/ireports/jasperoutput/User List " + dateFormat.format(date).toString() + ".pdf";


            // ExporterOutput
            OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(filename);
            // Output
            exporter.setExporterOutput(exporterOutput);

            //
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            exporter.setConfiguration(configuration);
            exporter.exportReport();

            System.out.print("Done!");


        } catch (
                Exception e)

        {
            e.printStackTrace();
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("pdfreport");

        return modelAndView;

    }


    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET)
    public void testBuildPdf() {

        ModelAndView modelAndView = new ModelAndView();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            JasperReport jasperReport = JasperCompileManager.compileReport("D://MUM Courses/PM/ireports/report2.jrxml");

            List<User> users = userService.getAllUsers();

            for (int i = 0; i < users.size(); i++) {
                //if(!u)
                System.out.println(users.get(i).toString());
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);
            // JRDataSource dataSource = new JRJpaDataSource(users);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            JasperExportManager.exportReportToPdfFile(jasperPrint, "D://MUM Courses/PM/ireports/jasperoutput/TarekegnJasperReport.pdf");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("pdfreport");
    }

}

