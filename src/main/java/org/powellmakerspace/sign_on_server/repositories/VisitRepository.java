package org.powellmakerspace.sign_on_server.repositories;

import org.powellmakerspace.sign_on_server.models.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {

    Iterable<Visit> findAllVisitsByDepartureTimeIsNull();

    Iterable<Visit> findAllVisitsByArrivalTimeBetween(long startDate, long endDate);

}
