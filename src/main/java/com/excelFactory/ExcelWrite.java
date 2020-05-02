package com.excelFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelWrite {

	
	private static void writeHeader(String[] headers, HSSFSheet sheet) {

		// Create our Headers
		AtomicInteger columnNumber = new AtomicInteger(0);
		HSSFRow headerRow = sheet.createRow(0);
		Arrays.stream(headers).forEach(hd -> {
			Cell cell = headerRow.createCell(columnNumber.getAndIncrement());
			cell.setCellValue(hd);
		});
	}

	private static void writeContent(HSSFSheet sheet, String client, Map<Integer, String> cons) {

		// Get our Last Updated row
		AtomicInteger rowNumber = new AtomicInteger(sheet.getLastRowNum() + 1);

		// iterate over the map
		cons.forEach((key, value) -> {

			// Set Our Column Number to Zero
			AtomicInteger columnNumber = new AtomicInteger(0);

			// Get our Row
			HSSFRow row = sheet.createRow(rowNumber.getAndIncrement());

			String[] content = { client, Integer.toString(key), value };
			Arrays.stream(content).forEach(hd -> {
				Cell cell = row.createCell(columnNumber.getAndIncrement());
				cell.setCellValue(hd);
			});
		});
	}
}
