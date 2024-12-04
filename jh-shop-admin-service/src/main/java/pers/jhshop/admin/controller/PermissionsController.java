package pers.jhshop.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.common.entity.ResultBo;
import pers.jhshop.admin.consts.JhShopAdminApiConstants;
import pers.jhshop.admin.model.req.PermissionsCreateReq;
import pers.jhshop.admin.model.req.PermissionsQueryReq;
import pers.jhshop.admin.model.req.PermissionsUpdateReq;
import pers.jhshop.admin.model.vo.PermissionsVO;
import pers.jhshop.admin.service.IPermissionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Slf4j
@RestController
@RequestMapping(JhShopAdminApiConstants.API_USER + "permissions")
@RequiredArgsConstructor
public class PermissionsController {
    private final IPermissionsService permissionsService;

    @PostMapping("create")
    public ResultBo create(@RequestBody PermissionsCreateReq createReq) {
        permissionsService.createBiz(createReq);
        return ResultBo.success();
    }

    @PostMapping("update")
    public ResultBo update(@RequestBody PermissionsUpdateReq updateReq) {
        permissionsService.updateBiz(updateReq);
        return ResultBo.success();
    }

    @GetMapping("getById")
    public ResultBo<PermissionsVO> getById(Long id) {
        PermissionsVO vo = permissionsService.getByIdBiz(id);
        return ResultBo.success(vo);
    }

    @PostMapping("page")
    public ResultBo<Page<PermissionsVO>> page(@RequestBody PermissionsQueryReq queryReq) {
        Page page = permissionsService.pageBiz(queryReq);
        return ResultBo.success(page);
    }
}

