package com.kirilanastasoff.ars.airplane.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kirilanastasoff.ars.model.airplane.Stop;
import com.kirilanastasoff.ars.repository.airplane.StopRepository;

@Service
public class StopServiceImpl implements StopService {
	@Autowired
	private StopRepository stopRepository;
	
	@Override
	public Set<Stop> getAllStops() {
		return (Set<Stop>) stopRepository.findAll();
	}

	@Override
	public Stop getStopByCode(String stopCode) {
		Optional<Stop> stop = Optional.ofNullable(stopRepository.findByCode(stopCode));
		if(stop != null )
		{
			return stop.get();
		}
		return null;
	}
}
