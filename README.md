# Buggy Demo Service

A purposely broken Spring Boot project used for AI log analysis via AWS Bedrock.

## How to Run

1. Install Java 17
2. Install Maven
3. Run:

   mvn spring-boot:run

4. Call:

   http://localhost:8080/user/1

This will generate runtime errors like:
- NullPointerException
- ArithmeticException
- 500 Internal Server Error

Copy the logs into your Python project's `sample_logs.json` for Bedrock analysis.
