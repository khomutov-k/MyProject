package DAO;

import Domain.Request;

import java.util.List;

public interface RequestRepository {
    int addRequest(Request Request) ; // Add Request to DB
    int deleteRequest(Request Request); // Delete Request from DB
    //TODO Edit operations

    List<Request> findAll(); // Select query to get all Requests from DB
    Request findById(long id); // Select query to get  Request by ID
}
