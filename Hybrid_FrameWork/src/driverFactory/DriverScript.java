package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
String inputpath ="./FileInput/Controller.xlsx";
String outputpath ="./FileOutput/HybridResults.xlsx";
String TCSheet ="TestCases";
String TSSheet ="TestSteps";
@Test
public void startTest() throws Throwable
{
	boolean res =false;
	String tcres="";
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	//count no of rows in TCSheet AND TsSheet
	int TCCount =xl.rowCount(TCSheet);
	int TSCount =xl.rowCount(TSSheet);
	Reporter.log("No of rows in TCSheet::"+TCCount+"   "+"No of rows in TSSheet::"+TSCount,true);
	//iterate all rows in TCSheet
	for(int i=1;i<=TCCount;i++)
	{
		String Module_Status= xl.getCellData(TCSheet, i, 2);
		if(Module_Status.equalsIgnoreCase("Y"))
		{
			String tcid =xl.getCellData(TCSheet, i, 0);
			//iterate all rows in TSSheet
			for(int j=1;j<=TSCount;j++)
			{
				String tsid =xl.getCellData(TSSheet, j, 0);
				if(tcid.equalsIgnoreCase(tsid))
				{
					String keyword =xl.getCellData(TSSheet, j, 3);
					if(keyword.equalsIgnoreCase("adminLogin"))
					{
						String para1 =xl.getCellData(TSSheet, j, 5);
						String para2 =xl.getCellData(TSSheet, j, 6);
						res =FunctionLibrary.pBLogin(para1, para2);
					}
					else if(keyword.equalsIgnoreCase("newBranch"))
					{
						String para1 =xl.getCellData(TSSheet, j, 5);
						String para2 =xl.getCellData(TSSheet, j, 6);
						String para3 =xl.getCellData(TSSheet, j, 7);
						String para4 =xl.getCellData(TSSheet, j, 8);
						String para5 =xl.getCellData(TSSheet, j, 9);
						String para6 =xl.getCellData(TSSheet, j, 10);
						String para7 =xl.getCellData(TSSheet, j, 11);
						String para8 =xl.getCellData(TSSheet, j, 12);
						String para9 =xl.getCellData(TSSheet, j, 13);
						FunctionLibrary.pbBranches();
						res = FunctionLibrary.pbNewBranch(para1, para2, para3, para4, para5, para6, para7, para8, para9);
					}
					else if(keyword.equalsIgnoreCase("branchUpdate"))
					{
						String para1 =xl.getCellData(TSSheet, j, 5);
						String para2 =xl.getCellData(TSSheet, j, 6);
						String para3 =xl.getCellData(TSSheet, j, 9);
						String para4 =xl.getCellData(TSSheet, j, 10);
						FunctionLibrary.pbBranches();
						res =FunctionLibrary.pBBranchUpdate(para1, para2, para3, para4);
						
					}
					else if(keyword.equalsIgnoreCase("adminLogout"))
					{
						res =FunctionLibrary.pbLogout();
					}
					String tsres="";
					if(res)
					{
						//if res is true write as pass into TSSheet
						tsres="Pass";
						xl.setCellData(TSSheet, j, 4, tsres, outputpath);
					}
					else
					{
						//if res is false write as fail into TSSheet
						tsres="Fail";
						xl.setCellData(TSSheet, j, 4, tsres, outputpath);
					}
					tcres=tsres;
					
				}
			}
			//write as tcres into TCSheet
			xl.setCellData(TCSheet, i, 3, tcres, outputpath);
		}
		else
		{
			//write as blocked in to status cell which are flag to N
			xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
		}
		
	}
	
	
}
}
