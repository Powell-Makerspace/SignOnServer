package org.powellmakerspace.SignOnServer.models;

import org.powellmakerspace.SignOnServer.models.enums.VisitPurpose;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long visitId;
    private long memberId;
    private long arrivalTime;
    private long departureTime;
    private VisitPurpose visitPurpose;



    /**
     * Constructor for a visit object containing no parameters
     */
    public Visit(){
    }



    /**
     * Constructor for a visit object
     * @param memberId long foreign key connecting the visit to the member who made it
     * @param arrivalTime long timestamp for the arrival time and date
     * @param departureTime long timestamp for the departure time and date
     * @param visitPurpose Enum description of the purpose of visit
     */
    public Visit(long memberId, long arrivalTime, long departureTime, VisitPurpose visitPurpose){
        this.memberId = memberId;
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

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(long departureTime) {
        this.departureTime = departureTime;
    }

    public VisitPurpose getVisitPurpose() {
        return visitPurpose;
    }

    public void setVisitPurpose(VisitPurpose visitPurpose) {
        this.visitPurpose = visitPurpose;
    }
}
