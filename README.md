# pricer-alert project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## tests
run kafka/infinispan 
```shell script
docker-compose up
```

Open 2 terminals and connect to the kafka instances

retrieve alert
```shell script 
./bin/kafka-console-consumer.sh --bootstrap-server kafka:29092 --topic alert
```
send messages
```shell script
./bin/kafka-console-producer.sh --broker-list kafka:29092 --topic price 
```
Send a message with a euro-dollar as a paire and a value more than 10
```json
{"specversion": "0.3", "id": "21627e26-31eb-43e7-8343-92a696fd96b1","source": "","type": "price","time": "2019-10-01T12:02:23.812262+02:00[Europe/Warsaw]","data": {"paire" : "euro-dollar","value" : "11"}}
```
on the terminal 1 you can see that an alert is rised "Sell the euro-dollar"
```json
{"id":"277bf30b-33eb-4e9a-ab33-3f805036ed45","source":"/process/pricer","type":"alert","time":"2021-03-04T09:52:14.358935+01:00","data":"Sell the euro-dollar","kogitoProcessinstanceId":"173c9715-a4a3-493f-ba92-d38569d459df","kogitoProcessId":"pricer","kogitoProcessinstanceState":"1","specversion":"1.0"}
```
Send a message with a euro-dinars as a paire and a value less than 10

```json
{"specversion": "0.3", "id": "21627e26-31eb-43e7-8343-92a696fd96b1","source": "","type": "price","time": "2019-10-01T12:02:23.812262+02:00[Europe/Warsaw]","data": {"paire" : "euro-dinars","value" : "9"}}
```
on the terminal 1 you can see that an alert is rised "Buy the euro-dinars"

```json
{"id":"504a795d-8483-4088-a3ca-32879dda3e80","source":"/process/pricer","type":"alert","time":"2021-03-04T09:55:44.814814+01:00","data":"Buy the euro-dinars","kogitoProcessinstanceId":"9ee9bae4-de9c-474d-83bf-951262b8bf44","kogitoProcessId":"pricer","kogitoProcessinstanceState":"1","specversion":"1.0"}
```


