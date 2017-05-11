package com.mum.pm.report_module.service;

import com.mum.pm.report_module.model.SubResult;
import com.mum.pm.report_module.repository.SubResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tareman on 5/8/2017.
 */

@Service("subResultService")
public class SubResultServiceImp implements SubResultService {

    @Autowired
    SubResultRepository subResultRepository;


    @Override
    public void save(SubResult subResult) {
        subResultRepository.save(subResult);
    }

    @Override
    public SubResult findById(int id) {
        return subResultRepository.findById(id);
    }

    @Override
    public List<SubResult> getAllSubResults() {
        return subResultRepository.getAllSubResults();
    }

    @Override
    public List<SubResult> getAllSubResultByReportId(int reportId) {
        return subResultRepository.getAllSubResults();
    }
}
