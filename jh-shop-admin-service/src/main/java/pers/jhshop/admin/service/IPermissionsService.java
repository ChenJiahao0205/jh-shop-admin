package pers.jhshop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.jhshop.admin.model.entity.Permissions;
import pers.jhshop.admin.model.req.PermissionsCreateReq;
import pers.jhshop.admin.model.req.PermissionsQueryReq;
import pers.jhshop.admin.model.req.PermissionsUpdateReq;
import pers.jhshop.admin.model.vo.PermissionsVO;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
public interface IPermissionsService extends IService<Permissions> {

    void createBiz(PermissionsCreateReq createReq);

    void updateBiz(PermissionsUpdateReq updateReq);

    PermissionsVO getByIdBiz(Long id);

    Page<PermissionsVO> pageBiz(PermissionsQueryReq queryReq);

    Page<Permissions> page(PermissionsQueryReq queryReq);

    List<Permissions> listByQueryReq(PermissionsQueryReq queryReq);

    Map<Long, Permissions> getIdEntityMap(List<Long> ids);

    Permissions getOneByQueryReq(PermissionsQueryReq queryReq);

}
