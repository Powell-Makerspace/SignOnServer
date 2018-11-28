package org.powellmakerspace.SignOnServer.repositories;

import org.powellmakerspace.SignOnServer.models.Member;
import org.powellmakerspace.SignOnServer.models.enums.MembershipType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Crud repository for members.  This interface is implemented by Spring.
 * Methods to find members are implemented by Spring.
 */
public interface MemberRepository extends CrudRepository<Member, Long> {

    Optional<Member> findByMemberName(String memberName);

    Iterable<Member> findMembersByMemberNameLike(String filter);

    Iterable<Member> findMembersByMembershipType(MembershipType membershipType);

    Iterable<Member> findMembersByMemberNameLikeAndMembershipType(String memberName, MembershipType membershipType);
}
