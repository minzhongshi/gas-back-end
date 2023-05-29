package com.shiminzhong.serviceuser.model.condition;

import lombok.Data;

@Data
public class PageQuery {

    private int pageNumber = 1;

    private int pageSize = 10;
}
