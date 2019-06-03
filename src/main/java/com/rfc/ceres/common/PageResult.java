package com.rfc.ceres.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
public class PageResult<T> implements Serializable {

	private List<T> content;
	private PageInfo pageInfo;


	public PageResult(Page<T> page) {
		this.content = page.getContent();
		this.pageInfo = extractPageInfo(page);
	}

	private PageInfo extractPageInfo(Page<T> page) {
		return new PageInfo(page.getTotalPages(), page.getPageable().getPageNumber(), page.getTotalElements());
	}
}
