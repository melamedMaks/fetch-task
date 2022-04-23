# fetch-task
Fetch hiring task

The task completed according to the requirements:

1. Retrieve the data from https://fetch-hiring.s3.amazonaws.com/hiring.json.
2. Display all the items grouped by "listId"
3. Sort the results first by "listId" then by "name" when displaying.
4. Filter out any items where "name" is blank or null.

The app displays presenter screen with greeting message and animated arrow down icon that calls to press the button "LOAD" below.
After button click, the app navigates to the next screen while loading the data from API and showing a progress bar.
Loaded data presented by scrollable up/down cards grouped by list id that displays detailed item lists within scrollable left/right cards.
The user is able to swipe down and refresh the data or press back button on his device for previous screen.

The app is written in Kotlin from scratch and implements the following:

* Basic Activity Template with two fragments
* MVVM pattern
* Retrofit2
* RecyclerView
* SwipeRefreshLayout
* Material indeterminate progress-bar
* ObjectAnimator
* Junit 4 and Truth (testing)
* Custom launcher icon
