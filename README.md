# Movie Theater Seating Challenge

## **Problem Statement**

The problem requires us to design and implement an algorithm to reserve seat requests for a group of customers maximizing their satisfaction and safety.  

## **Assumptions**

We try to generate the requirements according to what is being asked. 
_Customer Satisfaction_ can be broken down into following assumptions: 

1. According to this [article](https://www.hollywood.com/movie--theaters/movie-theater-seats-which-are-the-best-60824411#:~:text=While%20the%20back%20may%20be,to%20two%2Dthirds%20back.%E2%80%9D) from Hollywood.com, the best viewing experience for the audience is around the center rows followed by the back rows and then the front rows. 
2. So our seating priority will be from center row to last row and then from (center - 1) to 1st row. 
3. And aisle or middle seats are equally preferred so we will start allocating from left to right.
4. Audience in groups want to enjoy the movie seated together if possible.

_Customer Safety_ can be followed by keeping the following two assumptions in mind:

5. There has to be a buffer of 3 seats between two different groups of audience in the same row.
5. Customers in different rows are mutually safe. 

6. The reservation requests are sequentially ordered.
7. If the requested number of seats exceeds the vacant seats then, the reservation request cannot be processed.
8. A reservation request can only be processed if the requested seats are more than 0. 

## **Algorithm**
> FOR request : requests
>>   FOR row from center to end    
>>>      Try to accomodate whole group in this row by looping across the columns.  
>>>      IF (whole group accomodated) break.        
>> IF not possible then  
>> FOR row from center to end   
>>>     Try to accomodate as much of the group in this row by looping across the columns.
>>>     IF (whole group accomodated) break.    
>> IF (whole group not accomodated)  
>> FOR row from (center - 1) to first  
>>>     Try to accomodate whole group in this row by looping across the columns.
>>>      IF (whole group accomodated) break.  
>> IF not possible then  
>> FOR row from (center - 1) to first   
>>>     Try to accomodate as much of the group in this row by looping across the columns.
>>>     IF (whole group accomodated) break.
> RETURN accomodated groups

### **Runtime and Correctness**

_Correctness_ : The algorithm covers all the cases and is self terminating depending on the input file. All the for loops have break conditions and are within a certain range. The algorithm considers all the assumptions stated.

_Runtime_ : O (n (2mk)) $\sim$ O(nmk) where,
  - n is the number of reservation requests 
  - m is the number of rows of seats 
  - k is the number of columns of seats.  

## **Steps to Run** 
1. Download and save the `src` folder in a directory.
2. Open CMD in this directory. 
3. Run commands to 
    - compile the java files:
        >javac src/main/allocator/Allocator.java src/test/testAllocation.java src/main/driver/Driver.java
    - execute the program with the input file path:
        > java src/main/driver/Driver InputFilePath
    - testing the program:
        > java src/main/driver/Driver test 
    - For verbose output: 
        > java src/main/driver/Driver InputFilePath verbose

