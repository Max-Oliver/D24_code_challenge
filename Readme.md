# Directa24 Back-End Developer Challenge 

In this challenge, the REST API contains information about a collection of movie released after the year 2010, directed by acclaimed directors.  
Given the threshold value, the goal is to use the API to get the list of the names of the directors with most movies directed. Specifically, the list of names of directors with movie count strictly greater than the given threshold.   
The list of names must be returned in alphabetical order.  

To access the collection of users perform HTTP GET request to:
https://directa24-movies.wiremockapi.cloud/api/movies/search?page=<pageNumber>
where <pageNumber> is an integer denoting the page of the results to return.

For example, GET request to:
https://directa24-movies.wiremockapi.cloud/api/movies/search?page=2
will return the second page of the collection of movies. Pages are numbered from 1, so in order to access the first page, you need to ask for page number 1.
The response to such request is a JSON with the following 5 fields:

- page: The current page of the results  
- per_page: The maximum number of movies returned per page.  
- total: The total number of movies on all pages of the result.  
- total_pages: The total number of pages with results.  
- data: An array of objects containing movies returned on the requested page  

Each movie record has the following schema:  
- Title: title of the movie  
- Year: year the movie was released  
- Rated: movie rating  
- Released: movie release date  
- Runtime: movie duration time in minutes  
- Genre: move genre  
- Director: movie director  
- Writer: movie writers  
- Actors: movie actors  

## Function Description  
  
Complete the function 

    List<String> getDirectors(int threshold)

getDirectors has the following parameter:
- threshold: integer denoting the threshold value for the number movies a person has directed

The function must return a list of strings denoting the name of the directors whose number of movies directed is strictly greater than the given threshold. 
The directors name in the list must be ordered in alphabetical order.


#### Sample Input For Custom Testing
    4  
#### Sample Output
    Martin Scorsese
    Woody Allen
    
The threshold value is 4, so the result must contain directors names with more than 4 movies directed.   
There are 2 such directors and names in the alphabetical order listed in Sample Output.

# Proposal Solution:

#### Call to the API:
<img width="1021" alt="image" src="https://github.com/Max-Oliver/D24_code_challenge/assets/37275050/ed882a21-0e2b-4e7a-823c-77f5e6ed1a93">

#### Call to the Endpoint by Umbral:
<img width="1021" alt="image" src="https://github.com/Max-Oliver/D24_code_challenge/assets/37275050/cca5ef54-a9bb-40c8-bbab-43f407764c23">


#### Call to the Endpoint by Page and Umbral:
<img width="1021" alt="image" src="https://github.com/Max-Oliver/D24_code_challenge/assets/37275050/08ad3e92-ddbe-4478-943d-78c960d2086d">

## Notes:
In this path:
> src > main > resources > postman.collection > D24-challenge.json

You will see the collection exported from postman as a JSON file, you can import to postman as a file, and easely check this endpoints.
