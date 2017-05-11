package com.mum.pm.report_module.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created by tareman on 5/9/2017.
 */
@Controller
@RequestMapping("/download")
public class FileDownloadController {
    // @RequestMapping("/pdf/{fileName:.+}")
    @RequestMapping("/pdf/{type}/{id}")
    public void downloadPDFResource(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @PathVariable("type") String type, @PathVariable("id") int id) throws Exception {

        String ep = "D:/MUM Courses/PM/ireports/jasperoutput/Exam Paper " + id + ".pdf";
        String g = "D:/MUM Courses/PM/ireports/jasperoutput/Grade Report " + id + ".pdf";
        String in = "/download/pdf/QuestionList.pdf";

        File file = null;


        GradeReportController gr = new GradeReportController();
        String report = null;

        if (type.equals("grade")) {
            file = new File(g);
            if (file.exists())
                report = g;
            else
                report = gr.gradeReportGenerate(id);

        } else {
            file = new File(ep);
            if (file.exists())
                report = ep;
            else
                report = gr.questionDetailPdfGenerate(id);
        }
        //
        file = new File(report);

        //   URL resource = getClass().getResource(in);
        //  file= Paths.get(resource.toURI()).toFile();


        if (!file.exists()) {
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        System.out.println("Tare 3");
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        System.out.println("mimetype : " + mimeType);

        response.setContentType(mimeType);

        System.out.println("Tare 4");

        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));


        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));

        response.setContentLength((int) file.length());

        System.out.println("Tare 5");

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
        String message = "Successful. The file you are looking for does exist";
        System.out.println(message);

    }
}
