# format the code
.PHONY: format
format:
	mvn spotless:apply spring-javaformat:apply

.PHONY: mvn-verify
mvn-verify: format
	mvn clean verify

.PHONY: mvn-install
mvn-install: format
	mvn clean install

.PHONY: mvn-deploy
mvn-deploy: format
	mvn clean deploy