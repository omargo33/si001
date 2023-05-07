# Notas para creacion de un Servicio Discovery EUREKA

**Autor:** *o.velez@qapaq.io*

El ejecucion de este documento se encuetra basado en los ejemplos del la siguiente lista de reproduccion de [youtube][1], y se debe de tener las siguietnes dependencias al archivo pom.xml

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
``` 

## Configuraciones en el archivo aplication.properties

```properties
## Puerto para que el servidor sea expuesto.
server.port=8761

## Para que el servidor no se registre a si mismo se usan las siguientres entradas
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

## Clase principal
En nuestra clase principal que suele tener la anotacion @SpringBootApplication se debe de agregar la anotacion **@EnableEurekaServer**


```java
@SpringBootApplication
@EnableEurekaServer // Enable eureka server
public class DiscoveryServiceApplication
... 
```

# Microservio cliente
En los microservicios que se desean registrar en el servidor de descubrimiento se debe de agregar la siguiente dependencia en el archivo pom.xml

```xml
<!-- dependencia para manejo de netflix eureka -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    <version>3.0.4</version>
</dependency>
```
> **_NOTE:_**  Puede ser necesario cambiar la *parent version a:* 2.5.14

## Configuraciones en el archivo aplication.properties

```properties
##
## Eureka
##
server.port=0
eureka.instance.instance-id=${spring.application.name}:${random.uuid}
```

## Clase principal
En nuestra clase principal que suele tener la anotacion @SpringBootApplication se debe de agregar la anotacion **@EnableEurekaServer**


```java

@SpringBootApplication
@EnableEurekaClient
public class GS00100Application {
... 
```


[1]:https://www.youtube.com/playlist?list=PL145AyWAbMDhwUbBL74s1D2ZV9EqBaQ1t