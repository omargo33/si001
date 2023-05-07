## Notas para creacion de un API GATEWA

**Autor:** *o.velez@qapaq.io*

El ejecucion de este documento se encuetra basado en lo ejemplos del la siguiente lista de reproduccion del [youtube][1] y se debe de tener las siguietnes dependencias al archivo pom.xml

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
    </dependency>
    ...
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        <version>3.0.4</version>
    </dependency>
</dependencies>
``` 
## Configuraciones en el archivo aplication.properties

[1]:https://www.youtube.com/playlist?list=PL145AyWAbMDhwUbBL74s1D2ZV9EqBaQ1t