package org.powellmakerspace.SignOnServer;

import com.github.javafaker.Faker;
import org.powellmakerspace.SignOnServer.models.Member;
import org.powellmakerspace.SignOnServer.models.Visit;
import org.powellmakerspace.SignOnServer.models.enums.MembershipType;
import org.powellmakerspace.SignOnServer.models.enums.VisitPurpose;
import org.powellmakerspace.SignOnServer.services.MemberService;
import org.powellmakerspace.SignOnServer.services.VisitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SignOnServerApplication {

	private MembershipType[] membershipTypes = {MembershipType.INDIVIDUAL,
			MembershipType.FAMILY, MembershipType.LIBRARY_PASS, MembershipType.NONMEMBER,
			MembershipType.NORTHWEST_COLLEGE, MembershipType.PUNCH_PASS, MembershipType.VISTA
	};

	private VisitPurpose[] visitPurposes = {VisitPurpose.CLASS, VisitPurpose.TEACH,
			VisitPurpose.VOLUNTEER, VisitPurpose.WORK_ON_PROJECT, VisitPurpose.COWORKSPACE,
			VisitPurpose.COMMERCIAL_KITCHEN, VisitPurpose.VISTA
	};

	public static void main(String[] args) {
		SpringApplication.run(SignOnServerApplication.class, args);
	}


	@Bean
	public CommandLineRunner seedDatabase(MemberService memberService, VisitService visitService){
		return new CommandLineRunner(){
			@Override
			public void run(String... args) throws Exception {
				Faker faker = new Faker();

				// Create Dummy Data for Members
				for (int i = 0; i < 500; i++){
					Member member = new Member();
					member.setMemberName(faker.hobbit().character());
					member.setMembershipType(membershipTypes[faker.random().nextInt(membershipTypes.length)]);
					if (member.getMembershipType() == MembershipType.PUNCH_PASS){
						member.setPunchPasses(faker.random().nextInt(10));
					}
					else {
						member.setPunchPasses(-1);
					}

					memberService.createMember(member);
				}

//				// Create Dummy Data for Visits
				for (int i = 0; i<500; i++){
					Visit visit = new Visit();
					visit.setMember(memberService.getMember(faker.random().nextLong(500L)));
					// Random Time up to Jan. 1, 2019
					visit.setArrivalTime(faker.random().nextLong(1546300800L));
					// Random time of visit less than 1 day
					visit.setDepartureTime(visit.getArrivalTime() + faker.random().nextLong(60*60*24));
					visit.setVisitPurpose(visitPurposes[faker.random().nextInt(visitPurposes.length)]);

					visitService.createVisit(visit);
				}

			}
		};
	}
}
