package com.example.produtoisapi.userManagement.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchRequest<T> {
	@Valid
	@NotNull
	Page page;

	@Valid
	@NotNull
	T query;
}
