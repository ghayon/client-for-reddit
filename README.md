# client-for-reddit

## Time spent: 
    - spent somewhere between 9˜hours over the course of 3 days

## Included
    - Pull to Refresh
    - Pagination support
    - App state-preservation/restoration
    - Indicator of unread/read post
    - Dismiss Post Button
    - Dismiss All Button
    - Support split layout
    
## Did not include:
    - Animations
    - Downloadable thumbnail photo (had url parsed at model but i overpassed the available hours to do it)
    
    

* For this project I used several features from __Android Jetpack__. 
* For app architecture I used __MVVM__
* To resolve communication between activities, fragments and viewmodels I used __LiveData__ objects (for most cases)
* To populate layouts I used __Databinding__
* To make the Reddit service call I used __Retrofrit__
* To show thumbnail photos I used __Picasso__
* To save read/dismiss I used __Shared preferences__, instead I could have used __Room__ and also be able to save posts in the DB. Didn't used __Room__ to use the time elsewhere
