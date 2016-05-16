package cn.com.tsjx.infomation.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.com.tsjx.attch.entity.Attch;
import cn.com.tsjx.attch.service.AttchService;
import cn.com.tsjx.brand.entity.Brand;
import cn.com.tsjx.brand.service.BrandService;
import cn.com.tsjx.catagory.entity.Catagory;
import cn.com.tsjx.catagory.service.CatagoryService;
import cn.com.tsjx.common.constants.enums.InfomationEnum;
import cn.com.tsjx.common.util.StringUtil;
import cn.com.tsjx.models.entity.Models;
import cn.com.tsjx.models.service.ModelsService;
import com.qiniu.UploadDemo;
import com.qiniu.WaterSet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.common.web.model.Params;
import cn.com.tsjx.infomation.dao.InfomationDao;
import cn.com.tsjx.infomation.entity.Infomation;
import cn.com.tsjx.infomation.entity.InfomationDto;
import cn.com.tsjx.infomation.service.InfomationService;
import cn.com.tsjx.user.entity.User;
import org.springframework.util.StringUtils;

@Service("infomationService")
public class InfomationServiceImpl extends BaseServiceImpl<Infomation, Long> implements InfomationService {

	private static Logger LOG = LoggerFactory.getLogger(InfomationServiceImpl.class);

	@Resource
	private InfomationDao infomationDao;

	@Resource
	AttchService attchService;

	@Resource
	CatagoryService catagoryService;

	@Resource
	BrandService brandService;

	@Resource
	ModelsService modelsService;

	@Value("${file.uplaoddir}")
	String path;

	@Override
	protected BaseDao<Infomation, Long> getBaseDao() {
		return this.infomationDao;
	}

	@Override
	public List<Infomation> getInfomationsByParam(User user, Infomation infomation) {

		return infomationDao.getInfomationsByParam(user, infomation);
	}

	@Override
	public Pager<InfomationDto> getPagerCollections(Params map, Pager<InfomationDto> pager) {

		return infomationDao.getPagerCollections(map, pager);
	}

	@Override
	public Pager<InfomationDto> getInfoPagerWithImg(Params map, Pager<InfomationDto> pager, Boolean relUser) {

		if (relUser) {
			return infomationDao.getInfoPagerWithImg(map, pager);
		} else {
			return infomationDao.getInfoPagerWithImgNoUser(map, pager);
		}
	}

	@Override
	public void bathInfomation(InputStream inputStream, Long userId) throws IOException {
		List<Infomation> infomationList = new ArrayList<Infomation>();
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		int sheetSize = workbook.getNumberOfSheets();

		for (int i = 0; i < sheetSize; i++) {
			HSSFSheet sheet = workbook.getSheetAt(i);
			int rowLen = sheet.getLastRowNum() + 1; // 最后一行的索引值加 1 才是总行数

			HSSFRow row = sheet.getRow(1);

			for (int j = 1; j < rowLen; j++) {
				row = sheet.getRow(j);
				Infomation infomation = new Infomation();
				if (row.getCell(0) == null || "".equals(row.getCell(0))) {
					continue;
				}

				if ("出售".equals(row.getCell(0).toString().trim())) {
					infomation.setSellType("0");
				} else if ("租赁".equals(row.getCell(0).toString().trim())) {
					infomation.setSellType("1");
				} else if ("求购".equals(row.getCell(0).toString().trim())) {
					infomation.setSellType("2");
				} else if ("求租".equals(row.getCell(0).toString().trim())) {
					infomation.setSellType("3");
				}
				infomation.setCatagoryBigName(row.getCell(1).toString().trim());
				infomation.setCatagoryMidName(row.getCell(2).toString().trim());
				if (!StringUtils.isEmpty(row.getCell(3))) {
					infomation.setCatagoryName(row.getCell(3).toString().trim());
				}
				infomation.setBrandName(getString(row.getCell(4)).trim());
				infomation.setModelName(getString(row.getCell(5)).trim());

				Catagory catagory = new Catagory();
				catagory.setLayer("0");
				catagory.setCatagoryName(infomation.getCatagoryBigName());
				List<Catagory> catagories = catagoryService.find(catagory);
				if (catagories != null && catagories.size() > 0) {
					infomation.setCatagoryBigId(catagories.get(0).getId());

					catagory.setCatagoryName(infomation.getCatagoryMidName());
					catagory.setLayer("1");
					catagories = catagoryService.find(catagory);
					if (catagories != null && catagories.size() > 0) {
						infomation.setCatagoryMidId(catagories.get(0).getId());

						catagory.setCatagoryName(infomation.getCatagoryName());
						catagory.setLayer(null);
						catagories = catagoryService.find(catagory);
						if (catagories != null && catagories.size() > 0) {
							infomation.setCatagoryId(catagories.get(0).getId());
						}
					}
				}

				//品牌型号ID
				if (infomation.getCatagoryId() != null && infomation.getCatagoryId() != 0) {

					Brand brand = new Brand();
					brand.setBrandName(infomation.getBrandName());
					brand.setCatagoryId(infomation.getCatagoryId());
					List<Brand> brands = brandService.find(brand);
					//品牌存在
					if (brands != null && brands.size() > 0) {
						infomation.setBrandId(brands.get(0).getId());

						Models models = new Models();
						models.setBrandId(brands.get(0).getId());
						models.setModelsName(infomation.getModelName());
						List<Models> modelses = modelsService.find(models);
						//型号存在
						if (modelses != null && modelses.size() > 0) {
							infomation.setModelId(modelses.get(0).getId());
						} else {//型号不存在
							models = modelsService.insert(models);
							infomation.setModelId(models.getId());
						}
					} else { //品牌不存在，新增自定义的品牌型号
						brand = brandService.insert(brand);
						Models models = new Models();
						models.setBrandId(brand.getId());
						models.setModelsName(infomation.getModelName());
						models = modelsService.insert(models);
						infomation.setBrandId(brand.getId());
						infomation.setModelId(models.getId());
					}
				}

				DecimalFormat df = new DecimalFormat("0");
				infomation.setEquipYear(df.format(row.getCell(6).getNumericCellValue()));
				infomation.setWorkTime(df.format(row.getCell(7).getNumericCellValue()));
				infomation.setPrice(new BigDecimal(row.getCell(8).toString()));
				infomation.setEquipmentLocation(row.getCell(9).toString());

				infomation.setIsNew("0");
				infomation.setEquipmentCondition("0");
				infomation.setProcedures("0");
				infomation.setSrc("0");

				String strCell = df.format(row.getCell(11).getNumericCellValue());
				infomation.setRemark("联系人：" + row.getCell(10).toString() + ",联系方式：" + strCell);
				if (row.getCell(13) != null && !"".equals(row.getCell(13))) {
					infomation.setRemark(infomation.getRemark() + ",备注：" + row.getCell(13).toString());
				}
				infomation.setValidTime("30");
				infomation.setIsTop("0");
				infomation.setUserId(userId);
				infomation.setStatus(InfomationEnum.status_sj.code());
				infomation.setWeight("1");//暂时表示导入数据
				infomation.setAuditType(Integer.parseInt(InfomationEnum.audit_type_auto.code()));
				if ("全新".equals(row.getCell(12).toString().trim())) {
					infomation.setEquipmentCondition("0");
				} else if ("二手".equals(row.getCell(12).toString().trim())) {
					infomation.setEquipmentCondition("1");
				} else if ("再制造".equals(row.getCell(12).toString().trim())) {
					infomation.setEquipmentCondition("2");
				} else if ("库存".equals(row.getCell(12).toString().trim())) {
					infomation.setEquipmentCondition("3");
				}
				if (!StringUtils.isEmpty(row.getCell(14))) {
					infomation.setCreateBy(row.getCell(14).toString());//暂存图片
				}
				infomation.setPubTime(new Date());
				infomationList.add(infomation);
			}
		}
		LOG.info("本次上传总计导入信息{}条!" + infomationList.size());

		for (Infomation infomation : infomationList) {
			String imgUrl = infomation.getCreateBy();
			infomation.setCreateBy("");
			infomationDao.insert(infomation);
			if (StringUtil.isNotBlank(imgUrl)) {
				String[] imgs = imgUrl.split(",");
				for (String img : imgs) {
					Attch attch = new Attch();
					attch.setInformationId(infomation.getId());
					//                    attch.setUserId(user.getId());
					attch.setAttchUrl("/images/upload/".replaceAll("/", "%2F") + img);
					//                    handleImg("/images/upload/" + img);
					attchService.insert(attch);
				}
			}
		}

	}

	public String getString(Cell cell) {
		String result = "";
		DecimalFormat df = new DecimalFormat("0");
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			result = String.valueOf(df.format(cell.getNumericCellValue()));
			break;
		case HSSFCell.CELL_TYPE_STRING:
			result = cell.getRichStringCellValue().getString();
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			result = cell.getCellFormula();
			break;
		}
		return result;
	}

	private void handleImg(String filePath) {
		//添加水印
		WaterSet.pressImage(path + "/wap/images/watermark.png", path + filePath, 4, 1);
		//上传图片
		try {
			new UploadDemo().uploadImgs(path + filePath, filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void autoDown() {
		infomationDao.autoDown();
	}
}
