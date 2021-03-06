# Tide Forecast App for Vancouver Coast

## An app predicting tidal elevations for the given time near Vancouver / Strait of Georgia.

Some **Cool** features:

- Accurately predict tidal elevation of any given time at Vancouver coast for within the future 10 years!
- Beautiful plots of a tide chart +/- 24 hours of the given time.
- Possible extension to the other location in the Strait of Georgia, and presumably any coastal ocean of the world!

**Who** is going to love it?

- People enjoying sports the ocean coast: surfing, kayaking, ocean swimming, sailing...
- Commercial/Recreational ferry/boat/ship driver
- Scientist doing nearshore research
- Seals seeking food in tidal zone?!
- ...

**Explanations**

In theory, tide in the nature is mainly caused by the gravity attraction of moon and sun. Since the relative location
between earth, moon and soon can be reliably predicted, then the ideal tidal elevation of a certain place is supposed to
be calculated. And a bit more than that, earlier research found that tide is actually just a combination of hundreds (or
more) waves with known frequencies
(tidal constituents). As soon as we know the phase angle and amplitude of each tidal constituent, then we can easily
predict of tide with a very good accuracy.

However, in the real life, tidal prediction is a bit more complicated than that, since the local tidal can also be
largely dependent on geographical structures like shorelines and bathymetries. Luckily, based on the excellent records
of tidal elevations worldwide, we have been able to calculate these necessary parameters to derive tidal elevations for
the most coastal area in the world, and then a good prediction can be achieved by this calculation and slightly adjusted
due to extreme weather conditions.

What's more, a correct prediction of tide is really important for commercial shipping, environment maintaining, coastal
marine life and recreational purpose.

In my app, I will combine my background in oceanography and knowledge of program design in CPSC 210, to ensure my app of
solid scientific foundation, good coding style and straightforward user interface.

## User Stories

* As a user, I want to type in a time, and get the tidal elevation at that time and corresponding tidal chart of the day
* As a user, I want to save the search to MyFavoriteSearch
* As a user, I want to review all my saved TideSearch
* As a user, I want to delete some of my saved TideSearch
* As a user, I want to be able to save my favorite TideSearch list to a file
* As a user, I want to be able to load my saved favorite TideSearch list from a file



## Phase 4: Task 2
* Map interface was used
* in my model package, FavoriteSearch class, I used a HashMap as a field to map my SearchKey (a string) and 
TideCalculate (a class containing all the necessary calculation procedure of a tidal analysis). The field is
  named as favoriteList, and it can avoid saving duplicate searches into my database. 
  

## Phase 4: Task 3
* Reflection:

  
* The cohesion of my design is not good enough, I should split some of my big Class into small classes, and each of 
them should be focused on a single task. For example, my TideGUI class are having too many functions about running
  the app instead of just focusing on GUI part, I should separate it in to a TideGUI and TideRun class. 

* In my TideGUI class, I should also separate my titlePanel, buttonPanel and messagePanel into different classes,
  and build a TidePanel interface or super-class, so that I can put all my panels together and reduce some duplicated
  codes.
  
* In my ui package, I should design a Window abstract-class or interface, so that I can put some common behaviour
  of my InputWindow class and ReviewWindow class into it, which may reduce some replicated codes.

* For the same 
  reason, my TideSearch function is too clumsy and contains a lot of calculation functions, I should just let 
  TideCalculate class handle all the calculations.


