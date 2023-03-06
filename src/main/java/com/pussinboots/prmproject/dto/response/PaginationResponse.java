package com.pussinboots.prmproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PaginationResponse<T> {
    private T data;
    private int totalPage;
    private long totalRow;
}
