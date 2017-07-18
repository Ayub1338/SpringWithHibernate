package com.local.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import com.local.commons.Database;
import com.local.model.Actor;
import com.local.model.User;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

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
	public JasperPrint generateDynamicJasperReport(AddictivesSearchDTO addictivesSearchDTO)
			throws JRException, ClassNotFoundException, SQLException {
		FastReportBuilder drb = new FastReportBuilder();
		Style defaultStyle = getDefaultStyle();
		drb.setDefaultStyles(defaultStyle, defaultStyle, getColumnHeaderStyle(), getColumnDetailsStyle());
		DynamicReport dr = drb.addColumn("Primary Key", "id", Long.class.getName(), 50)
				.addColumn("code", "code", String.class.getName(), 50)
				.addColumn("description", "description", String.class.getName(), 50)
				.addColumn("displayMessage","displayMessage",String.class.getName(), 50)
				.setUseFullPageWidth(true).setIgnorePagination(true)
				.build();
		JRDataSource ds = new JRBeanCollectionDataSource(getDummyCollection());
		JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
		 JasperViewer.viewReport(jasperPrint);
		return jasperPrint;
	}

	private Style getDefaultStyle() {
		Style defaultStyle = new Style();
		defaultStyle.setName("DefaultStyle");
		defaultStyle.getFont().setFontName("DejaVu Sans");
		defaultStyle.getFont().setFontSize(14f);
		defaultStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		defaultStyle.setBorder(new Border(0.5f,Border.BORDER_STYLE_SOLID,Color.BLUE));
		defaultStyle.setBackgroundColor(Color.LIGHT_GRAY);
		defaultStyle.setTransparency(Transparency.OPAQUE);
		return defaultStyle;
	}
	private Style getColumnDetailsStyle() {
		Style defaultStyle = new Style();
		defaultStyle.setName("column detail style");
		defaultStyle.getFont().setFontName("DejaVu Sans");
		defaultStyle.getFont().setFontSize(10f);;
		defaultStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		defaultStyle.setBorder(new Border(0.5f,Border.BORDER_STYLE_SOLID,Color.BLUE));
		defaultStyle.setBackgroundColor(Color.WHITE);
		defaultStyle.setTransparency(Transparency.OPAQUE);
		return defaultStyle;
	}
	
	private Style getColumnHeaderStyle() {
		Style defaultStyle = new Style();
		defaultStyle.setName("BgStyle");
		defaultStyle.getFont().setFontName("DejaVu Sans");
		defaultStyle.getFont().setFontSize(14f);
		defaultStyle.getFont().setBold(true);
		defaultStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		defaultStyle.setBorder(new Border(0.5f,Border.BORDER_STYLE_SOLID,Color.BLUE));
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
