# online-store

This project is based off the case study provided, The core cases were implemented and model classes are constructed around the API calls. 
Retrofit was used to create various HTTP API calls and to handle the responses, a JSON converter is used to parse these responses and convert them into the model classes. The JSON in this instance was converted via the GSON Library.
The main Activity handles all the various HTTP calls via retrofit and many of the onclick functionality found in the fragment interfaces.

Inside the controllers folder the core fragments and Activities that make up the main buisness logic can be found as well as a Global Activity class to keep a reference to the varius product collections. 

Inside the models folder the responses from the server and API calls can be found. 

The view folder contains the the main view adapters needed to display the various models. 
