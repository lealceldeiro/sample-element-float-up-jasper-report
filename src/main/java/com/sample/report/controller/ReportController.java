package com.sample.report.controller;


import com.sample.report.config.ReportArea;
import com.sample.report.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.net.HttpURLConnection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@Api(tags = "Report Float Demo",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_PDF_VALUE)
@ReportArea
@AllArgsConstructor(onConstructor = @__(@Autowired))
@ApiResponses(value = {
        @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Successful response"),
        @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error")})
@RequestMapping("/v1")
public class ReportController {

    private ReportService reportService;

    @RequestMapping(value = "/report", method = GET, produces = APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> report(
            @ApiParam(value = "Indicates whether the first div (BLUE) should be shown or not", defaultValue = "true")
            @RequestParam boolean showFirst,
            @ApiParam(value = "Indicates whether the second div (RED) should be shown or not", defaultValue = "true")
            @RequestParam boolean showSecond) throws JRException, FileNotFoundException {
        byte[] response = reportService.generateReport(showFirst, showSecond);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=report.pdf");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
