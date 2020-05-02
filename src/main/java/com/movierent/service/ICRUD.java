package com.movierent.service;

import java.util.List;

public interface ICRUD<T, V> {

	List<T> list();

	T listById(V obj);

	T save(T obj);

	T edit(T obj);

	void delete(V id);
}
