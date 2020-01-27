# mdccourse
Oulu UAS course: Mobile Data Communications

Exercise 1:

A simple exercise about basics of android programming.

Exercise 2:

Exercise about sending SMS and e-mail in an android program.

Exercise 3:

Exercise in XML parsing. The XML file is acquired using HttpURLConnection in an AsyncTask and parsed using XmlPullParser.
Note: As I could not reach the teacher's HTTP server even with GlobalProtect VPN, I used my own PC as HTTP server with Apache2.

Exercise 4:

Exercise in JSON parsing. The JSON file is acquired using Volley's JsonArrayRequest, which automatically uses asynchronous task to fetch the requested data. Parsing of the acquired data is done in initialization of a RecyclerView, which is used to display the parsed data.
Note: As I could not reach the teacher's HTTP server even with GlobalProtect VPN, I used my own PC as HTTP server with Apache2.

Project Work: Yle RSS Reader

For project work I chose to implement a simple RSS reader for YLE's RSS feed available at https://feeds.yle.fi/uutiset/v1/majorHeadlines/YLE_UUTISET.rss.
Volley's StringRequest was used to fetch the data. The acquired string is then used as an input stream for a XmlPullParser, which searches the contents of specified tags and populates corresponding string variables in a data class used to hold a single news item. An ArrayList consisting of these data classes is then used as dataset for RecyclerView, which is used to display the news items on screen.

Video: https://youtu.be/jAaAHWrUZTA
