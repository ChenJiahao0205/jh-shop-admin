package pers.jhshop.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.common.entity.ResultBo;
import pers.jhshop.admin.consts.JhShopAdminApiConstants;
import pers.jhshop.admin.model.req.ProductOperationsCreateReq;
import pers.jhshop.admin.model.req.ProductOperationsQueryReq;
import pers.jhshop.admin.model.req.ProductOperationsUpdateReq;
import pers.jhshop.admin.model.vo.ProductOperationsVO;
import pers.jhshop.admin.service.IProductOperationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 商品管理操作表 前端控制器
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Slf4j
@RestController
@RequestMapping(JhShopAdminApiConstants.API_USER + "productOperations")
@RequiredArgsConstructor
public class ProductOperationsController {
    private final IProductOperationsService productOperationsService;

    @PostMapping("create")
    public ResultBo create(@RequestBody ProductOperationsCreateReq createReq) {
        productOperationsService.createBiz(createReq);
        return ResultBo.success();
    }

    @PostMapping("update")
    public ResultBo update(@RequestBody ProductOperationsUpdateReq updateReq) {
        productOperationsService.updateBiz(updateReq);
        return ResultBo.success();
    }

    @GetMapping("getById")
    public ResultBo<ProductOperationsVO> getById(Long id) {
        ProductOperationsVO vo = productOperationsService.getByIdBiz(id);
        return ResultBo.success(vo);
    }

    @PostMapping("page")
    public ResultBo<Page<ProductOperationsVO>> page(@RequestBody ProductOperationsQueryReq queryReq) {
        Page page = productOperationsService.pageBiz(queryReq);
        return ResultBo.success(page);
    }
}

