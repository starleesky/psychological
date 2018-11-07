package cn.com.tsjx.common.util.lang;

import cn.com.eap.entity.EapEvaluating;
import cn.com.eap.web.EvaTypeEnum;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xin.l on 2018/11/2.
 *
 * @author xin.l
 */
public class ExcelExtUtils {
    /**
     * @param response
     * @param fileName excel文件名
     * @param headMap  表头map
     * @param dataList 表格数据
     */
    public static void exportXlsx(HttpServletResponse response, InputStream resource, List<EapEvaluating> list) {

        Workbook workbook = exportXlsx(resource, list);
        response.setContentType("application/vnd.ms-excel;charset=ISO8859_1");

        OutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();
            String fn = new String("export".getBytes(), "ISO8859_1");
            response.setHeader("Content-disposition", "attachment; filename=" + fn + ".xlsx");
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 导出数据
     *
     */
    public static Workbook exportXlsx(InputStream resource, List<EapEvaluating> list) {

        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int n1=4, n2=4, n3=4;
        for (EapEvaluating eapEvaluating : list) {
            Row row = null;
            Sheet sheet = null;
            int columnIndex = 0;

            if (EvaTypeEnum.MBTI.getType().equals(eapEvaluating.getEvaType())) {
                sheet = workbook.getSheet(EvaTypeEnum.MBTI.description());
                row = sheet.createRow(n1);
                n1++;
            }else if(EvaTypeEnum.OQ45.getType().equals(eapEvaluating.getEvaType())) {
                sheet = workbook.getSheet(EvaTypeEnum.OQ45.description());
                row = sheet.createRow(n2);
                n2++;
            }else if(EvaTypeEnum.SCL90.getType().equals(eapEvaluating.getEvaType())) {
                sheet = workbook.getSheet(EvaTypeEnum.SCL90.description());
                row = sheet.createRow(n3);
                n3++;
            }

            Cell cell = row.createCell(columnIndex);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(eapEvaluating.getCreateTime());
            cell.setCellValue(dateString);
            columnIndex++;

            cell = row.createCell(columnIndex);
            cell.setCellValue(eapEvaluating.getUserName());
            columnIndex++;
            cell = row.createCell(columnIndex);
            cell.setCellValue(eapEvaluating.getCompany());
            columnIndex++;

            cell = row.createCell(columnIndex);
            if("1".equals(eapEvaluating.getSex())){
                cell.setCellValue("男");
            }else{
                cell.setCellValue("女");

            }
            columnIndex++;

            cell = row.createCell(columnIndex);
            cell.setCellValue(eapEvaluating.getAge());
            columnIndex++;

            cell = row.createCell(columnIndex);
            cell.setCellValue(eapEvaluating.getUserPhone());
            columnIndex++;

            String[] split = eapEvaluating.getAnswer().split(",");
            for (String s : split) {
                cell = row.createCell(columnIndex);
                cell.setCellValue(s);
                columnIndex++;
            }

            String score = eapEvaluating.getScore();
            Map<String, String> result = Splitter.on(",")
                    .withKeyValueSeparator(":")
                    .split(score.substring(0,score.length()-1));
            //E	I	S	N	T	F	J	P	高分字母1	高分字母2	高分字母3	高分字母4
            if (EvaTypeEnum.MBTI.getType().equals(eapEvaluating.getEvaType())) {
                cell = row.createCell(columnIndex);
                cell.setCellValue(result.get("E"));
                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(result.get("I"));
                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(result.get("S"));
                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(result.get("N"));
                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(result.get("T"));
                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(result.get("F"));
                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(result.get("J"));
                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(result.get("P"));
                columnIndex++;

                cell = row.createCell(columnIndex);
                cell.setCellValue(Integer.parseInt(result.getOrDefault("E","0"))>Integer.parseInt(result.getOrDefault("I","0"))?"E":"I");
                columnIndex++;

                cell = row.createCell(columnIndex);
                cell.setCellValue(Integer.parseInt(result.getOrDefault("S","0"))>Integer.parseInt(result.getOrDefault("N","0"))?"S":"N");
                columnIndex++;

                cell = row.createCell(columnIndex);
                cell.setCellValue(Integer.parseInt(result.getOrDefault("T","0"))>Integer.parseInt(result.getOrDefault("F","0"))?"T":"F");
                columnIndex++;

                cell = row.createCell(columnIndex);
                cell.setCellValue(Integer.parseInt(result.getOrDefault("J","0"))>Integer.parseInt(result.getOrDefault("P","0"))?"J":"P");
                columnIndex++;

            }


            //人际关系:19,社会功能:19,情  绪:57,
            if (EvaTypeEnum.OQ45.getType().equals(eapEvaluating.getEvaType())) {
                cell = row.createCell(columnIndex);
                cell.setCellValue(Integer.parseInt(result.getOrDefault("人际关系","0"))+Integer.parseInt(result.getOrDefault("社会功能","0"))+Integer.parseInt(result.getOrDefault("情  绪","0")));
                columnIndex++;

                cell = row.createCell(columnIndex);
                cell.setCellValue(Integer.parseInt(result.get("情  绪")));
                columnIndex++;

                cell = row.createCell(columnIndex);
                cell.setCellValue(Integer.parseInt(result.get("人际关系")));
                columnIndex++;

                cell = row.createCell(columnIndex);
                cell.setCellValue(Integer.parseInt(result.get("社会功能")));
                columnIndex++;
            }

            //躯体化	强   迫	人际敏感	抑   郁	焦   虑	敌   对	恐   怖	偏   执	精神病	其他	项目总均分
            //焦   虑:27,人际敏感:29,敌   对:14,恐   怖:21,其   他:18,躯体化:32,精神病:30,偏   执:20,抑   郁:37,强   迫:36,
            if (EvaTypeEnum.SCL90.getType().equals(eapEvaluating.getEvaType())) {
                cell = row.createCell(columnIndex);
                cell.setCellValue(new BigDecimal(result.getOrDefault("躯体化","0")).divide(new BigDecimal("12"),2,   BigDecimal.ROUND_HALF_UP).doubleValue());
                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(new BigDecimal(result.getOrDefault("强   迫","0")).divide(new BigDecimal("10"),2,   BigDecimal.ROUND_HALF_UP).doubleValue());
                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(new BigDecimal(result.getOrDefault("人际敏感","0")).divide(new BigDecimal("9"),2,   BigDecimal.ROUND_HALF_UP).doubleValue());
                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(new BigDecimal(result.getOrDefault("抑   郁","0")).divide(new BigDecimal("13"),2,   BigDecimal.ROUND_HALF_UP).doubleValue());
                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(new BigDecimal(result.getOrDefault("焦   虑","0")).divide(new BigDecimal("10"),2,   BigDecimal.ROUND_HALF_UP).doubleValue());
                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(result.get("敌   对"));
                cell.setCellValue(new BigDecimal(result.getOrDefault("焦   虑","0")).divide(new BigDecimal("10"),2,   BigDecimal.ROUND_HALF_UP).doubleValue());
                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(result.get("恐   怖"));
                cell.setCellValue(new BigDecimal(result.getOrDefault("恐   怖","0")).divide(new BigDecimal("7"),2,   BigDecimal.ROUND_HALF_UP).doubleValue());

                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(result.get("偏   执"));
                cell.setCellValue(new BigDecimal(result.getOrDefault("偏   执","0")).divide(new BigDecimal("6"),2,   BigDecimal.ROUND_HALF_UP).doubleValue());
                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(result.get("精神病"));
                cell.setCellValue(new BigDecimal(result.getOrDefault("精神病","0")).divide(new BigDecimal("10"),2,   BigDecimal.ROUND_HALF_UP).doubleValue());

                columnIndex++;
                cell = row.createCell(columnIndex);
                cell.setCellValue(result.get("其   他"));
                cell.setCellValue(new BigDecimal(result.getOrDefault("其   他","0")).divide(new BigDecimal("7"),2,   BigDecimal.ROUND_HALF_UP).doubleValue());

                columnIndex++;
                cell = row.createCell(columnIndex);
                Integer num = Integer.parseInt(result.getOrDefault("躯体化", "0")) + Integer.parseInt(
                        result.getOrDefault("强   迫", "0")) + Integer.parseInt(
                        result.getOrDefault("人际敏感", "0")) + Integer.parseInt(
                        result.getOrDefault("抑   郁", "0")) + Integer.parseInt(
                        result.getOrDefault("焦   虑", "0")) + Integer.parseInt(
                        result.getOrDefault("敌   对", "0")) + Integer.parseInt(
                        result.getOrDefault("恐   怖", "0")) + Integer.parseInt(
                        result.getOrDefault("偏   执", "0")) + Integer.parseInt(result.getOrDefault("精神病", "0")) + Integer
                        .parseInt(result.getOrDefault("其   他", "0"));
                cell.setCellValue(new BigDecimal(num).divide(new BigDecimal("90"),2,   BigDecimal.ROUND_HALF_UP).doubleValue());

                columnIndex++;
            }



            }


        return workbook;
    }


    public static void main(String[] args) {
        String input = "P:9,S:6,T:1,E:10,F:7,I:2,J:1,N:7,";
        Map<String, String> result = Splitter.on(",")
                .withKeyValueSeparator(":")
                .split(input.substring(0,input.length()));
        System.out.println(JSON.toJSONString(result));
    }
    /**
     * 导出数据
     *
     */
    public static Workbook exportXlsx(List<EapEvaluating> list,String sheetName) {

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet(sheetName);

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("姓名");
        cell = row.createCell(1);
        cell.setCellValue("单位");
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("年龄");
        cell = row.createCell(4);
        cell.setCellValue("手机");
        int rowIndex = 1, columnIndex = 5;







        return workbook;
    }

    private static void setCellValue(Cell cell, Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof String) {
            cell.setCellValue((String) obj);
        } else if (obj instanceof Date) {
            Date date = (Date) obj;
            if (date != null)
                cell.setCellValue(DateFormatUtils.ISO_DATE_FORMAT.format(date));
        } else if (obj instanceof Calendar) {
            Calendar calendar = (Calendar) obj;
            if (calendar != null) {
                cell.setCellValue(DateFormatUtils.ISO_DATE_FORMAT.format(calendar.getTime()));
            }
        } else if (obj instanceof Timestamp) {
            Timestamp timestamp = (Timestamp) obj;
            if (timestamp != null) {
                cell.setCellValue(DateFormatUtils.ISO_DATE_FORMAT.format(timestamp.getTimestamp()));
            }
        } else if (obj instanceof Double) {
            cell.setCellValue((Double) obj);
        } else {
            cell.setCellValue(obj.toString());
        }
    }
}
