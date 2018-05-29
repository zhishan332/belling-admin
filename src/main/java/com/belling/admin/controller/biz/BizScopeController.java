package com.belling.admin.controller.biz;

import com.belling.admin.model.BizScope;
import com.belling.admin.model.User;
import com.belling.admin.service.BizScopeService;
import com.belling.base.controller.BaseController;
import com.belling.base.exception.ValidateException;
import com.belling.base.model.Pagination;
import com.belling.base.model.ResponseResult;
import com.belling.base.model.TablePageResult;
import com.belling.base.provider.PasswordProvider;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/biz/scope")
public class BizScopeController extends BaseController {


    @Autowired
    private BizScopeService bizScopeService;


    @RequiresPermissions("sys:bizscope:list")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "/bizscope/list";
    }


    /**
     * 编辑用户
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Integer id, Model model) {
        BizScope bizScope;
        if (id == null) {
            bizScope = new BizScope();
        } else {
            bizScope = bizScopeService.get(id);
        }
        model.addAttribute("vo", bizScope);
        return "/bizscope/edit";
    }

    /**
     * 分页查询
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public TablePageResult page(HttpServletRequest req, String uId, String keyword, Integer draw) {
        String curpage = req.getParameter("start");
        if (Strings.isNullOrEmpty(curpage)) {
            curpage = "0";
        }
        int start = Integer.parseInt(curpage);
        if (start <= 0) {
            start = 0;
        }
        String curlg = req.getParameter("length");
        if (Strings.isNullOrEmpty(curlg)) {
            curlg = "1";
        }
        int length = Integer.parseInt(curlg);
        if (length <= 0) {
            length = 8;
        }
        Pagination<BizScope> page = new Pagination<>((start / length) + 1, length);

        page = bizScopeService.findPaginationByParam(getCurrentUser().getId(), keyword,
                keyword, keyword, keyword, keyword, null, null, page);
        return TablePageResult.createSuccessResult(page.getList(), page.getRowCount(), draw + 1);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult save(BizScope bizScope) {

        if (StringUtils.isBlank(bizScope.getBizValue())) {
            throw new ValidateException("业务编号不能为空");
        }

        if (bizScope.getId() == null || bizScope.getId() <= 0) {
            bizScope.setCreateTime(new Date());
            bizScope.setCreateUser(getCurrentUser().getAccount());
        }

        bizScope.setUpdateTime(new Date());
        bizScope.setUpdateUser(getCurrentUser().getAccount());

        bizScopeService.save(bizScope);
        return ResponseResult.createSuccessResult();
    }

}
