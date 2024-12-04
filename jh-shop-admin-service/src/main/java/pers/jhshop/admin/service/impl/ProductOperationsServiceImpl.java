package pers.jhshop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.admin.model.req.ProductOperationsCreateReq;
import pers.jhshop.admin.model.req.ProductOperationsQueryReq;
import pers.jhshop.admin.model.req.ProductOperationsUpdateReq;
import pers.jhshop.admin.model.vo.ProductOperationsVO;
import pers.jhshop.admin.model.entity.ProductOperations;
import pers.jhshop.admin.mapper.ProductOperationsMapper;
import pers.jhshop.admin.service.IProductOperationsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.jhshop.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;

/**
 * <p>
 * 商品管理操作表 服务实现类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductOperationsServiceImpl extends ServiceImpl<ProductOperationsMapper, ProductOperations> implements IProductOperationsService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBiz(ProductOperationsCreateReq createReq) {


        ProductOperations entity = new ProductOperations();
        BeanUtil.copyProperties(createReq, entity);

        boolean insertResult = entity.insert();

        if (!insertResult) {
            throw new ServiceException("数据插入失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBiz(ProductOperationsUpdateReq updateReq) {

        // 1.入参有效性判断
        if (Objects.isNull(updateReq.getId())) {
            throw new ServiceException("id不能为空");
        }

        ProductOperations entity = getById(updateReq.getId());
        if (Objects.isNull(entity)) {
            throw new ServiceException("商品管理操作表不存在");
        }

        // 2.重复性判断
        ProductOperations entityToUpdate = new ProductOperations();
        BeanUtil.copyProperties(updateReq, entityToUpdate);

        boolean updateResult = entityToUpdate.updateById();
        if (!updateResult) {
            throw new ServiceException("数据更新失败");
        }
    }

    @Override
    public ProductOperationsVO getByIdBiz(Long id) {
        // 1.入参有效性判断
        if (Objects.isNull(id)) {
            throw new ServiceException("id不能为空");
        }

        // 2.存在性判断
        ProductOperations entity = getById(id);
        if (Objects.isNull(entity)) {
            throw new ServiceException("商品管理操作表不存在");
        }

        ProductOperationsVO vo = new ProductOperationsVO();
        BeanUtil.copyProperties(entity, vo);

            return vo;
    }

    @Override
    public Page<ProductOperationsVO> pageBiz(ProductOperationsQueryReq queryReq) {
        Page<ProductOperations> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        page.addOrder(OrderItem.desc("id"));

        LambdaQueryWrapper<ProductOperations> queryWrapper = getLambdaQueryWrapper(queryReq);

        page(page, queryWrapper);

        Page<ProductOperationsVO> pageVOResult = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<ProductOperations> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return pageVOResult;
        }

        List<ProductOperationsVO> vos = records.stream().map(record -> {
            ProductOperationsVO vo = new ProductOperationsVO();
            BeanUtil.copyProperties(record, vo);
    
            return vo;
        }).collect(Collectors.toList());

        pageVOResult.setRecords(vos);
        return pageVOResult;
    }

    @Override
    public Page<ProductOperations> page(ProductOperationsQueryReq queryReq) {
        Page<ProductOperations> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        LambdaQueryWrapper<ProductOperations> queryWrapper = getLambdaQueryWrapper(queryReq);
        page(page, queryWrapper);
        return page;
    }

    @Override
    public List<ProductOperations> listByQueryReq(ProductOperationsQueryReq queryReq) {
        LambdaQueryWrapper<ProductOperations> queryWrapper = getLambdaQueryWrapper(queryReq);
        List<ProductOperations> listByQueryReq = list(queryWrapper);
        return listByQueryReq;
    }

    @Override
    public Map<Long, ProductOperations> getIdEntityMap(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new HashMap<>();
        }

        LambdaQueryWrapper<ProductOperations> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(ProductOperations::getId, ids);
        List<ProductOperations> entities = list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return new HashMap<>();
        }

        return entities.stream().collect(Collectors.toMap(ProductOperations::getId, Function.identity(), (v1, v2) -> v1));
    }

    @Override
    public ProductOperations getOneByQueryReq(ProductOperationsQueryReq queryReq) {
        LambdaQueryWrapper<ProductOperations> queryWrapper = getLambdaQueryWrapper(queryReq);
        queryWrapper.last("LIMIT 1");

        List<ProductOperations> listByQueryReq = list(queryWrapper);
        if (CollectionUtils.isEmpty(listByQueryReq)) {
            return null;
        }

        return listByQueryReq.get(0);
    }

    private LambdaQueryWrapper<ProductOperations> getLambdaQueryWrapper(ProductOperationsQueryReq queryReq) {
        LambdaQueryWrapper<ProductOperations> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(Objects.nonNull(queryReq.getId()), ProductOperations::getId, queryReq.getId());
        queryWrapper.eq(Objects.nonNull(queryReq.getAdminId()), ProductOperations::getAdminId, queryReq.getAdminId());
        queryWrapper.eq(Objects.nonNull(queryReq.getProductId()), ProductOperations::getProductId, queryReq.getProductId());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getOperation()), ProductOperations::getOperation, queryReq.getOperation());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getOperationLike()), ProductOperations::getOperation, queryReq.getOperationLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getCreatedAt()), ProductOperations::getCreatedAt, queryReq.getCreatedAt());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getDescription()), ProductOperations::getDescription, queryReq.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getDescriptionLike()), ProductOperations::getDescription, queryReq.getDescriptionLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getValidFlag()), ProductOperations::getValidFlag, queryReq.getValidFlag());

        return queryWrapper;
    }

}
