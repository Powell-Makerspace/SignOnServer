package org.powellmakerspace.sign_on_server.repositories;

import org.powellmakerspace.sign_on_server.models.access_mechanism.AccessMechanism;
import org.powellmakerspace.sign_on_server.models.access_mechanism.membership.FamilyMembership;
import org.powellmakerspace.sign_on_server.models.access_mechanism.membership.IndividualMembership;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccessMechanismRepository extends CrudRepository<AccessMechanism, Long> {

    @Query("SELECT m FROM AccessMechanism m WHERE TYPE(m) = IndividualMembership")
    Iterable<IndividualMembership> findAllIndividualMemberships();

    @Query("SELECT m FROM AccessMechanism m WHERE TYPE(m) = FamilyMembership")
    Iterable<FamilyMembership> findAllFamilyMemberships();

}
