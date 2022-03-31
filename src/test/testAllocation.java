package src.test;

import java.util.LinkedHashMap;

import src.main.allocator.Allocator;

public class testAllocation {
    Allocator testSeatAllocator = new Allocator();
    
    public void runTests() {
        testZeroSeatReservation();
        testInsufficientSeats();
        testPrioritySeating();
        testConsecutiveSeating();
        testCustomerSafety();
        testCustomerGroupingForGreaterThanTwenty();
        testCustomerGrouping();
    }

    private void testZeroSeatReservation(){
        int result = testSeatAllocator.handleReq("R000 0", 0);
        assert result == -1 : "Zero reservation case handled correctly" ;
    }

    private void testInsufficientSeats() {
        int result = testSeatAllocator.handleReq("R000 210", 0);
        assert result == 1 : "Unable to allocate seats due to insufficient space" ;
    }

    private void testCustomerSafety() {
        testSeatAllocator = new Allocator();
        testSeatAllocator.handleReq("R000 2", 0);
        testSeatAllocator.handleReq("R001 3", 0);
        LinkedHashMap<String, String> reservations = testSeatAllocator.getReservations();
        String result = reservations.get("R000") + ", " + reservations.get("R001");
        assert result.equals("D1, D2, D6, D7, D8") : "Safety distance not maintained";
    }

    private void testPrioritySeating(){
        testSeatAllocator = new Allocator();
        testSeatAllocator.handleReq("R000 4", 0);
        String result = testSeatAllocator.getReservations().get("R000");
        assert result.equals("D1, D2, D3, D4") : "Reservations not started from Dth row";
    }
    private void testConsecutiveSeating(){
        testSeatAllocator = new Allocator();
        testSeatAllocator.handleReq("R000 3", 0);
        String result = testSeatAllocator.getReservations().get("R000");
        assert result.equals("D1, D2, D3") : "Seating is not consecutive";
    }

    private void testCustomerGrouping() {
        testSeatAllocator = new Allocator();
        testSeatAllocator.handleReq("R000 8", 0);
        testSeatAllocator.handleReq("R001 10", 0);
        String result1 = testSeatAllocator.getReservations().get("R000").substring(0, 2);
        String result2 = testSeatAllocator.getReservations().get("R001").substring(0, 2); 
        assert result1.equals("D1") && result2.equals("E1") : "Grouping was not performed for new group";
    }
    
    private void testCustomerGroupingForGreaterThanTwenty() {
        int result = testSeatAllocator.handleReq("R000 25", 0);
        assert result == 0 : "Failure in accomodating a group larger than a row";            
    }

}
