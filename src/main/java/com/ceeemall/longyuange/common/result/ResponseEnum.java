package com.ceeemall.longyuange.common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author llp
 * @date 2021/6/21 15:09
 */
@Getter
@AllArgsConstructor
@ToString
public enum ResponseEnum {
    SUCCESS(200,"操作成功"),
    ERROR(500,"服务内部错误"),
    BAD_SQL_GRAMMAR_ERROR(-101,"Sql语法错误"),
    BORROW_AMOUNT_NULL_ERROR(-201,"借款恶毒不能为0"),
    SERVLET_ERROR(-102,"servlet请求异常")
    ;


    // 响应状态码
    private Integer code;
    // 响应信息
    private String message;
}
