# API REST Mantenimiento de clientes Bancarios

API Rest, creada con SpringBoot 2.3.12.RELEASE con DB H2 embebida para para probar los datos. Libreria Swagger incorporada.

## Tecnologías Utilizadas

* SpringBoot 2.3.12.RELEASE
* Swagger 3
* H2 DB "Embedded"
* Docker

## Url de Presentación
```bash
http://localhost:9060/api/v1/
```
|
## Url Swagger
```bash
http://localhost:9060/swagger-ui.html
```

## Url H2 Database
```bash
http://localhost:9060/h2-console/
user: "testApi"
pass: "rootApi"
```
## Docker file

```python
FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
EXPOSE 9060
ARG JAR_FILE=target/customer-api-1.0.0.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
```
## Contacto
* Mauricio Águila
* mauricio.aguila.g@outlook.com

## License
[MIT](https://choosealicense.com/licenses/mit/)
# customer-api
