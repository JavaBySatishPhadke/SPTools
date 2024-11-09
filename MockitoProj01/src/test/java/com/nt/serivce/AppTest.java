package com.nt.serivce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.nt.dao.ILoginDAO;

/**
 * Unit test for simple App.
 */
public class AppTest {
	private  static ILoginMgmtService  loginService;
	private  static ILoginDAO  loginDao;
	
	@BeforeAll
	public  static void  setupOnce() {
		// create Mock obj by generating the Proxy class implementing DAO Interface
		   loginDao=Mockito.mock(ILoginDAO.class);  
		   System.out.println("Proxy obj class name::"+loginDao.getClass()+" ....."+Arrays.toString(loginDao.getClass().getInterfaces()));
		   //assign the Mock /Proxy obj to  Service class obj
		   loginService=new LoginMgmtServiceImpl(loginDao);
	}
	
	@Test
	   public void  testLoginWithValidCredentials() {
		  //provide functionality for the  Mock obj  method and make the obj as the Stud object
		Mockito.when(loginDao.authenticate("raja", "rani")).thenReturn(1);
		//invoke the method
		String actualResult=loginService.login("raja", "rani");
		 assertEquals("Valid Credentials",actualResult);
	   }
	
	@Test
	   public void  testLoginWithInValidCredentials() {
		  //provide functionality for the  Mock obj  method and make the obj as the Stub object
		Mockito.when(loginDao.authenticate("raja", "rani11")).thenReturn(0);
		//invoke the method
		String actualResult=loginService.login("raja", "rani11");
		 assertEquals("InValid Credentials",actualResult);
	   }
	
	@Test
	   public void  testLoginWithNoCredentials() {
		 assertThrows(IllegalArgumentException.class,  ()->{ loginService.login("",""); });
	   }
	
	
	@Test
	public   void  testRegisterUser() {
		   loginService.registerUser("raja", "rani");
		   loginService.registerUser("anil", "hyd");
		   loginService.registerUser("chari", "");
		   
		   Mockito.verify(loginDao,Mockito.times(1)).addUser("raja", "rani");
		   Mockito.verify(loginDao,Mockito.times(1)).addUser("anil", "hyd");
		   Mockito.verify(loginDao,Mockito.times(0)).addUser("chari", "");
	}
	
	
	@Test
	public    void   testArrayListForMockVsStubVsSpy() {
		   List<String>  listMock= Mockito.mock(ArrayList.class);  //mock object
		   List<String>  listSpy= Mockito.spy(ArrayList.class);  //mock object
		  // List<String>  listSpy= Mockito.spy(new ArrayList<String>());  //spy object
		   //add times to  mock  , spy objects
		   listMock.add("table");
		   listSpy.add("table");
		   // define  dummy  functionality for  for  Mock , Spy objects  (Stub objects)
		   Mockito.when(listMock.size()).thenReturn(10);
		   Mockito.when(listSpy.size()).thenReturn(10);
		   System.out.println(listMock.size()+"......."+listSpy.size());
	}
	
	
	@AfterAll
	public   static void  tearDownOnce() {
		loginDao=null;
		loginService=null;
	}
	
	

   
}
