package com.rfc.ceres.common;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
class PageInfo {

	@NonNull private int totalPages;
	@NonNull private int pageNumber;
	@NonNull private long totalElements;

}
