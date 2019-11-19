package com.demo.api.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 返回参数
 * </p>
 *
 * @author wangliang
 * @since 2017/12/1
 */
@Data
@Builder
public class BaseResponse<T>{
    /**
     * 返回编码
     */
    private Integer code;
    /**
     * 返回描述
     */
    private String message;
    /**
     * 操作数据信息(请求结果成功返回)
     */
    private T dataInfo;
}
