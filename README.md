# spring-social-facebook-marketing
Project extends capabilities of spring-social-facebook project to support Facebook Marketing API.

Currently version 2.5 of Marketing API is supported.

## CHANGELOG

### 0.2.1:
- Improvements in Insights API: added cpc and clicks metric, changed spend from int to double.

### 0.2.0:
- Added method for creating and updating the AdSet targeting object through a String
- Fixed problem with deserialization of targeting object (map in connections, excluded_connections and friends_of_connections)
