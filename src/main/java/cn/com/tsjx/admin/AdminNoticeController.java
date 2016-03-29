
package cn.com.tsjx.admin;

import cn.com.tsjx.auditRecord.service.AuditRecordService;
import cn.com.tsjx.common.constants.enums.NoticeEnum;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.notice.entity.Notice;
import cn.com.tsjx.notice.service.NoticeService;
import cn.com.tsjx.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * MainController
 *
 * @author muxing
 * @date 2016/3/13
 */
@Controller
@RequestMapping("/admin")
public class AdminNoticeController {

	@Resource
	UserService userService;

	@Resource
	AuditRecordService auditRecordService;

	@Resource
	NoticeService noticeService;

	@RequestMapping(value = "/notice/list/getData")
	@ResponseBody
	public Pager<Notice> list(Pager<Notice> pager, Notice notice, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", notice);
		pager = noticeService.page(params, pager);
		return pager;
	}

	@RequestMapping(value = "/notice/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> add(@RequestBody Notice notice, Model model) {

		notice.setNoticeType(NoticeEnum.notice_type_admin.code());
		noticeService.insert(notice);
		//强制更新所有用户的消息状态
		userService.updateMsgAll();
		Result<String> result = new Result<String>();
		result.setMessage("操作成功！");
		result.setResult(true);
		return result;
	}

	@RequestMapping(value = "/notice/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> update(@RequestBody Notice notice) {
		noticeService.update(notice);
		Result<String> result = new Result<String>();
		result.setMessage("操作成功！");
		result.setResult(true);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/notice/del", method = RequestMethod.GET)
	public Result<Boolean> del(Long id) {
		Result<Boolean> result = new Result<Boolean>();
		noticeService.delete(id);
		result.setMessage("删除成功");
		result.setObject(true);
		result.setResult(true);
		return result;
	}

}
