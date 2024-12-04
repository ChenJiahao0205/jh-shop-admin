package pers.jhshop.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.common.entity.ResultBo;
import pers.jhshop.admin.consts.JhShopAdminApiConstants;
import pers.jhshop.admin.model.req.LogsCreateReq;
import pers.jhshop.admin.model.req.LogsQueryReq;
import pers.jhshop.admin.model.req.LogsUpdateReq;
import pers.jhshop.admin.model.vo.LogsVO;
import pers.jhshop.admin.service.ILogsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * 管理员操作日志表 前端控制器
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Slf4j
@RestController
@RequestMapping(JhShopAdminApiConstants.API_USER + "logs")
@RequiredArgsConstructor
public class LogsController {
    private final ILogsService logsService;

    @PostMapping("create")
    public ResultBo create(@RequestBody LogsCreateReq createReq) {
        logsService.createBiz(createReq);
        return ResultBo.success();
    }

    @PostMapping("update")
    public ResultBo update(@RequestBody LogsUpdateReq updateReq) {
        logsService.updateBiz(updateReq);
        return ResultBo.success();
    }

    @GetMapping("getById")
    public ResultBo<LogsVO> getById(Long id) {
        LogsVO vo = logsService.getByIdBiz(id);
        return ResultBo.success(vo);
    }

    @PostMapping("page")
    public ResultBo<Page<LogsVO>> page(@RequestBody LogsQueryReq queryReq) {
        Page page = logsService.pageBiz(queryReq);
        return ResultBo.success(page);
    }
}

