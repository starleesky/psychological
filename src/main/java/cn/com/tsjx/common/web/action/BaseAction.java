package cn.com.tsjx.common.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.common.collect.Maps;
import com.opensymphony.xwork2.ActionSupport;


import cn.com.tsjx.common.config.Config;
import cn.com.tsjx.common.context.UserContext;
import cn.com.tsjx.common.util.json.JsonMapper;
import cn.com.tsjx.common.web.model.DataGrid;
import cn.com.tsjx.common.web.model.LabelValue;
import cn.com.tsjx.common.web.model.Page;

/**
 * Action基类。
 * 
 * @author liwei
 */
public class BaseAction extends ActionSupport {

    private static final long serialVersionUID = -2153134096945168503L;

    /** Result: Redirect */
    public static final String REDIRECT = "redirect";

    /** Result: CHAIN */
    public static final String CHAIN = "chain";

    /** Result: JSON */
    public static final String JSON = "json";

    /** Result: XML */
    public static final String XML = "xml";

    /** Result: No Auth */
    public static final String NOAUTH = "noauth";

    /** Result: No Data Auth */
    public static final String NO_DATA_AUTH = "NO_DATA_AUTH";

    /** Result: Page not found */
    public static final String PAGE_NOT_FOUND = "PAGE_NOT_FOUND";

    @Resource
    private Config config;

    @Resource
    protected UserContext userContext;

    /**
     * 操作对象标识
     */
    protected Long id;

    /**
     * 操作对象标识列表
     */
    protected Long[] ids;

    /**
     * 数据字符串
     */
    protected String data;

    /**
     * 重定向URL
     */
    protected String url;

    /**
     * 偏移量（开始行数）
     */
    protected int start;

    /**
     * 获取行数
     */
    protected int count;

    /**
     * 排序字段
     */
    protected String sort;

    protected Map<String, List<LabelValue>> enums = Maps.newHashMap();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public Map<String, List<LabelValue>> getEnums() {
        return enums;
    }

    public void setEnums(Map<String, List<LabelValue>> enums) {
        this.enums = enums;
    }

    // 列表
    public String list() {
        return SUCCESS;
    }

    // 添加
    public String add() {
        return SUCCESS;
    }

    // 编辑
    public String edit() {
        return SUCCESS;
    }

    /**
     * 获取分页对象
     * 
     * @return
     */
    protected Page getPage() {
        if (this.count <= 0) {
            return null;
        }
        if (this.sort == null || this.sort.length() == 0) {
            return new Page(start, count);
        } else {
            String fsort = null;
            String order = Page.ORDER_ASC;
            if (sort.charAt(0) == '-') {
                fsort = this.sort.substring(1);
                order = Page.ORDER_DESC;
            } else {
                fsort = this.sort;
                order = Page.ORDER_ASC;
            }
            return new Page(start, count, fsort, order);
        }
    }

    /**
     * 将对象转换为Json字符串。
     * 
     * @param obj
     * @return
     */
    public String toJson(Object obj) {
        return JsonMapper.builder().toJson(obj);
    }

    /**
     * 将分页数据对象转换为JSON并设置到data属性。
     * 
     * @param datagrid
     */
    protected <T> void renderDataGrid(DataGrid<T> datagrid) {
        this.renderJson(datagrid);
    }

    /**
     * 将对象转换为JSON字符串，并设置到data属性
     * 
     * @param object
     */
    protected <T> void renderJson(T object) {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("application/json");
        this.setData(this.toJson(object));
    }

    /**
     * 将属性增加到Request中
     * 
     * @param name
     *            名称
     * @param value
     *            值
     */
    protected void addAttribute(String name, Object value) {
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute(name, value);
    }

    /**
     * 判断是否有该URI的权限。
     * 
     * @param uri
     * @return
     */
    public boolean hasAuth(String uri) {
        return this.userContext.hasAuth(uri);
    }

    /**
     * 返回上下文路径
     * 
     * @return
     */
    public String getContextPath() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String contextPath = request.getContextPath();
        if (contextPath.equals("/")) {
            contextPath = "";
        }
        return contextPath;
    }
    
    
    protected HttpServletResponse getResponse(){
        HttpServletResponse response = ServletActionContext.getResponse();
        return response;
    }
    
    protected HttpServletRequest getResquest(){
        HttpServletRequest request = ServletActionContext.getRequest();
        return request;
    }
}
