package org.powellmakerspace.SignOnServer.repositories;

import org.powellmakerspace.SignOnServer.models.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {

    Iterable<Visit> findAllVisitsByDepartureTimeIsNull();

    Iterable<Visit> findAllVisitsByArrivalTimeBetween(long startDate, long endDate);

}
