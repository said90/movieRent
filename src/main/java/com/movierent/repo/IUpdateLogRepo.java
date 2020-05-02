package com.movierent.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movierent.model.UpdateLog;

public interface IUpdateLogRepo  extends JpaRepository<UpdateLog, Integer>{

}
