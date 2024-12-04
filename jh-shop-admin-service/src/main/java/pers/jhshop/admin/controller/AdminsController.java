package pers.jhshop.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.admin.consts.JhShopAdminApiConstants;
import pers.jhshop.common.entity.ResultBo;
import pers.jhshop.admin.model.req.AdminsCreateReq;
import pers.jhshop.admin.model.req.AdminsQueryReq;
import pers.jhshop.admin.model.req.AdminsUpdateReq;
import pers.jhshop.admin.model.vo.AdminsVO;
import pers.jhshop.admin.service.IAdminsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Slf4j
@RestController
@RequestMapping(JhShopAdminApiConstants.API_USER + "admins")
@RequiredArgsConstructor
public class AdminsController {
    private final IAdminsService adminsService;

    @PostMapping("create")
    public ResultBo create(@RequestBody AdminsCreateReq createReq) {
        adminsService.createBiz(createReq);
        return ResultBo.success();
    }

    @PostMapping("update")
    public ResultBo update(@RequestBody AdminsUpdateReq updateReq) {
        adminsService.updateBiz(updateReq);
        return ResultBo.success();
    }

    @GetMapping("getById")
    public ResultBo<AdminsVO> getById(Long id) {
        AdminsVO vo = adminsService.getByIdBiz(id);
        return ResultBo.success(vo);
    }

    @PostMapping("page")
    public ResultBo<Page<AdminsVO>> page(@RequestBody AdminsQueryReq queryReq) {
        Page page = adminsService.pageBiz(queryReq);
        return ResultBo.success(page);
    }
}

