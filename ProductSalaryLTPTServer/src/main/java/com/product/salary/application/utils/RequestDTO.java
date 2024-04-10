package com.product.salary.application.utils;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class RequestDTO implements Serializable {
    /**
     * requestType: loại request
     */
    private String requestType;
    /**
     * request: nội dung request
     */
    private String request;
    /**
     * data: dữ liệu
     */
    private Object data;
}
