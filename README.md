
Demo On YouTube
https://youtu.be/NLcB28so_9c


# Pre-work - ToDoApp

ToDoApp is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: Shi Wang

Time spent: 8 hours spent in total

## User Stories

The following **required** functionality is completed:

*  User can **successfully add and remove items** from the todo list
*  User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
*  User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

*  Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
*  Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [ ] Add support for completion due dates for todo items (and display within listview item)
*  Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [ ] Add support for selecting the priority of each todo item (and display in listview item)
* [ ] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* Use DBFlow to do SQLite operations
* Use ProcessModelTransaction

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/HpNQuqY.gifv' title='GIF Walkthrough' width='' alt='GIF Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** It is very convenient to use, especially for beginners, with powerful liberies as well as dev docs.

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** An adapter is a bridge between data and view, it makes the data displaying more handy.The Adapter uses the convertView as a way of recycling old View objects that are no longer being used. In this way, the ListView can send the Adapter old, "recycled" view objects that are no longer being displayed instead of instantiating an entirely new object each time the Adapter wants to display a new list item.

## Notes

I have 0 experience with Android before, I start this app from scratch. It was difficult at the very beginning,
but gradually adding new features become easier after finish the basic code structure, really learnt a lot!

## License

    Copyright Shi Wang

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
