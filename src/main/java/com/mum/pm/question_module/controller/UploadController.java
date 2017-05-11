package com.mum.pm.question_module.controller;

import com.mum.pm.question_module.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by phandungmykieu on 4/25/17.
 */

@Controller
@RequestMapping("/upload")
public class UploadController {

    public static final String UPLOADED_FOLDER = System.getProperty("user.dir");
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = {"/fileUpload"}, method = RequestMethod.GET)
    public ModelAndView uploadFile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("uploadFile");
        return modelAndView;
    }


    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String importParse(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {


        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadFile";
        }

        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            questionService.uploadQuestions(path);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "uploadSuccess";

    }

}
