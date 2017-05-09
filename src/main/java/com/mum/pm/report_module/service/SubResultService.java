package com.mum.pm.report_module.service;

import com.mum.pm.report_module.model.SubResult;

import java.util.List;

/**
 * Created by tareman on 5/8/2017.
 */
public interface SubResultService {

    void save(SubResult subResult);
    SubResult findById(int id);
    List<SubResult> getAllSubResults();
    List<SubResult> getAllSubResultByReportId(int reportId);
}
