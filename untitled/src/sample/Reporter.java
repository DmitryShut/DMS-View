package sample;

import entity.Report;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reporter {

    public static void reportCustomers(List<Report> reports){
        String path = "D:\\study\\DB\\untitled\\src\\sample";
        File file = new File(path+"\\passengers_report.jrxml");
        File subreport = new File(path+"\\subreport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JasperCompileManager.compileReportToFile(subreport.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reports);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "Java Techie");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }



}
