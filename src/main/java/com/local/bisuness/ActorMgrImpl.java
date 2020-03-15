package com.local.bisuness;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.local.dao.ActorDao;
import com.local.model.Actor;
import com.local.model.User;

import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service("ActorMgr")
@Transactional
public class ActorMgrImpl implements ActorMgr{

	@Autowired
	ActorDao actorDao;
	//Adding comment to check githib
	
	@Override
	public List<Actor> getAllActors() {
		List<Actor> actors = new ArrayList<>();
		return actorDao.getAllActors();
	}


	@Override
	public Actor getActorById(String actorId) {
		
		return actorDao.getActorById(actorId);
	}


	@Override
	public Actor getjasperReport(String actorId) {
		actorDao.getjasperReport(actorId);
		return new Actor();
	}


	@Override
	public Object createUser(User user) {
		return actorDao.createUser(user);
		
	}


	@Override
	public byte[] getJasperReport() throws FileNotFoundException, SQLException {
		
		byte[] byteInArray = null;
		try {
			JasperPrint jasperPrint = actorDao.getJasperReport();
		String destFileName = "D:"+File.pathSeparator+"document.pdf";
			byteInArray = JasperExportManager.exportReportToPdf(jasperPrint);
			JasperExportManager.exportReportToPdfFile(jasperPrint,destFileName);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byteInArray;
	}


	@Override
	public byte[] generateDynamicJasperReport() throws ClassNotFoundException, ColumnBuilderException, SQLException, IllegalArgumentException, IllegalAccessException {
		byte[] byteInArray = null;
		try {
			JasperPrint jasperPrint = actorDao.generateDynamicJasperReport();
		String destFileName = "D:"+File.pathSeparator+"dynamicjasper.pdf";
			byteInArray = JasperExportManager.exportReportToPdf(jasperPrint);
			JasperExportManager.exportReportToPdfFile(jasperPrint,destFileName);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byteInArray;
	}

	
}
