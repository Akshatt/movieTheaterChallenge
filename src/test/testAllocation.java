package src.test;

import src.allocator.Allocator;

public class testAllocation {
    Allocator testSeatAllocator = new Allocator();
    
    public void runTests() {
        testZeroSeatReservation();
        testInsufficientSeats();
    }

    private void testZeroSeatReservation(){
        int result = testSeatAllocator.handleReq("R000 0");
        assert (result == -1) : "Zero reservation case handled correctly." ;
    }

    private void testInsufficientSeats() {
        int result = testSeatAllocator.handleReq("R000 210");
        assert (result == 1) : "Unable to allocate seats due to insufficient space" ;
    }

    private void testCustomerSafety() {

    }

    private void testPrioritySeating(){

    }

    private void testCustomerGroupingForLessThanTwenty() {

    }
    
    private void testCustomerGroupingForGreaterThanTwenty() {

    }

    private void testCustomerGroupingForTwenty() {

    }
}
