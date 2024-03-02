package com.lhy.spzx.model.vo.common;

import lombok.Getter;

@Getter // �ṩ��ȡ����ֵ��getter����
public enum ResultCodeEnum {

    SUCCESS(200 , "�����ɹ�") ,
    LOGIN_ERROR(201 , "�û��������������"),
    VALIDATECODE_ERROR(202 , "��֤�����") ,
    LOGIN_AUTH(208 , "�û�δ��¼"),
    USER_NAME_IS_EXISTS(209 , "�û����Ѿ�����"),
    SYSTEM_ERROR(9999 , "�����������������Ժ�����"),
    NODE_ERROR( 217, "�ýڵ������ӽڵ㣬������ɾ��"),
    DATA_ERROR(204, "�����쳣"),
    ACCOUNT_STOP( 216, "�˺���ͣ��"),

    STOCK_LESS( 219, "��治��"),

    ;

    private Integer code ;      // ҵ��״̬��
    private String message ;    // ��Ӧ��Ϣ

    private ResultCodeEnum(Integer code , String message) {
        this.code = code ;
        this.message = message ;
    }

}
