package com.diego.springbootthymeleaf.controller;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class ReportUtil implements Serializable {
  
  private static final long serialVersionUID = 1L;

  // Retorna o pdf em byte para download através do navegador
  public byte[] geraRelatorio(List<?> listData, String relatorio, ServletContext servletContext) throws JRException {
    JRBeanCollectionDataSource jrSource = new JRBeanCollectionDataSource(listData);

    String caminhoJasper = servletContext.getRealPath("relatorios") + File.separator + relatorio + ".jasper";

    JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoJasper, new HashMap<>(), jrSource);

    return JasperExportManager.exportReportToPdf(jasperPrint);
  }
}
