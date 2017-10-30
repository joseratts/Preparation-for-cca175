Problem Statement


1. Create a hive meta store database named problem6 and import all tables from mysql retail_db database into hive meta store.  

2. On spark shell use data available on meta store as source and perform step 3,4,5 and 6. [this proves your ability to use meta store as a source]  

3. Rank products within department by price and order by department ascending and rank descending [this proves you can produce ranked and sorted data on joined data sets]  

4. Find top 10 customers with most unique product purchases. if more than one customer has the same number of product purchases then the customer with the lowest customer_id will take precedence [this proves you can produce aggregate statistics on joined datasets]  

5. On dataset from step 3, apply filter such that only products less than 100 are extracted [this proves you can use subqueries and also filter data]  

5. On dataset from step 4, extract details of products purchased by top 10 customers which are priced at less than 100 USD per unit [this proves you can use subqueries and also filter data]  

7. Store the result of 5 and 6 in new meta store tables within hive. [this proves your ability to use metastore as a sink]  
