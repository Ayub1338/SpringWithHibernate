package com.local.dao;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.impl.FetchingScrollableResultsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import com.local.commons.Database;
import com.local.commons.LookupDTO;
import com.local.model.Actor;
import com.local.model.User;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

@Repository("ActorDao")
public class ActorDaoImpl implements ActorDao {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	@Bean
	public SessionFactory getsesssionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public List<Actor> getAllActors() {
		Session session = sessionFactory.openSession();
		List<Actor> listOfActors = session.createQuery("from Actor").list();
		listOfActors.stream().forEach(actor -> {
			System.out.println(actor.toString());
		});

		return listOfActors;
	}

	@Override
	public Actor getActorById(String actorId) {
		Actor actor = null;

		Session session = sessionFactory.openSession();
		Integer actorID = new Integer(actorId);
		actor = (Actor) session.get(Actor.class, actorID);

		return actor;

	}

	@Override
	public void getjasperReport(String actorId) {

	}

	@Override
	public Object createUser(User user) {
		Transaction tx = null;
	 
		Session session = sessionFactory.openSession();
	session.beginTransaction();
		System.out.println(user.toString());
		try{
		session.save(user);
		System.out.println("Inserted Successfully");
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		}
		catch(Exception e){
			return e.getMessage();
		}
		return user;
	

	}

	@Override
	public JasperPrint getJasperReport() throws FileNotFoundException, JRException {
		JasperPrint jasperPrint = null;
		/// SpringMavenMvc/src/main/resources/jasper/Blank_Letter_Landscape_Table_Based.jasper
		File file = ResourceUtils.getFile("classpath:/jasper/Blank_Letter_Landscape_Table_Based.jrxml");
		Connection con = Database.getConnection();
		FileInputStream inputStream = new FileInputStream(file);
		System.out.println(file.getPath());
		System.out.println(file.getAbsolutePath());

		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		HashMap<String, Object> params = new HashMap<>();
		jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);

		return jasperPrint;

	}
	
	@Override
	public JasperPrint generateDynamicJasperReport()
			throws JRException, ClassNotFoundException, SQLException, ColumnBuilderException, IllegalArgumentException, IllegalAccessException {
		FastReportBuilder fastReportBuilder = new FastReportBuilder();
		Style defaultStyle = getDefaultStyle();
		fastReportBuilder.setDefaultStyles(defaultStyle, defaultStyle, getColumnHeaderStyle(), getColumnDetailsStyle());
		DynamicReport dynamicReport = null;/* = drb.addColumn("Primary Key", "id", Long.class.getName(), 50)
				.addColumn("code", "code", String.class.getName(), 50)
				.addColumn("description", "description", String.class.getName(), 50)
				.addColumn("displayMessage","displayMessage",String.class.getName(), 50)
				.setUseFullPageWidth(true).setIgnorePagination(true)
				.build();*/
		
		List<LookupDTO> lookupDTOs =getDummyCollection();
		LookupDTO dto = new LookupDTO();
		dto = lookupDTOs.get(0);
		Map<String, String> columnsMap = dto.getColumnsMap();
			Field[] fields = dto.getClass().getDeclaredFields();
			for(Field field: fields){
				field.setAccessible(true);
				if(field.getType().equals(String.class)){
					fastReportBuilder.addColumn(columnsMap.get(field.getName()),field.getName(),field.getType().getName(),50,false);
				}
			}
		
		dynamicReport = fastReportBuilder.setUseFullPageWidth(true).setIgnorePagination(true).build();
		JRDataSource jrDatasource = new JRBeanCollectionDataSource(lookupDTOs);
		JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), jrDatasource);
		// JasperViewer.viewReport(jasperPrint);
		return jasperPrint;
	}

	private Style getDefaultStyle() {
		Style defaultStyle = new Style();
		defaultStyle.setName("DefaultStyle");
		defaultStyle.getFont().setFontName("DejaVu Sans");
		defaultStyle.getFont().setFontSize(14);
		defaultStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		defaultStyle.setBackgroundColor(Color.LIGHT_GRAY);
		defaultStyle.setTransparency(Transparency.OPAQUE);
		return defaultStyle;
	}
	private Style getColumnDetailsStyle() {
		Style defaultStyle = new Style();
		defaultStyle.setName("column detail style");
		defaultStyle.getFont().setFontName("DejaVu Sans");
		defaultStyle.getFont().setFontSize(10);
		defaultStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		defaultStyle.setBackgroundColor(Color.WHITE);
		defaultStyle.setTransparency(Transparency.OPAQUE);
		return defaultStyle;
	}
	
	private Style getColumnHeaderStyle() {
		Style defaultStyle = new Style();
		defaultStyle.setName("BgStyle");
		defaultStyle.getFont().setFontName("DejaVu Sans");
		defaultStyle.getFont().setFontSize(14);
		defaultStyle.getFont().setBold(true);
		defaultStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		defaultStyle.setBackgroundColor(Color.LIGHT_GRAY);
		defaultStyle.setTransparency(Transparency.OPAQUE);
		return defaultStyle;
	}
	
	private List<LookupDTO> getDummyCollection(){
		List<LookupDTO> dummyList = new ArrayList<>();
		dummyList.add(new LookupDTO(1l, "code1", "description1", "displayMessage1"));
		dummyList.add(new LookupDTO(2l, "code2", "description2", "displayMessage2"));
		dummyList.add(new LookupDTO(3l, "code3", "description3", "displayMessage3"));
		dummyList.add(new LookupDTO(4l, "code4", "description4", "displayMessage4"));
		return dummyList;
		
	}

}
