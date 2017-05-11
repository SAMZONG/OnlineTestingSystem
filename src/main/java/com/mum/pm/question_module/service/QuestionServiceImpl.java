package com.mum.pm.question_module.service;

import com.mum.pm.inventory_module.dao.SubCategoryDAO;
import com.mum.pm.question_module.model.Question;
import com.mum.pm.question_module.repository.QuestionRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by phandungmykieu on 4/26/17.
 */

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {


    private static HSSFWorkbook wb = null;
    private static Sheet ws = null;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    SubCategoryDAO subCategoryDAO;

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public Question findById(int id) {
        return questionRepository.findById(id);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Iterable<Question> getQuestionsBySubCategoryID(Integer subCategoryId) {
        return questionRepository.findBySubCategoryId(subCategoryId);
    }

    @Override
    public void uploadQuestions(Path path) {

        int check = 0;
        int count = 0;
        String description = null;
        List<String> listString = null;
        List<Integer> listInteger = null;
        int id = 0;
        String question_description = null;
        String answer_1 = null;
        String answer_2 = null;
        String answer_3 = null;
        String answer_4 = null;
        String answer_5 = null;
        int correct_answer = 0;
        String categoryName;
        String subCategoryName;
        int sub_category_id = 0;

        try {

            FileInputStream excelFile = new FileInputStream(new File(String.valueOf(path)));
            Workbook wb = new XSSFWorkbook(excelFile);
            ws = wb.getSheetAt(0);
            Iterator<Row> iterator = ws.iterator();

            while (iterator.hasNext()) {
                count++;
                listInteger = new ArrayList<Integer>();
                listString = new ArrayList<String>();
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();

                    if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        listInteger.add((int) currentCell.getNumericCellValue());
                    } else if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        listString.add(currentCell.getStringCellValue());
                    }
                }


                for (int i = 0; i < listInteger.size(); i++) {
                    //   id = listInteger.get(0);
                    correct_answer = listInteger.get(0);
                    // sub_category_id = listInteger.get(1);
                }

                for (int i = 0; i < listString.size(); i++) {
                    try {
                        question_description = listString.get(0);
                        answer_1 = listString.get(1);
                        answer_2 = listString.get(2);
                        answer_3 = listString.get(3);
                        answer_4 = listString.get(4);
                        answer_5 = listString.get(5);
                        categoryName = listString.get(6);
                        subCategoryName = listString.get(7);
                        sub_category_id = subCategoryDAO.findBySubCategoryName(subCategoryName).getSubCategoryId();

                    } catch (Exception e) {
                        check = 1;
                        System.out.println("Row numeber " + count + " has some columns field invalid.. Please check it....");

                    }
                }
                if (check == 0) {
                    Question question = new Question(question_description, answer_1, answer_2, answer_3
                            , answer_4, answer_5, correct_answer, sub_category_id);
                    System.out.println(question_description + " " + answer_1 + " " + answer_2 + " " + answer_3 + " "
                            + answer_4 + " " + answer_5 + " " + correct_answer + " " + sub_category_id);
                    questionRepository.save(question);
                }
                check = 0;
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
