package cn.com.tsjx.infomation.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.com.tsjx.attch.entity.Attch;
import cn.com.tsjx.attch.service.AttchService;
import cn.com.tsjx.common.constants.enums.InfomationEnum;
import cn.com.tsjx.common.util.StringUtil;
import com.qiniu.UploadDemo;
import com.qiniu.WaterSet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
    public void bathInfomation(InputStream inputStream) throws IOException {
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

                if ("出售".equals(row.getCell(0).toString())) {
                    infomation.setSellType("0");
                } else if ("租赁".equals(row.getCell(0).toString())) {
                    infomation.setSellType("1");
                } else if ("求购".equals(row.getCell(0).toString())) {
                    infomation.setSellType("2");
                } else if ("求租".equals(row.getCell(0).toString())) {
                    infomation.setSellType("3");
                }
                infomation.setCatagoryBigName(row.getCell(1).toString());
                infomation.setCatagoryMidName(row.getCell(2).toString());
                if (!StringUtils.isEmpty(row.getCell(3))) {
                    infomation.setCatagoryName(row.getCell(3).toString());
                }
                infomation.setBrandName(row.getCell(4).toString());
                infomation.setModelName(row.getCell(5).toString());

                infomation.setEquipYear(row.getCell(6).toString());
                infomation.setWorkTime(row.getCell(7).toString());
                infomation.setPrice(new BigDecimal(row.getCell(8).toString()));
                infomation.setEquipmentLocation(row.getCell(9).toString());

                infomation.setIsNew("0");
                infomation.setEquipmentCondition("0");
                infomation.setProcedures("0");
                infomation.setSrc("0");

                infomation.setRemark("联系人：" + row.getCell(10).toString() + ",联系方式：" + row.getCell(11).toString());
                infomation.setValidTime("30");
                infomation.setIsTop("0");
                infomation.setStatus(InfomationEnum.status_sj.code());
                infomation.setAuditType(Integer.parseInt(InfomationEnum.audit_type_auto.code()));
                if (!StringUtils.isEmpty(row.getCell(12))) {
                    infomation.setCreateBy(row.getCell(12).toString());//暂存图片
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
                    attch.setAttchUrl("/images/upload/" + img);
                    handleImg("/images/upload/" + img);
                    attchService.insert(attch);
                }
            }
        }

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
}
