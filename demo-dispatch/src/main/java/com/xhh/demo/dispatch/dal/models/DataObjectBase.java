package com.xhh.demo.dispatch.dal.models;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * <ul>
 *     <li>数据模型基类</li>
 *     <li>Created by tiger on 14-11-18 下午3:23</li>
 * </ul>
 */
@Getter
@Setter
public class DataObjectBase implements Serializable {

    private static final long serialVersionUID = -489318615684747263L;

    /**
     * 数据库主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 最后更新人
     */
    private String updatedBy;

    /**
     * 数据当前版本号，默认为0
     */
    private Integer version = 0;

    /**
     * @see org.apache.commons.lang3.builder.ToStringBuilder#reflectionToString(Object, org.apache.commons.lang3.builder.ToStringStyle)
     * @see org.apache.commons.lang3.builder.ToStringStyle#SHORT_PREFIX_STYLE
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * @see org.apache.commons.lang3.builder.EqualsBuilder#
     * reflectionEquals(Object, Object)
     */
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    /**
     * @see org.apache.commons.lang3.builder.HashCodeBuilder#reflectionHashCode
     */
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
