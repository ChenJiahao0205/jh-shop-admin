package pers.jhshop.admin.model.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import pers.jhshop.admin.model.entity.Logs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管理员操作日志表查询Req
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "LogsQueryReq", description = "管理员操作日志表查询Req")
public class LogsQueryReq extends Page<Logs> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志ID，主键")
    private Long id;

    @ApiModelProperty(value = "管理员ID，外键关联管理员表")
    private Integer adminId;

    @ApiModelProperty(value = "操作描述")
    private String action;

    @ApiModelProperty(value = "操作描述-模糊匹配")
    private String actionLike;

    @ApiModelProperty(value = "操作模块")
    private String module;

    @ApiModelProperty(value = "操作模块-模糊匹配")
    private String moduleLike;

    @ApiModelProperty(value = "操作时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "描述-模糊匹配")
    private String descriptionLike;

    @ApiModelProperty(value = "生效标志(TRUE-生效, FALSE-失效)")
    private Boolean validFlag;



}
