package cn.com.tsjx.catagory.service.impl;

import javax.annotation.Resource;

import cn.com.tsjx.brand.entity.Brand;
import cn.com.tsjx.brand.service.BrandService;
import cn.com.tsjx.common.util.StringUtil;
import cn.com.tsjx.models.entity.Models;
import cn.com.tsjx.models.service.ModelsService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.catagory.dao.CatagoryDao;
import cn.com.tsjx.catagory.entity.Catagory;
import cn.com.tsjx.catagory.service.CatagoryService;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service("catagoryService")
public class CatagoryServiceImpl extends BaseServiceImpl<Catagory, Long> implements CatagoryService {

    @Resource
    private CatagoryDao catagoryDao;

    @Resource
    BrandService brandService;
    @Resource
    ModelsService modelsService;

    @Resource
    CatagoryService catagoryService;

    @Override
    protected BaseDao<Catagory, Long> getBaseDao() {
        return this.catagoryDao;
    }

    @Override
    public void uploadFileBuild(InputStream inputStream) throws Exception {
        Map<String, List<String>> models = new HashMap<String, List<String>>();
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        int sheetSize = workbook.getNumberOfSheets();

        for (int i = 0; i < sheetSize; i++) {
            HSSFSheet sheet = workbook.getSheetAt(i);
            int rowLen = sheet.getLastRowNum() + 1; // 最后一行的索引值加 1 才是总行数
            //			if (rowLen < 4) {// 至少4 行以上
            //				throw new IllegalArgumentException("数据格式错误");
            //			}
            HSSFRow row = sheet.getRow(1);

            System.out.println("机型:" + row.getCell(2));
            //根据机型查询是否已经存在机型
            Catagory catagory = new Catagory();
            catagory.setCatagoryName(row.getCell(2).toString().trim());
            List<Catagory> catagories = catagoryService.find(catagory);
            //得到机型ID
            Long catagoryId;

            catagory.setCatagoryName(row.getCell(1).toString().trim());
            List<Catagory> catagoryList = catagoryService.find(catagory);

            if (catagories != null && catagories.size() > 0) {
                if (catagoryList != null && catagoryList.size() > 0) {
                    if (catagories.get(0).getParentId().equals(catagoryList.get(0).getId())) {
                        throw new Exception(row.getCell(1).toString().trim() + "不存在产品子类");
                    }
                    catagoryId = catagories.get(0).getId();
                } else {
                    throw new Exception(row.getCell(1).toString().trim() + "不存在产品子类");
                }

            } else {
                if (catagoryList != null && catagoryList.size() > 0) {
                    catagory.setCatagoryName(row.getCell(2).toString().trim());
                    catagory.setParentId(String.valueOf(catagoryList.get(0).getId()));
                    catagory = catagoryService.insert(catagory);
                    catagoryId = catagory.getId();
                } else {
                    throw new Exception(row.getCell(1).toString().trim() + "不存在产品子类");
                }
            }
//			brand.put(String.valueOf(i), row.getCell(2).toString());

            String brand = "";
            String abcd = "";
            for (int j = 1; j < rowLen; j++) {
                row = sheet.getRow(j);
                System.out.println("品牌:" + row.getCell(4) + "型号:" + row.getCell(5));
                if (row.getCell(4) == null || row.getCell(5) == null) {
                    System.out.println("多少行忽略了：" + j);
                    continue;
                }
                String abc = row.getCell(3).toString();
                String key = row.getCell(4).toString();
                if (null == key || "".equals(key)|| " ".equals(key)) {
                    key = brand;
                }
                if (null == abc || "".equals(abc)|| " ".equals(abc)) {
                    abc = abcd;
                }
                List<String> brands = models.get(abc.trim() + "," + key.trim());

                if (brands != null) {
                    brands.add(row.getCell(5).toString().trim());
                } else {
                    List<String> modeList = new ArrayList<>();
                    modeList.add(row.getCell(5).toString().trim());
                    models.put(abc.trim() + "," + key.trim(), modeList);
                    brand = row.getCell(4).toString().trim();
                    abcd=abc;
                }

            }

            //获取map集合中的所有键的Set集合
            Set<String> keySet = models.keySet();
            //有了Set集合就可以获取其迭代器，取值
            Iterator<String> it = keySet.iterator();
            while (it.hasNext()) {
                String x = it.next();
                String[] split = x.split(",");
                Brand brand1 = new Brand();
                if (split.length == 2) {
                    brand1.setFirstLetter(split[0]);
                    brand1.setBrandName(split[1]);
                }

                brand1.setCatagoryId(catagoryId);
                brand1 = brandService.insert(brand1);
                List<String> mode = models.get(x);
                for (String s : mode) {
                    Models modelss = new Models();
                    modelss.setModelsName(s);
                    modelss.setBrandId(brand1.getId());
                    modelsService.insert(modelss);
                }
            }
            System.out.println("机型:" + row.getCell(2));
            models.clear();
        }
        System.out.println("解析结束了");
    }
}
