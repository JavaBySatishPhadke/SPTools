package com.nt.serivce;

import com.nt.dao.ILoginDAO;

public class LoginMgmtServiceImpl implements ILoginMgmtService {
	
	private  ILoginDAO loginDao;
	
	public   LoginMgmtServiceImpl(ILoginDAO  loginDao) {
		this.loginDao=loginDao;
	}

	@Override
	public String login(String user, String pwd) {
		System.out.println("DAO class obj "+loginDao.getClass());
		//validation
		if(user.equalsIgnoreCase("") || user.length()==0  ||  pwd.equalsIgnoreCase("") || pwd.length()==0)
			throw new IllegalArgumentException("Invalid inputs");
		//use DAO
		int count=loginDao.authenticate(user, pwd);
		if(count==1)
			return "Valid Credentials";
		else
			return "InValid Credentials";
	}

	@Override
	public String registerUser(String user, String pwd) {
		  if(!user.equalsIgnoreCase("")  && !pwd.equalsIgnoreCase("")) {
			  loginDao.addUser(user, pwd);
			  return  "user added";
		  }
		return "user not added";
	}

}
