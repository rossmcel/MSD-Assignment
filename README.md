# Android App Created for my Mobile Software Development Class

## Created a Movie Preferences Android app using Java for my final assignment in Mobile Software Development
<br>
This app incorporates features such as:
* CRUD operations
* Data retrieval from web API
* Dynamic data generation
* Android Map SDK


## Home Page

<h4>Home Page</h4>
<p style="text-align: center; margin: auto; width=30vw">
  <img src="https://github.com/rossmcel/MSD-Assignment/blob/eb1f1c6ab9d08bfd6f3aea7b1e05491c025ce25b/homePage.PNG" title="Home Page">
</p>
<h4>Navigation</h4>
<p style="text-align: center; margin-top: 3vh;  margin-left: 35vw; width=10vw">
  <img src="https://github.com/rossmcel/MSD-Assignment/blob/eb1f1c6ab9d08bfd6f3aea7b1e05491c025ce25b/navigation.PNG" title="Mobile Screen">
</p>
<h4>Navigation</h4>
<p style="text-align: center; margin-top: 3vh;  margin-left: 35vw; width=10vw">
  <img src="https://github.com/rossmcel/MSD-Assignment/blob/eb1f1c6ab9d08bfd6f3aea7b1e05491c025ce25b/genresPage.PNG" title="Mobile Screen">
</p>


## Detailed Class Breakdown
<br>
## Classes

### MainActivity
The entry point for the app. What it does:
	* Initialize the database/check for updates from the web api
	* Handle creation of elements used for navigation
	* Inflate the home page

### DatabaseManager & DatabaseHelper
Handle methods related to and interaction with the SQLite database
	* Called from multiple classes

### DatabaseSetup
Handles the initial insertion of data into the database


### DownloadWebPageTask
Responsible for handling the retrieval of data from the designated web api
	* Called from MainActivity
	* Updates data where matching data already exists, where data does not already exist, new data is inserted

### nav_list
Handles navigation

### HomeFragment
The Home Page
	* Inflates the home page layout
	* Populates the Genres table layout
	* Launches the Showings and Map activities through handling button clicks
	* Populates the list of the User's Top 10 movies

### GenreListFragment
Where the user adds their favourite genres
	* Populates the spinner object with the list of genres found in the database
	* Allows the user to pick genres from the list of genres in the spinner
	* Adds picked genres to the database
	* Populates the list of picked genres
	* Allows the user to delete a genre by clicking on the list item row

### MovieListFragment
Where the user can generate and see their favourite movies
	* Generates movies based on the user's favourite genres
	* Populates the list of generated movies

### MapsActivity
A Google Maps Activity that displays cinema locations
	* Cinema Locations are retrieved from the database

### MovieShowings
An Activity that displays a list of all movie showings


## What I Would Add If I Had More Time
	* Improve the algorithm that generates movies by changing it to select movies based on what other users like
	* Add more functionality to the map
	* Include images along with the movie titles
	* Add functionality to the settings button in the options icon
	* Get the genre list to auto-update when an item is deleted (similar to how genres are added)

