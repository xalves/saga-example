# saga-example

Implementing an orchestration-based SAGA pattern involves 3 important parts:

1. Service - which  performs the local transaction
2. Event - which os equivalent to a local transaction
3. Orchestrator - which coordinates the service and event, it decides what local transaction to execute next

Let's consider we are building an online-order processing system. There will be three main microservices:

1. Order Service
2. Payment Service
3. Inventory Service

# TODO

* Managing distributed transactions
* Eventual Consistency
* Compensating transaction sequencing
* Add RabbitMQ or Kakfa
* idempotency of operations
* network related exception handling
* ensure event order
* Add check style
* Move configs to file or config server
* Jenkinsfile
* optionally add simple db




