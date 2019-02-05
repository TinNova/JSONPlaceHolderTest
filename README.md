# Kotlin Android Architecture Components Sample App with Unit & Espresso Tests

### Summary
This is a sample of good practise Android Development, nothing more.

It pulls in responses from two endpoints from [JsonPlaceHolder.typicode.com](https://jsonplaceholder.typicode.com/), it zips them together using RxJava
then displays the first ten results in a RecyclerView

The Unit Tests contain a mock of the endpoints and tests if the endpoints are recognised and that the zip function works
The Espresso test checks if the RecyclerView is displayed, however what more interesting is the RxJava "IdealingResource".

### App Uses:
* Kotlin
* MVVM
* RxJava
* Espresso w/Rx IdealingResource as testInstrumentationRunner
* Unit Tests with Mockito
* Sample of a Kotlin ViewExtension
