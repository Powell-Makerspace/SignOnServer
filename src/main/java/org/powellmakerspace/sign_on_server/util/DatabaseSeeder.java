package org.powellmakerspace.sign_on_server.util;


import com.github.javafaker.Faker;
import org.powellmakerspace.sign_on_server.config.DbSeederProperties;
import org.powellmakerspace.sign_on_server.exception.ResourceNotFoundException;
import org.powellmakerspace.sign_on_server.models.Member;
import org.powellmakerspace.sign_on_server.models.Visit;
import org.powellmakerspace.sign_on_server.models.enums.MembershipType;
import org.powellmakerspace.sign_on_server.models.enums.VisitPurpose;
import org.powellmakerspace.sign_on_server.services.MemberService;
import org.powellmakerspace.sign_on_server.services.VisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Utility for seeding the database with random testing data upon application startup
 *
 * This utility provides random data in the database.  This helps facilitate local testing by showing what the
 * application would look like and behave with large amounts of data present.
 *
 * The utility is configured through the {@link DbSeederProperties} class which is generally provided by spring. This
 * properties class contains configurations such as whether or not to run the utility at startup, how many members to
 * generate and how many visits to generate
 */
@Component
@ConditionalOnProperty("seeder.enable")
public class DatabaseSeeder implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

    private MembershipType[] membershipTypes = {
            MembershipType.INDIVIDUAL,
            MembershipType.FAMILY,
            MembershipType.LIBRARY_PASS,
            MembershipType.NONMEMBER,
            MembershipType.NORTHWEST_COLLEGE,
            MembershipType.PUNCH_PASS,
            MembershipType.VISTA
    };

    private VisitPurpose[] visitPurposes = {
            VisitPurpose.CLASS,
            VisitPurpose.TEACH,
            VisitPurpose.VOLUNTEER,
            VisitPurpose.WORK_ON_PROJECT,
            VisitPurpose.COWORKSPACE,
            VisitPurpose.COMMERCIAL_KITCHEN,
            VisitPurpose.VISTA
    };

    private DbSeederProperties dbSeederProperties;
    private MemberService memberService;
    private VisitService visitService;

    /**
     * Create a new instance of the DatabaseSeeder Utility
     *
     * @param dbSeederProperties Properties configuration used when running the database seeder
     * @param memberService The member service to use when adding new members
     * @param visitService The visit service to use when adding new visits
     */
    public DatabaseSeeder(
            DbSeederProperties dbSeederProperties,
            MemberService memberService, VisitService visitService
    ) {
        this.dbSeederProperties = dbSeederProperties;
        this.memberService = memberService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws ResourceNotFoundException {

        long systemTime = 0;
        if(logger.isInfoEnabled()){
            systemTime = System.currentTimeMillis();
            logger.info("Starting Database Seeding Utility");
        }

        Faker faker = new Faker();

        // Create Dummy Data for Members
        for (int i = 0; i < dbSeederProperties.getNumberOfMembers(); i++){
            Member member = new Member();
            member.setMemberName(faker.hobbit().character());
            member.setAccessMechanism(membershipTypes[faker.random().nextInt(membershipTypes.length)]);
            if (member.getAccessMechanism() == MembershipType.PUNCH_PASS){
                member.setPunchPasses(faker.random().nextInt(10));
            }
            else {
                member.setPunchPasses(-1);
            }

            memberService.createMember(member);
        }

        // Create Dummy Data for Visits
        for (int i = 0; i < dbSeederProperties.getNumberOfVisits(); i++){
            Visit visit = new Visit();
            visit.setMember(memberService.getMember(faker.random().nextInt(1, dbSeederProperties.getNumberOfMembers())));
            // Random Time up to Jan. 1, 2019
            LocalDateTime randomDateTime = LocalDateTime.of(
                    faker.random().nextInt(2000,2020),
                    faker.random().nextInt(1,12),
                    faker.random().nextInt(1,28),
                    faker.random().nextInt(0,23),
                    faker.random().nextInt(0,59)
            );
            visit.setArrivalTime(randomDateTime);
            // Random time of visit less than 1 day
            visit.setDepartureTime(randomDateTime
                    .plusHours(faker.random().nextInt(8))
                    .plusMinutes(faker.random().nextInt(59)));

            visit.setVisitPurpose(visitPurposes[faker.random().nextInt(visitPurposes.length)]);

            visitService.createVisit(visit);
        }

        if (logger.isInfoEnabled()){
            logger.info("Stopping Database Seeding Utility. Time: {}",
                    (System.currentTimeMillis() - systemTime)/1000.0);
        }
    }
}