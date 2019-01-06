package org.powellmakerspace.sign_on_server.repositories;

import org.powellmakerspace.sign_on_server.models.Member;
import org.powellmakerspace.sign_on_server.models.membership.second_attempt.AccessMechanism;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Crud repository for members.  This interface is implemented by Spring.
 * Methods to find members are implemented by Spring.
 */
public interface MemberRepository extends CrudRepository<Member, Long> {

    Optional<Member> findByMemberName(String memberName);

    Iterable<Member> findMembersByMemberNameLike(String filter);

    Iterable<Member> findMembersByMembershipType(AccessMechanism membershipType);

    Iterable<Member> findMembersByMemberNameLikeAndMembershipType(String memberName, AccessMechanism membershipType);
}
