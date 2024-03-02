package com.lhy.spzx.model.vo.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "��Ӧ���ʵ����")
public class Result<T> {

    //������
    @Schema(description = "ҵ��״̬��")
    private Integer code;

    //������Ϣ
    @Schema(description = "��Ӧ��Ϣ")
    private String message;

    //��������
    @Schema(description = "ҵ������")
    private T data;

    // ˽�л�����
    private Result() {}

    // ��������
    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = new Result<>();
        result.setData(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    // ͨ��ö�ٹ���Result����
    public static <T> Result build(T body , ResultCodeEnum resultCodeEnum) {
        return build(body , resultCodeEnum.getCode() , resultCodeEnum.getMessage()) ;
    }

}
