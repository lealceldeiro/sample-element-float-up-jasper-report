package com.sample.report.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ReportService {

    public byte[] generateReport(boolean floatUp) throws JRException, FileNotFoundException {
        // region get jrxml name
        ClassLoader classLoader = getClass().getClassLoader();
        final URL resource = classLoader.getResource("report.jrxml");
        if (resource == null) {
            throw new FileNotFoundException("report.jrxml");
        }
        //endregion
        String path = resource.getPath();

        Map<String, Object> map = new HashMap<>();
        map.put("floatUp", floatUp);

        JasperReport jasperReport = JasperCompileManager.compileReport(path);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

}
