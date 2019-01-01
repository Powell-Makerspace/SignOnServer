package org.powellmakerspace.SignOnServer.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.powellmakerspace.SignOnServer.models.enums.VisitPurpose;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visit_seq")
    @SequenceGenerator(name="visit_seq", sequenceName = "visit_visit_id", allocationSize = 1)
    private long visitId;

    @ManyToOne
    /*
    We want to make it such that it is not possible to have a null member call.
    For some reason, the annotation below cannot be made on a ManyToOne relation
    @Column(nullable = false)
    */
    @JsonIgnore
    private Member member;

    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private VisitPurpose visitPurpose;



    /**
     * Constructor for a visit object containing no parameters
     */
    public Visit(){
    }



    /**
     * Constructor for a visit object
     * @param member Member member who made the visit
     * @param arrivalTime long timestamp for the arrival time and date
     * @param departureTime long timestamp for the departure time and date
     * @param visitPurpose Enum description of the purpose of visit
     */
    public Visit(Member member, LocalDateTime arrivalTime, LocalDateTime departureTime, VisitPurpose visitPurpose){
        this.member = member;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.visitPurpose = visitPurpose;
    }


    /**
     * Getters and Setters for all visit attributes
     */

    public long getVisitId() {
        return visitId;
    }

    public void setVisitId(long visitId) {
        this.visitId = visitId;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public VisitPurpose getVisitPurpose() {
        return visitPurpose;
    }

    public void setVisitPurpose(VisitPurpose visitPurpose) {
        this.visitPurpose = visitPurpose;
    }
}
