package pers.jhshop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.admin.model.req.AdminsCreateReq;
import pers.jhshop.admin.model.req.AdminsQueryReq;
import pers.jhshop.admin.model.req.AdminsUpdateReq;
import pers.jhshop.admin.model.vo.AdminsVO;
import pers.jhshop.admin.model.entity.Admins;
import pers.jhshop.admin.mapper.AdminsMapper;
import pers.jhshop.admin.service.IAdminsService;
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
 * 管理员表 服务实现类
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdminsServiceImpl extends ServiceImpl<AdminsMapper, Admins> implements IAdminsService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBiz(AdminsCreateReq createReq) {


        Admins entity = new Admins();
        BeanUtil.copyProperties(createReq, entity);

        boolean insertResult = entity.insert();

        if (!insertResult) {
            throw new ServiceException("数据插入失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBiz(AdminsUpdateReq updateReq) {

        // 1.入参有效性判断
        if (Objects.isNull(updateReq.getId())) {
            throw new ServiceException("id不能为空");
        }

        Admins entity = getById(updateReq.getId());
        if (Objects.isNull(entity)) {
            throw new ServiceException("管理员表不存在");
        }

        // 2.重复性判断
        Admins entityToUpdate = new Admins();
        BeanUtil.copyProperties(updateReq, entityToUpdate);

        boolean updateResult = entityToUpdate.updateById();
        if (!updateResult) {
            throw new ServiceException("数据更新失败");
        }
    }

    @Override
    public AdminsVO getByIdBiz(Long id) {
        // 1.入参有效性判断
        if (Objects.isNull(id)) {
            throw new ServiceException("id不能为空");
        }

        // 2.存在性判断
        Admins entity = getById(id);
        if (Objects.isNull(entity)) {
            throw new ServiceException("管理员表不存在");
        }

        AdminsVO vo = new AdminsVO();
        BeanUtil.copyProperties(entity, vo);

            return vo;
    }

    @Override
    public Page<AdminsVO> pageBiz(AdminsQueryReq queryReq) {
        Page<Admins> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        page.addOrder(OrderItem.desc("id"));

        LambdaQueryWrapper<Admins> queryWrapper = getLambdaQueryWrapper(queryReq);

        page(page, queryWrapper);

        Page<AdminsVO> pageVOResult = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<Admins> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return pageVOResult;
        }

        List<AdminsVO> vos = records.stream().map(record -> {
            AdminsVO vo = new AdminsVO();
            BeanUtil.copyProperties(record, vo);
    
            return vo;
        }).collect(Collectors.toList());

        pageVOResult.setRecords(vos);
        return pageVOResult;
    }

    @Override
    public Page<Admins> page(AdminsQueryReq queryReq) {
        Page<Admins> page = new Page<>(queryReq.getCurrent(), queryReq.getSize());
        LambdaQueryWrapper<Admins> queryWrapper = getLambdaQueryWrapper(queryReq);
        page(page, queryWrapper);
        return page;
    }

    @Override
    public List<Admins> listByQueryReq(AdminsQueryReq queryReq) {
        LambdaQueryWrapper<Admins> queryWrapper = getLambdaQueryWrapper(queryReq);
        List<Admins> listByQueryReq = list(queryWrapper);
        return listByQueryReq;
    }

    @Override
    public Map<Long, Admins> getIdEntityMap(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new HashMap<>();
        }

        LambdaQueryWrapper<Admins> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(Admins::getId, ids);
        List<Admins> entities = list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return new HashMap<>();
        }

        return entities.stream().collect(Collectors.toMap(Admins::getId, Function.identity(), (v1, v2) -> v1));
    }

    @Override
    public Admins getOneByQueryReq(AdminsQueryReq queryReq) {
        LambdaQueryWrapper<Admins> queryWrapper = getLambdaQueryWrapper(queryReq);
        queryWrapper.last("LIMIT 1");

        List<Admins> listByQueryReq = list(queryWrapper);
        if (CollectionUtils.isEmpty(listByQueryReq)) {
            return null;
        }

        return listByQueryReq.get(0);
    }

    private LambdaQueryWrapper<Admins> getLambdaQueryWrapper(AdminsQueryReq queryReq) {
        LambdaQueryWrapper<Admins> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.eq(Objects.nonNull(queryReq.getId()), Admins::getId, queryReq.getId());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getUsername()), Admins::getUsername, queryReq.getUsername());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getUsernameLike()), Admins::getUsername, queryReq.getUsernameLike());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getPassword()), Admins::getPassword, queryReq.getPassword());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getPasswordLike()), Admins::getPassword, queryReq.getPasswordLike());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getRole()), Admins::getRole, queryReq.getRole());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getRoleLike()), Admins::getRole, queryReq.getRoleLike());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getEmail()), Admins::getEmail, queryReq.getEmail());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getEmailLike()), Admins::getEmail, queryReq.getEmailLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getCreatedAt()), Admins::getCreatedAt, queryReq.getCreatedAt());
        queryWrapper.eq(Objects.nonNull(queryReq.getUpdatedAt()), Admins::getUpdatedAt, queryReq.getUpdatedAt());
        queryWrapper.eq(StringUtils.isNotBlank(queryReq.getDescription()), Admins::getDescription, queryReq.getDescription());
        queryWrapper.like(StringUtils.isNotBlank(queryReq.getDescriptionLike()), Admins::getDescription, queryReq.getDescriptionLike());
        queryWrapper.eq(Objects.nonNull(queryReq.getValidFlag()), Admins::getValidFlag, queryReq.getValidFlag());

        return queryWrapper;
    }

}
