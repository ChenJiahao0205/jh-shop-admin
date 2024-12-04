package pers.jhshop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.jhshop.admin.model.entity.Admins;
import pers.jhshop.admin.model.req.AdminsCreateReq;
import pers.jhshop.admin.model.req.AdminsQueryReq;
import pers.jhshop.admin.model.req.AdminsUpdateReq;
import pers.jhshop.admin.model.vo.AdminsVO;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
public interface IAdminsService extends IService<Admins> {

    void createBiz(AdminsCreateReq createReq);

    void updateBiz(AdminsUpdateReq updateReq);

    AdminsVO getByIdBiz(Long id);

    Page<AdminsVO> pageBiz(AdminsQueryReq queryReq);

    Page<Admins> page(AdminsQueryReq queryReq);

    List<Admins> listByQueryReq(AdminsQueryReq queryReq);

    Map<Long, Admins> getIdEntityMap(List<Long> ids);

    Admins getOneByQueryReq(AdminsQueryReq queryReq);

}
