const movie = {
    movieName: "RRR",
    movieLanguage: "Telugu",
    imdbRating: 8.0
};

const { movieName, movieLanguage, imdbRating } = movie;

document.getElementById("movieDetails").textContent = 
    `Name: ${movieName}, Language: ${movieLanguage}, Rating: ${imdbRating}`;