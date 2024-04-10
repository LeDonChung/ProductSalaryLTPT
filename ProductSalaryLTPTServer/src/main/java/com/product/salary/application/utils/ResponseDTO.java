package com.product.salary.application.utils;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class ResponseDTO implements Serializable {
    /**
     *  responseType: loại response
     */
    private String responseType;
    /**
     * response: nội dung response
     */
    private String response;
    /**
     * data: dữ liệu
     */
    private Object data;
}
