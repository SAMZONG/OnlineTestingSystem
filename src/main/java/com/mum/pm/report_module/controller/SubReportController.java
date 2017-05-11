package com.mum.pm.report_module.controller;

import com.mum.pm.quiz.model.SubReport;
import com.mum.pm.report_module.service.SubReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tareman on 5/10/2017.
 */

@RestController
public class SubReportController {

    @Autowired
    SubReportService subReportService;


    @RequestMapping(path = "/admin/getReport/{id})", method = RequestMethod.GET)
    @ResponseBody
    public List<SubReport> getReportById(@PathVariable("id") int reportId) {
        return subReportService.getReportsById(reportId);
    }

        @RequestMapping(path = "/admin/getReport", method = RequestMethod.GET)
        @ResponseBody
        public List<SubReport> getReportById(){
            return subReportService.getAllSubReports();
        }
}
