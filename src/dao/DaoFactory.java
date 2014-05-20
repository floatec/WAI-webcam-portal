package dao;

import dao.CamDaoImpl;

public class DaoFactory {
	
	private static DaoFactory instance = new DaoFactory();
	
	private DaoFactory() {		
	}
	
	public static DaoFactory getInstance() {
		return instance;
	}
	
	public CamDao getCamDao() {
		return new CamDaoImpl();
	}
}
