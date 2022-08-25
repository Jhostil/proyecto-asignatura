web: java -Dspring.profiles.active=default -Dserver.port=$PORT -jar target/*.war
heroku ps:scale worker=1 urgentworker=3