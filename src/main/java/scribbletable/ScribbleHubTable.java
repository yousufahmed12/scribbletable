package scribbletable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

public class ScribbleHubTable {
	
	
	private static String inputTextFile = "C:\\Users\\thuin\\OneDrive\\Desktop\\inputStat.txt";
	
	private static String outputExcelFile = "C:\\Users\\thuin\\OneDrive\\Desktop\\Writesheet.xlsx";
	
	public static void main(String[] args) throws IOException{
		
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		
	ArrayList<String> arr=new ArrayList<String>();
	File f=new File(inputTextFile);
	
	Scanner in=new Scanner(f);
	System.out.println("Read Data From The Txt file ");
	while(in.hasNext())
	{    

	arr.add(in.nextLine());
	}
	
	ArrayList<String> data1 = new ArrayList<String>();
	ArrayList<String> data2 = new ArrayList<String>();
	
	for(String stat:arr) {
		
		String newStat = stat.replaceAll(":", "").replaceAll("\\s+", " ").trim().replaceAll(" +", " ");
		String newStat2 = newStat.replaceAll("Remaining Points", "RemainingZPoints");
		String newStat3 = newStat2.replaceAll("Sour Apples", "SourZApples");
		
		String[] arrayStat = newStat3.split(" ");
		data1.add(arrayStat[0].replaceAll("Z", " "));
		data2.add(arrayStat[1].replaceAll("Z", " "));
		
	}
	publishToExcel(data1,data2);
	
	System.out.println("It is complete and no errors");
	
	ClassNameHere.infoBox("It is complete and no errors", "Yes");

	}
	
	public static void publishToExcel(ArrayList<String> data1, ArrayList<String> data2) throws IOException {
		   // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
        
        // Create a Sheet
        Sheet sheet = workbook.createSheet();

        // Create Other rows and cells with employees data
        for(int rowNum = 0; rowNum < data1.size(); rowNum++) {
            Row row = sheet.createRow(rowNum);

            row.createCell(0)
                    .setCellValue(data1.get(rowNum));

            row.createCell(1)
                    .setCellValue(data2.get(rowNum));
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < data1.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(outputExcelFile);
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
	}
	

	
}

