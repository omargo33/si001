# Arquitectura de la Aplicación

La arquitectura del aplicativo esta diseñada para ser escalable y modular, de tal forma que se puedan agregar nuevos módulos sin afectar el funcionamiento de los ya existentes. 

![Diagrama de arquitectura](documentacion/diagrama.png)

## Componentes
**Firewall:** Es el encargado de proteger la aplicación de accesos no autorizados, bloqueando los puertos que no se utilizan y permitiendo el acceso solo a los puertos que se utilizan.

**Keycloak:** Un servidor de seguridades que permite la autenticación y autorización de los usuarios, a la vez que permite la administración de los usuarios y roles. Y que se integra con el aplicativo mediante el protocolo OpenID Connect.
- *Tema:* Se utiliza un tema personalizado para la interfaz de usuario de Keycloak, de tal forma que se integre con la interfaz de usuario del aplicativo.
- *Base de datos:* Se utiliza una base de datos externa para almacenar los usuarios y roles, de tal forma que se pueda escalar el aplicativo sin afectar el funcionamiento de Keycloak.

**API Gateway:** Es el punto de entrada de la aplicación, es el encargado de recibir las peticiones de los clientes y redirigirlas a los microservicios correspondientes. También es el encargado de la seguridad de la aplicación, ya que valida los tokens de acceso de los usuarios y los roles que tienen asignados.

**Discovery Service:** Es el encargado de registrar los microservicios y de mantener actualizada la lista de microservicios disponibles. El API Gateway consulta al Discovery Service para saber a que microservicio debe redirigir la petición.

**Microservicios:** Son los encargados de realizar las operaciones de negocio de la aplicación, y se comunican con la base de datos para obtener y almacenar la información. Estos microservicios se comunican entre si para realizar operaciones complejas.
- *Base de datos:* Se utiliza una base de datos externa para almacenar la información de la aplicación, de tal forma que se pueda escalar el aplicativo sin afectar el funcionamiento de los microservicios.
- *Cache/Redis:* Se utiliza una cache para almacenar la información que se utiliza con frecuencia, de tal forma que se reduzca el tiempo de respuesta de los microservicios.

# Puesta en marcha
Para poner en marcha es necesario tener instalado docker y docker-compose, y ejecutar el siguiente comando:

```bash
docker-compose up -d
```
> De no tener instalado docker y docker-compose, puede seguir los siguientes tutoriales:
- [Instalar Docker](https://docs.docker.com/engine/install/)
- [Instalar Docker Compose](https://docs.docker.com/compose/install/)
- [Instalar Docker en Windows](https://docs.docker.com/docker-for-windows/install/)
- [Instalar Docker Compose en Windows](https://docs.docker.com/compose/install/)

## Path de los servicios en la arquitectura
En el archivo /etc/host se debe agregar las siguientes linea para que para colaborar con la ubicacion de los servicios en la arquitectura

> No olvide usar el usuario root

```bash
## Servidores de arquitectura
127.0.0.1   keycloak
127.0.0.1   api-gateway
127.0.0.1   discovery-service
127.0.0.1   db-GA00100
127.0.0.1   db-GS00101
127.0.0.1   db-SI00100
```

## Endpoints

### Keycloak

| Método | Endpoint | Descripción |
| ------ | ------ | ------ |
| GET | /auth/realms/{realm}/protocol/openid-connect/auth | Inicia el proceso de autenticación |
| POST | /auth/realms/{realm}/protocol/openid-connect/token | Obtiene el token de acceso |
| GET | /auth/realms/{realm}/protocol/openid-connect/userinfo | Obtiene la información del usuario |


### API Gateway

| Método | Endpoint | Descripción |
| ------ | ------ | ------ |
| GET | /api/usuarios | Obtiene la lista de usuarios |
| GET | /api/usuarios/{id} | Obtiene un usuario por su id |
| POST | /api/usuarios | Crea un nuevo usuario |
| PUT | /api/usuarios/{id} | Actualiza un usuario por su id |
| DELETE | /api/usuarios/{id} | Elimina un usuario por su id |
| GET | /api/roles | Obtiene la lista de roles |
| GET | /api/roles/{id} | Obtiene un rol por su id |

### Discovery Service

| Método | Endpoint | Descripción |
| ------ | ------ | ------ |
| GET | /usuarios | Obtiene la lista de usuarios |
| GET | /usuarios/{id} | Obtiene un usuario por su id |
| POST | /usuarios | Crea un nuevo usuario |
| PUT | /usuarios/{id} | Actualiza un usuario por su id |


### Microservicio de roles

| Método | Endpoint | Descripción |
| ------ | ------ | ------ |
| GET | /roles | Obtiene la lista de roles |
| GET | /roles/{id} | Obtiene un rol por su id |

---
![logo](documentacion/logo.png)

<o.velez@qapaq.com>

**Create on 2023-06-17**
