package com.douane.managed.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import com.douane.entite.OpAttribution;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
/*
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

*/
/**
 * @author javaQuery
 * @date 24nd November, 2015
 * @Github: https://github.com/javaquery/Examples
 */
@ManagedBean(name="JasperTableExampleBean")
@RequestScoped
@Transactional
public class JasperTableExampleBean implements Serializable{
	@ManagedProperty(value="#{gacBean}")
    private GACBean gacBean;
    public GACBean getGacBean() {
		return gacBean;
	}
	public void setGacBean(GACBean gacBean) {
		this.gacBean = gacBean;
	}
	public void buildReport() {
    	FacesContext facescontext = FacesContext.getCurrentInstance();
		ExternalContext external = facescontext.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		HttpServletResponse response = (HttpServletResponse) external.getResponse();
       
    }
    public void saisieMatexReport(OpAttribution op) {
    	FacesContext facescontext = FacesContext.getCurrentInstance();
		ExternalContext external = facescontext.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		HttpServletResponse response = (HttpServletResponse) external.getResponse();
        try {
            
            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            
            /*parameters.put("nomDepositaire", op.getOperateur().getNomAgent());
            parameters.put("fonctionDepositaire", op.getOperateur().getPosteny().getDesignation());
            parameters.put("nomDetenteur", op.getDetenteur().getNomAgent());
            */
            String designation = op.getMat().getDesign().getNomenMat().getNomenclature() + " - "
            		+ op.getMat().getDesign().getTypematerieladd().getDesignation() + " - "
            		+ op.getMat().getDesign().getMarque() + " - "
            		+ op.getMat().getDesign().getRenseignement() + " - "
            		+ op.getMat().getDesign().getCaract()
            ;
            parameters.put("nomDepositaire", op.getOperateur().getNomAgent());
            parameters.put("fonctionDepositaire", op.getOperateur().getPosteny().getDesignation());
            parameters.put("nomDetenteur", op.getDetenteur().getNomAgent());
            parameters.put("fonctionDetenteur", op.getDetenteur().getPosteny().getDesignation());
            parameters.put("designationMat", designation);
            parameters.put("especeunite", op.getMat().getDesign().getEspeceUnite());
            parameters.put("pu", op.getMat().getDesign().getPu());
            parameters.put("nbr", 1);
            parameters.put("valeurtotal", op.getMat().getDesign().getPu());
            
            
            //parameters.put("ItemDataSource", itemsJRBean);

            /* Using compiled version(.jasper) of Jasper report to generate PDF */
            JasperPrint jasperPrint = JasperFillManager.fillReport("/home/misa/git/dev-douanes_debug/src/main/java/com/douane/managed/jasperReport/saisieMatEx.jasper", parameters, new JREmptyDataSource());

            /* outputStream to create PDF */
           // OutputStream outputStream = new FileOutputStream(new File(outputFile));
            /* Write content to PDF file */
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

            System.out.println("File Generated");
        }
         catch (JRException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}