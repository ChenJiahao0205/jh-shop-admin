package pers.jhshop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.admin.model.req.PermissionsCreateReq;
import pers.jhshop.admin.model.req.PermissionsQueryReq;
import pers.jhshop.admin.model.req.PermissionsUpdateReq;
import pers.jhshop.admin.model.vo.PermissionsVO;
import pers.jhshop.admin.model.entity.Permissions;
import pers.jhshop.admin.mapper.PermissionsMapper;
import pers.jhshop.admin.service.IPermissionsService;
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
 * 权限表 服务实现类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBiz(PermissionsCreateReq createReq) {


        Permissions entity = new Permissions();
        BeanUtil.copyProperties(createReq, entity);

        boolean insertResult = entity.insert();

        if (!insertResult) {
            throw new ServiceException("数据插入失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBiz(PermissionsUpdateReq updateReq) {

        // 1.入参有效性判断
        if (Objects.isNull(updateReq.getId())) {
            throw new ServiceException("id不能为空");
        }

        Permissions entity = getById(updateReq.getId());
        if (Objects.isNull(entity)) {
            throw new ServiceException("权限表不存在");
        }

        // 2.重复性判断
        Permissions entityToUpdate = new Permissions();
        BeanUtil.copyProperties(updateReq, entityToUpdate);

        boolean updateResult = entityToUpdate.updateById();
        if (!updateResult) {
            throw new ServiceException("数据更新失败");
        }
    }

    @Override
    public PermissionsVO getByIdBiz(Long id) {
        // 1.入参有效性判断
        if (Objects.isNull(id)) {
            throw new ServiceException("id不能为空");
        }

        // 2.存在性判断
        Permissions entity = getById(id);
        if (Objects.isNull(entity)) {
            throw new ServiceException("权限表不存在");
        }

        PermissionsVO vo = new PermissionsVO();
        BeanUtil.copyProperties(entity, vo);

            return vo;
    }

    @Override
    public Page<PermissionsVO> pageBiz(PermissionsQueryReq queryReq) {
        Page<Permissions> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        page.addOrder(OrderItem.desc("id"));

        LambdaQueryWrapper<Permissions> queryWrapper = getLambdaQueryWrapper(queryReq);

        page(page, queryWrapper);

        Page<PermissionsVO> pageVOResult = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<Permissions> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return pageVOResult;
        }

        List<PermissionsVO> vos = records.stream().map(record -> {
            PermissionsVO vo = new PermissionsVO();
            BeanUtil.copyProperties(record, vo);
    
            return vo;
        }).collect(Collectors.toList());

        pageVOResult.setRecords(vos);
        return pageVOResult;
    }

    @Override
    public Page<Permissions> page(PermissionsQueryReq queryReq) {
        Page<Permissions> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        LambdaQueryWrapper<Permissions> queryWrapper = getLambdaQueryWrapper(queryReq);
        page(page, queryWrapper);
        return page;
    }

    @Override
    public List<Permissions> listByQueryReq(PermissionsQueryReq queryReq) {
        LambdaQueryWrapper<Permissions> queryWrapper = getLambdaQueryWrapper(queryReq);
        List<Permissions> listByQueryReq = list(queryWrapper);
        return listByQueryReq;
    }

    @Override
    public Map<Long, Permissions> getIdEntityMap(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new HashMap<>();
        }

        LambdaQueryWrapper<Permissions> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Permissions::getId, ids);
        List<Permissions> entities = list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return new HashMap<>();
        }

        return entities.stream().collect(Collectors.toMap(Permissions::getId, Function.identity(), (v1, v2) -> v1));
    }

    @Override
    public Permissions getOneByQueryReq(PermissionsQueryReq queryReq) {
        LambdaQueryWrapper<Permissions> queryWrapper = getLambdaQueryWrapper(queryReq);
        queryWrapper.last("LIMIT 1");

        List<Permissions> listByQueryReq = list(queryWrapper);
        if (CollectionUtils.isEmpty(listByQueryReq)) {
            return null;
        }

        return listByQueryReq.get(0);
    }

    private LambdaQueryWrapper<Permissions> getLambdaQueryWrapper(PermissionsQueryReq queryReq) {
        LambdaQueryWrapper<Permissions> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(Objects.nonNull(queryReq.getId()), Permissions::getId, queryReq.getId());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getRole()), Permissions::getRole, queryReq.getRole());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getRoleLike()), Permissions::getRole, queryReq.getRoleLike());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getPermission()), Permissions::getPermission, queryReq.getPermission());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getPermissionLike()), Permissions::getPermission, queryReq.getPermissionLike());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getDescription()), Permissions::getDescription, queryReq.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getDescriptionLike()), Permissions::getDescription, queryReq.getDescriptionLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getValidFlag()), Permissions::getValidFlag, queryReq.getValidFlag());

        return queryWrapper;
    }

}
