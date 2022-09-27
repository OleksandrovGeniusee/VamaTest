# Vama-test-android

Design description
------
The app follows the rules of clean architecture, it is split on 3 modules: domain, data and app.
Data is stored in 2 tables [RMAlbum] and [RMGenre]. It is used repository pattern working with the
data. In repository there are 2 data sources [AlbumRemoteDataSource] to fetch the data from the
remote API and [AlbumLocalDataSource] for storing the data in local storage for offline usage or in
cases when there is an error happens. In app module there is MVVM pattern implemented for the
presentation layer

Features
------
List of top 100 albums with album name, artists and an album photo cover. After first fetching of
the remote data it is stored in local storage and after the next launches of the app it is shown
immediately and after successful response from the server the data is update in local storage and
it is updated on the main screen as well. Details screen also shows:
1. Photo cover
2. Album name
3. Artists
4. Genres names
5. Release date
6. Copyright info
7. Button with the ability to navigation to album on browser

[![Watch the video](https://img.youtube.com/vi/ZQRI68Gt2y0/hqdefault.jpg)](https://youtube.com/shorts/ZQRI68Gt2y0)