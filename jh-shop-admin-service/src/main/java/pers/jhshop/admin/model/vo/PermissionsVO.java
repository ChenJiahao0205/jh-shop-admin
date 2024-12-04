package pers.jhshop.admin.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

import pers.jhshop.common.entity.BaseVo;

/**
 * <p>
 * 权限表VO
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PermissionsVO", description = "权限表列表展示VO")
public class PermissionsVO extends BaseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限ID，主键")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String role;

    @ApiModelProperty(value = "权限描述")
    private String permission;

    @ApiModelProperty(value = "描述")
    private String description;

}
