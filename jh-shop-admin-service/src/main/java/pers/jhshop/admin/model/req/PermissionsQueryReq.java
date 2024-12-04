package pers.jhshop.admin.model.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.admin.model.entity.Permissions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权限表查询Req
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PermissionsQueryReq", description = "权限表查询Req")
public class PermissionsQueryReq extends Page<Permissions> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限ID，主键")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String role;

    @ApiModelProperty(value = "角色名称-模糊匹配")
    private String roleLike;

    @ApiModelProperty(value = "权限描述")
    private String permission;

    @ApiModelProperty(value = "权限描述-模糊匹配")
    private String permissionLike;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "描述-模糊匹配")
    private String descriptionLike;

    @ApiModelProperty(value = "生效标志(TRUE-生效, FALSE-失效)")
    private Boolean validFlag;



}
