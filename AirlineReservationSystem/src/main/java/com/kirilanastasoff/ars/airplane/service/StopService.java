package com.kirilanastasoff.ars.airplane.service;

import java.util.Set;

import com.kirilanastasoff.ars.model.airplane.Stop;

public interface StopService {

	Set<Stop> getAllStops();

	Stop getStopByCode(String stopCode);

}
