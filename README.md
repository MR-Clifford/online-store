# online-store

This project is based off the case study provided, The core cases were implemented and model classes are constructed around the API calls. 
Retrofit was used to create various HTTP API calls to handle the responses and a JSON converter is used to parse these responses and convert them into the model classes. The JSON in this instance was converted via the GSON Library.
The main Activity handles all the various HTTP calls via retrofit and all of the onclick functionality found in the fragment interfaces.

Inside the controllers folder the core fragments and Activities that make up the main buisness logic can be found as well as a Global Activity class to keep a reference to the varius product collections. 

Inside the models folder the responses from the server have been made into classes. The API calls have also been made into an interface to adhere to the retrofit schema and centralise all the API call declarations . 

The view folder contains the the main view adapters needed to display the various models. These follow the typcial android design models where the views are declared via a XML and the adpaters convert models into a easy to read view. 
