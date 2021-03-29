# Events API
## Tech stack
* Java `openjdk version "15.0.2" 2021-01-19`
* Gradle wrapper `Gradle 6.8.3`

## Run instructions
* `git clone git@github.com:akaliacius/ticket-api.git`
* navigate to `PROJECT_ROOT`
* `./gradlew clean build`
* `./gradlew run`

> It sould start Micronout app on Netty port 8080


## API Endpoints
* `localhost:8080/api/venues` - list all venues
* `localhost:8080/api/venues/{id}` - gets venue by id
* `localhost:8080/api/artists` - list all artists
* `localhost:8080/api/artists/{id}` - get artist by id
* `localhost:8080/api/events` - list all events
* `localhost:8080/api/events/{id}` - get event by id

> All `get by id` endpoints are broken. Whenever I use `filter` on Flowable Micronaut gives me an error. 