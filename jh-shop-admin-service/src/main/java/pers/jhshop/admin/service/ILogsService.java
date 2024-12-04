package pers.jhshop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.jhshop.admin.model.entity.Logs;
import pers.jhshop.admin.model.req.LogsCreateReq;
import pers.jhshop.admin.model.req.LogsQueryReq;
import pers.jhshop.admin.model.req.LogsUpdateReq;
import pers.jhshop.admin.model.vo.LogsVO;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 管理员操作日志表 服务类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
public interface ILogsService extends IService<Logs> {

    void createBiz(LogsCreateReq createReq);

    void updateBiz(LogsUpdateReq updateReq);

    LogsVO getByIdBiz(Long id);

    Page<LogsVO> pageBiz(LogsQueryReq queryReq);

    Page<Logs> page(LogsQueryReq queryReq);

    List<Logs> listByQueryReq(LogsQueryReq queryReq);

    Map<Long, Logs> getIdEntityMap(List<Long> ids);

    Logs getOneByQueryReq(LogsQueryReq queryReq);

}
