package solomonj.msg.userapp.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import solomonj.msg.userapp.jpa.model.User;

public class JasperAction {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String sourceFileName = "c:\\Users\\solomonj\\workspace\\userapp\\userapp-web\\src\\main\\resources\\reports\\jasper\\template.jasper";

		List<User> dataList = DataBeanList.getDataBeanList();
		String printFileName;

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
		Map parameters = new HashMap();
		try {
			printFileName = JasperFillManager.fillReportToFile(sourceFileName, parameters, beanColDataSource);
			if (printFileName != null) {

				JasperExportManager.exportReportToPdfFile(printFileName, "C://sample_report.pdf");
			}
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
