package com.plusplm.excel; 

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.plusplm.SystemInfor;
import com.plusplm.model.BlockMP;
import com.plusplm.model.BlockMPxls;
import com.plusplm.model.PcrfChapter;

public class excelUtil {

	/**
	 * Block Division 트리 엑셀
	 */
	public static List<BlockMPxls> importBlockTreeExcel(XSSFWorkbook workbook) throws Exception{
		List<BlockMPxls> list = new ArrayList<BlockMPxls>();
		
		XSSFSheet curSheet;
		XSSFRow curRow;
		XSSFCell curCell;
		BlockMPxls vo;
		
		for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
			curSheet = workbook.getSheetAt(sheetIndex);
			for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
				if (rowIndex != 0) {
					curRow = curSheet.getRow(rowIndex);
					vo = new BlockMPxls();
					String value;
					if (curRow != null && curRow.getCell(0) != null) {
						if (!"".equals(curRow.getCell(0).getStringCellValue())) {
							for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
								curCell = curRow.getCell(cellIndex);
								if (true) {
									value = "";
									if(curCell != null) {
									    switch (curCell.getCellType()) {
									    	case HSSFCell.CELL_TYPE_FORMULA:
									    		value = SystemInfor.isNull(curCell.getCellFormula());
									    		break;
									    	case HSSFCell.CELL_TYPE_NUMERIC:
									    		if(DateUtil.isCellDateFormatted(curCell)){
													SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
													String formattedStr = dateFormat.format(curCell.getDateCellValue());
													value = SystemInfor.isNull(formattedStr) + "";
													break;
	
												}else{
													Double numericCellValue = curCell.getNumericCellValue();
	
													if(Math.floor(numericCellValue) == numericCellValue){
														value = SystemInfor.isNull(numericCellValue.intValue()) + "";
													}else{
														value = SystemInfor.isNull(numericCellValue) + "";
													}
													break;
												}
									    		//value = Utils.isNull(curCell.getNumericCellValue()) + "";
									    		//break;
									    	case HSSFCell.CELL_TYPE_STRING:
										    	value = SystemInfor.isNull(curCell.getStringCellValue()) + "";
										    	break;
										   	case HSSFCell.CELL_TYPE_BLANK:
										    	value = SystemInfor.isNull(curCell.getBooleanCellValue()) + "";
										    	break;
										   	case HSSFCell.CELL_TYPE_ERROR:
										    	value = SystemInfor.isNull(curCell.getErrorCellValue()) + "";
										    	break;
										   	default:
										    	value = new String();
										    	break;
									    }

									   	switch (cellIndex) {
											case 0:
												vo.setBlocNm(SystemInfor.isNull(value));
												break;
										   	case 1:
										   		vo.setErectBlk(SystemInfor.isNull(value));
										   		break;
										   	case 3:
										   		vo.setP2Blk(SystemInfor.isNull(value));
										   		break;
										   	case 4:
										   		vo.setP1Blk(SystemInfor.isNull(value));
										   		break;	
										   	case 5:
										   		vo.setPrePe(SystemInfor.isNull(value));
										   		break;	
										   	case 6:
										   		vo.setBlock(SystemInfor.isNull(value));
										   		break;	
										   	default:
										   		break;
									    }
									}
								}
							}
							list.add(vo);
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * BlockMP 엑셀 import
	 */
	public static List<BlockMP> importBlockMPCmdExcel(XSSFWorkbook workbook) throws Exception{
		List<BlockMP> list = new ArrayList<BlockMP>();
		
		XSSFSheet curSheet;
		XSSFRow curRow;
		XSSFCell curCell;
		BlockMP vo;
		
		for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
			curSheet = workbook.getSheetAt(sheetIndex);
			for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
				if (rowIndex != 0) {
					curRow = curSheet.getRow(rowIndex);
					vo = new BlockMP();
					String value;
					if (curRow != null && curRow.getCell(0) != null) {
						if (!"".equals(curRow.getCell(0).getStringCellValue())) {
							for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
								curCell = curRow.getCell(cellIndex);
								if (true) {
									value = "";
									if(curCell != null) {
									    switch (curCell.getCellType()) {
									    	case HSSFCell.CELL_TYPE_FORMULA:
									    		value = SystemInfor.isNull(curCell.getCellFormula());
									    		break;
									    	case HSSFCell.CELL_TYPE_NUMERIC:
									    		if(DateUtil.isCellDateFormatted(curCell)){
													SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
													String formattedStr = dateFormat.format(curCell.getDateCellValue());
													value = SystemInfor.isNull(formattedStr) + "";
													break;
	
												}else{
													Double numericCellValue = curCell.getNumericCellValue();
	
													if(Math.floor(numericCellValue) == numericCellValue){
														value = SystemInfor.isNull(numericCellValue.intValue()) + "";
													}else{
														value = SystemInfor.isNull(numericCellValue) + "";
													}
													break;
												}
									    		//value = Utils.isNull(curCell.getNumericCellValue()) + "";
									    		//break;
									    	case HSSFCell.CELL_TYPE_STRING:
										    	value = SystemInfor.isNull(curCell.getStringCellValue()) + "";
										    	break;
										   	case HSSFCell.CELL_TYPE_BLANK:
										    	value = new String();
										    	break;
										   	case HSSFCell.CELL_TYPE_ERROR:
										    	value = SystemInfor.isNull(curCell.getErrorCellValue()) + "";
										    	break;
										   	default:
										    	value = new String();
										    	break;
									    }
									   	switch (cellIndex) {
											case 0:
												vo.setShipCode(SystemInfor.isNull(value));
												break;
										   	case 1:
										   		vo.setLoadBlockId(SystemInfor.isNull(value));
										   		break;
										   	case 2:
										   		vo.setPe1BlockId(SystemInfor.isNull(value));
										   		break;
										   	case 3:
										   		vo.setBlockId(SystemInfor.isNull(value));
										   		break;	
										   	case 4:
										   		vo.setWeight(SystemInfor.isNull(value));
										   		break;	
										   	case 5:
										   		vo.setLocationGbn(SystemInfor.isNull(value));
										   		break;	
										   	case 6:
										   		vo.setFactory(SystemInfor.isNull(value));
										   		break;	
										   	case 7:
										   		vo.setWorkGbn(SystemInfor.isNull(value));
										   		break;	
										   	case 8:
										   		vo.setComeDate(SystemInfor.isNull(value));
										   		break;
										   	case 9:
										   		vo.setProStartDate(SystemInfor.isNull(value));
										   		break;
										  	case 10:
										   		vo.setProEndDate(SystemInfor.isNull(value));
										   		break;		
										   	case 11:
										   		vo.setAssemStartDate(SystemInfor.isNull(value));
										   		break;	
										   	case 12:
										   		vo.setAssemMiddleDate(SystemInfor.isNull(value));
										   		break;	
										   	case 13:
										   		vo.setAssemEndDate(SystemInfor.isNull(value));
										   		break;	
										   	case 14:
										   		vo.setPrefitStartDate(SystemInfor.isNull(value));
										   		break;	
										 	case 15:
										   		vo.setPrefitEndDate(SystemInfor.isNull(value));
										   		break;	
										   	case 16:
										   		vo.setPrePeStartDate(SystemInfor.isNull(value));
										   		break;	
										   	case 17:
										   		vo.setPrePeEndDate(SystemInfor.isNull(value));
										   		break;	
										   	case 18:
										   		vo.setPrePtStartDate(SystemInfor.isNull(value));
										   		break;
										   	case 19:
										   		vo.setPrePtEndDate(SystemInfor.isNull(value));
										   		break;	
										   	case 20:
										   		vo.setPe1StartDate(SystemInfor.isNull(value));
										   		break;
											case 21:
										   		vo.setPe1EndDate(SystemInfor.isNull(value));
										   		break;	
										   	case 22:
										   		vo.setPe1fitStartDate(SystemInfor.isNull(value));
										   		break;
										   	case 23:
										   		vo.setPe1fitEndDate(SystemInfor.isNull(value));
										   		break;
										   	case 24:
										   		vo.setPatStartDate(SystemInfor.isNull(value));
										   		break;
										   	case 25:
										   		vo.setPatEndDate(SystemInfor.isNull(value));
										   		break;
										   	case 26:
										   		vo.setPe2StartDate(SystemInfor.isNull(value));
										   		break;
										   	case 27:
										   		vo.setPe2EndDate(SystemInfor.isNull(value));
										   		break;
										   	case 28:
										   		vo.setLoadStartDate(SystemInfor.isNull(value));
										   		break;
										   	case 29:
										   		vo.setLoadMiddleDate(SystemInfor.isNull(value));
										   		break;
										   	case 30:
										   		vo.setLoadEndDate(SystemInfor.isNull(value));
										   		break;
										   	case 31:
										   		vo.setNote(SystemInfor.isNull(value));
										   		break;
										   	default:
										   		break;
									    }
									}
								}
							}
							list.add(vo);
						}
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * PcrfChapter 엑셀 import
	 */
	public static List<PcrfChapter> importPcrfChapterExcel(XSSFWorkbook workbook) throws Exception{
		List<PcrfChapter> list = new ArrayList<PcrfChapter>();
		
		XSSFSheet curSheet;
		XSSFRow curRow;
		XSSFCell curCell;
		PcrfChapter vo;
		
		for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
			curSheet = workbook.getSheetAt(sheetIndex);
			for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
				if (rowIndex != 0) {
					curRow = curSheet.getRow(rowIndex);
					vo = new PcrfChapter();
					String value;
					if (curRow != null && curRow.getCell(0) != null) {
						if (!"".equals(curRow.getCell(0).getStringCellValue())) {
							for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
								curCell = curRow.getCell(cellIndex);
								if (true) {
									value = "";
									if(curCell != null) {
									    switch (curCell.getCellType()) {
									    	case HSSFCell.CELL_TYPE_FORMULA:
									    		value = SystemInfor.isNull(curCell.getCellFormula());
									    		break;
									    	case HSSFCell.CELL_TYPE_NUMERIC:
									    		if(DateUtil.isCellDateFormatted(curCell)){
													SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
													String formattedStr = dateFormat.format(curCell.getDateCellValue());
													value = SystemInfor.isNull(formattedStr) + "";
													break;
	
												}else{
													Double numericCellValue = curCell.getNumericCellValue();
	
													if(Math.floor(numericCellValue) == numericCellValue){
														value = SystemInfor.isNull(numericCellValue.intValue()) + "";
													}else{
														value = SystemInfor.isNull(numericCellValue) + "";
													}
													break;
												}
									    		//value = Utils.isNull(curCell.getNumericCellValue()) + "";
									    		//break;
									    	case HSSFCell.CELL_TYPE_STRING:
										    	value = SystemInfor.isNull(curCell.getStringCellValue()) + "";
										    	break;
										   	case HSSFCell.CELL_TYPE_BLANK:
										    	value = new String();
										    	break;
										   	case HSSFCell.CELL_TYPE_ERROR:
										    	value = SystemInfor.isNull(curCell.getErrorCellValue()) + "";
										    	break;
										   	default:
										    	value = new String();
										    	break;
									    }
									   	switch (cellIndex) {
											case 0:
												vo.setChapter(SystemInfor.isNull(value));
												break;
										   	case 1:
										   		vo.setNobs(SystemInfor.isNull(value));
										   		break;
										   	default:
										   		break;
									    }
									}
								}
							}
							list.add(vo);
						}
					}
				}
			}
		}
		return list;
	}
	
}
