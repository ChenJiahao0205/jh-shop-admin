package pers.jhshop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.jhshop.admin.model.entity.ProductOperations;
import pers.jhshop.admin.model.req.ProductOperationsCreateReq;
import pers.jhshop.admin.model.req.ProductOperationsQueryReq;
import pers.jhshop.admin.model.req.ProductOperationsUpdateReq;
import pers.jhshop.admin.model.vo.ProductOperationsVO;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 商品管理操作表 服务类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
public interface IProductOperationsService extends IService<ProductOperations> {

    void createBiz(ProductOperationsCreateReq createReq);

    void updateBiz(ProductOperationsUpdateReq updateReq);

    ProductOperationsVO getByIdBiz(Long id);

    Page<ProductOperationsVO> pageBiz(ProductOperationsQueryReq queryReq);

    Page<ProductOperations> page(ProductOperationsQueryReq queryReq);

    List<ProductOperations> listByQueryReq(ProductOperationsQueryReq queryReq);

    Map<Long, ProductOperations> getIdEntityMap(List<Long> ids);

    ProductOperations getOneByQueryReq(ProductOperationsQueryReq queryReq);

}
