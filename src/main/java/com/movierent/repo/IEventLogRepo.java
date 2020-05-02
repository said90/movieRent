package com.movierent.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movierent.model.EventLog;

public interface IEventLogRepo extends JpaRepository<EventLog, Integer> {

}
